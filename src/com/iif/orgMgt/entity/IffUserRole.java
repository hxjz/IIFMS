package com.iif.orgMgt.entity;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 用户角色关系表
 * @author LiuM
 * @date 2015-03-20
 *
 */
public class IffUserRole extends BaseEntity {

	private static final long serialVersionUID = 3006754436100499355L;

	private String id;

	private UserAccount user;	//用户

	private IffRole role;	//角色

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public IffRole getRole() {
		return role;
	}

	public void setRole(IffRole role) {
		this.role = role;
	}

}
