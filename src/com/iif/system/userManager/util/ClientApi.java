package com.iif.system.userManager.util;

import java.util.HashMap;
import java.util.Map;

import com.iif.common.util.CommonHttp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 客户库同步接口类  全部是静态方法
 * @author LiuM
 * @date 2014-9-22
 * ZiroomCustomerApi
 */
public class ClientApi {

	/**
	 * 更新用户信息(已测)
	 * 2014-9-24
	 * @param params 参数列表
	 * @return
	 * CustomerInfo 客户信息对象
	 */
	public static JSONObject update(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	/**
	 * 修改证件号（已测）
	 * 2014-9-24
	 * @param params 参数列表
	 * @return
	 * CustomerInfo 客户信息对象
	 */
	public static JSONObject changeCertNum(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	/**
	 * 修改登录名
	 * 2014-9-24
	 * @param params 参数列表
	 * @return
	 * CustomerInfo 客户信息对象
	 */
	public static JSONObject changeLoginName(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	/**
	 * 新增登录名
	 * 2014-9-24
	 * @param params 参数列表
	 * @return
	 * uid 网站客户uid
	 */
	public static JSONObject register(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	/**
	 * 查询用户
	 * 2014-9-24
	 * @param params 参数列表
	 * @return
	 * uid 网站客户uid
	 */
	public static JSONObject searchUser(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	
	/**
	 * 根据UID获取客户姓名
	 * @param uid UID
	 * add by tanght 20141218
	 * @return 客户姓名
	 */
	public static String getNameByUid(String uid){
		Map<String,String> params = new HashMap<String, String>();
		params.put("uid", uid);
		JSONObject obj = searchUser(params);
		
		JSONArray data = (JSONArray)obj.get("data");
		if(data.size() == 0){
			return "";
		}
		JSONObject cusInfo = data.getJSONObject(0);
		System.out.println(cusInfo);
		return cusInfo.getString("user_name");
	}
	
	/**
	 * 根据电话获取UID
	 * @param mobile 客户电话
	 * add by tanght 20141218
	 * @return UID
	 */
	public static String getUidByMobile(String mobile){
		Map<String,String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		JSONObject obj = searchUser(params);
		
		JSONArray data = (JSONArray)obj.get("data");
		JSONObject cusInfo = data.getJSONObject(0); 
		return cusInfo.getString("uid");
	}
	
	/**
	 * 在线新签 - 创建签约用户
	 * 2015-03-16
	 * @param params 参数列表
	 * @return
	 * CustomerInfo 客户信息对象
	 */
	public static JSONObject addCustomer(Map<String,String> params){
		String url = CommonHttp.jointUrlString(null,params);//拼接请求url
		return ClientApi.sendPost(url);
	}
	
	/**
	 * 发送请求
	 * @param url
	 * @return
	 */
	public static JSONObject sendPost(String url){
		System.out.println("访问客户库接口同步URL===========："+url) ;
		String jsonStr = CommonHttp.sendUrl(url,"POST");
		JSONObject jsonObj = null;
		try {
			jsonObj = JSONObject.fromObject(jsonStr) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	public static void main(String[] args) throws Exception {
		/*Map<String,String> params = new HashMap<String,String>();
		params.put("login_name", "138111111111");
		params.put("password","888888");
		params.put("key", Md5.md5s("138111111111"+"Iamcomfortableguest"+"888888"+"Frommyapartment"));
		JSONObject jsObj = register(params);
		JSONObject jsObj = searchUser(params);
		System.out.println(jsObj);*/
		// System.out.println(getNameByUid("84aa5c23-088a-3c39-33d6-6ef39b1ffcd7"));
	}
}
