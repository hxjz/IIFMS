package com.iif.orgMgt.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;
import com.iif.orgMgt.entity.IffRole;

/**
 * 用户角色DAO interface
 * @author LiuM
 * @date 2014-09-01
 * @since 1.0
 */
public interface IRoleDao extends IBaseDao{

	/**
	 * 根据查询条件获取角色列表
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<IffRole> findByFilterMap(Map filterMap);
}
