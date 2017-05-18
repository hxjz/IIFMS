package com.iif.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.hxjz.common.utils.SystemConstant;

/**
 * 短信工具类
 * @author 
 *
 */
public class SMSUtils {

	public static final String SMS_SWITCH_CLOSE_MSG = "fail:短信开关已关闭";

	private static String url;

	private static String username;

	private static String password;

	private static String smsIsSend;
	
	private static String userid;

	static {
		if (url == null) {
			userid = SystemConstant.getSystemConstant("iif.userid");
			url = SystemConstant.getSystemConstant("iif.smsUrl");
			username = SystemConstant.getSystemConstant("iif.smsUserName");
			password = SystemConstant.getSystemConstant("iif.smsPassword");
			smsIsSend = SystemConstant.getSystemConstant("iif.smsIsSend");
		}
	}

	/**
	 * @param phone
	 *            手机号
	 * @param smg
	 *            短信内容
	 */
	public static String noteSend(String phone, String smg) {
		if("false".equals(smsIsSend)) {
			return SMS_SWITCH_CLOSE_MSG;
		}

		String send = "";
		try {
			// note by dengzm at 2013-5-16
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("userid", userid));// 用户名
			parameters.add(new BasicNameValuePair("account", username));// 用户名
			parameters.add(new BasicNameValuePair("password", password));// 密码
			parameters.add(new BasicNameValuePair("mobile", phone));// 手机号码
			parameters.add(new BasicNameValuePair("content", smg));// 内容
			parameters.add(new BasicNameValuePair("sendTime", ""));// 内容
			parameters.add(new BasicNameValuePair("action", "send"));// 内容
			parameters.add(new BasicNameValuePair("extno", ""));// 内容
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
			// 5 把实体数据设置到请求对象
			httpPost.setEntity(entity);
			// 6 执行请求
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity ett = httpResponse.getEntity(); // 获取响应里面的内容
			// 得到服务气端发回的响应的内容（都在一个字符串里面）
			send = EntityUtils.toString(ett);
		} catch (Exception e) {
			e.printStackTrace();
			return "-100";
		}
		return send;
	}

}
