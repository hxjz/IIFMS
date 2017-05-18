package com.iif.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HTTP请求基础工具类
 * 
 * @author LiuM
 * @date 2017 CommonHttp
 */
public class CommonHttp {

	/**
	 * 发送http请求 得到返回的json串 2014-1-9
	 * 
	 * @param params
	 *            参数列表
	 * @param strUrl
	 *            请求php的url
	 * @param sendType
	 *            请求的方式"POST" or "GET"
	 * @return String
	 */
	public static String sendUrl(String strUrl, String sendType) {
		String jsonStr = "";
		URL url;
		HttpURLConnection conn;
		try {
			url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(sendType);
			conn.setConnectTimeout(5 * 1000);
			// 得到输入流，即获得了网页的内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				jsonStr = new String(line.getBytes(), "utf-8");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 拼接url的参数部分，得到完整的url请求 2014-1-13
	 * 
	 * @param strUrl
	 *            配置文件中取出的url
	 * @param params
	 *            需要拼接的参数
	 * @return String 完整的url请求
	 */
	public static String jointUrlString(String strUrl,
			Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			sb.append(strUrl + "?");
			int num = 0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				num++;
				String paramName = entry.getKey();
				String paramValue = entry.getValue();
				try {
					paramValue = URLEncoder.encode(paramValue == null ? ""
							: paramValue, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (num == 1) {
					sb.append(paramName + "=");
					sb.append(paramValue);
				} else if (num > 1 && num <= params.size()) {
					sb.append("&" + paramName + "=");
					sb.append(paramValue);
				}
			}
			return sb.toString();
		} else {
			return strUrl;
		}
	}
}
