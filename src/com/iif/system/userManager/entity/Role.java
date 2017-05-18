package com.iif.system.userManager.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 角色实体类
 * 
 * @author thinkpad
 * @data 2009-7-25
 */
public class Role extends BaseEntity {

	private Long id = null;

	private String code = null;

	private String name;// 角色名称

	private String description;// 说明
	
	private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
