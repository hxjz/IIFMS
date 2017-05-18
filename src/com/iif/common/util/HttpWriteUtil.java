package com.iif.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class HttpWriteUtil {

	/**
	 * 重写httpResponse write方法
	 * @throws IOException 
	 */
	public static void write(String s) throws IOException{
		HttpServletResponse response = HttpUtil.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			throw new IOException("response写信息异常！");
		}
	}
	
	/**
	 * ajax成功返回
	 * @throws IOException 
	 */
	public static void successWrite(Object obj) throws IOException{
		JSONObject jObj = new JSONObject();
		jObj.put("status", "success");
		if (obj!=null) {
			jObj.put("data", obj);
		}
		write(jObj.toString());
	}

	/**
	 * 操作失败返回
	 * @throws IOException 
	 */
	public static void failWrite(String message) throws IOException{
		JSONObject obj = new JSONObject();
		obj.put("status", "failure");

		if(!StringUtils.isEmpty(message)) {
			obj.put("message", message);
		} else {
			obj.put("message", "操作失败！");
		}

		write(obj.toString());
	}

	public static void successWrite() throws IOException{
		successWrite(null);
	}
	
	public static void failWrite() throws IOException{
		failWrite(null);
	}
}
