package com.iif.orgMgt.entity;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class Company extends BaseEntity {
	
	private static final long serialVersionUID = -6543802777407027625L;

	public Company() {
	}

	public Company(String _id) {
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

	static public final String NAME_CODE = "name";

	private String name;

	/**
	 * 获取名称的值
	 * @return 名称的值
	 */
	public String getName() {

		return name;
	}

	/**
	 * 设置名称的值
	 * @param name String 新的名称的值
	 */
	public void setName(String _name) {

		name = _name;
	}

	static public final String NAMEAB_CODE = "nameAb";

	private String nameAb;

	/**
	 * 获取简称的值
	 * @return 简称的值
	 */
	public String getNameAb() {

		return nameAb;
	}

	/**
	 * 设置简称的值
	 * @param nameAb String 新的简称的值
	 */
	public void setNameAb(String _nameAb) {

		nameAb = _nameAb;
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

	static public final String LINKMAN_CODE = "linkman";

	private String linkman;

	/**
	 * 获取联系人的值
	 * @return 联系人的值
	 */
	public String getLinkman() {

		return linkman;
	}

	/**
	 * 设置联系人的值
	 * @param linkman String 新的联系人的值
	 */
	public void setLinkman(String _linkman) {

		linkman = _linkman;
	}

	static public final String TELNO_CODE = "telNo";

	private String telNo;

	/**
	 * 获取电话的值
	 * @return 电话的值
	 */
	public String getTelNo() {

		return telNo;
	}

	/**
	 * 设置电话的值
	 * @param telNo String 新的电话的值
	 */
	public void setTelNo(String _telNo) {

		telNo = _telNo;
	}

	static public final String FAXNO_CODE = "faxNo";

	private String faxNo;

	/**
	 * 获取传真的值
	 * @return 传真的值
	 */
	public String getFaxNo() {

		return faxNo;
	}

	/**
	 * 设置传真的值
	 * @param faxNo String 新的传真的值
	 */
	public void setFaxNo(String _faxNo) {

		faxNo = _faxNo;
	}

	private String address_DL;

	public void setAddress_DL(String _address_DL) {
		address_DL = _address_DL;
	}

	public String getAddress_DL() {
		return address_DL;
	}

	static public final String ADDRESS_CODE = "address";

	static public final String ZIPCODE_CODE = "zipCode";

	private String zipCode;

	/**
	 * 获取邮编的值
	 * @return 邮编的值
	 */
	public String getZipCode() {

		return zipCode;
	}

	/**
	 * 设置邮编的值
	 * @param zipCode String 新的邮编的值
	 */
	public void setZipCode(String _zipCode) {

		zipCode = _zipCode;
	}

	static public final String HTTPADDRESS_CODE = "httpAddress";

	private String httpAddress;

	/**
	 * 获取网址的值
	 * @return 网址的值
	 */
	public String getHttpAddress() {

		return httpAddress;
	}

	/**
	 * 设置网址的值
	 * @param httpAddress String 新的网址的值
	 */
	public void setHttpAddress(String _httpAddress) {

		httpAddress = _httpAddress;
	}

	static public final String EMAIL_CODE = "email";

	private String email;

	/**
	 * 获取E_mail的值
	 * @return E_mail的值
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * 设置E_mail的值
	 * @param email String 新的E_mail的值
	 */
	public void setEmail(String _email) {

		email = _email;
	}

	static public final String LOGO_CODE = "logo";

	private byte[] logo;

	/**
	 * 获取公司标识图片文件的值
	 * @return 公司标识图片文件的值
	 */
	public byte[] getLogo() {

		return logo;
	}

	/**
	 * 设置公司标识图片文件的值
	 * @param logo byte[] 新的公司标识图片文件的值
	 */
	public void setLogo(byte[] _logo) {

		logo = _logo;
	}

	static public final String VALID_CODE = "valid";

	private boolean valid;

	/**
	 * 获取本记录有效的值
	 * @return 本记录有效的值
	 */
	public boolean getValid() {

		return valid;
	}

	/**
	 * 设置本记录有效的值
	 * @param valid boolean 新的本记录有效的值
	 */
	public void setValid(boolean _valid) {

		valid = _valid;
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

