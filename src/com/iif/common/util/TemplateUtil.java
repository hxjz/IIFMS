package com.iif.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hxjz.common.utils.Page;

/**
 * 返回数据模版工具类
 * @author LiuM
 * 2017
 */
public class TemplateUtil {
	/**
	 * Ojbect tranfer to Map for easy-ui datagrid
	 * @param page 分页工具类
	 * @param data list结果集
	 * @return map结果集
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Map toDatagridMap(Page page, List data){
		return toDatagridMap(page, data, null);
	}
	
	/**
	 * Ojbect tranfer to Map for easy-ui datagrid
	 * @param page 分页工具类
	 * @param data list结果集
	 * @param objMap data中数据字典对应的map
	 * @rule map命名规则为:data中的字段名+Map
	 * @return map结果集
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map toDatagridMap(Page page, List data, Map objMap){
		Map resultData = new HashMap();
		resultData.put("total", page.getTotalCount()); // easy-ui基本数据格式为：data :{total:0,rows:[]}
		
		// 替换data中数据字典的值
		if (objMap != null) {
			// 提取objMap中的字段名
			Set fieldMapKeys = objMap.keySet();
			List<String> fieldNames = new ArrayList<String>(); // objMap中的字段名
			Iterator item = fieldMapKeys.iterator();
			while (item.hasNext()) {
				String name = (String) (item.next());
				fieldNames.add(name.substring(0, name.length() - 3)); // 命名规则  xxxMap
			}
			// 替换枚举中相应的值
			for(Object entity : data){
				// 根据字段名称获取相应的字段的值
				for(String fieldName : fieldNames){
					try {
						Field field = entity.getClass().getDeclaredField(fieldName);
						field.setAccessible(true);
						Object entityFieldValue = field.get(entity); // 实体中的值
						// 遍历objMap中的map对象，替换值
						Map fieldMap = (Map) objMap.get(fieldName+"Map");
						Object newValue = null;
						try{
							newValue = fieldMap.get(Integer.parseInt((String)entityFieldValue));
						} catch (Exception e) {
							newValue = fieldMap.get(entityFieldValue);
						}
						field.set(entity, newValue);
					} catch (NoSuchFieldException e) {
						System.out.println("********字段错误，请按照'字段名+Map'规则命名fieldMap********");
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						System.out.println("********非法调用private类型字段********");
						e.printStackTrace();
					} 
				}
			}
		}
		resultData.put("rows", data);
		
		return resultData;
	}
	
	/**
	 * Ojbect tranfer to Map for easy-ui datagrid
	 * @param num 分页条数
	 * @param data list结果集
	 * @param objMap data中数据字典对应的map
	 * @rule map命名规则为:data中的字段名+Map
	 * @return map结果集
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map toDatagridInfo(Integer num, List data, Map objMap){
		Map resultData = new HashMap();
		resultData.put("total", num); // easy-ui基本数据格式为：data :{total:0,rows:[]}
		
		// 替换data中数据字典的值
		if (objMap != null) {
			// 提取objMap中的字段名
			Set fieldMapKeys = objMap.keySet();
			List<String> fieldNames = new ArrayList<String>(); // objMap中的字段名
			Iterator item = fieldMapKeys.iterator();
			while (item.hasNext()) {
				String name = (String) (item.next());
				fieldNames.add(name.substring(0, name.length() - 3)); // 命名规则  xxxMap
			}
			// 替换枚举中相应的值
			for(Object entity : data){
				// 根据字段名称获取相应的字段的值
				for(String fieldName : fieldNames){
					try {
						Field field = entity.getClass().getDeclaredField(fieldName);
						field.setAccessible(true);
						Object entityFieldValue = field.get(entity); // 实体中的值
						// 遍历objMap中的map对象，替换值
						Map fieldMap = (Map) objMap.get(fieldName+"Map");
						Object newValue = null;
						try{
							newValue = fieldMap.get(Integer.parseInt((String)entityFieldValue));
						} catch (Exception e) {
							newValue = fieldMap.get(entityFieldValue);
						}
						field.set(entity, newValue);
					} catch (NoSuchFieldException e) {
						System.out.println("********字段错误，请按照'字段名+Map'规则命名fieldMap********");
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						System.out.println("********非法调用private类型字段********");
						e.printStackTrace();
					} 
				}
			}
		}
		resultData.put("rows", data);
		
		return resultData;
	}
	
	/**
	 * success map template
	 * @param data 要返回的数据
	 * @return map like status="success",data=data 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map toSuccessMap(Object data){
		Map resultData = new HashMap();
		resultData.put("status", "success");
		if(data != null){
			resultData.put("data", data);
		}
		return resultData;
	}
	
	/**
	 * success map template
	 * @return map like status="success"
	 */
	@SuppressWarnings("rawtypes")
	public static Map toSuccessMap(){
		return toSuccessMap(null);
	}
	
	/**
	 * fail map template
	 * @param data 要返回的数据
	 * @return map like status="fail",data=data 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map toFailMap(Object data){
		Map resultData = new HashMap();
		resultData.put("status", "fail");
		if(data != null){
			resultData.put("data", data);
		}
		return resultData;
	}

	/**
	 * fail map template
	 * @param data 要返回的数据
	 * @return map like status="fail"
	 */
	@SuppressWarnings("rawtypes")
	public static Map toFailMap(){
		return toFailMap(null);
	}
	
}
