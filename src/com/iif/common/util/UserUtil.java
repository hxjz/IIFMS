package com.iif.common.util ;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hxjz.common.utils.HttpTool;
import com.iif.orgMgt.entity.UserAccount;
import com.iif.system.userManager.entity.User;

/**
 * 用户工具类，提供获取当前登陆用户、员工的方法
 * 
 * @author xlg
 * @since 2013-3-27
 */
public class UserUtil {

	// 登陆人信息session标记
	public static String LOGIN_USER_SESSION_ID = "currentUser";
	
	protected static final Logger logger = Logger.getLogger(UserUtil.class) ;

	/**
	 * 获取当前用户(服务系统）
	 * 
	 * @return
	 */
	public static UserAccount getCurrentUser() {
		UserAccount user = null ;
		try {
			user = (UserAccount) HttpUtil.getSession().getAttribute(LOGIN_USER_SESSION_ID) ;
		} catch (Exception e) {
			logger.error("Session中没有取出当前用户！" , e) ;
		}
		return user ;
	}

	/**
	 * 往sesson中注入user
	 * 
	 * @return
	 */
	public static User setUser2Session(User _user) {
		return setUser2Session(_user,HttpTool.getSession()) ;
	}

	/**
	 * 往sesson中注入user
	 * 
	 * @return
	 */
	public static User setUser2Session(User _user,HttpSession session) {
		User user = null ;
		try {
			session.setAttribute(LOGIN_USER_SESSION_ID , _user) ;
		} catch (Exception e) {
			logger.error("Session中注入当前用户失败！" , e) ;
		}
		return user ;
	}
	
	/**
	 * 将用户名与令牌写入cookie中
	 * 
	 * @param userName
	 */
	public static void setToken2Cookie(String userName) {
		SecurityUtil.addCookie(SysConstant.COOKIE_KEY_USER_NAME , 
				userName) ;
		SecurityUtil.addCookie(SysConstant.COOKIE_KEY_TOKEN , 
				SecurityUtil.getTokenByUserName(userName)) ;
	}
	
	/**
	 * 将用户名与令牌写入cookie中
	 * 
	 * @param userName
	 * @param response
	 */
	public static void setToken2Cookie(String userName,HttpServletResponse response) {
		SecurityUtil.addCookie(SysConstant.COOKIE_KEY_USER_NAME , 
				userName,response) ;
		SecurityUtil.addCookie(SysConstant.COOKIE_KEY_TOKEN , 
				SecurityUtil.getTokenByUserName(userName),response) ;
	}
}
