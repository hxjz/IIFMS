package com.iif.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * http客户端工具类，发送http请求
 * 
 */
public class HttpClientUtil {
	/**
	 * 通过cookie验证是否有授权
	 * 
	 * @param cookies
	 * @param url
	 *        请求地址
	 * @param service
	 *        当前服务code
	 * @return
	 */
	public static String validAuthorize(Cookie[] cookies , String url , String serviceCode) {

		if (cookies == null || StringUtils.isBlank(url) || StringUtils.isBlank(serviceCode)) {
			return null ;
		}

		URL zirromUrl ;

		try {

			zirromUrl = new URL(url) ;
			HttpURLConnection conn = (HttpURLConnection) zirromUrl.openConnection() ;
			conn.setDoOutput(true) ;
			conn.setDoInput(true) ;
			conn.setRequestMethod("POST") ;
			conn.setUseCaches(false) ;
			conn.setRequestProperty("Charsert" , "UTF-8") ;

			StringBuffer cookieStr = new StringBuffer() ;
			if (cookies != null) {
				for( Cookie cookie : cookies) {
					cookieStr.append(cookie.getName()).append("=").append(cookie.getValue()).append(";") ;
				}
			}
			conn.addRequestProperty("Cookie" , cookieStr.toString()) ;

			conn.getOutputStream().write(("service=" + serviceCode).getBytes("UTF-8")) ;

			InputStreamReader reder = new InputStreamReader(conn.getInputStream() , "utf-8") ;

			BufferedReader breader = new BufferedReader(reder) ;

			String content = "" ;
			StringBuilder result = new StringBuilder();
			while((content = breader.readLine()) != null) {
				result.append(content) ;
			}
			return result.toString() ;

		} catch (Exception e) {
			e.printStackTrace() ;
		}
		return null ;
	}
	
	/** 通过HTTP Get请求获取数据
	 * @param path 请求路径
	 * @return 响应结果串
	 * @throws Exception
	 */
	public static String getDataByHttpGet(String path) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet  httpget = new HttpGet(path);
		HttpResponse httpResponse = httpClient.execute(httpget);
		
		HttpEntity ett = (HttpEntity) httpResponse.getEntity(); // 获取响应里面的内容
		
		String str=  EntityUtils.toString(ett);
		return str;
	}
}
