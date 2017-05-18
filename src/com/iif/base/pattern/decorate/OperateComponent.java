package com.iif.base.pattern.decorate;

/**
 * 抽象构建角色
 * 
 * @author LiuM
 * 
 */
public interface OperateComponent<T> {

	/**
	 * 
	 * @return
	 */
	T operation();

}
