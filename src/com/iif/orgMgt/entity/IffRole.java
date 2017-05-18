package com.iif.orgMgt.entity;

import java.util.Date;
import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 角色实体类
 * 
 * @author thinkpad
 * @data 2009-7-25
 */
public class IffRole extends BaseEntity {
	
	private static final long serialVersionUID = -5015740038821560748L;

	private String id;

	private String name;// 角色名称

	private String description;// 说明

	private Integer isDel = 0;//是否有效

	private Employee createUser;//创建人

	private Date createDate;//创建日期

	private Employee modifyUser;//修改人

	private Date modifyDate;//修改日期

	transient private List<RoleResource> roleResourceses;//角色资源映射表

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Employee getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Employee createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Employee getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Employee modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<RoleResource> getRoleResourceses() {
		return roleResourceses;
	}

	public void setRoleResourceses(List<RoleResource> roleResourceses) {
		this.roleResourceses = roleResourceses;
	}
}
