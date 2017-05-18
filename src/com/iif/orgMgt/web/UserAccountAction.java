package com.iif.orgMgt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.util.JsonUtil;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.orgMgt.entity.IffRole;
import com.iif.orgMgt.entity.UserAccount;
import com.iif.orgMgt.entity.IffUserRole;
import com.iif.orgMgt.service.IRoleService;
import com.iif.orgMgt.service.IUserAccountService;

/**
 * @author LiuM
 * @date 2014-08-22
 * @since 1.0
 * @Desc
 */
@Controller
@RequestMapping("/orgMgt/userAccount/*")
public class UserAccountAction extends BaseAction {
	private final static transient Log LOGGER = LogFactory.getLog(UserAccountAction.class);
	
	@Autowired
	private IUserAccountService userAccountService = null;

	@Autowired
	private IRoleService roleService = null;
	/**
	 * 跳转到用户列表
	 * 
	 * @return
	 */
	@RequestMapping("listUserAccount.action")
	public String listUserAccount() {
		return "jsp/system/user/listUserAccount";
	}

	@RequestMapping("toEditUserAccount.action")
	public String toEditUserAccount() throws Exception {
		String userId = HttpTool.getParameter("userId");
		HttpTool.setAttribute("userId", userId);
		
		return "jsp/system/user/editUserAccount";
	}

	/**
	 * 初始化用户列表
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("showAll.action")
	public Map showAll() throws Exception {
		int pageNum = HttpTool.getIntegerParameter("page");
		int pageSize = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, pageSize);
		
		Map searchMap = super.buildSearch();
		// 列表信息
		searchMap.put("filter_and_acctStatus_LIKE_I", SysConstant.IS_NOT_DEL);
		List<UserAccount> userList = userAccountService.findByPage(page, searchMap);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (userList != null && !userList.isEmpty()) {
			for (UserAccount user : userList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("account", user.getUserAccount());//用户count
				if(user.getLinkEmployeeId()!=null) {
					map.put("name", user.getLinkEmployeeId().getName());//用户名称
					if(user.getLinkEmployeeId().getDepartment() != null) {
						map.put("dept", user.getLinkEmployeeId().getDepartment().getName());//部门
					}
				}
				List roleLst = user.getUserRoles();
				if(roleLst != null && roleLst.size() > 0) {
					IffUserRole role = (IffUserRole)roleLst.get(0);
					if(role.getRole() != null) {
						map.put("roles", role.getRole().getName());//角色名称
					}
				}
				map.put("lockDate", DateUtil.DateToStr(user.getAcctLockTime(), DateUtil.getEomsSheetDatepattern()));
				list.add(map);
			}
		}
		return TemplateUtil.toDatagridMap(page, list);
	}

	/**
	 * 用户加载角色
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("loadRoles.action")
	public List loadRoles() throws Exception{
		
		String userId=HttpTool.getParameter("userId");
		UserAccount userAccount=(UserAccount)userAccountService.findById(userId);
		if (userAccount==null) {
			LOGGER.info("加载角色时用户为空！");
			return null;
		}
		
		List<IffUserRole> userRoles=userAccount.getUserRoles();
		
		//定义角色列表
		List<IffRole> roles = (List<IffRole>)roleService.findAll();
		
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		//循环变量
		Map tempObj = new HashMap();
		Map attrObj = new HashMap();
		//判空
		if(roles != null && !roles.isEmpty()) {
			for (IffRole role : roles) {
				
				if (role==null) {
					continue;
				}
				
				attrObj = new JSONObject();
				attrObj.put(SysConstant.MENU_ID, role.getId());
				
				if (userRoles!=null) {
					for( int i = 0 ; i < userRoles.size() ; i++) {
						IffUserRole userRole=userRoles.get(i);
						IffRole trole=userRole.getRole();
						if (trole!=null&&trole.getId().equals(role.getId())) {
							attrObj.put(SysConstant.MENU_CLASS,SysConstant.MENU_CHECKED);
							break;
						}
					}
				}

				tempObj = new JSONObject();
				tempObj.put(SysConstant.MENU_ATTR, attrObj);
				tempObj.put(SysConstant.MENU_TITLE, role.getName());
				rtnList.add(tempObj);
			}
		}
		
		//返回显示授权列表
		return rtnList;
	}
	
	/**
	 * 用户添加角色
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addRole.action")
	public String addRole() throws Exception{
		String userId=HttpTool.getParameter("userId");
		String roleId=HttpTool.getParameter("roleId");
		
		UserAccount userAccount=(UserAccount)userAccountService.findById(userId);
		List<IffUserRole> userRoles=userAccount.getUserRoles();
		if (userRoles!=null) {
			IffUserRole userRole=new IffUserRole();
			IffRole role=new IffRole();
			role.setId(roleId);
			userRole.setRole(role);
			userRole.setUser(userAccount);
			userRoles.add(userRole);
			userAccountService.save(userAccount);
		}
		
		return JsonUtil.jsonSuccess("");
	}
	
	/**
	 * 用户删除角色
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delRole.action")
	public String delRole() throws Exception{
		
		String userId=HttpTool.getParameter("userId");
		String roleId=HttpTool.getParameter("roleId");
		
		UserAccount user=(UserAccount)userAccountService.findById(userId);
		List<IffUserRole> userRoles=user.getUserRoles();
		
		if (userRoles!=null) {
			for( int i = 0 ; i < userRoles.size(); i++) {
				IffUserRole userRole=userRoles.get(i);
				if (roleId.equals(userRole.getRole().getId())) {
					userRoles.remove(userRole);
				}
			}
			userAccountService.save(user);
		}
		
		return JsonUtil.jsonSuccess("");
	}

}
