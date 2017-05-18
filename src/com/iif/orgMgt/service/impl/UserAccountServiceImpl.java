package com.iif.orgMgt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.hxjz.common.utils.StrUtil;
import com.iif.orgMgt.dao.IUserAccountDao;
import com.iif.orgMgt.entity.UserAccount;
import com.iif.orgMgt.service.IUserAccountService;
import com.iif.system.resource.dao.IResourceDao;
import com.iif.system.resource.entity.Resource;
import com.iif.system.userManager.dao.IUserDao;

/**
 * 用户管理Service
 * @author LiuM
 * @date   2014-08-28
 */
@Service
@Transactional()
public class UserAccountServiceImpl extends BaseService implements IUserAccountService{
	@Autowired
	private IUserAccountDao userAccountDao = null;
	
	@Autowired
	private IResourceDao resourceDao = null;
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class) ;
	
	@Autowired
	private IUserDao userDao = null;
	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public UserAccount loadUserByUsername(String username) {
		return userAccountDao.loadUserByUsername(username);
	}
	
	/**
	 * 组织登录用户
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserAccount loadSecurityUser(UserAccount userAccount) {
		// 获取用户可以访问的菜单和资源
		List<Resource> resurceList = new ArrayList<Resource>();
		// 超级管理员可以访问所有的资源
		resurceList = resourceDao.findAllEnable();
		//String resourceStr = "";
		StringBuffer resourceStr = new StringBuffer();
		for (int i = 0; i < resurceList.size(); i++) {
			if (StrUtil.isNotNullOrBlank(resurceList.get(i).getUrl())) {
				resourceStr.append( resurceList.get(i).getUrl()+",");
				//resourceStr = resourceStr + resurceList.get(i).getUrl() + ",";
			}
		}
//		LOGGER.info(resourceStr.toString() ) ;
		userAccount.setResources(resourceStr.toString());
		userAccount.setResourceList(resurceList);
		// 获取用户可以访问的模块
		List moduleResourceList = new ArrayList();
		for (int i = 0; i < resurceList.size(); i++) {
			if (null != resurceList.get(i).getType()
					&& resurceList.get(i).getType() == 1) {
				moduleResourceList.add(resurceList.get(i));
			}
		}
		userAccount.setModuleResourceList(moduleResourceList);
		String roleCodes = StrUtil.CollectionToStr(userDao.findRoleCodeByUserId(new Long("24657")), ",", false);
		userAccount.setRoleCodes(roleCodes);
		userAccount.setRoleNames("系统管理员");
		
		return userAccount;
	}
}
