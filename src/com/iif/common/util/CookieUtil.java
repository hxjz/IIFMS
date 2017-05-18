package com.iif.common.util;
/**
 * (c) Copyright 2003~2008 by BYXT Technology Corperation, Ltd. All Rights
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * XzCookie类提供读取、写回、删除Http应用中的Cooki的静态方法.
 *  
 * <pre>
 * Cookie 用户访问Web服务器时，服务器在用户端硬盘存放的信息.
 * 
 * Cooki的属性包括Cooki名，maxAge（生命期长度）, domain(域), Path(路径). 
 * 
 * 出于安全性，Cooki的使用接受如下的限制： 
 * Cooki不能跨越域传递，只有创建它的域才能使用；
 * Cooki以秒为单位有效期,超过有效期即失效； 可通过设置cookie.setMaxAge(0)来立即删除Cookie;
 * 可通过设置cookie.setMaxAge(-1)表示当浏览器关闭的时候删除Cooki.
 * 
 * Cooki的有效路径是指Cookie在其路径及子路径下才有效，在缺省状态下，Cooki的有效路径是当前request的路径.
 * 注意用户浏览器可能关闭Cookie功能.
 * </pre>
 * 
 * @author ht
 */
public class CookieUtil
{
	private static Logger log=Logger.getLogger(CookieUtil.class);
	/**
	 * 读取客户端的Cooki的值.
	 * </p>
	 * 
	 * @param request
	 *            HttpServletRequest的请求句柄.
	 * @param cookieName
	 *            Cooki对象的名称.
	 * @return 返回对应于Cooki对象的名称的内容的.
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName)
	{
		Cookie cookie, aryCookie[];
		aryCookie = request.getCookies();
		if (aryCookie == null)
		{
			return null;
		}

		for (int i = 0; i < aryCookie.length; i++)
		{
			cookie = aryCookie[i];
			String s = cookie.getName();
			if (s.equals(cookieName))
			{
				return cookie.getValue();
			}
		}

		return null;
	}
	
	/**
	 * 读取客户端的Cooki的值.
	 * </p>
	 * 
	 * @param request
	 *            HttpServletRequest的请求句柄.
	 * @param cookieName
	 *            Cooki对象的名称.
	 * @return 返回对应于Cooki对象的名称的内容的.
	 */
	public static Cookie getCookieValue(String cookieName, Cookie[] cookies)
	{
	
		for (int i = 0;cookies!=null&& i < cookies.length; i++)
		{
		
			String s = cookies[i].getName();
			if (s.equals(cookieName))
			{
				return cookies[i];
			}
		}

		return null;
	}
	
	

	/**
	 * 读取客户端所有的Cooki的值.
	 * </p>
	 * 
	 * @param request  HttpServletRequest的请求句柄.
	 * @return 返回对应于所有Cooki对象的值的数组.
	 */
	public static String[] getCookieValue(HttpServletRequest request)
	{
		Cookie cookie, aryCookie[];
		aryCookie = request.getCookies();
		if (aryCookie == null)
		{
			return null;
		}
		String ss[] = new String[aryCookie.length];
		for (int i = 0; i < aryCookie.length; i++)
		{
			cookie = aryCookie[i];
			String s = cookie.getName();
			// //System.out.println(s);
			ss[i] = s;
		}

		return ss;
	}

	/**
	 * @param request
	 *            HttpServletRequest的请求句柄.
	 * @return 返回对应于客户端所有Cooki对象的内容的HashMap表.
	 */
	public static Map getCookieMap(HttpServletRequest request)
	{
		Cookie cookie, aryCookie[];
		HashMap h = new HashMap();

		aryCookie = request.getCookies();
		if (aryCookie == null)
		{
			return null;
		}
		for (int i = 0; i < aryCookie.length; i++)
		{
			cookie = aryCookie[i];

			String s = cookie.getName();
			String v = cookie.getValue();

			h.put(s, v);
		}

		return h;
	}

