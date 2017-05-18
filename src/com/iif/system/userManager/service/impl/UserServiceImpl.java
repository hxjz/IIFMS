package com.iif.system.userManager.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.PasswordMd5;
import com.hxjz.common.utils.StrUtil;
import com.iif.system.priv.dao.AssignDao;
import com.iif.system.priv.entity.ResourceOwner;
import com.iif.system.resource.dao.IResourceDao;
import com.iif.system.resource.entity.Resource;
import com.iif.system.userManager.dao.IOrgDao;
import com.iif.system.userManager.dao.IUserDao;
import com.iif.system.userManager.dao.IUserRoleDao;
import com.iif.system.userManager.entity.Org;
import com.iif.system.userManager.entity.User;
import com.iif.system.userManager.entity.UserRole;
import com.iif.system.userManager.service.IUserService;

/**
 * 用户Service实现类
 * 
 * @author LiuM
 * @date 2014-08-28
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements IUserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class) ;
	
	@Autowired
	private IUserDao userDao = null;

	@Autowired
	private IResourceDao resourceDao = null;
	
	@Autowired
	private AssignDao assignDao = null;
	
	@Autowired
	private IOrgDao orgDao = null;
	@Autowired
	private IUserRoleDao userRoleDao = null;
	
	@Override
	public User loadUserByUsername(String username) {
		return userDao.loadUserByUsername(username);
	}

	@Override
	public void deleteIds(String ids) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			userDao.delete(new Long(idArray[i]));
		}
	}

	@Override
	public void updatePassword(Long userId, String password) {
		userDao.updatePassword(userId, password);
	}

	@Override
	public void initAdministrator(Long rootOrgId) {
		User administrator = userDao.hasTypeForAdmin(0);
		User admin = userDao.hasTypeForAdmin(1);
		User AMSadmin = userDao.hasTypeForAdmin(2) ;
		if (null == administrator) {
			User user = new User();
			user.setUsername("administrator");
			user.setPassword("111111");
			user.setPassword(PasswordMd5.createPassword(user.getPassword()));// 密码跟登录名一样,并且进行加密
			user.setName("系统管理员");
			user.setOrgId(rootOrgId);
			user.setIsDel(0);
			user.setLoginNum(0l);
			user.setUserType(0);
			userDao.save(user);
		} else {
			administrator.setOrgId(null);
			userDao.update(administrator);
		}
		if (null == admin) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("111111");
			user.setPassword(PasswordMd5.createPassword(user.getPassword()));// 密码跟登录名一样,并且进行加密
			user.setName("管理员");
			user.setOrgId(rootOrgId);
			user.setIsDel(0);
			user.setLoginNum(0l);
			user.setUserType(1);
			userDao.save(user);
		} else {
			admin.setOrgId(null);
			userDao.update(admin);
		}
		if(null == AMSadmin){
			User user = new User() ;
			user.setUsername("AMSadmin") ;
			user.setPassword("123456") ;
			user.setPassword(PasswordMd5.createPassword(user.getPassword())) ;
			user.setName("AMS管理员") ;
			user.setOrgId(rootOrgId);
			user.setIsDel(0);
			user.setLoginNum(0l);
			user.setUserType(2);
			userDao.save(user);
		}else{
			AMSadmin.setOrgId(null);
			userDao.update(AMSadmin);
		}
	}

	@Override
	public User loadSecurityUser(User user) {
		// 获取用户可以访问的菜单和资源
		List<Resource> resurceList = new ArrayList<Resource>();
		List<Resource> resurceList_ = new ArrayList<Resource>();
		// 超级管理员可以访问所有的资源
		if (null != user.getUserType()
				&& (user.getUserType() == 0 || user.getUserType() == 1 || user.getUserType() == 2)) {
			resurceList = resourceDao.findAllEnable();
		} else {
			
			if(user.getHlTreepath().indexOf("A17231") >=0){//是否是配置操作组
				user.setIsConfigureOperating(true); 
			}else{
				user.setIsConfigureOperating(false);
			}
			
			if(user.getHlTreepath().indexOf("A18109") >=0){//是否是业务拓展中心
				user.setIsDevelopmentCenter(true);
			}else{
				user.setIsDevelopmentCenter(false);
			}
			user.setHljobseries("");
				
				String hljobseries = user.getHljobseries() ;
				if(StringUtils.isNotBlank(hljobseries)){
					if("大区助理".equals(hljobseries) || "店助理".equals(hljobseries) || "区域助理".equals(hljobseries)){
						user.setRoleNames("运营助理") ;
					}
				}
				user.setOrgNames("");
				Org org = orgDao.findOrgByDeptid(user.getDeptid(), user.getSetidDept());
				if(org != null){
					user.setOrgNames(org.getName());
				}
				
				if("店经理".equals(hljobseries)){//角色是店面经理的所有权限
					List<ResourceOwner> resourceOwnerList = userRoleDao.findByOwnerIdAndAssignType("1116", Integer.valueOf(3));
					if(resourceOwnerList != null && !resourceOwnerList.isEmpty()){
						for (ResourceOwner resourceOwner : resourceOwnerList) {
							Resource resource = (Resource) resourceDao.findById(Resource.class, resourceOwner.getResourceId());
							if(resource != null){
								resurceList.add(resource);
							}
						}
					}
				}
				if("店助理".equals(hljobseries)){//角色是店面助理的所有权限
					List<ResourceOwner> resourceOwnerList = userRoleDao.findByOwnerIdAndAssignType("1117", Integer.valueOf(3));
					if(resourceOwnerList != null && !resourceOwnerList.isEmpty()){
						for (ResourceOwner resourceOwner : resourceOwnerList) {
							Resource resource = (Resource) resourceDao.findById(Resource.class, resourceOwner.getResourceId());
							if(resource != null){
								resurceList.add(resource);
							}
						}
					}
				}
				/*
				String ss= StringUtils.isBlank(user.getRoleNames()) ? "" : user.getRoleNames().replace(",", "','");
				ss = "'" + ss + "'" ;
				
				List<Role> roles=assignDao.findByroleName(ss);
				
				if(roles.size()>0){
					//resurceList = resourceDao.findAllEnableForUserId(user.getId());
					for (Role role : roles) {
					List<Long> idList=new ArrayList<Long>();
					idList.add(user.getId());
					idList.add(role.getId());
					idList.add(user.getOrgId());
					for (Long roleId : idList) {
					 resurceList_= assignDao.findAllEnableForUserId(roleId);
 					 for (Resource resource : resurceList_) {
 						if(resurceList.size() == 0){
 							resurceList.add(resource) ;
 						}else{
 							boolean flag = false ;
 							for(Resource _temp : resurceList){
 								if(_temp.getId() == resource.getId() || _temp.getId().equals(resource.getId())) {
									flag = true ;
									break ;
 								}
 							}
 							if(!flag) resurceList.add(resource) ;
 						}
					}
				}
				}
			}	
			*/
			//以下是新权限结构
			//修改为从新结构获取权限
			 resurceList_= assignDao.findAllEnableByUserId(user.getId(),user.getUsername());
			 for (Resource resource : resurceList_) {
				if(resurceList.size() == 0){
					resurceList.add(resource) ;
				}else{
					boolean flag = false ;
					for(Resource _temp : resurceList){
						if(_temp.getId() == resource.getId() || _temp.getId().equals(resource.getId())) {
							flag = true ;
							break ;
						}
					}
					if(!flag) resurceList.add(resource) ;
				}
			}
		}
		//String resourceStr = "";
		StringBuffer resourceStr = new StringBuffer();
		for (int i = 0; i < resurceList.size(); i++) {
			if (StrUtil.isNotNullOrBlank(resurceList.get(i).getUrl())) {
				resourceStr.append( resurceList.get(i).getUrl()+",");
				//resourceStr = resourceStr + resurceList.get(i).getUrl() + ",";
			}
		}
		LOGGER.info(resourceStr.toString() ) ;
		user.setResources(resourceStr.toString());
		user.setResourceList(resurceList);
		// 获取用户可以访问的模块
		List moduleResourceList = new ArrayList();
		for (int i = 0; i < resurceList.size(); i++) {
			if (null != resurceList.get(i).getType()
					&& resurceList.get(i).getType() == 1) {
				moduleResourceList.add(resurceList.get(i));
			}
		}
		user.setModuleResourceList(moduleResourceList);
		String roleCodes = StrUtil.CollectionToStr(userDao
				.findRoleCodeByUserId(user.getId()), ",", false);
		user.setRoleCodes(roleCodes);
		if (null != user.getUserType()
				&& (user.getUserType() == 0 || user.getUserType() == 1 || user.getUserType() == 2)) {
			user.setOrgName("系统节点");
			if (null == user.getRoleNames()) {
				user.setRoleNames("系统管理员");
			}
		} else if (null != user.getOrg()) {
			user.setOrgName(user.getOrg().getName());
		}
		return user;
	}

	@Override
	public void save(User user, String[] roles) {
		user.setIsDel(0);
		user.setLoginNum(0l);
		if (StringUtils.isNotBlank(user.getHlTreepath())
				&& user.getHlTreepath().contains("null")) {
			user.setHlTreepath(user.getDeptid());
		}
		userDao.save(user);
		if (null != roles) {
			for (String roleId : roles) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(new Long(roleId));
				userRole.setUserId(user.getId());
				userDao.save(userRole);
			}
		}
		assembleUser(user);
	}

	@Override
	public void update(User user, String[] roles) {
		User oldUser = (User) userDao.findById(User.class, user.getId());
		oldUser.setName(user.getName());
		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(user.getPassword());
		oldUser.setIsEnable(user.getIsEnable());
		oldUser.setDeptid(user.getDeptid());
		userDao.update(oldUser);
		if (null != roles) {
			userDao.deleteGrantRole(user.getId());
			for (String roleId : roles) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(new Long(roleId));
				userRole.setUserId(user.getId());
				userDao.save(userRole);
			}
		}
		assembleUser(oldUser);
	}

	/**
	 * 根据用户名获取组ID集合
	 * 
	 * @param userId
	 * @return
	 */
	public void assembleUser(User user) {
		String roleNames = StrUtil.CollectionToStr(userDao
				.findRoleNameByUserId(user.getId()), ",", false);
		user.setRoleNames(roleNames);
		userDao.update(user);
	}

	@Override
	public Collection findRoleIdByUserId(Long userId) {
		return userDao.findRoleIdByUserId(userId);
	}

	@Override
	public List findByPage(Page page, Map searchMap) {
		return userDao.findByPage(page, searchMap);
	}

	@Override
	public List findByPageOfParamsList(Page page , Map searchMap , List<String> deptIdList){
		return userDao.findByPageOfParamsList(page, searchMap , deptIdList) ;
	}
	
	@Override
	public List<User> findUserByRoldName(String roleName, Long orgId) {
		return userDao.findUserByRoldName(roleName, orgId);
	}
	
	@Override
	public User findUserByEmplid(String emplid, String setidDept) {
		return userDao.findUserByEmplid(emplid, setidDept);
	}
	
	@Override
	public List<Long> getAllUserId(){
		return userDao.getAllUserId() ;
	}

	@Override
	public User findUserByJobcode(String[] jobcode, String deptid,
			String setidDept) {
		return userDao.findUserByJobcode(jobcode, deptid, setidDept);
	}

	@Override
	public void updateOrgId() {
		userDao.updateOrgId();
	}

	@Override
	public User findByHlUser(String username) {
		return userDao.findByHlUser(username);
	}
	
	@Override
	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}
	
	@Override
	public User findDirectorByDeptId(String deptid){
		return userDao.findDirectorByDeptId(deptid);
	}
}
