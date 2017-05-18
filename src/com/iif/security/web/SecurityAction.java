package com.iif.security.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.StrUtil;
import com.iif.common.util.SysConstant;
import com.iif.orgMgt.entity.RoleResource;
import com.iif.orgMgt.entity.UserAccount;
import com.iif.orgMgt.entity.IffUserRole;
import com.iif.orgMgt.service.IRoleResourceService;
import com.iif.orgMgt.service.IUserAccountService;
import com.iif.system.resource.entity.Resource;
import com.iif.system.resource.service.IResourceService;
import com.iif.system.userManager.service.IUserService;

/**
 * 安全Action
 * 
 * @author LiuM
 * @data 2017
 */
@Controller
@RequestMapping("/security/*") 
public class SecurityAction{

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IRoleResourceService roleResourceService;
	
	/**
	 * 登录
	 */
	@RequestMapping("login.action") 
	public String login(HttpServletRequest request, HttpServletResponse reposnse) throws Exception {
		
		String username = HttpTool.getParameter("j_username", "").trim();
		String password = HttpTool.getParameter("j_password", "").trim();
		// 检验用户名和密码是否为空
		if (StrUtil.isNullOrBlank(username) || StrUtil.isNullOrBlank(password)) {
			return "redirect:/login.jsp";
		}
		//////////////////////////////////////////
		/*// 根据用户加载用户
		UserAccount userAccount = userAccountService.loadUserByUsername(username);
		// 登录失败
		if (null == userAccount) {
			HttpTool.setAttribute("error", "不存在此用户!");
			return "redirect:/login.jsp?error=no user";
		}
		if (userAccount.getPassword()==null || !userAccount.getPassword().equals(password)) {
			HttpTool.setAttribute("error", "密码错误!");
			request.getSession().removeAttribute("currentUser");
			return "redirect:/login.jsp?error=password error";
		}
		
		request.getSession().setAttribute("currentUser", userAccount);
		///////////////////////////////////////////

		return "redirect:index.action?userId="+userAccount.getId(); */
		
		return "redirect:index.action";
	}

	/**
	 * 显示首页页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("index.action") 
	public String index(HttpServletRequest request, HttpServletResponse reposnse) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("style","default");
		//获取权限配置
		//////////////////////////////////////////
		UserAccount tempUser = (UserAccount)request.getSession().getAttribute("currentUser");
		String strUid = HttpTool.getParameter("userId");
		if(StringUtils.isNotEmpty(strUid)) {
			tempUser.setId(strUid);
		}
		
		/*********************************获取已有菜单资源BEGIN**************list*******************/
		if(tempUser != null && StringUtils.isNotEmpty(tempUser.getId())) {
			UserAccount user = (UserAccount)userAccountService.findById(tempUser.getId());
			List<IffUserRole> userRoleList = user.getUserRoles();
			//所有有权限的按钮资源
			List<Resource> hasAuthButtonResource=new ArrayList<Resource>();
			
			if(userRoleList != null && !userRoleList.isEmpty()) {
				Map filterMap = new HashMap();
				filterMap.put("order_resource__orderCode", "asc");
				List<RoleResource> temp;
				List<RoleResource> list = new ArrayList<RoleResource>();
	
				HttpTool.getSession().setAttribute(SysConstant.CURRENT_ROLE, userRoleList.get(0).getRole());//设置当前角色
				for (IffUserRole userRole : userRoleList) {
					if(userRole.getRole() != null 
							&& userRole.getRole().getRoleResourceses() != null
							&& !userRole.getRole().getRoleResourceses().isEmpty()
							) {
						
						filterMap.put("filter_and_role__id_EQ_S",userRole.getRole().getId());
						filterMap.put("filter_and_resource__id_EQ_L", 1);
						
						List<RoleResource> roleResourceses = roleResourceService.findRoleResourceByFilterMap(filterMap);
						
						if (roleResourceses!=null&&roleResourceses.size()>0) {
							filterMap.clear();
							filterMap.put("filter_and_parent__id_EQ_S",roleResourceses.get(0).getId());
							filterMap.put("filter_and_resource__isEnable_EQ_I",1);
							filterMap.put("order_resource__orderCode", "asc");
							temp = roleResourceService.findRoleResourceByFilterMap(filterMap);
							if(temp != null) {
								list.addAll(temp);
							}
						}
						
					}
					HttpTool.setAttribute("userRoleList", list);
				}
				/*********************************获取已有菜单资源END*********************************/
				//无权限的资源
				List<Resource> noAuthResource=null;
				
				//所有需要校验的按钮资源
				List<Resource> allButtonResource=resourceService.findAllButtonResource();
	
				for (int i = 0; i < list.size(); i++) {
					RoleResource srr=list.get(i);
					Resource resource=srr.getResource();
					if (resource!=null&&resource.getIsButton()!=null&&resource.getIsButton()==1) {
						hasAuthButtonResource.add(resource);
					}
				}
	
				//所有需要验证的-有权限的=没有权限的
				allButtonResource.removeAll(hasAuthButtonResource);
				noAuthResource=allButtonResource;
				
				user.setNoAuthResource(noAuthResource);
				session.setAttribute("currentUser", user);
			}
			//////////////////////////////////////////
		}
		
		return "index";
	}

}
