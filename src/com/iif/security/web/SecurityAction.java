package com.iif.security.web;

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
import com.iif.common.util.SysPropUtil;
import com.iif.common.util.UserUtil;
import com.iif.orgMgt.entity.UserAccount;
import com.iif.orgMgt.service.IRoleResourceService;
import com.iif.orgMgt.service.IUserAccountService;
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
		String strUserName = SysPropUtil.getSystemConstant(SysConstant.INIT_USER);
		String strPassowrd = SysPropUtil.getSystemConstant(SysConstant.INIT_PASSWORD);
		String[] arrUn = strUserName.split(",");
		String[] arrPs = strPassowrd.split(",");
		
		// 检验用户名和密码是否为空
		if (!(username.equals(arrUn[0]) || username.equals(arrUn[1]))) {
			HttpTool.setAttribute("error", "不存在此用户!");
			return "redirect:/login.jsp?error="+"No User!";
		}
		
		if (!((username.equals(arrUn[0])&&password.equals(arrPs[0])) || (username.equals(arrUn[1])&&password.equals(arrPs[1])))) {
			HttpTool.setAttribute("error", "密码错误!");
			return "redirect:/login.jsp?error="+"Password Error!";
		}
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserAccount(username);
		request.getSession().setAttribute("currentUser", userAccount);

		return "redirect:index.action";
		
	}

	/**
	 * 显示首页页面
	 */
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
		
		HttpTool.setAttribute("userName", tempUser.getUserAccount()); //页面显示登录者名称
		HttpTool.setAttribute("sectionName", SysPropUtil.getSystemConstant(SysConstant.INIT_ROOT_ORG_NAME));//页面显示使用单位名称
		
		return "index";
	}

}
