package com.iif.orgMgt.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.StaticMethod;
import com.iif.common.enums.TrueOrFalseEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.JsonUtil;
import com.iif.common.util.TemplateUtil;
import com.iif.common.util.UserUtil;
import com.iif.orgMgt.entity.IffRole;
import com.iif.orgMgt.entity.RoleResource;
import com.iif.orgMgt.service.IRoleResourceService;
import com.iif.orgMgt.service.IRoleService;
import com.iif.system.resource.entity.Resource;
import com.iif.system.resource.service.IResourceService;

/**
 * 用户角色Action
 * @Author LiuM
 * @Date   2014-8-28
 * @Since  1.0
 * @Desc   
 */
@Controller
@RequestMapping("/orgMgt/role/*")
public class RoleAction extends BaseAction {
	@Autowired
	private IRoleService roleService = null;
	
	@Autowired
	private IRoleResourceService roleResourceService = null;
	
	@Autowired
	private IResourceService resourceService = null;
	
	/**
	 * 初始化角色列表
	 * @return
	 */
	@RequestMapping("listRole.action")
	public String listRole(){
		return "jsp/system/user/listRole";
	}
	
	/**
	 * 初始化角色列表
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("showAll.action")
	@ResponseBody
	public Map showAll() throws Exception{
		int pageNum = HttpTool.getIntegerParameter("page");
		int pageSize = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, pageSize);
		page.setPageSize(50);
		Map searchMap = super.buildSearch();
		//列表信息
		List<IffRole> roles= roleService.findByPage(page, searchMap);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (roles != null && !roles.isEmpty()) {
			for (IffRole role : roles) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", role.getId());
				map.put("name", role.getName());
				map.put("desc", role.getDescription());
				map.put("valid", role.getIsDel());
				
				map.put("modifyDate", DateUtil.DateToStr(role.getModifyDate(), DateUtil.getEomsSheetDatepattern()));
				list.add(map);
			}
		}
		return TemplateUtil.toDatagridMap(page, list);
	}

	/**
	 * 更改是否有效状态
	 * @throws Exception 
	 */
	@RequestMapping("changeState.action")
	@ResponseBody
	public String changeState() throws Exception {
		String roleId = HttpTool.getParameter("roleId");

		if(StringUtils.isBlank(roleId)) {
			return JsonUtil.jsonFailure("请选择一条记录进行操作.");
		}

		IffRole role = (IffRole)roleService.findById(roleId);

		if(role == null) {
			return JsonUtil.jsonFailure("记录不存在或已被删除,请刷新页面重试.");
		}

		if(role.getIsDel() == null) {
			role.setIsDel(0);
		}
		role.setIsDel(1^role.getIsDel());

		role.setModifyDate(new Date());
		if(UserUtil.getCurrentUser() != null && UserUtil.getCurrentUser().getLinkEmployeeId() !=null){
			role.setModifyUser(UserUtil.getCurrentUser().getLinkEmployeeId());
		}

		roleService.save(role);

		return JsonUtil.jsonSuccess(null);
	}
	
	/**
	 * 初始化新增界面
	 * @param 
	 */
	@RequestMapping("toAddRole.action")
	public String toAddRole() throws Exception {
		List<?> isEnableList = InitSelect.getSelectList(TrueOrFalseEnum.class);
		HttpTool.setAttribute("isEnableList", isEnableList);
		
		return "jsp/system/user/addRole";
	}
	
	/**
	 * 保存新增角色数据
	 * @param 
	 */
	@RequestMapping("saveAddedRole.action")
	@ResponseBody
	public String saveAddedRole(@ModelAttribute IffRole role) {
		if(null != role) {
			role.setCreateDate(new Date());//创建时间
			if(UserUtil.getCurrentUser() != null&& UserUtil.getCurrentUser().getLinkEmployeeId() !=null){
				role.setCreateUser(UserUtil.getCurrentUser().getLinkEmployeeId());//创建人
				role.setModifyUser(UserUtil.getCurrentUser().getLinkEmployeeId());//修改人
			}
			role.setModifyDate(new Date());//修改时间
			roleService.save(role);
			return JsonUtil.jsonSuccess("保存成功.");
		}
		return JsonUtil.jsonFailure("保存失败.");
	}
	
