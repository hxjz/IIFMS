package com.iif.orgMgt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.iif.orgMgt.dao.IRoleDao;
import com.iif.orgMgt.entity.IffRole;
import com.iif.orgMgt.service.IRoleService;

/**
 * 角色管理service
 * @author LiuM
 * @date   2014-08-28
 */
@Service
@Transactional()
public class RoleServiceImpl extends BaseService implements IRoleService{
	
	@Autowired
	private IRoleDao smsRoleDao;

	/**
	 * 根据查询条件查询角色列表
	 * @param filterMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<IffRole> findByFilterMap(Map filterMap){
		return smsRoleDao.findByFilterMap(filterMap);
	}
}
