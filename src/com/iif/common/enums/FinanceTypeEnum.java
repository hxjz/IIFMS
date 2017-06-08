package com.iif.common.enums;

import com.iif.common.util.RedisUtil;

import java.util.LinkedHashMap;

/**
 * @ClassName: FinanceTypeEnum
 * @Description: 财物类型 enum
 * @Author: GaoGang
 * @Date: 2017-05-21 14:23
 * @Version: V1.0
 */
public class FinanceTypeEnum {
    static final public int TYPE1 = 1;// 手印痕迹
    static final public int TYPE2 = 2;// 足迹痕迹
    static final public int TYPE3 = 3;// 工具痕迹
    static final public int TYPE4 = 4;// 文件痕迹
    static final public int TYPE5 = 5;// 枪弹痕迹
    static final public int TYPE6 = 6;// 特殊痕迹
    static final public int TYPE7 = 7;//  理化物证
    static final public int TYPE8= 8;//  生物物证
    static final public int TYPE9 = 9;// 电子物证
    static final public int TYPE10 = 10;// 视听物证
    static final public int TYPE11 = 11;// 毒化物证


    // 此名固定，不可修改
    @SuppressWarnings("rawtypes")
    public LinkedHashMap enumMap;

    @SuppressWarnings("rawtypes")
    public LinkedHashMap getEnumMap() {
        return enumMap;
    }

    // jsp中按照类似"value=0 text=全部"的规则来操作
    @SuppressWarnings("rawtypes")
    public FinanceTypeEnum() {
        enumMap = new LinkedHashMap();
        RedisUtil.putEnumMap(enumMap, this);
    }


}
