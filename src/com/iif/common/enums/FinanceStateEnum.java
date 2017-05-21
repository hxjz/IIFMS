package com.iif.common.enums;

import com.iif.common.util.RedisUtil;

import java.util.LinkedHashMap;

/**
 * @ClassName: FinanceStateEnum
 * @Description: 财物状态
 * @Author: GaoGang
 * @Date: 2017-05-21 15:08
 * @Version: V1.0
 */
public class FinanceStateEnum {
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
    public FinanceStateEnum() {
        enumMap = new LinkedHashMap();
        RedisUtil.putEnumMap(enumMap, this);
    }
}
