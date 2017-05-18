package com.iif.system.priv.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 权限与拥有者关系
 * @author:
 *
 */
public class ResourceOwner extends BaseEntity {
	
	private static final long serialVersionUID = 2383760207350432513L;
	//主键
	private Long id;
	//权限id
	private Long resourceId = null;
	//分配对象的id（部门id 用户name 或角色id）
	private String ownerId;
	//权限分配类型：1部门，2用户，3角色
	private Integer assignType = 0;
	//是否已删除
	private Integer isDeleted = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getAssignType() {
		return assignType;
	}
	public void setAssignType(Integer assignType) {
		this.assignType = assignType;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
