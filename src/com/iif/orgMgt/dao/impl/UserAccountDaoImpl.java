package com.iif.orgMgt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.iif.orgMgt.dao.IUserAccountDao;
import com.iif.orgMgt.entity.UserAccount;

/**
 * 用户管理DAO
 * @author LiuM
 * @date 2014-09-01
 * @since 1.0
 */
@Repository()
public class UserAccountDaoImpl extends BaseDao implements IUserAccountDao{

	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserAccount loadUserByUsername(String username) {
		if (username==null) {
			return null;
		}
		// 临时处理，用户有多条数据，取emplRcd最大的一条，HR同步的时候不应该出现多条（thinkpad）
		String queryString = " from UserAccount u where  lower(u.userAccount)=? ";
		List<UserAccount> list = (List<UserAccount>)this.getHibernateTemplate().find(queryString,
				username.toLowerCase());
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public UserAccount getUserByEmp( String  empId ){
		StringBuffer hql = new StringBuffer("from UserAccount u where u.linkEmployee.id =? ");
		List<String> params = new ArrayList<String>();
		params.add(empId);
		List<UserAccount> list = super.findByHql(hql, params);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
