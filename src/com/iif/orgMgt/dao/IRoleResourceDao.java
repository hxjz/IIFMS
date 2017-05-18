package com.iif.orgMgt.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;
import com.iif.orgMgt.entity.RoleResource;

/**
 * 用户角色资源DAO interface
 * @author LiuM
 * @date 2014-09-01
 * @since 1.0
 */
public interface IRoleResourceDao extends IBaseDao{

	/**
	 * 根据查询条件获取角色资源列表
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public List<RoleResource> findByFilterMap(Map filterMap);
}
