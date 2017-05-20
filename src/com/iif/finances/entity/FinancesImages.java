package com.iif.finances.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * @ClassName: Picture
 * @Description: 图片类
 * @Author: GaoGang
 * @Date: 2017-05-20 21:51
 * @Version: V1.0
 */
public class FinancesImages extends BaseEntity {

    private String id;  // 图片编号

    private String financeId;   // 关联的财务id

    private String imageName;  //  图片名称

    private String imageType;  //  图片类型 eg：1 正面图 2 侧面图 3 俯视图等等

    private String imageDesc;  //  图片描述

    private String imageUrl;  //  图片位置,url


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }
}
