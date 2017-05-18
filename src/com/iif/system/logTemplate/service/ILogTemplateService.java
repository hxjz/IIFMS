package com.iif.system.logTemplate.service;

import com.hxjz.common.core.orm.IBaseService;

public interface ILogTemplateService extends IBaseService{

	/**
	 * 初始化日志模板，放入Redis缓存
	 */
	public abstract void initLogTemplate();

}