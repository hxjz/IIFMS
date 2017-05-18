package com.iif.system.resource.entity;

import java.io.Serializable;
import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 资源实体类
 * 
 * @Author LiuM
 * @Since DEC 8, 2014
 */
public class Resource extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 194743320665795897L;

	private Long id = null;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 是否叶子节点
	 */
	private Integer isLeaf = 0;

	/**
	 * url
	 */
	private String url;

	/**
	 * 排序
	 */
	private Integer orderCode = 1;

	/**
	 * 状态
	 */
	private Integer isEnable = 0;
	
	private Integer isDel = 0;

	/**
	 * 父菜单ID
	 */
	private Long parentId;
	
	/**
	 * 是否单独一行
	 */
	private Integer isLine = 0;

	/**
	 * 菜单类型(1 模块 2 非链接菜单 3 链接菜单 4 操作)
	 */
	private Integer type = 0;
	
	/**
	 * 菜单级数
	 */
	private Integer levelNum = 0;
	
	transient private List<Resource> children;
	
	public Integer getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}

	private String iconCls = null;
	/**
	 * 是否是按钮1是，0否
	 * @return
	 */
	private Integer isButton = 0;
	
	public Integer getIsButton() {
		return isButton;
	}

	public void setIsButton(Integer isButton) {
		this.isButton = isButton;
	}

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

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getIsLine() {
		return isLine;
	}

	public void setIsLine(Integer isLine) {
		this.isLine = isLine;
	}


	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}
}