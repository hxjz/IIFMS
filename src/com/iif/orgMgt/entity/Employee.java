package com.iif.orgMgt.entity;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class Employee extends BaseEntity {

	private static final long serialVersionUID = -8392466148668776949L ;

	public Employee() {

	}

	public Employee(String _id) {
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

	private String code;

	/**
	 * 获取人员编号的值
	 * @return 人员编号的值
	 */
	public String getCode() {

		return code;
	}

	/**
	 * 设置人员编号的值
	 * @param code String 新的人员编号的值
	 */
	public void setCode(String _code) {

		code = _code;
	}

	static public final String NAME_CODE = "name";

	private String name;

	/**
	 * 获取人员名称的值
	 * @return 人员名称的值
	 */
	public String getName() {

		return name;
	}

	/**
	 * 设置人员名称的值
	 * @param name String 新的人员名称的值
	 */
	public void setName(String _name) {

		name = _name;
	}

	static public final String SEX_CODE = "sex";

	private Integer sex = 0;

	/**
	 * 获取性别的值
	 * @return 性别的值
	 */
	public Integer getSex() {

		return sex;
	}

	/**
	 * 设置性别的值
	 * @param sex Integer 新的性别的值
	 */
	public void setSex(Integer _sex) {

		sex = _sex;
	}

	static public final String IDCARDNO_CODE = "idCardNo";

	private String idCardNo;

	/**
	 * 获取身份证号的值
	 * @return 身份证号的值
	 */
	public String getIdCardNo() {

		return idCardNo;
	}

	/**
	 * 设置身份证号的值
	 * @param idCardNo String 新的身份证号的值
	 */
	public void setIdCardNo(String _idCardNo) {

		idCardNo = _idCardNo;
	}

	static public final String BIRTHDAY_CODE = "birthday";

	private Date birthday;

	/**
	 * 获取出生日期的值
	 * @return 出生日期的值
	 */
	public Date getBirthday() {

		return birthday;
	}

	/**
	 * 设置出生日期的值
	 * @param birthday Date 新的出生日期的值
	 */
	public void setBirthday(Date _birthday) {

		birthday = _birthday;
	}

	static public final String EMAIL_CODE = "email";

	private String email;

	/**
	 * 获取电子邮件的值
	 * @return 电子邮件的值
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * 设置电子邮件的值
	 * @param email String 新的电子邮件的值
	 */
	public void setEmail(String _email) {

		email = _email;
	}

	static public final String MOBILE_CODE = "mobile";

	private String mobile;

	/**
	 * 获取移动电话的值
	 * @return 移动电话的值
	 */
	public String getMobile() {

		return mobile;
	}

	/**
	 * 设置移动电话的值
	 * @param mobile String 新的移动电话的值
	 */
	public void setMobile(String _mobile) {

		mobile = _mobile;
	}

	static public final String TELNO_CODE = "telNo";

	private String telNo;

	/**
	 * 获取办公电话的值
	 * @return 办公电话的值
	 */
	public String getTelNo() {

		return telNo;
	}

	/**
	 * 设置办公电话的值
	 * @param telNo String 新的办公电话的值
	 */
	public void setTelNo(String _telNo) {

		telNo = _telNo;
	}

	static public final String FAXNO_CODE = "faxNo";

	private String faxNo;

	/**
	 * 获取传真号码的值
	 * @return 传真号码的值
	 */
	public String getFaxNo() {

		return faxNo;
	}

	/**
	 * 设置传真号码的值
	 * @param faxNo String 新的传真号码的值
	 */
	public void setFaxNo(String _faxNo) {

		faxNo = _faxNo;
	}

	static public final String QQNO_CODE = "qqNo";

	private String qqNo;

	/**
	 * 获取QQ号码的值
	 * @return QQ号码的值
	 */
	public String getQqNo() {

		return qqNo;
	}

	/**
	 * 设置QQ号码的值
	 * @param qqNo String 新的QQ号码的值
	 */
	public void setQqNo(String _qqNo) {

		qqNo = _qqNo;
	}

	static public final String MSNNO_CODE = "msnNo";

	private String msnNo;

	/**
	 * 获取MSN号码的值
	 * @return MSN号码的值
	 */
	public String getMsnNo() {

		return msnNo;
	}

	/**
	 * 设置MSN号码的值
	 * @param msnNo String 新的MSN号码的值
	 */
	public void setMsnNo(String _msnNo) {

		msnNo = _msnNo;
	}

	private String address_DL;

	public void setAddress_DL(String _address_DL) {
		address_DL = _address_DL;
	}

	public String getAddress_DL() {
		return address_DL;
	}

	static public final String ADDRESS_CODE = "address";

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	static public final String ZIPCODE_CODE = "zipCode";

	private String zipCode;

	/**
	 * 获取邮政编码的值
	 * @return 邮政编码的值
	 */
	public String getZipCode() {

		return zipCode;
	}

	/**
	 * 设置邮政编码的值
	 * @param zipCode String 新的邮政编码的值
	 */
	public void setZipCode(String _zipCode) {

		zipCode = _zipCode;
	}

	static public final String MEMO_CODE = "memo";

	private String memo;

	/**
	 * 获取备注的值
	 * @return 备注的值
	 */
	public String getMemo() {

		return memo;
	}

	/**
	 * 设置备注的值
	 * @param memo String 新的备注的值
	 */
	public void setMemo(String _memo) {

		memo = _memo;
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

	static public final String CSRTYPE_CODE = "csrType";

	private Integer csrType;

	/**
	 * 获取用户CC账号类型的值
	 * @return 用户CC账号类型的值
	 */
	public Integer getCsrType() {

		return csrType;
	}

	/**
	 * 设置用户CC账号类型的值
	 * @param csrType Integer 新的用户CC账号类型的值
	 */
	public void setCsrType(Integer _csrType) {

		csrType = _csrType;
	}

	static public final String CICUSERID_CODE = "cicUserId";

	private String cicUserId;

	/**
	 * 获取CIC账号的值
	 * @return CIC账号的值
	 */
	public String getCicUserId() {

		return cicUserId;
	}

	/**
	 * 设置CIC账号的值
	 * @param cicUserId String 新的CIC账号的值
	 */
	public void setCicUserId(String _cicUserId) {

		cicUserId = _cicUserId;
	}

	static public final String CICPWD_CODE = "cicPwd";

	private String cicPwd;

	/**
	 * 获取CIC账号的密码的值
	 * @return CIC账号的密码的值
	 */
	public String getCicPwd() {

		return cicPwd;
	}

	/**
	 * 设置CIC账号的密码的值
	 * @param cicPwd String 新的CIC账号的密码的值
	 */
	public void setCicPwd(String _cicPwd) {

		cicPwd = _cicPwd;
	}

	public String toString() {
		StringBuffer toString = new StringBuffer();
		Object toStringTemp = null;
		try {

			toStringTemp = PropertyUtils.getProperty(this, "code");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);
			toString.append("-");

			toStringTemp = PropertyUtils.getProperty(this, "name");
			if (toStringTemp == null) {
				toStringTemp = "";
			}
			toString.append(toStringTemp);

		} catch (Exception e) {

		}
		return toString.toString();
	}

	/**
	 * 部门（直接所属）
	 */
	transient private Department department;
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

}
