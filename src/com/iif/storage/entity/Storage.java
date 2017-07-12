package com.iif.storage.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 存储位置信息实体类
 * 
 * @author LiuM
 * @data 2017
 * @version V0.1
 */
public class Storage extends BaseEntity {
	private static final long serialVersionUID = -9158262272878724419L;

	private String id;      // key
	private String name;    // 显示名称 
	private String level;   // 层级显示
	private String abSide;  // 密集柜AB面控制
	private String device;  // 设备信息
	private String comment; // 备注
	private Integer status; // 使用状态
	private Integer index;  // 排序
	private Integer isAvail;// 是否启用
	private String parentId;// 父节点Id
	private String type;    // 储存类型(密集柜/储存区)
	private String conUrl;  // 控制URL地址
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getAbSide() {
		return abSide;
	}
	public void setAbSide(String abSide) {
		this.abSide = abSide;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getIsAvail() {
		return isAvail;
	}
	public void setIsAvail(Integer isAvail) {
		this.isAvail = isAvail;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConUrl() {
		return conUrl;
	}
	public void setConUrl(String conUrl) {
		this.conUrl = conUrl;
	}
  
}