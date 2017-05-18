package com.iif.orgMgt.entity;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class Post extends BaseEntity {
	
	private static final long serialVersionUID = -2672591016038444600L;

	public Post() {

	}

	public Post(String _id) {
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

	static public final String COMPANY_CODE = "company";
	private Company company;

	/**
	 *设置单位的值
	 *@param company 新的单位的值
	 */
	public void setCompany(Company _company) {
		company = _company;
	}

	/**
	 *获取单位的值
	 *@return 单位的值
	 */
	public Company getCompany() {
		return company;
	}

	static public final String DEPARTMENT_CODE = "department";
	private Department department;

	/**
	 *设置部门的值
	 *@param department 新的部门的值
	 */
	public void setDepartment(Department _department) {
		department = _department;
	}

	/**
	 *获取部门的值
	 *@return 部门的值
	 */
	public Department getDepartment() {
		return department;
	}

	static public final String NAME_CODE = "name";

	private String name;

	/**
	 * 获取岗位名称的值
	 * @return 岗位名称的值
	 */
	public String getName() {

		return name;
	}

	/**
	 * 设置岗位名称的值
	 * @param name String 新的岗位名称的值
	 */
	public void setName(String _name) {

		name = _name;
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
	 * @param hint String 新的提示码的值
	 */
	public void setHint(String _hint) {

		hint = _hint;
	}

	private String responsibility_DL;

	public void setResponsibility_DL(String _responsibility_DL) {
		responsibility_DL = _responsibility_DL;
	}

	public String getResponsibility_DL() {
		return responsibility_DL;
	}

	static public final String RESPONSIBILITY_CODE = "responsibility";

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

	static public final String CODE_CODE = "code";

	private String code;

	/**
	 * 获取编号的值
	 * @return 编号的值
	 */
	public String getCode() {

		return code;
	}

	/**
	 * 设置编号的值
	 * @param code String 新的编号的值
	 */
	public void setCode(String _code) {

		code = _code;
	}

	static public final String STANDPOST_CODE = "standPost";
	private StandPost standPost;

	/**
	 *设置所属标准岗位的值
	 *@param standPost 新的所属标准岗位的值
	 */
	public void setStandPost(StandPost _standPost) {
		standPost = _standPost;
	}

	/**
	 *获取所属标准岗位的值
	 *@return 所属标准岗位的值
	 */
	public StandPost getStandPost() {
		return standPost;
	}

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

