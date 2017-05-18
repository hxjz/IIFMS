package com.iif.orgMgt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.iif.orgMgt.dao.IRoleResourceDao;
import com.iif.orgMgt.entity.RoleResource;
import com.iif.orgMgt.service.IRoleResourceService;

/**
 * 角色资源表Service
 * @author LiuM
 * @date   2014-08-28
 */
@Service
@Transactional()
public class RoleResourceServiceImpl extends BaseService implements IRoleResourceService{

	@Autowired
	private IRoleResourceDao roleResourceDao;

	//查询角色资源关联列表
	@SuppressWarnings("rawtypes")
	public List<RoleResource> findRoleResourceByFilterMap(Map filterMap) {
		return roleResourceDao.findByFilterMap(filterMap);
	}
}
