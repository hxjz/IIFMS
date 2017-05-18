package com.iif.system.userManager.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.hxjz.common.utils.Page;
import com.iif.common.util.SysConstant;
import com.iif.system.userManager.entity.User;

@Repository()
public class UserDaoImpl extends BaseDao {

	/**
	 * 删除用户所有的角色
	 * 
	 * @param userId
	 */
	public void deleteGrantRole(Long userId) {
		this.getHibernateTemplate().bulkUpdate(
				"delete from UserRole where userId=?", userId);
	}

	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public User loadUserByUsername(String username) {
		// 临时处理，用户有多条数据，取emplRcd最大的一条，HR同步的时候不应该出现多条（thinkpad）
		String queryString = null;
		if (username.equals(SysConstant.admin_name) // 管理员情况的查询语句
				|| username.equals(SysConstant.administrator_name)) {
			queryString = " from User u where isDel=0 and u.username=?";
		} else { // 管理员情况的查询语句
			queryString = " from User u where isDel=0 and u.emplStatus='A' and isEnable = 1 and emplRcd = 0 and u.username=? order by effseq";
		}
		List<User> list = (List<User>)this.getHibernateTemplate().find(queryString,
				username);
		if (list.isEmpty()) {
			return null;
		}
		//生效序号（一天内的多次变动则去生效序号最大的）effseq
		if (list.size() > 0) {// 临时处理，用户有多条数据，取effseq最大的一条，HR同步的时候不应该出现多条（thinkpad）
			return list.get(list.size() - 1);
		} else {
			return list.get(0);
		}
	}

