package com.iif.system.userManager.entity;

import java.io.Serializable;
import java.util.Date;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 部门实体类
 * 
 * @author thinkpad
 * @data Sep 28, 2009
 */
public class Org extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -13076940837227354L;

	private Long id = null;

	/**
	 * 部门代码
	 */
	private String code = null;

	/**
	 * 单位名称
	 */
	private String name = null;

	/**
	 * 父节点ID
	 */
	private Long parentId = null;

	/**
	 * 父部门
	 */
	private Org parent = null;
	
	/**
	 * 父结点路径
	 */
	private String parentPath = null;

	/**
	 * 是否是叶子节点
	 */
	private Integer isLeaf = 0;

	/**
	 * 是否删除
	 */
	private Integer isDel = 0;

	private long sharePfloderNum = 0;// 共享文件夹数目

	/**
	 * 排序
	 */
	private Integer orderCode = 1;
	
	
	
	/*===================合并进来的===================*/
	
	/**
	 * setid
	 */
	private String setid;

	/**
	 * 部门编号
	 */
	private String deptid;

	/**
	 * 简称
	 */
	private String descrshort;

	/**
	 * 全称
	 */
	private String descr;

	/**
	 * 生效日期
	 */
	private Date effdt;

	/**
	 * 生效状态
	 */
	private String effStatus;

	/**
	 * 部门级别
	 */
	private String hlDeptGra;

	/**
	 * 地点集合代码
	 */
	private String setidLocation;

	/**
	 * 地点
	 */
	private String location;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 管理员ID
	 */
	private String managerId;

	/**
	 * 深度
	 */
	private Long hlTreelevel;

	/**
	 * 深度路径
	 */
	private String hlTreepath;

	/**
	 * 时间戳
	 */
	private Date hlOutDt;

	/**
	 * 全量增量标记
	 */
	private String hlGlsFlag;

	

	public String getSetid() {
		return this.setid;
	}

	public void setSetid(String setid) {
		this.setid = setid;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDescrshort() {
		return this.descrshort;
	}

	public void setDescrshort(String descrshort) {
		this.descrshort = descrshort;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getEffdt() {
		return this.effdt;
	}

	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}

	public String getEffStatus() {
		return this.effStatus;
	}

	public void setEffStatus(String effStatus) {
		this.effStatus = effStatus;
	}

	public String getHlDeptGra() {
		return this.hlDeptGra;
	}

	public void setHlDeptGra(String hlDeptGra) {
		this.hlDeptGra = hlDeptGra;
	}

	public String getSetidLocation() {
		return this.setidLocation;
	}

	public void setSetidLocation(String setidLocation) {
		this.setidLocation = setidLocation;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Long getHlTreelevel() {
		return this.hlTreelevel;
	}

	public void setHlTreelevel(Long hlTreelevel) {
		this.hlTreelevel = hlTreelevel;
	}

	public String getHlTreepath() {
		return this.hlTreepath;
	}

	public void setHlTreepath(String hlTreepath) {
		this.hlTreepath = hlTreepath;
	}

	public Date getHlOutDt() {
		return this.hlOutDt;
	}

	public void setHlOutDt(Date hlOutDt) {
		this.hlOutDt = hlOutDt;
	}

	public String getHlGlsFlag() {
		return this.hlGlsFlag;
	}

	public void setHlGlsFlag(String hlGlsFlag) {
		this.hlGlsFlag = hlGlsFlag;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getSharePfloderNum() {
		return sharePfloderNum;
	}

	public void setSharePfloderNum(long sharePfloderNum) {
		this.sharePfloderNum = sharePfloderNum;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
}
