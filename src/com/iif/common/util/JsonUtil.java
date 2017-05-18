package com.iif.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.hxjz.common.utils.Page;

/**
 * json转换工具类
 * 
 * @author LiuM
 * @since 2017
 */
public class JsonUtil {

	/**
	 * 把一个对象通过反射生成Json格式数据
	 * 
	 * @param obj
	 *            需要转化的对象
	 * @return 返回一个Json格式的字符串
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static String objToJson(Object obj) throws Exception {
		StringBuilder json = new StringBuilder();
		Class clazz = obj.getClass();
		Field[] fields = obj.getClass().getDeclaredFields();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int j = 0;
		boolean hasContent = false;
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			Field f = clazz.getDeclaredField(name);
			Method m = clazz.getDeclaredMethod(getGetField(name));
			Object value = m.invoke(obj);
			String val = null;
			if (value instanceof Date) {
				val = sdf.format(value);
			} else if(value instanceof List){
				val = listToJson((List)value);
			}else{
				val = "\"" + value + "\"";
			}
			if(value != null && val != null){
				if (0 == j) {
					json.append("{\"");
					json.append(name).append("\":").append(val);
				} else{
					json.append(",").append("\"");
					json.append(name).append("\":").append(val);
				} 
				j++;
				hasContent = true;
			}
		}
		if(hasContent){
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * 得到get+属性名的get方法
	 * 
	 * @param fieldName
	 *            属性的名字
	 * @return String 如：getName的方法名
	 */
	public static String getGetField(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

	}

	/**
	 * 把List转化成Json格式的数据
	 * 
	 * @param list
	 *            传入的List对象
	 * @return String Json格式字符串
	 * @throws Exception
	 */
	public static String listToJson(List<?> list) throws Exception {
		if (list == null || list.size() == 0) {
			return null;
		}
		StringBuilder json = new StringBuilder();
		json.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				json.append(objToJson(list.get(i))).append("]");
			} else {
				json.append(objToJson(list.get(i))).append(",");
			}
		}
		return json.toString();
	}

	/**
	 * 列表页list用数据转换
	 * 
	 * @param lstResult
	 * @return string
	 */
	@SuppressWarnings("rawtypes")
	public static String pageJsonObject(List lstResult, Page page) {
		String strRtn = "";

		// Default Setting Start
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", 0);// total录数 0
		jsonMap.put("rows", new ArrayList());// rows存放空的List
		JSONObject result = JSONObject.fromObject(jsonMap);// 格式化result
		strRtn = result.toString();
		// Default Setting End

		if (lstResult != null && lstResult.size() > 0) {
			jsonMap = new HashMap<String, Object>();// 定义map
			jsonMap.put("total", page.getTotalCount());// total键 存放总记录数，必须的
			jsonMap.put("rows", lstResult);// rows键 存放每页记录 list
			result = JSONObject.fromObject(jsonMap);// 格式化result 一定要是JSONObject
			strRtn = result.toString();
		}

		return strRtn;
	}

	public static String strToJson(String data) {
		StringBuilder json = new StringBuilder();
		json.append("{\"").append("data\":\"").append(data).append("\"}");
		return json.toString();
	}

	public static String jsonSuccess(String data) {
		StringBuilder json = new StringBuilder();
		json.append("{\"status").append("\":\"success\",")
				.append("\"message\":\"");
		if (StringUtils.isBlank(data)) {
			json.append("操作成功");
		} else {
			json.append(data);
		}
		json.append("\"}");
		return json.toString();
	}

	public static String jsonFailure(String data) {
		StringBuilder json = new StringBuilder();
		json.append("{\"status").append("\":\"failure\",")
				.append("\"message\":\"");
		if (StringUtils.isBlank(data)) {
			json.append("操作失败");
		} else {
			json.append(data);
		}
		json.append("\"}");
		return json.toString();
	}
	
	/**
	 * 生成失败的json字符串
	 * @author guohm
	 * @datetime 2014年8月6日 上午9:03:55
	 * @since 1.0.0
	 * @param errmsg
	 * @return
	 */
	public static String parseFailureJson(String errmsg) {
		StringBuilder json = new StringBuilder();
		json.append("{\"flag").append("\":\"failure\",")
				.append("\"errmsg\":\"");
		if (StringUtils.isBlank(errmsg)) {
			json.append("未知异常");
		} else {
			json.append(errmsg);
		}
		json.append("\"}");
		return json.toString();
	}
	
	public static String parseSuccessJson(String errmsg) {
		StringBuilder json = new StringBuilder();
		json.append("{\"flag").append("\":\"success\",")
				.append("\"errmsg\":\"");
		if (StringUtils.isBlank(errmsg)) {
			json.append("success");
		} else {
			json.append(errmsg);
		}
		json.append("\"}");
		return json.toString();
	}
}
