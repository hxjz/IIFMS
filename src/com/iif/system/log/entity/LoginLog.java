package com.iif.system.log.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 登录日志
 * 
 * @author LiuM
 * @date 2017
 */
public class LoginLog extends BaseEntity {
	
	private static final long serialVersionUID = 6599185557304784270L;

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

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
	public Set getOperationLogs() {
		return operationLogs;
	}

	@SuppressWarnings("rawtypes")
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