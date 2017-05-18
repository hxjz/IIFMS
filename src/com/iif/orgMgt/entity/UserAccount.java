package com.iif.orgMgt.entity;

import java.util.Date;
import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;
import com.iif.system.resource.entity.Resource;

public class UserAccount extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8060018138428638915L;

	/**
	 * 角色名称集合
	 */
	private String roleNames = null;

	private String roleCodes = null;

	private String resources = null;

	transient private List<Resource> resourceList = null;// 用户可以访问的资源

	transient private List<Resource> moduleResourceList = null;// 用户可以访问的模块

	private List<Resource> noAuthResource = null;// 不可访问的资源

	transient private List<IffUserRole> userRoles;// 角色集合

	public List<Resource> getNoAuthResource() {
		return noAuthResource;
	}

	public void setNoAuthResource(List<Resource> noAuthResource) {
		this.noAuthResource = noAuthResource;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
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

	public static String getUseraccountCode() {
		return USERACCOUNT_CODE;
	}

	public static String getPasswordCode() {
		return PASSWORD_CODE;
	}

	public static String getIsadminCode() {
		return ISADMIN_CODE;
	}

	/*
	 * public static String getLinkemployeeCode() { return LINKEMPLOYEE_CODE; }
	 */
	public static String getUpdpswdateCode() {
		return UPDPSWDATE_CODE;
	}

	public static String getRequireupdatepwCode() {
		return REQUIREUPDATEPW_CODE;
	}

	public static String getLastlogindateCode() {
		return LASTLOGINDATE_CODE;
	}

	public static String getDatabelongCode() {
		return DATABELONG_CODE;
	}

	public static String getLastloginipCode() {
		return LASTLOGINIP_CODE;
	}

	public static String getLoginfailtimeCode() {
		return LOGINFAILTIME_CODE;
	}

	public static String getAcctlocktimeCode() {
		return ACCTLOCKTIME_CODE;
	}

	public static String getAcctstatusCode() {
		return ACCTSTATUS_CODE;
	}

	public void setAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserAccount() {
	}

	public UserAccount(String _id) {
		this();
	//setId(_id);
	}
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	static public final String USERACCOUNT_CODE = "userAccount";

	private String userAccount;

	/**
	 * 获取用户账号的值
	 * 
	 * @return 用户账号的值
	 */
	public String getUserAccount() {

		return userAccount;
	}

	/**
	 * 设置用户账号的值
	 * 
	 * @param userAccount
	 *            String 新的用户账号的值
	 */
	public void setUserAccount(String _userAccount) {

		userAccount = _userAccount;
	}

	static public final String PASSWORD_CODE = "password";

	private String password;

	/**
	 * 获取密码的值
	 * 
	 * @return 密码的值
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * 设置密码的值
	 * 
	 * @param password
	 *            String 新的密码的值
	 */
	public void setPassword(String _password) {

		password = _password;
	}

	static public final String ISADMIN_CODE = "isAdmin";

	private Integer isAdmin = 0;

	/**
	 * 获取系统管理员的值
	 * 
	 * @return 系统管理员的值
	 */
	public Integer getIsAdmin() {

		return isAdmin;
	}

	/**
	 * 设置系统管理员的值
	 * 
	 * @param isAdmin
	 *            boolean 新的系统管理员的值
	 */
	public void setIsAdmin(Integer _isAdmin) {

		isAdmin = _isAdmin;
	}

	// static public final String LINKEMPLOYEE_CODE = "linkEmployee";
	// private Employee linkEmployee;

	/**
	 * 设置对应员工的值
	 *
	 * @param linkEmployee
	 *            新的对应员工的值
	 */
	/*
	 * public void setLinkEmployee(Employee _linkEmployee) { linkEmployee =
	 * _linkEmployee; }
	 */

	/**
	 * 获取对应员工的值
	 *
	 * @return 对应员工的值
	 */
	/*
	 * public Employee getLinkEmployee() { return linkEmployee; }
	 */

	static public final String UPDPSWDATE_CODE = "updPswDate";

	private Date updPswDate;

	/**
	 * 获取上次修改密码日期的值
	 * 
	 * @return 上次修改密码日期的值
	 */
	public Date getUpdPswDate() {

		return updPswDate;
	}

	/**
	 * 设置上次修改密码日期的值
	 * 
	 * @param updPswDate
	 *            Date 新的上次修改密码日期的值
	 */
	public void setUpdPswDate(Date _updPswDate) {

		updPswDate = _updPswDate;
	}

	static public final String REQUIREUPDATEPW_CODE = "requireUpdatePw";

	private Integer requireUpdatePw = 0;

	/**
	 * 获取需重设密码的值
	 * 
	 * @return 需重设密码的值
	 */
	public Integer getRequireUpdatePw() {

		return requireUpdatePw;
	}

	/**
	 * 设置需重设密码的值
	 * 
	 * @param requireUpdatePw
	 *            boolean 新的需重设密码的值
	 */
	public void setRequireUpdatePw(Integer _requireUpdatePw) {

		requireUpdatePw = _requireUpdatePw;
	}

	static public final String LASTLOGINDATE_CODE = "lastLoginDate";

	private Date lastLoginDate;

	/**
	 * 获取上次登录时间的值
	 * 
	 * @return 上次登录时间的值
	 */
	public Date getLastLoginDate() {

		return lastLoginDate;
	}

	/**
	 * 设置上次登录时间的值
	 * 
	 * @param lastLoginDate
	 *            Date 新的上次登录时间的值
	 */
	public void setLastLoginDate(Date _lastLoginDate) {

		lastLoginDate = _lastLoginDate;
	}

	static public final String DATABELONG_CODE = "dataBelong";

	private String dataBelong;

	/**
	 * 获取所属单位的值
	 * 
	 * @return 所属单位的值
	 */
	public String getDataBelong() {

		return dataBelong;
	}

	/**
	 * 设置所属单位的值
	 * 
	 * @param dataBelong
	 *            String 新的所属单位的值
	 */
	public void setDataBelong(String _dataBelong) {

		dataBelong = _dataBelong;
	}

	static public final String LASTLOGINIP_CODE = "lastLoginIP";

	private String lastLoginIP;

	/**
	 * 获取上次登录IP的值
	 * 
	 * @return 上次登录IP的值
	 */
	public String getLastLoginIP() {

		return lastLoginIP;
	}

	/**
	 * 设置上次登录IP的值
	 * 
	 * @param lastLoginIP
	 *            String 新的上次登录IP的值
	 */
	public void setLastLoginIP(String _lastLoginIP) {

		lastLoginIP = _lastLoginIP;
	}

	static public final String LOGINFAILTIME_CODE = "loginFailTime";

	private Integer loginFailTime = 0;

	/**
	 * 获取登录失败次数的值
	 * 
	 * @return 登录失败次数的值
	 */
	public Integer getLoginFailTime() {

		return loginFailTime;
	}

	/**
	 * 设置登录失败次数的值
	 * 
	 * @param loginFailTime
	 *            Integer 新的登录失败次数的值
	 */
	public void setLoginFailTime(Integer _loginFailTime) {

		loginFailTime = _loginFailTime;
	}

	static public final String ACCTLOCKTIME_CODE = "acctLockTime";

	private Date acctLockTime;

	/**
	 * 获取账号锁定时间的值
	 * 
	 * @return 账号锁定时间的值
	 */
	public Date getAcctLockTime() {

		return acctLockTime;
	}

	/**
	 * 设置账号锁定时间的值
	 * 
	 * @param acctLockTime
	 *            Date 新的账号锁定时间的值
	 */
	public void setAcctLockTime(Date _acctLockTime) {

		acctLockTime = _acctLockTime;
	}

	static public final String ACCTSTATUS_CODE = "acctStatus";

	private Integer acctStatus = 0;

	/**
	 * 获取账号状态的值
	 * 
	 * @return 账号状态的值
	 */
	public Integer getAcctStatus() {

		return acctStatus;
	}

	/**
	 * 设置账号状态的值
	 * 
	 * @param acctStatus
	 *            Integer 新的账号状态的值
	 */
	public void setAcctStatus(Integer _acctStatus) {
		acctStatus = _acctStatus;
	}

	public List<IffUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<IffUserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * 非数据库信息、临时存储
	 */
	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	/*private String linkEmployeeId;

	public String getLinkEmployeeId() {
		return linkEmployeeId;
	}

	public void setLinkEmployeeId(String linkEmployeeId) {
		this.linkEmployeeId = linkEmployeeId;
	}*/
	private Employee linkEmployeeId;
	
	public Employee getLinkEmployeeId() {
		return linkEmployeeId;
	}

	public void setLinkEmployeeId(Employee linkEmployeeId) {
		this.linkEmployeeId = linkEmployeeId;
	}
}// class_end

