package com.iif.common.enums;

import java.util.LinkedHashMap;

import com.iif.common.util.RedisUtil;

/**
 * 
 * 出库原因类型枚举
 * @author M
 * @version V0.1
 * @date 2017
 */
public class OutstockReasonTypeEnum {
	static final public int TYPE1 = 1;// 移交
	static final public int TYPE2 = 2;// 销毁
	static final public int TYPE3 = 3;// 其它
	static final public int TYPE4 = 4;// 
	static final public int TYPE5 = 5;// 

	// 此名固定，不可修改
	@SuppressWarnings("rawtypes")
	public LinkedHashMap enumMap;

	@SuppressWarnings("rawtypes")
	public LinkedHashMap getEnumMap() {
		return enumMap;
	}

	// jsp中按照类似"value=0 text=全部"的规则来操作
	@SuppressWarnings("rawtypes")
	public OutstockReasonTypeEnum() {
		enumMap = new LinkedHashMap();
		RedisUtil.putEnumMap(enumMap, this);
	}
}
