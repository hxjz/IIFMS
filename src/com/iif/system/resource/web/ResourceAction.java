package com.iif.system.resource.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.StaticMethod;
import com.iif.common.enums.TrueOrFalseEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.JsonUtil;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.system.resource.entity.Resource;
import com.iif.system.resource.service.IResourceService;

/**
 * @author LiuM
 * @date 2014-08-28
 * @since 1.0
 * @Desc
 */
@Controller
@RequestMapping("/system/resource/*")
public class ResourceAction extends BaseAction {
	@Autowired
	private IResourceService resourceService = null;

	@RequestMapping("indexResource.action")
	public String indexResource() throws Exception {
		// 是否启用下拉
		List<?> isEnableList = InitSelect.getSelectList(TrueOrFalseEnum.class);
		HttpTool.setAttribute("isEnableList", isEnableList);
		// 调业务方法
		HttpTool.setAttribute("elementListForTree", getElementForMenuTreeMgt());
		return "jsp/system/resource/indexResource";
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getElementForMenuTreeMgt() {
		List<Resource> resourceList = resourceService.findAll("orderCode",true);
		List<Resource> enabledResourceItemList = new ArrayList<Resource>();
		Integer isDel = 999;//删除标志
		long isParent = 999;
		for (int i = 0; i < resourceList.size(); i++) {
			if (StringUtils.isNotEmpty((resourceList.get(i).getIsEnable() + ""))) {
				isDel = resourceList.get(i).getIsDel();
				isParent = resourceList.get(i).getId();
			}
			
			//已删除菜单不显示、顶级菜单项不显示
			if(isDel==0 && (isParent!=1)) {
				enabledResourceItemList.add(resourceList.get(i));
			}

		}
		return enabledResourceItemList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("showAll.action")
	@ResponseBody
	public Map showAll() throws Exception {
		int pageNum = HttpTool.getIntegerParameter("page");
		int pageSize = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, pageSize);
		page.setPageSize(50);
		Map searchMap = super.buildSearch();
		searchMap.put("filter_and_isDel_EQ_I", SysConstant.IS_NOT_DEL);
		List<Resource> resourceList = resourceService.findByPage(page, searchMap);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		searchMap.put("order_orderCode", "asc");

		if (null != resourceList) {
			for (Resource resource : resourceList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", resource.getId() == null ? "" : resource.getId());
				map.put("name", resource.getName() == null ? "" : resource.getName());
				map.put("url", resource.getUrl() == null ? "" : resource.getUrl());
				map.put("parentId", resource.getParentId() == null ? "" : resource.getParentId());
				map.put("iconCls", resource.getIconCls() == null ? "" : resource.getIconCls());
				map.put("order", resource.getOrderCode() == null ? "" : resource.getOrderCode());
				if (StringUtils.isNotBlank(resource.getIsEnable() + "")) {
					int isEnable = resource.getIsEnable();
					if (1 == isEnable) {
						map.put("isEnable", "启用");
					} else {
						map.put("isEnable", "禁用");
					}
				}
				map.put("orderCode", resource.getOrderCode() == null ? "" : resource.getOrderCode());
				list.add(map);
			}
		}
		return TemplateUtil.toDatagridMap(page, list);
	}

	@RequestMapping("addNewResourceModule.action")
	public ModelAndView addNewResourceModule() {
		// 是否启用下拉
		List<?> isEnableList = InitSelect.getSelectList(TrueOrFalseEnum.class);
		HttpTool.setAttribute("isEnableList", isEnableList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("isEnableList", isEnableList);
		mv.addObject("parentId", SysConstant.RESOURCE_ROOT_ID);
		mv.setViewName("jsp/system/resource/addNewResourceModule");
		return mv;
	}

	/**
	 * 保存新增模块
	 */
	@RequestMapping("saveAddedResourceModule.action")
	@ResponseBody
	public String saveAddedResourceModule(@ModelAttribute Resource resource) throws Exception {
		if (null != resource) {
			resource.setIsLeaf(SysConstant.RESOURCE_IS_LEAF_N);
			resource.setType(SysConstant.RESOURCE_TYPE_1);
			resource.setIsLine(SysConstant.RESOURCE_IS_LINE_Y);
			resource.setLevelNum(1);
			resourceService.save(resource);
			return JsonUtil.jsonSuccess(null);
		} else {
			return JsonUtil.jsonFailure(null);
		}
	}

	/**
	 * 添加菜单功能
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("addNewResource.action")
	public String addNewResource() {
		// 是否启用下拉
		List<?> isEnableList = InitSelect.getSelectList(TrueOrFalseEnum.class);
		HttpTool.setAttribute("isEnableList", isEnableList);
		String parentID = HttpTool.getParameter("parentId");
		Long resourceId = StaticMethod.nullObject2Long(parentID);
		if (StringUtils.isNotBlank(resourceId + "")) {
			Map filterMap = new HashMap();
			filterMap.put("filter_and_id_EQ_L", resourceId);
			Resource parentResource = (Resource) resourceService.findByFilterMap(filterMap).get(0);
			if (null != parentResource) {

			}
		}
		return "jsp/system/resource/addNewResource";
	}

	/**
	 * 获取上级名称
	 * 
	 * @author LiuM
	 * @date 2014年10月22日 上午10:10:09
	 * @since 1.0
	 * @desc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getParentResourceName.action")
	@ResponseBody
	public String getParentResourceName() throws Exception {
		String parentIdStr = HttpTool.getParameter("parentId");
		if (StringUtils.isNotBlank(parentIdStr)) {
			// 需要转为long型
			long parentIDlong = Long.parseLong(parentIdStr);
			// 需要用filter方法来查找
			Map filterMap = new HashMap();
			filterMap.put("filter_and_id_EQ_L", parentIDlong);
			Resource parentResource = (Resource) resourceService.findByFilterMap(filterMap).get(0);

			if (null != parentResource) {
				String parentRescourceName = parentResource.getName();
				if (StringUtils.isNotBlank(parentRescourceName)) {
					return JsonUtil.strToJson(URLEncoder.encode(parentRescourceName, SysConstant.ENCODING_UTF));
				} else {
					return JsonUtil.strToJson("");
				}
			} else {
				return JsonUtil.strToJson("");
			}
		} else {
			return JsonUtil.strToJson("新添加一级目录");
		}
	}

	@RequestMapping("saveAddedResource.action")
	@ResponseBody
	public String saveAddedResource(@ModelAttribute Resource resource) throws Exception {
		if (null != resource) {
			long parentId = 0;
			Resource newAddedResource = new Resource();
			// 设置parentId
			if (StringUtils.isNotBlank(resource.getParentId() + "")) {
				parentId = resource.getParentId();
				if (StringUtils.isNotBlank(parentId + "")) {
					newAddedResource.setParentId(parentId);
				} else {
					newAddedResource.setParentId(new Long(1));
				}
			}
			
			// 设置是否是按钮
			Integer isButton = 0;
			if (StringUtils.isNotBlank(resource.getIsButton() + "")) {
				int isButtonNum = resource.getIsButton();
				if (StringUtils.isNotBlank(isButtonNum + "")) {
					isButton = isButtonNum;
					if (!isButton.equals(1)) {//不是按钮
						// 设置父菜单为非叶子节点
						Resource parent = (Resource) resourceService.findById(resource.getParentId());
						parent.setIsLeaf(0);
						resourceService.save(parent);
						// 设置设置父菜单为非叶子节点结束
						newAddedResource.setIsLeaf(1);
						newAddedResource.setType(3);
					} else {
						// 设置父菜单为叶子节点
						Resource parent = (Resource) resourceService.findById(resource.getParentId());
						parent.setIsLeaf(1);
						resourceService.save(parent);
						newAddedResource.setIsLeaf(1);
						newAddedResource.setType(4);
					}
				}
			}
			
			// 设置名称
			if (StringUtils.isNotBlank(resource.getName())) {
				String resourceName = resource.getName();
				if (StringUtils.isNotBlank(resourceName)) {
					newAddedResource.setName(resourceName);
				} else {
					newAddedResource.setName("");
				}
			}
			// 设置是否启用
			if (StringUtils.isNotBlank(resource.getIsEnable() + "")) {
				newAddedResource.setIsEnable(resource.getIsEnable());
			}
			
			// 设置序号
			if (StringUtils.isNotBlank(resource.getOrderCode() + "")) {
				newAddedResource.setOrderCode(resource.getOrderCode());
			}
			
			// 设置图标
			if (StringUtils.isNotBlank(resource.getIconCls() + "")) {
				String icon = resource.getIconCls();
				if (StringUtils.isNotBlank(icon) && "1".equals(icon)) {
					newAddedResource.setIconCls("leafMenuIcon");
				} else {
					newAddedResource.setIconCls("leafIcon");
				}
			}
			
			// 设置是否换行
			if (StringUtils.isNotEmpty(resource.getIsLine() + "")) {
				int isLine = resource.getIsLine();
				if (StringUtils.isNotBlank(isLine + "") && isLine == 1) {
					newAddedResource.setIsLine(isLine);
				}
				if (StringUtils.isNotBlank(isLine + "") && isLine == 0) {
					newAddedResource.setIsLine(isLine);
				}
			}

			// 设置url
			if (StringUtils.isNotBlank(resource.getUrl())) {
				String url = resource.getUrl();
				if (StringUtils.isNotBlank(url)) {
					newAddedResource.setUrl(url);
				} else {
					newAddedResource.setUrl(null);
				}
			}

			//设置levelNum
			newAddedResource.setLevelNum(SysConstant.RESOURCE_LEVEL_NUM_3);
			resourceService.save(newAddedResource);
			return JsonUtil.jsonSuccess(null);
		} else {
			return JsonUtil.jsonFailure("");
		}
	}

	@RequestMapping("saveUpdatedItems.action")
	@ResponseBody
	public String saveUpdatedItems() {
		// 下面把所有插入和修改数据行属性值（在前台已经封装成一个字符串）全部得到，进行解析
		String rtnMsg = null;
		try {
			String updateParam = HttpTool.getParameter("updatedRow");
			JSONArray updateItemArray = JSONArray.fromObject(updateParam);
			String data = saveItemToDB(updateItemArray);
			rtnMsg = JsonUtil.jsonSuccess(data);
		} catch (Exception e) {
			rtnMsg = JsonUtil.jsonFailure("");
		}
		return rtnMsg;
	}

	/**
	 * @Desc 保存到数据库里
	 * @param updateResourceArray
	 */
	public String saveItemToDB(JSONArray updateResourceArray) throws Exception {
		// 解析为JSONArray形式
		// 保存插入的dictItem数据
		Resource resource = null;
		if (updateResourceArray.size() != 0) {
			for (int j = 0; j < updateResourceArray.size(); j++) {
				JSONObject insertObj = updateResourceArray.getJSONObject(j);
				Long id = insertObj.getLong("id");
				String name = insertObj.getString("name");
				String url = insertObj.getString("url");
				String isEnableStr = insertObj.getString("isEnable");
				String icon = insertObj.getString("iconCls");
				Long parentId = insertObj.getLong("parentId");
				int orderCode = insertObj.getInt("order");
				if (StringUtils.isNotBlank(id + "")) {
					resource = (Resource) resourceService.findById(id);
				}
				if (null != resource) {
					// 取值更新
					resource.setName(name + "");
					resource.setUrl(StringUtils.isBlank(url)?null:url);
					if (StringUtils.isNotBlank(icon)) {
						resource.setIconCls(icon);
					}
					if (StringUtils.isNotBlank(parentId + "")) {
						resource.setParentId(parentId);
					}
					if (StringUtils.isNotBlank(isEnableStr)) {
						if("禁用".equals(isEnableStr)) {
							resource.setIsEnable(SysConstant.IS_UNENABLE);
						}else if("启用".equals(isEnableStr)){
							resource.setIsEnable(SysConstant.IS_ENABLE);
						}else {
							resource.setIsEnable(Integer.parseInt(isEnableStr));
						}
					}
					if (StringUtils.isNotBlank(orderCode + "")) {
						resource.setOrderCode(orderCode);
					}
					resourceService.save(resource);
				}
			}
			return null;
		} else {
			return "没有修改任何记录！";
		}
	}

	/**
	 * 删除选中条目 伪删除
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("deleteItemByIDs.action")
	@ResponseBody
	public String deleteItemByIDs() throws Exception {
		String itemIDs = HttpTool.getParameter("selectedIDs");
		if (StringUtils.isNotBlank(itemIDs)) {
			String[] strArray = null;
			strArray = itemIDs.split(",");
			for (int a = 0; a < strArray.length; a++) {
				Long resourceId = StaticMethod.nullObject2Long(strArray[a]);
				@SuppressWarnings("rawtypes")
				Map filterMap = new HashMap();
				filterMap.put("filter_and_id_EQ_L", resourceId);
				Resource resourceInDB = (Resource) resourceService.findByFilterMap(filterMap).get(0);
				if (null != resourceInDB && StringUtils .isNotEmpty((resourceInDB.getParentId() + ""))) {
					resourceInDB.setIsEnable(SysConstant.IS_UNENABLE);
					resourceInDB.setIsDel(SysConstant.IS_DEL);
					resourceService.save(resourceInDB);
				}
			}
			return JsonUtil.jsonSuccess(null);
		} else {
			return JsonUtil.jsonFailure(null);
		}
	}
}
