package com.iif.system.log.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 登录日志
 * 
 * @author thinkpad
 * @data Dec 2, 2009
 */
public class LoginLog extends BaseEntity {

	/**
	 * id
	 */
	private Long id = null;

	/**
	 * 登陆用户名
	 */
	private String userName;

	/**
	 * 登陆IP
	 */

	private String loginIp;

	/**
	 * 登陆时间
	 */

	private Date loginTime;

	/**
	 * 登出时间
	 */

	private Date logoutTime;

	/**
	 * 部门名称
	 */

	private String orgName;

	/**
	 * 操作日志Set
	 */

	private Set operationLogs = new HashSet(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Set getOperationLogs() {
		return operationLogs;
	}

	public void setOperationLogs(Set operationLogs) {
		this.operationLogs = operationLogs;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}