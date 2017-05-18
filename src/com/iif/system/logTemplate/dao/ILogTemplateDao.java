package com.iif.system.logTemplate.dao;

import java.util.List;

import com.hxjz.common.core.orm.IBaseDao;

public interface ILogTemplateDao extends IBaseDao{

	/**
	 * 获取所有有效日志模板内容列表
	 * @return
	 */
	public abstract List findAllLogTemplate();

}