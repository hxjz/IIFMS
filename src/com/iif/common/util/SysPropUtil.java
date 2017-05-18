package com.iif.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.Assert;

/**
 * 读取系统变量工具
 * @author Tanght
 * @2014-9-18
 */
public class SysPropUtil {
	public static Properties constants;// 系统变量存放区
	/**
	 * 获取系统变量(默认为system.properties)
	 * @param constantName 属性名称
	 * @return
	 */
	public static String getSystemConstant(String constantName) {
		return getSystemConstant("system.properties", constantName);
	}

	/**
	 * 获取系统变量
	 * @param propertyName properties的名称
	 * @param constantName 属性名称
	 * @return value
	 */
	public static String getSystemConstant(String propertyName, String constantName) {
		String property = null;
		if (null == constants) {
			initSystemProperties(propertyName);
		}
		property = constants.getProperty(constantName);
		Assert.notNull(property, "系统变量" + constantName + "不存在");
		return property;
	}
	
	/**
	 * 初始化系统属性文件
	 */
	private static void initSystemProperties(String propertyName) {
		constants = new Properties();
		ClassLoader cl = SysPropUtil.class.getClassLoader();
		// 加载数据字典分类初始化文件
		InputStream stream = cl.getResourceAsStream(propertyName);
		try {
			constants.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
