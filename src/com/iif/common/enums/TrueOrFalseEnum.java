package com.iif.common.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 是与否枚举
 * @author LiuM
 *
 */
public class TrueOrFalseEnum {
	static final public int TRUE = 1;// 是
	static final public int FALSE = 0;// 否

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"value=0 text=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public TrueOrFalseEnum() {
		enumMap = new LinkedHashMap();
		RedisUtil.putEnumMap(enumMap, this);
	}
}
