package com.iif.system.logTemplate.entity;

import java.util.Date;

import com.hxjz.common.core.orm.BaseEntity;

/**
 * 日志模板实体类
 * 
 * @author rxw
 * @2013-8-14
 */
public class LogTemplate extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8316245820772477749L;


	public LogTemplate() {
		super();
	}

	/**
	 * id
	 */
	private String id;

	/**
	 * 操作类型
	 */
	private int dataType;
	
	/**
	 * 处理类型
	 */
	private int dealType;

	/**
	 * 模板
	 */
	private String template;
	
	/**
	 * 创建人
	 */
	//private Employee creator;

	/**
	 * 创建日期
	 */
	private Date createTime;

	/**
	 * 修改人
	 */
	//private Employee modifier;

	/**
	 * 修改日期
	 */
	private Date modifyTime;

	/**
	 * 是否删除
	 */
	private Integer isDel = 0;
	
	/**
	 * 开关
	 */
	private Integer isOpen = 0;
	

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	/*public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}
*/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/*public Employee getModifier() {
		return modifier;
	}

	public void setModifier(Employee modifier) {
		this.modifier = modifier;
	}*/

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	

}