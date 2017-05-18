package com.iif.base.entity;

/**
 * SELECT 下拉菜单 OPTION
 * 
 * @author LiuM
 * 
 */
public class Options {

	private Object key;

	private Object value;

	public Options() {
	}

	public Options(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}