	/**
	 * 初始化授权页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("toEditRole.action")
	public String toEditRole() throws Exception{

		//获取角色ID
		String roleId = HttpTool.getParameter("roleId");
		if(StringUtils.isEmpty(roleId)) {
			return JsonUtil.jsonFailure("未传入角色标识.");
		}
		
		HttpTool.setAttribute("roleId", roleId);

		return "jsp/system/user/editRole";
	}
	
	/**
	 * 加载子级菜单
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("loadResource.action")
	@ResponseBody
	public List loadResource() throws Exception {
		String roleId = HttpTool.getParameter("roleId");
		String parentId = HttpTool.getParameter("parentId");
		
		//定义menu列表
		List<Resource> subResources = null;

		//判断是否是空值
		if(StringUtils.isEmpty(parentId)) {
			//获取顶级菜单
			subResources = resourceService.findMenuByType();
		} else {
			//获取子菜单
			subResources = resourceService.findEnableMenuByParentId(StaticMethod.nullObject2Long(parentId));
		}

		//定义反馈JSON
		List jArray = new ArrayList();
		//循环变量
		Map tempObj;
		Map attrObj;

		//判空
		if(subResources != null && !subResources.isEmpty()) {
			for (Resource resource : subResources) {
				Map searchMap = new HashMap();
				searchMap.put("filter_and_role__id_EQ_S", roleId);
				searchMap.put("filter_and_resource__id_EQ_L", resource.getId());
				List<RoleResource> lstRoleResource = roleResourceService.findRoleResourceByFilterMap(searchMap);

				attrObj = new JSONObject();
				attrObj.put("id", resource.getId());
				if(lstRoleResource != null && lstRoleResource.size() > 0) {
					attrObj.put("CLASS", "jstree-checked");
				}
				attrObj.put("AUTHOR_TYPE", "_MENU");

				tempObj = new JSONObject();
				
				tempObj.put("attr", attrObj);

				tempObj.put("state", "closed");

				tempObj.put("data", resource.getName());

				jArray.add(tempObj);
			}
		}
		
		return jArray;
	}
	
	/**
	 * 添加资源
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("addResource.action")
	@ResponseBody
	public String addResource() throws Exception{

		//获取角色ID
		String roleId = HttpTool.getParameter("roleId");
		if(StringUtils.isEmpty(roleId)) {
			return JsonUtil.jsonFailure("未传入角色标识");
		}

		//获取menuId
		String menuId = HttpTool.getParameter("menuId");
		if(StringUtils.isEmpty(menuId)) {
			return JsonUtil.jsonFailure("未传入菜单标识");
		}

		//获取权限类型
		String nodeType = HttpTool.getParameter("nodeType");
		if(StringUtils.isEmpty(nodeType)) {
			return JsonUtil.jsonFailure("资源类型不明确");
		}

		//获取上级menuId
		String parId = HttpTool.getParameter("parId");

		//创建角色
		IffRole role = new IffRole();
		role.setId(roleId);

		//创建角色资源关系
		RoleResource roleResource = new RoleResource();
	
		//添加上级菜单
		if(!StringUtils.isEmpty(parId)) {
			Map searchMap = new HashMap();
			searchMap.put("filter_and_role__id_EQ_S", roleId);
			searchMap.put("filter_and_resource__id_EQ_L", parId);
			List<RoleResource> lstRoleResource = roleResourceService.findRoleResourceByFilterMap(searchMap);
			if(lstRoleResource!=null && lstRoleResource.size() > 0) {
				roleResource.setParent(lstRoleResource.get(0));
			}
		}

		//创建菜单
		Resource resource = new Resource();
		resource.setId(StaticMethod.nullObject2Long(menuId));

		//设置角色、菜单
		roleResource.setRole(role);
		roleResource.setResource(resource);

		//保存
		roleResourceService.save(roleResource);

		return JsonUtil.jsonSuccess("");
	}

	/**
	 * 删除资源
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("delResource.action")
	@ResponseBody
	public String delResource() throws Exception{

		//获取角色ID
		String roleId = HttpTool.getParameter("roleId");
		if(StringUtils.isEmpty(roleId)) {
			return JsonUtil.jsonFailure("未传入角色标识");
		}

		//获取resourceId
		String resourceId = HttpTool.getParameter("menuId");
		if(StringUtils.isEmpty(resourceId)) {
			return JsonUtil.jsonFailure("未传入菜单标识");
		}

		//获取权限类型
		String nodeType = HttpTool.getParameter("nodeType");
		if(StringUtils.isEmpty(nodeType)) {
			return JsonUtil.jsonFailure("资源类型不明确");
		}
		
		//获取角色资源关系
		Map searchMap = new HashMap();
		searchMap.put("filter_and_role__id_EQ_S", roleId);
		searchMap.put("filter_and_resource__id_EQ_L", resourceId);
		List<RoleResource> lstRoleResource = roleResourceService.findRoleResourceByFilterMap(searchMap);
		if(lstRoleResource == null || lstRoleResource.size() < 1) {
			return JsonUtil.jsonFailure("角色菜单关系不存在");
		}
		//物理删除
		roleResourceService.deleteIds(lstRoleResource.get(0).getId(), "String");

		return JsonUtil.jsonSuccess("");
	}
	
}