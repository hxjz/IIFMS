package com.iif.system.log.entity;

import java.util.Date;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 操作日志
 * 
 * @author thinkpad
 * @data Dec 2, 2009
 */
public class OperationLog extends BaseEntity {

	private Long id = null;

	/**
	 * 登录
	 */
	private Long loginLogId;

	private LoginLog loginLog;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 业务名称
	 */
	private String businessName;

	/**
	 * 操作名称
	 */
	private String operationName;

	/**
	 * 操作时间
	 */
	private Date operationTime;

	/**
	 * 是否成功
	 */
	private Integer isSuccess = 0;

	/**
	 * 部门名称
	 */
	private String orgName;

	/**
	 * 操作类型编码
	 */
	private String operationTypeCode;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 实体名
	 */
	private String entityName;

	/**
	 * 主键名称
	 */
	private String idName;

	private String idValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
	}

	public Long getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(Long loginLogId) {
		this.loginLogId = loginLogId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getOperationTypeCode() {
		return operationTypeCode;
	}

	public void setOperationTypeCode(String operationTypeCode) {
		this.operationTypeCode = operationTypeCode;
	}
}