	/**
	 * 按名称删除COOKIE
	 * 
	 * @param request
	 *            HttpServletRequest的请求句柄.
	 * @param response
	 *            HttpServletResponse的请求句柄.
	 * @param name
	 *            Cooki对象的名称.
	 * @return 返回对应于Cooki对象的名称的内容..
	 * 
	 * new on 20070719
	 */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String name) 
	{
		Cookie cookie = new Cookie(name, null);
		//cookie.setDomain(domain);
		cookie.setMaxAge(0);
		cookie.setValue("");
		cookie.setPath("/");
		response.addCookie(cookie);
	}	

	/*	deprecated by ht on 20070719
	public static void removeCookie(HttpServletRequest request, 
			HttpServletResponse response, String cookieName)
	{
		Cookie cookie, aryCookie[];
		aryCookie = request.getCookies();

		// added by lcl
		if (aryCookie == null)
			return;
		// ////////////////////////////

		int i;

		for (i = 0; i < aryCookie.length; i++)
		{
			cookie = aryCookie[i];

			if (cookie.getName().equals(cookieName))
			{
				cookie.setValue(null);
				// added by lcl
				cookie.setMaxAge(0);
				// ////////////////////////////////////////////
				response.addCookie(cookie);

			}
			else
			{
				Cookie cook = new Cookie(cookieName, null);
				cook.setVersion(0); // 这一行代码好像没有作用

				// added by lcl
				cook.setMaxAge(0);
				// /////////////////////////////////////////////

				response.addCookie(cook);
			}
		}
	}
	 */

	/**
	 * 将Cookie对象加载到客户端.
	 * 
	 * @param response
	 *            HttpServletResponse的请求句柄.
	 * @param cookie
	 *            将被加载到客户端的Cookie对象
	 */
	public static void addCookie(HttpServletResponse response, Cookie cookie)
	{
		response.addCookie(cookie);
	}

	/**
	 * 将Cookie加载到客户端.
	 * 
	 * @param response
	 *            HttpServletResponse的请求句柄.
	 * @param cookieName
	 *            cookie名
	 * @param cookieValue
	 *            cookie值
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue)
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		response.addCookie(cookie);
	}

	/**
	 * 将Cookie对象可控地加载到客户端.
	 * 
	 * @param response
	 *            HttpServletResponse的请求句柄.
	 * @param cookieName
	 *            Cooki对象的名称
	 * @param cookieValue
	 *            Cooki对象的值
	 * @param age
	 *            Cooki的生命期,<br>
	 *            负数: 表示当浏览器关闭的时候删除Cookie,<br>
	 *            0: 表示立即删除Cookie, <br>
	 *            正数: 表示Cookie的生存时间(以秒为单位)<br>
	 * 
	 * @param path
	 *            Cooki的路径
	 */
	public static void addCookie(HttpServletResponse response, 
			String cookieName, String cookieValue, int age, String path)throws Exception
	{
		log.info("设置cookie的入参是编码之前 ： cookieName="+cookieName+"  cookieValue="+cookieValue+" age="+age+"   path="+path);
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(age);
		cookie.setPath(path);
		response.addCookie(cookie);
		log.info("设置cookie "+cookieName+" 成功!");
		
		
	}

	/**
	 * 将Cookie对象可控地加载到客户端.
	 * 
	 * @param response
	 *            HttpServletResponse的请求句柄.
	 * @param cookieName
	 *            Cooki对象的名称
	 * @param cookieValue
	 *            Cooki对象的值
	 * @param domain
	 *            Cooki的所属域
	 * @param age
	 *            Cooki的生命期,<br>
	 *            负数: 表示当浏览器关闭的时候删除Cookie,<br>
	 *            0: 表示立即删除Cookie, <br>
	 *            正数: 表示Cookie的生存时间(以秒为单位)<br>
	 * 
	 * @param path
	 *            Cooki的路径
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, 
			String cookieValue, String domain, int age, String path)
	{
		// int maxAge = -1;
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setDomain(domain);
		cookie.setMaxAge(age);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

}
