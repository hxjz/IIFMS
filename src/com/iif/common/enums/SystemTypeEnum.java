package com.iif.common.enums;

import com.iif.common.util.RedisUtil;

import java.util.LinkedHashMap;

/**
 * @ClassName: SystemTypeEnum
 * @Description: 系统类型，将 枚举名称 改为中文
 * @Author: GaoGang
 * @Date: 2017-07-04 23:33
 * @Version: V1.0
 */
public class SystemTypeEnum {
    // 此名固定，不可修改
    @SuppressWarnings("rawtypes")
    public LinkedHashMap enumMap;

    @SuppressWarnings("rawtypes")
    public LinkedHashMap getEnumMap() {
        return enumMap;
    }

    // jsp中按照类似"value=0 text=全部"的规则来操作
    @SuppressWarnings("rawtypes")
    public SystemTypeEnum() {
        enumMap = new LinkedHashMap();
        RedisUtil.putEnumMap(enumMap, this);
    }
}
