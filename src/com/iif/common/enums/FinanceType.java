package com.iif.common.enums;

/**
 * @ClassName: FinanceType
 * @Description:
 * @Author: GaoGang
 * @Date: 2017-05-27 01:46
 * @Version: V1.0
 */
public enum FinanceType {
    ONE(1, "手迹痕迹"),
    TWO(2, "足迹痕迹"),
    THREE(3, "工具痕迹"),
    FOUR(4, "文件物证"),
    FIVE(5, "枪弹痕迹"),
    SIX(6, "毒化痕迹"),
    SEVEN(7, "特殊痕迹"),
    EIGHT(8, "理化物证"),
    NINE(9, "生化物证"),
    TEN(10, "电子物证"),
    ELEVEN(11, "视听物证");

    private int typeCode;
    private String typeName;

    FinanceType(int typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
