package com.iif.system.log.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;
import com.hxjz.common.utils.Page;
import com.iif.system.log.entity.LoginLog;

public interface ILoginLogDao extends IBaseDao{

	/**
	 * 分页查询登陆日志
	 * @param page
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract List findByPage(Page page, Map searchMap);

	/**
	 * 同步方法，JDBC方式保存日志信息
	 * @param loginLog
	 */
	public abstract void saveForJdbc(LoginLog loginLog);

}