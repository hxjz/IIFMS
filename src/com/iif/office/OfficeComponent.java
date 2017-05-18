package com.iif.office;

import java.io.File;

/**
 * office组件处理接口
 * 
 * @author LiuM
 * 
 */
public interface OfficeComponent<T> {

	/**
	 * 创建组件
	 * 
	 * @param file
	 */
	void create(File file);

	/**
	 * 读取组件
	 * 
	 * @param file
	 * @return
	 */
	ResultEntity<T> readForList(File file);

}
