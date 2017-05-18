package com.iif.common.util ;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.hxjz.common.utils.DateUtil;

/**
 * 安全验证工具类
 * 
 * @author wgh 2013-12-10
 */
public class SecurityUtil {

	/**
	 * 添加cookie
	 * 
	 * @param cookieName cookie名
	 * @param value 值
	 * 
	 */
	public static void addCookie(String cookieName , String value) {

		HttpServletResponse response = null;//ServletActionContext.getResponse() ;
		addCookie(cookieName , value , response) ;

	}

	/**
	 * 添加cookie
	 * 
	 * @param cookieName
	 *        cookie名
	 * @param value
	 *        值
	 */
	public static void addCookie(String cookieName , String value , HttpServletResponse response) {

		Cookie cookie = new Cookie(cookieName , value) ;
		cookie.setDomain(SysConstant.DOMAIN) ;
		cookie.setPath("/") ;
		response.addCookie(cookie) ;
	}

	/**
	 * 用户名+秘钥 通过MD5加密生成令牌
	 * 
	 * @param userName
	 *        用户名
	 * @return 令牌
	 */
	public static String getTokenByUserName(String userName) {
		
		Date timeStamp = new Date() ;
		String tS = DateUtil.DateToStr(timeStamp , 
											SysConstant.DATE_FORMAT_DAY) ;
		String token = Md5.md5s(userName + SysConstant.SECRET_KEY + tS) ;
		return token ;
	}
	
	/**
	 * 根据键获取cookie
	 * @param key
	 * @param cookies
	 * @return
	 */
	public static Cookie getCookie(String key,Cookie[] cookies){
		if (cookies==null) {
			return null;
		}
		for( Cookie cookie : cookies) {
			if (cookie!=null&&cookie.getName().equals(key)) {
				return cookie;
			}
		}
		return null;
	}

}
