package com.iif.orgMgt.entity;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class Department extends BaseEntity {

	private static final long serialVersionUID = 6655776249321661237L;

	public Department() {

	}

	public Department(String _id) {
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

	static public final String ADDRESS_CODE = "address";

	private String address;

	/**
	 * 获取部门地址的值
	 * @return 部门地址的值
	 */
	public String getAddress() {

		return address;
	}

	/**
	 * 设置部门地址的值
	 * @param address String 新的部门地址的值
	 */
	public void setAddress(String _address) {

		address = _address;
	}

	static public final String COMPANY_CODE = "company";
	private String company;

	/**
	 *设置单位的值
	 *@param company 新的单位的值
	 */
	public void setCompany(String _company) {
		company = _company;
	}

	/**
	 *获取单位的值
	 *@return 单位的值
	 */
	public String getCompany() {
		return company;
	}

	static public final String HINT_CODE = "hint";

	private String hint;

	/**
	 * 获取提示码的值
	 * @return 提示码的值
	 */
	public String getHint() {

		return hint;
	}

	/**
	 * 设置提示码的值
	 * @param hInteger String 新的提示码的值
	 */
	public void setHint(String _hint) {

		hint = _hint;
	}

	static public final String NAME_CODE = "name";

	private String name;

	/**
	 * 获取部门名称的值
	 * @return 部门名称的值
	 */
	public String getName() {

		return name;
	}

	/**
	 * 设置部门名称的值
	 * @param name String 新的部门名称的值
	 */
	public void setName(String _name) {

		name = _name;
	}

	static public final String RESPONSIBILITY_CODE = "responsibility";

	private String responsibility;

	/**
	 * 获取部门职责的值
	 * @return 部门职责的值
	 */
	public String getResponsibility() {

		return responsibility;
	}

	/**
	 * 设置部门职责的值
	 * @param responsibility String 新的部门职责的值
	 */
	public void setResponsibility(String _responsibility) {

		responsibility = _responsibility;
	}

	static public final String TELNO_CODE = "telNo";

	private String telNo;

	/**
	 * 获取部门联系电话的值
	 * @return 部门联系电话的值
	 */
	public String getTelNo() {

		return telNo;
	}

	/**
	 * 设置部门联系电话的值
	 * @param telNo String 新的部门联系电话的值
	 */
	public void setTelNo(String _telNo) {

		telNo = _telNo;
	}

	static public final String VALID_CODE = "valid";

	private boolean valid;

	/**
	 * 获取记录有效的值
	 * @return 记录有效的值
	 */
	public boolean getValid() {

		return valid;
	}

	/**
	 * 设置记录有效的值
	 * @param valid boolean 新的记录有效的值
	 */
	public void setValid(boolean _valid) {

		valid = _valid;
	}

	static public final String MAINPOST_CODE = "mainPost";
	private Post mainPost;

	/**
	 *设置负责人岗位的值
	 *@param mainPost 新的负责人岗位的值
	 */
	public void setMainPost(Post _mainPost) {
		mainPost = _mainPost;
	}

	/**
	 *获取负责人岗位的值
	 *@return 负责人岗位的值
	 */
	public Post getMainPost() {
		return mainPost;
	}

	/*static public final String HL_DEPT_CODE = "hlDept";
	private HlDeptStgMainStruInfo hlDept;

	*//**
	 *设置HR部门数据类的值
	 *@param hlDept 新的HR部门数据类的值
	 *//*
	public void setHlDept(HlDeptStgMainStruInfo _hlDept) {
		hlDept = _hlDept;
	}

	*//**
	 *获取HR部门数据类的值
	 *@return HR部门数据类的值
	 *//*
	public HlDeptStgMainStruInfo getHlDept() {
		return hlDept;
	}*/

	public String toString() {
		StringBuffer toString = new StringBuffer();
		Object toStringTemp = null;
		try {

			toStringTemp = PropertyUtils.getProperty(this, "name");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);

		} catch (Exception e) {

		}
		return toString.toString();
	}

}//class_end

