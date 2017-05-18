package com.iif.common.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 是否可用
 * @author LiuM
 *
 */
public class ValidEnum {
	static final public int YES = 1;// 可用
	static final public int NO = 0;// 不可用

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"key=0 value=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public ValidEnum() {
		enumMap = new LinkedHashMap();
		RedisUtil.putEnumMap(enumMap, this);
	}
}
