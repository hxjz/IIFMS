package com.iif.system.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.hxjz.common.utils.Page;
import com.iif.system.log.dao.ILoginLogDao;
import com.iif.system.log.entity.LoginLog;
import com.iif.system.log.service.ILoginLogService;

/**
 * <p>@desc : 登陆日志Service </p>
 * <p>@see : </p>
 *
 * <p>@author : LiuM</p>
 * <p>@createDate : 2014-08-28</p>
 * <p>@version : v1.0 </p>
 * <p>All Rights Reserved By Acewill Infomation Technology(Beijing) Co.,Ltd</p>
 */

@Service(value = "com.iif.system.log.service.LoginLogService")
@Transactional()
public class LoginLogServiceImpl extends BaseService implements ILoginLogService {

	@Autowired
	private ILoginLogDao loginLogDao = null;

	/**
	 * 分页获取登录日志
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page, Map searchMap) {
		return loginLogDao.findByPage(page, searchMap);
	}

	public void save(LoginLog loginLog) {
		loginLogDao.save(loginLog);
	}
}
