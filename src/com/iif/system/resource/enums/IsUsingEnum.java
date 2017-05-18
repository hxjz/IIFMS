package com.iif.system.resource.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * （启用、禁用）下拉列表类
 */
/***使用Demo： HttpTool.setAttribute("xxList", InitSelect.getSelectList(XX.class.toString())); ***/
public class IsUsingEnum {
	public static int QY = 1;//启用
	public static int JY = 0;//禁用
	
	//此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	//jsp中按照类似"value=0 text=启用"的规则来操作
	@SuppressWarnings("rawtypes")
	public IsUsingEnum(){
		enumMap= new LinkedHashMap();
		
		RedisUtil.putEnumMap(enumMap, this);
	}
}
