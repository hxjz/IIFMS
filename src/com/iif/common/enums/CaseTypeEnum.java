package com.iif.common.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 案件类型枚举
 * @author LiuM
 * @version V0.1
 * @date 2017
 *
 */
public class CaseTypeEnum {
	static final public int TYPE1 = 1;// TEMP
	static final public int TYPE2 = 2;// TEMP
	static final public int TYPE3 = 3;// TEMP
	static final public int TYPE4 = 4;// TEMP
	static final public int TYPE5 = 5;// TEMP
	static final public int TYPE6 = 6;// TEMP

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"value=0 text=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public CaseTypeEnum() {
		enumMap = new LinkedHashMap();
		RedisUtil.putEnumMap(enumMap, this);
	}
}
