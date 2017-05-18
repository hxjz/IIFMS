package com.iif.orgMgt.entity.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 帐户状态枚举
 * @author LiuM
 *
 */
public class UserStateEnum {
	static final public int VALID = 0;//有效
	static final public int LOCK = 1;//锁定
	static final public int INVALID = 2;//无效

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"value=0 text=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public UserStateEnum() {
		enumMap= new LinkedHashMap();
		
		RedisUtil.putEnumMap(enumMap, this);
	}
}