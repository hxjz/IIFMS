package com.iif.orgMgt.service;

import com.hxjz.common.core.orm.IBaseService;
import com.iif.orgMgt.entity.UserAccount;

/**
 * 用户管理Service
 * @author LiuM
 * @date   2014-08-28
 */
public interface IUserAccountService extends IBaseService{
	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public UserAccount loadUserByUsername(String username);
	
	/**
	 * 组织登录用户
	 * 
	 * @param user
	 * @return
	 */
	public UserAccount loadSecurityUser(UserAccount userAccount);
}
