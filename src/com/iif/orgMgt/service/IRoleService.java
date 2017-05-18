package com.iif.orgMgt.service;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseService;
import com.iif.orgMgt.entity.IffRole;

/**
 * 角色管理service interface
 * @author LiuM
 * @date   2014-08-28
 */
public interface IRoleService extends IBaseService{

	/**
	 * 根据查询条件查询角色列表
	 * @param filterMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<IffRole> findByFilterMap(Map filterMap);
}
