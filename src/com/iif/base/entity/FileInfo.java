package com.iif.base.entity;

public class FileInfo {

	/**
	 * 页面中显示的
	 */
	private String url;

	/**
	 * 绝对路径
	 */
	private String absolutePath;

	/**
	 * 相对路径
	 */
	private String relativePath;

	/**
	 * 大小
	 */
	private long size;

	public FileInfo() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}