package com.iif.system.userManager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;
import com.iif.system.resource.entity.Resource;

/**
 * 用户实体类
 * 
 * @author thinkpad
 * @data 2009-8-7
 */
public class User extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5873229197546052093L;

	private Long id = null;

	/**
	 * 用户名
	 */
	private String username = null;

	/**
	 * 用户姓名
	 */
	private String name = null;

	/**
	 * 密码
	 */
	private String password = null;

	/**
	 * 是否启用
	 */
	private Integer isEnable = 1;

	/**
	 * 部门ID
	 */
	private Long orgId = null;

	private Org org = null;

	private String orgName = null;

	/**
	 * 用户类型，0标识超级管理员 1标识普通用户
	 */

	private Long groupId = null;

	/**
	 * 组名称集合
	 */
	private String groupName = null;

	/**
	 * 角色名称集合
	 */
	private String roleNames = null;

	private String roleCodes = null;

	private String resources = null;

	private List<Resource> resourceList = null;// 用户可以访问的资源

	private List<Resource> moduleResourceList = null;// 用户可以访问的模块

	/**
	 * 是否删除
	 */
	private Integer isDel = 0;

	private Long loginNum = 0l;

	private Integer userType = 0;

	private String type = null;

	private Long loginLogId = null;

	/* ===================合并进来的=================== */

	/**
	 * 员工编号
	 */
	private String emplid;

	/**
	 * 员工记录
	 */
	private Long emplRcd;

	/**
	 * 生效日期
	 */
	private Date effdt;

	/**
	 * 生效序号
	 */
	private Long effseq;

	/**
	 * 状态
	 */
	private String hlStatus;

	/**
	 * 姓名
	 */
	private String name1;

	/**
	 * 姓氏
	 */
	private String lastName;

	/**
	 * 名字
	 */
	private String firstName;

	/**
	 * 姓名拼音
	 */
	private String nameAc;

	/**
	 * 国家(地址)
	 */
	private String country;

	/**
	 * 地址
	 */
	private String hlAddress;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 县/市
	 */
	private String county;

	/**
	 * 省
	 */
	private String state;

	/**
	 * 邮政编码
	 */
	private String postal;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 婚姻状况
	 */
	private String marStatus;

	/**
	 * 结婚日期
	 */
	private Date marStatusDt;

	/**
	 * 出生日期
	 */
	private Date birthdate;

	/**
	 * 出生国家
	 */
	private String birthcountry;

	/**
	 * 出生省
	 */
	private String birthstate;

	/**
	 * 国家(电话)
	 */
	private String countryCode;

	/**
	 * 移动电话
	 */
	private String phoneMobile;

	/**
	 * 公司电话
	 */
	private String phoneCompany;

	/**
	 * 家庭电话
	 */
	private String phoneHome;

	/**
	 * 登录账号
	 */
	private String hlUser;

	/**
	 * 电子邮件地址
	 */
	private String emailAddr;

	/**
	 * 传真
	 */
	private String phoneFax;

	/**
	 * 操作
	 */
	private String action;

	/**
	 * 操作原因
	 */
	private String actionReason;

	/**
	 * 部门SETID
	 */
	private String setidDept;

	/**
	 * 职务SETID
	 */
	private String setidJobcode;

	/**
	 * 地点SETID
	 */
	private String setidLocation;

	/**
	 * 部门
	 */
	private String deptid;
	
	/**
	 * 标识职务的：大区助理、专员、副总经理、副总裁、经纪人、店经理、区域经理、营销总监、主管、经理、助理
	 */
	private String hljobseries;
	
	public String getHljobseries() {
		return hljobseries;
	}

	public void setHljobseries(String hljobseries) {
		this.hljobseries = hljobseries;
	}

	/**
	 * 是否是业务拓展中心
	 */
	private boolean isDevelopmentCenter;
	/**
	 * 是否是配置操作组
	 */
	private boolean isConfigureOperating;
	/**
	 * 部门name
	 */
	private String orgNames;
	/**
	 * 深度路径
	 */
	private String hlTreepath;

	/**
	 * 职务代码
	 */
	private String jobcode;
	

	/**
	 * 主管ID
	 */
	private String supervisorId;

	/**
	 * 薪资状态
	 */
	private String emplStatus;

	/**
	 * 地点
	 */
	private String location;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 审核通过
	 */
	private String hlAppCheck;

	/**
	 * 临时关闭业务系统
	 */
	private String hlTemClose;

	/**
	 * 最大生效日期 
	 */
	private Date effdtFrom;

	/**
	 * 时间戳 
	 */
	private Date hlOutDt;

	/**
	 * 全量增量标记
	 */
	private String hlGlsFlag;

	// Property accessors

	public String getEmplid() {
		return this.emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public Long getEmplRcd() {
		return this.emplRcd;
	}

	public void setEmplRcd(Long emplRcd) {
		this.emplRcd = emplRcd;
	}

	public Date getEffdt() {
		return this.effdt;
	}

	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}

	public Long getEffseq() {
		return this.effseq;
	}

	public void setEffseq(Long effseq) {
		this.effseq = effseq;
	}

	public String getHlStatus() {
		return this.hlStatus;
	}

	public void setHlStatus(String hlStatus) {
		this.hlStatus = hlStatus;
	}

	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNameAc() {
		return this.nameAc;
	}

	public void setNameAc(String nameAc) {
		this.nameAc = nameAc;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHlAddress() {
		return this.hlAddress;
	}

	public void setHlAddress(String hlAddress) {
		this.hlAddress = hlAddress;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMarStatus() {
		return this.marStatus;
	}

	public void setMarStatus(String marStatus) {
		this.marStatus = marStatus;
	}

	public Date getMarStatusDt() {
		return this.marStatusDt;
	}

	public void setMarStatusDt(Date marStatusDt) {
		this.marStatusDt = marStatusDt;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthcountry() {
		return this.birthcountry;
	}

	public void setBirthcountry(String birthcountry) {
		this.birthcountry = birthcountry;
	}

	public String getBirthstate() {
		return this.birthstate;
	}

	public void setBirthstate(String birthstate) {
		this.birthstate = birthstate;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneMobile() {
		return this.phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getPhoneCompany() {
		return this.phoneCompany;
	}

	public void setPhoneCompany(String phoneCompany) {
		this.phoneCompany = phoneCompany;
	}

	public String getPhoneHome() {
		return this.phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getHlUser() {
		return this.hlUser;
	}

	public void setHlUser(String hlUser) {
		this.hlUser = hlUser;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getPhoneFax() {
		return this.phoneFax;
	}

	public void setPhoneFax(String phoneFax) {
		this.phoneFax = phoneFax;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionReason() {
		return this.actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}

	public String getSetidDept() {
		return this.setidDept;
	}

	public void setSetidDept(String setidDept) {
		this.setidDept = setidDept;
	}

	public String getSetidJobcode() {
		return this.setidJobcode;
	}

	public void setSetidJobcode(String setidJobcode) {
		this.setidJobcode = setidJobcode;
	}

	public String getSetidLocation() {
		return this.setidLocation;
	}

	public void setSetidLocation(String setidLocation) {
		this.setidLocation = setidLocation;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getHlTreepath() {
		return this.hlTreepath;
	}

	public void setHlTreepath(String hlTreepath) {
		this.hlTreepath = hlTreepath;
	}

	public String getJobcode() {
		return this.jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public String getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getEmplStatus() {
		return this.emplStatus;
	}

	public void setEmplStatus(String emplStatus) {
		this.emplStatus = emplStatus;
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

	public String getHlAppCheck() {
		return this.hlAppCheck;
	}

	public void setHlAppCheck(String hlAppCheck) {
		this.hlAppCheck = hlAppCheck;
	}

	public String getHlTemClose() {
		return this.hlTemClose;
	}

	public void setHlTemClose(String hlTemClose) {
		this.hlTemClose = hlTemClose;
	}

	public Date getEffdtFrom() {
		return this.effdtFrom;
	}

	public void setEffdtFrom(Date effdtFrom) {
		this.effdtFrom = effdtFrom;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public List<Resource> getModuleResourceList() {
		return moduleResourceList;
	}

	public void setModuleResourceList(List<Resource> moduleResourceList) {
		this.moduleResourceList = moduleResourceList;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Long getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Long loginNum) {
		this.loginNum = loginNum;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(Long loginLogId) {
		this.loginLogId = loginLogId;
	}
	
	public boolean getIsDevelopmentCenter() {
		return isDevelopmentCenter ;
	}

	
	public void setIsDevelopmentCenter(boolean isDevelopmentCenter) {
		this.isDevelopmentCenter = isDevelopmentCenter ;
	}

	
	public boolean getIsConfigureOperating() {
		return isConfigureOperating ;
	}

	
	public void setIsConfigureOperating(boolean isConfigureOperating) {
		this.isConfigureOperating = isConfigureOperating ;
	}
	
	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	
}
