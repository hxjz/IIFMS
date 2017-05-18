package com.iif.system.resource.service.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hxjz.common.core.orm.BaseService;
import com.iif.system.resource.dao.IBaseUtilDao;
import com.iif.system.resource.service.IBaseUtilService;

@Service
public class BaseUtilServiceImpl extends BaseService implements IBaseUtilService {

	@Resource
	private IBaseUtilDao baseUtilDao;

	public JdbcTemplate getJdbcTemplate() {
		return baseUtilDao.getJdbcTemplate();
	}
}
