package com.iif.orgMgt.entity;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class PostEmployee extends BaseEntity{

	private static final long serialVersionUID = 404681811803171990L;
	
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

	static public final String POST_CODE = "post";
	private Post post;

	/**
	 *设置岗位的值
	 *@param post 新的岗位的值
	 */
	public void setPost(Post _post) {
		post = _post;
	}

	/**
	 *获取岗位的值
	 *@return 岗位的值
	 */
	public Post getPost() {
		return post;
	}

	static public final String EMPLOYEE_CODE = "employee";
	private Employee employee;

	/**
	 *设置任职人员的值
	 *@param employee 新的任职人员的值
	 */
	public void setEmployee(Employee _employee) {
		employee = _employee;
	}

	/**
	 *获取任职人员的值
	 *@return 任职人员的值
	 */
	public Employee getEmployee() {
		return employee;
	}

	static public final String DEFTPOST_CODE = "deftPost";

	private boolean deftPost;

	/**
	 * 获取人员默认岗位的值
	 * @return 人员默认岗位的值
	 */
	public boolean getDeftPost() {

		return deftPost;
	}

	/**
	 * 设置人员默认岗位的值
	 * @param deftPost boolean 新的人员默认岗位的值
	 */
	public void setDeftPost(boolean _deftPost) {

		deftPost = _deftPost;
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

	public String toString() {
		StringBuffer toString = new StringBuffer();
		Object toStringTemp = null;
		try {

			toStringTemp = PropertyUtils.getProperty(this, "company");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);
			toString.append("-");

			toStringTemp = PropertyUtils.getProperty(this, "department");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);
			toString.append("-");

			toStringTemp = PropertyUtils.getProperty(this, "post");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);
			toString.append("-");

			toStringTemp = PropertyUtils.getProperty(this, "employee");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);

		} catch (Exception e) {

		}
		return toString.toString();
	}

}//class_end

