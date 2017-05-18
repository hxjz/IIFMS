package com.iif.security.web ;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hxjz.common.utils.SystemConstant;
import com.iif.orgMgt.entity.UserAccount;


/**
 * 权限控制标签，暂时验证是否登录，暂未时间写是否有权限访问
 * 
 * @author LiuM
 * @date 2017
 * @since 0.1
 */
public class SecurityFilter implements Filter {

	protected FilterConfig filterConfig = null ;
	
	private String loginUrl = null;

	protected static final Logger logger = Logger.getLogger(SecurityFilter.class) ;

	public void destroy() {
	}

	/**
	 * 安全过滤
	 */
	public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws IOException , ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest ;
		HttpServletResponse response = (HttpServletResponse) servletResponse ;

		// 获取当前访问URL
		String currentUrl = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo()) ;
		if(Boolean.valueOf(SystemConstant.getSystemConstant("query_string_on")) && request.getQueryString()!=null){ // 是否显示参数
			currentUrl = currentUrl + "?" + request.getQueryString();
		}
		
		logger.info("***********当前请求的URL为：***********" + currentUrl) ;
		
		if(loginUrl.contains(currentUrl)) {
			filterChain.doFilter(servletRequest , servletResponse) ;
			request.getSession().setAttribute("currentUser", new UserAccount());
			return;
		}

		// session中没有user
		if (null == request.getSession().getAttribute("currentUser")) {
			request.getSession().setAttribute("currentUser", new UserAccount());

			response.sendRedirect(request.getContextPath()+"/jump.jsp") ;
			return ;
		}

		// 过滤掉当前访问URL后面参数
		currentUrl = currentUrl.substring(1);
		if (currentUrl.lastIndexOf("?") > 0) {
			currentUrl = currentUrl.substring(0 , currentUrl.lastIndexOf("?")) ;
		}

		filterChain.doFilter(servletRequest , servletResponse) ;
	}

	/**
	 * 初始化不需要验证的的URL
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		loginUrl = "/security/login.action";
	}
}
