package com.iif.orgMgt.entity;

import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;
import com.iif.system.resource.entity.Resource;

/**
 * 菜单角色关系表
 * 
 * @author LiuM
 * @date 2014-9-1
 * @since 1.0
 */
public class RoleResource extends BaseEntity {

	private static final long serialVersionUID = 5034146068767143243L;

	private String id;

	private IffRole role;	//角色

	private Resource resource; //资源id

	private RoleResource parent; //父节点ID

	private Integer isMenu = 0; //是否是菜单

	private List<RoleResource> children;//子节点
	
	private String name; //菜单名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IffRole getRole() {
		return role;
	}

	public void setRole(IffRole role) {
		this.role = role;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public RoleResource getParent() {
		return parent;
	}

	public void setParent(RoleResource parent) {
		this.parent = parent;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public List<RoleResource> getChildren() {
		return children;
	}

	public void setChildren(List<RoleResource> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
