package com.iif.system.log.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;
import com.hxjz.common.utils.Page;

public interface IOperationLogDao extends IBaseDao{

	/**
	 * 分页方式查询操作日志
	 * @param page
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract List findByPage(Page page, Map searchMap);

}