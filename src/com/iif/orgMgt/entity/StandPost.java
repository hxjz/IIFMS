package com.iif.orgMgt.entity;

import org.apache.commons.beanutils.PropertyUtils;

import com.hxjz.common.core.orm.BaseEntity;

public class StandPost extends BaseEntity {

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	static public final String CODE_CODE = "code";
	
	private String code;

	/**
	 * 获取编码的值
	 * @return 编码的值
	 */
	public String getCode() {

		return code;
	}

	/**
	 * 设置编码的值
	 * @param code String 新的编码的值
	 */
	public void setCode(String _code) {

		code = _code;
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

	static public final String DESC_CODE = "desc";

	private String desc;

	/**
	 * 获取描述的值
	 * @return 描述的值
	 */
	public String getDesc() {

		return desc;
	}

	/**
	 * 设置描述的值
	 * @param desc String 新的描述的值
	 */
	public void setDesc(String _desc) {

		desc = _desc;
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