	/**
	 * 根据用户编号和部门setid查询用户
	 * 
	 * @param emplid
	 * @param setidDept
	 * @return
	 */
	public User findUserByEmplid(String emplid, String setidDept) {
		//String queryString = " from User u where isDel=0 and u.emplStatus='A' and u.emplid=? and u.setidDept=? order by emplRcd";
		//emplRcd 员工记录（0为主岗、其它为兼职岗位）实际使用的时候需要使用
		//effseq 生效序号（一天内的多次变动则去生效序号最大的）
		String queryString = " from User u where isDel=0 and u.emplStatus='A' and u.emplid=? and u.setidDept=? order by effseq";
		List<User> list = (List<User>)this.getHibernateTemplate().find(queryString, emplid,
				setidDept);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() > 0) { // 用户有多条数据，取emplRcd最大的一条
			return list.get(list.size() - 1);
		} else {
			return list.get(0);
		}
	}
	public List<User> findUserByEmplidNew(String emplid, String setidDept) {
		//String queryString = " from User u where isDel=0 and u.emplStatus='A' and u.emplid=? and u.setidDept=? order by emplRcd";
		//emplRcd 员工记录（0为主岗、其它为兼职岗位）实际使用的时候需要使用
		//effseq 生效序号（一天内的多次变动则去生效序号最大的）
		String queryString = " from User u where isDel=0 and u.emplStatus='A' and u.isEnable = 1 and u.emplid=? and setidDept = 'HL001' and u.setidDept=? order by effseq";
		List<User> list = (List<User>)this.getHibernateTemplate().find(queryString, emplid,
				setidDept);
		if (list.isEmpty()) {
			return null;
		}
		else {
			return list;
		}
	}

	public List<Long> getAllUserId(){
		String query = "select u.id from User u where u.isEnable = 1 and u.isDel = 0 and u.emplRcd = 0 and u.emplStatus = 'A' and u.id <> 30755 and u.setidDept = 'HL001'" ;
		return (List<Long>)super.getHibernateTemplate().find(query) ;
	}
	
	/**
	 * 根据用户职务和部门setid查询用户
	 * 
	 * @param emplid
	 * @param setidDept
	 * @return
	 */
	public User findUserByJobcode(String[] jobcode, String deptid,
			String setidDept) {
		StringBuilder queryString = new StringBuilder(
				"from User u where isDel=0 and isEnable = 1 and u.emplStatus='A' and u.deptid=? and u.setidDept=? ");
		if (jobcode != null && jobcode.length > 0) {
			queryString.append(" and u.jobcode in (");
			for (int i = 0; i < jobcode.length; i++) {
				queryString.append(jobcode[i]);
				if (i < jobcode.length - 1) {
					queryString.append(",");
				}
			}
			queryString.append(") ");
		}
		queryString.append(" order by u.emplRcd");
		List<User> list = (List<User>)this.getHibernateTemplate().find(
				queryString.toString(), deptid, setidDept);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() > 0) { // 用户有多条数据，取emplRcd最大的一条
			return list.get(list.size() - 1);
		} else {
			return list.get(0);
		}
	}

	/**
	 * 删除用户
	 */
	public void delete(Long id) {
		this.getJdbcTemplate().update(
				"delete from um_user_role where user_id=" + id);
		this.getJdbcTemplate().update("update um_user set is_del=1 where id=?",
				id);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param password
	 */
	public void updatePassword(Long userId, String password) {
		this.getHibernateTemplate().bulkUpdate(
				" update User set password=? where id=?",
				new Object[] { password, userId });
	}

	/**
	 * 判断是否含有超级管理员
	 * 
	 * @return
	 */
	public User hasTypeForAdmin(Integer userType) {
		String queryString = " from User where userType=?";
		List list = this.getHibernateTemplate().find(queryString, userType);
		if (list.size() == 0) {
			return null;
		}
		return (User) list.get(0);
	}

	/**
	 * 根据用户Id获取角色ID集合
	 * 
	 * @param userId
	 * @return
	 */
	public Collection findRoleIdByUserId(Long userId) {
		return this.getHibernateTemplate().find(
				"select roleId from UserRole where userId=?", userId);
	}

	/**
	 * 根据用户Id获取角色名称集合
	 * 
	 * @param userId
	 * @return
	 */
	public Collection findRoleNameByUserId(Long userId) {
		return this.getHibernateTemplate().find(
				"select role.name from UserRole where userId=?", userId);
	}
	
	/**
	 * 根据角色名称和机构ID获得用户集合
	 * 
	 * @param roleName
	 * @param orgId
	 * @return
	 */
	public List<User> findUserByRoldName(String roleName, Long orgId) {
		return (List<User>)this
				.getHibernateTemplate()
				.find(
						"from user where id in (select userId from UserRole where role.name = ?) and orgId = ? ",
						roleName, orgId);
	}

	/**
	 * 根据组ID获取用户ID集合
	 * 
	 * @param groupId
	 * @return
	 */
	public List findUserIdByGroupId(Long groupId) {
		List list = this.getHibernateTemplate().find(
				"select distinct(userId) from UserGroup where groupId=?",
				groupId);
		return list;
	}

	/**
	 * 分页获取对象
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page, Map searchMap) {
		StringBuffer query = new StringBuffer(" from User where isDel=0 and setidDept = 'HL001' and emplRcd = 0 and isEnable = 1 and emplStatus = 'A'");
		searchMap.put(" order_setidDept", "asc");
		List valueList = new ArrayList();
		OrmConverter.assemblyQuery(query, searchMap, valueList);
		return super.findByHql(page, query, valueList);
	}

	public List findByPageOfParamsList(Page page , Map searchMap , List<String> deptIdList){
		StringBuffer query = new StringBuffer(" from User where isDel = 0 and setidDept = 'HL001' and emplRcd = 0 and isEnable = 1 and emplStatus = 'A'"); 
		List valueList = new ArrayList() ;
		OrmConverter.getQuery(query, searchMap, "filter_and_deptId_EQ_S", valueList) ;
		if(deptIdList != null){
			for(String str : deptIdList){
				query.append(" or deptId = '").append(str).append("' "); 
			}
		}
		searchMap.put(" order_setidDept", "asc") ;
		OrmConverter.assemblyQuery(query, searchMap, valueList);
		return super.findByHql(page, query, valueList);
	}
	
	public Collection findRoleCodeByUserId(Long userId) {
		return this.getHibernateTemplate().find(
				"select role.code from UserRole where userId=?", userId);
	}

	/**
	 * 更新用户表的org_id这个字段
	 */
	public void updateOrgId() {
		this
				.getJdbcTemplate()
 				.update(
						"update um_user u set u.org_id=(select max(o.id) from um_org o where u.deptid=o.deptid)");
	}

	/**
	 * 根据hr登录系统
	 * 
	 * @param username
	 * @return
	 */
	public User findByHlUser(String username) {
		// 临时处理，用户有多条数据，取emplRcd最大的一条，HR同步的时候不应该出现多条（thinkpad）
		String queryString = null;
		if (username.equals(SysConstant.admin_name) // 管理员情况的查询语句
				|| username.equals(SysConstant.administrator_name)) {
			queryString = " from User u where isDel=0 and u.username=?";
		} else { // 管理员情况的查询语句
			queryString = " from User u where u.isDel=0 and u.emplStatus='A' and u.emplRcd = 0 and u.setidDept = 'HL001' and u.isEnable = 1  and u.hlUser=? order by u.effseq";
		
		}
		List<User> list = (List<User>)this.getHibernateTemplate().find(queryString,
				username);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() > 0) {// 临时处理，用户有多条数据，取emplRcd最大的一条，HR同步的时候不应该出现多条（thinkpad）
			return list.get(list.size() - 1);
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 根据机构id
	 * @param deptid
	 * @return
	 */
	public List<User> findUserByDeptId(String username) {
		User user = findUserByUserName(username);
		if(user == null){
			return null;
		}else{
			return (List<User>)this.getHibernateTemplate().find("from User where deptid = ?",user.getDeptid());
		}
	}
	/**
	 * 根据用户编号查找用户
	 * @param deptid
	 * @return
	 */
	public User findUserByUserName(String username) {
		String sql = "from User where username = ? and emplRcd = 0 and isDel = 0 and isEnable = 1 and setidDept = 'HL001' and emplStatus = 'A'";
		List<User> list = (List<User>)this.getHibernateTemplate().find(sql,username);
		User user = null;
		if(list != null && list.size() >0){
			user = list.get(0);
		}
		return user;
	}
	
	/**
	 * 根据用户编号查找用户
	 * @param deptid
	 * @return
	 */
	public User findDirectorByDeptId(String deptid) {
		String sql = "select um from User um,JobcodeStg hl where um.jobcode = hl.jobcode and hl.setid = 'HL001' and hl.hlJobSeries = 'FUNC' and hl.hlJobLevel = '2'and um.deptid = ?";
		List<User> list = (List<User>)this.getHibernateTemplate().find(sql,deptid);
		User user = null;
		if(list != null && list.size() >0){
			user = list.get(0);
		}
		return user;
	}
}
