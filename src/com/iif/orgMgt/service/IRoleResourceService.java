package com.iif.orgMgt.service;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseService;
import com.iif.orgMgt.entity.RoleResource;

/**
 * 角色资源表Service
 * @author LiuM
 * @date   2014-08-28
 */
public interface IRoleResourceService extends IBaseService{

	//查询角色资源关联列表
	@SuppressWarnings("rawtypes")
	public List<RoleResource> findRoleResourceByFilterMap(Map filterMap);
}
