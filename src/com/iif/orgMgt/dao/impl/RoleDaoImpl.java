package com.iif.orgMgt.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.iif.orgMgt.dao.IRoleDao;
import com.iif.orgMgt.entity.IffRole;

/**
 * 用户角色DAO
 * @author LiuM
 * @date 2014-09-01
 * @since 1.0
 */
@Repository()
public class RoleDaoImpl extends BaseDao implements IRoleDao{

	/**
	 * 根据查询条件获取角色列表
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IffRole> findByFilterMap(Map filterMap) {
		StringBuffer hql = new StringBuffer("from Role where 1=1 ");
		List params = new ArrayList();
		OrmConverter.assemblyQuery(hql, filterMap, params);
		List<IffRole> list = this.findByHql(hql, params);
		return list;
	}
}
