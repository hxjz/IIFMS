package com.iif.storage.entity;

import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 字典信息实体类
 * 
 * @author LiuM
 * @data 2017
 * @version V0.1
 */
public class Storage extends BaseEntity {
	private static final long serialVersionUID = -9158262272878724419L;

	private String id;
	private String key;
	private String showName;
	private String showImg;
	private Integer index;
	private Integer storageStatus;
	private Integer isAvail;
	private String parentId;
    private List<Storage> child;    

	public List<Storage> getChild() {
		return child;
	}

	public void setChild(List<Storage> child) {
		this.child = child;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Storage() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	// getter&&setter

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getStorageStatus() {
		return storageStatus;
	}

	public void setStorageStatus(Integer storageStatus) {
		this.storageStatus = storageStatus;
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
}