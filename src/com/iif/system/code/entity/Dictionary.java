package com.iif.system.code.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 字典信息实体类
 * 
 * @author LiuM
 * @data 2017
 * @version V0.1
 */
@Entity
@Table(name = "tdictionary", catalog = "iif")
public class Dictionary extends BaseEntity {
	private static final long serialVersionUID = -9158262272878724419L;
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "fid", unique = true, nullable = false, length = 50)
	private String id;
	@Column(name = "fkey", nullable = true, length = 10)
	private String key;
	@Column(name = "fvalue", nullable = true, length = 100)
	private String value;
	@Column(name = "fenum_name", nullable = true, length = 100)
	private String enumName;
	@Column(name = "fis_del", nullable = true)
	private Integer isDel;
	@Column(name = "findex", nullable = true)
	private Integer index;
	@Column(name = "fcreate_time", nullable = true, length = 19)
	private Date createTime;
	@Column(name = "fupdate_time", nullable = true, length = 19)
	private Date updateTime;
	@Column(name = "fparent_id", nullable = true, length = 50)
	private String parentId;

	public Dictionary() {
		super();
	}
	
	public Dictionary(String id, String key, String value, String enumName,
			Integer isDel, Integer index, Date createTime,
			Date updateTime, String parentId) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.enumName = enumName;
		this.isDel = isDel;
		this.index = index;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.parentId = parentId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}