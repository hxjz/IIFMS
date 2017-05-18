package com.iif.system.userManager.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseService;
import com.hxjz.common.utils.Page;
import com.iif.system.userManager.entity.User;

/**
 * 用户Service实现类
 * 
 * @author LiuM
 * @date 2014-08-28
 * 
 */
public interface IUserService extends IBaseService {
	
	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public User loadUserByUsername(String username);

	/**
	 * 删除用户
	 * 
	 * @param ids
	 */
	public void deleteIds(String ids);

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param userId
	 */
	public void updatePassword(Long userId, String password);

	/**
	 * 初始化系统管理员
	 * 
	 * @param rootOrgId
	 */
	public void initAdministrator(Long rootOrgId);
	
	/**
	 * 根据用户获取职务
	 * @param user
	 * @return
	 */
	/*public String getHlJobSeriesByUser(User user){
		    JobcodeStg jobcodeStg = jobCodeDao.getJobcodeStgByJobCode("HL001", user.getJobcode());
		    if( jobcodeStg != null ){
			   JlevelStg jstg = jlevelDao.findOnlyJlevel("HL001", jobcodeStg.getHlJobSeries(), jobcodeStg.getHlJobLevel());
		    return  StaticMethod.nullObject2String(jstg.getDescr()) ; 
		    }else{
		    	return "";
		    }
	}*/

	/**
	 * 组织登录用户
	 * 
	 * @param user
	 * @return
	 */
	public User loadSecurityUser(User user);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param userGroup
	 */
	public void save(User user, String[] roles);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param groups
	 */
	public void update(User user, String[] roles);

	/**
	 * 根据用户名获取组ID集合
	 * 
	 * @param userId
	 * @return
	 */
	public void assembleUser(User user);

	/**
	 * 根据用户Id获取角色ID集合
	 * 
	 * @param userId
	 * @return
	 */
	public Collection findRoleIdByUserId(Long userId);

	/**
	 * 分页获取用户
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page, Map searchMap);

	public List findByPageOfParamsList(Page page , Map searchMap , List<String> deptIdList);
	
	/**
	 * 根据角色名称和机构ID获得用户集合
	 * 
	 * @param roleName
	 * @param orgId
	 * @return
	 */
	public List<User> findUserByRoldName(String roleName, Long orgId);
	
	/**
	 * 根据用户编号和部门setid查询用户
	 * 
	 * @param emplid
	 * @param setidDept
	 * @return
	 */
	public User findUserByEmplid(String emplid, String setidDept);
	
	public List<Long> getAllUserId();

	/**
	 * 根据用户职务和部门setid查询用户
	 * 
	 * @param emplid
	 * @param setidDept
	 * @return
	 */
	public User findUserByJobcode(String[] jobcode, String deptid, String setidDept);

	/**
	 * 更新用户表的org_id这个字段
	 */
	public void updateOrgId();

	public User findByHlUser(String username);
	
	/**
	 * 根据用户编号查找用户
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);
	
	/**
	 * 根据user的部门编号找该部门的主管
	 * @param deptid
	 * @return
	 */
	public User findDirectorByDeptId(String deptid);
}
