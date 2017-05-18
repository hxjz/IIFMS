package com.iif.orgMgt.dao;

import com.hxjz.common.core.orm.IBaseDao;
import com.iif.orgMgt.entity.UserAccount;

/**
 * 用户管理DAO interface
 * @author LiuM
 * @date 2014-09-01
 * @since 1.0
 */
public interface IUserAccountDao extends IBaseDao{

	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public UserAccount loadUserByUsername(String username);
	
	public UserAccount getUserByEmp( String  empId );
}
