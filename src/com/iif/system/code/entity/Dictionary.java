package com.iif.system.code.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 字典信息实体类
 * 
 * @author LiuM
 * @data 2017
 * @version V0.1
 */
public class Dictionary extends BaseEntity {
	private static final long serialVersionUID = -9158262272878724419L;

	private String id;
	private String key;
	private String value;
	private String enumName;
	private Integer index;
	private String parentId;

	public Dictionary() {
		super();
	}
	
	// getter&&setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEnumName() {
		return enumName;
	}
	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}