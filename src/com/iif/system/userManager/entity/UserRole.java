package com.iif.system.userManager.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 用户角色关系实体
 * 
 * @author thinkpad刘明
 * 
 */
public class UserRole extends BaseEntity {

	private Long id = null;

	private Long userId = null; //用户id

	private Long roleId = null; //角色id

	private Role role = null; //角色

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
