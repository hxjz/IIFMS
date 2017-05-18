package com.iif.system.priv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.StrUtil;
import com.iif.orgMgt.entity.IffRole;
import com.iif.orgMgt.entity.IffUserRole;
import com.iif.system.resource.entity.Resource;

/**
 * @author LiuM
 * @date 2014-08-28
 */
@Repository()
public class AssignDao extends BaseDao {
	
	//通过roleName找role
	public List<IffRole> findByroleName(String roleName){
		String hql="from Role r where r.name in("+roleName+")";
		List<IffRole> roleList = (List<IffRole>)super.getHibernateTemplate().find(hql) ;
		return roleList ;
	} 
	
	public List<IffUserRole> findByroleId(Long roleId){
		String sql="from UserRole where roleId=?";
		return (List<IffUserRole>)this.getHibernateTemplate().find(sql, roleId);
	}
	public void delete(Long id) {
		this.getHibernateTemplate().bulkUpdate(
				" delete from RoleResource where roleId=?", id);
		this.getHibernateTemplate().bulkUpdate(
				" delete from UserRole where roleId=?", id);
		this.getHibernateTemplate().bulkUpdate(" delete from Role where id=?",
				id);
	}

	/**
	 * 分页获取角色
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page, Map searchMap) {
		StringBuffer query = new StringBuffer(" from Role");
		List valueList = new ArrayList();
		return super.findByHql(page, query, valueList);
	}

	/**
	 * 获取角色授权资源
	 * 
	 * @param roleId
	 * @return
	 */
	public List findRoleResource(Long roleId) {
		return this.getHibernateTemplate().find(
				" from RoleResource where roleId=?", roleId);
	}

	public void deleteGrantResourceByRoleId(Long roleId) {
		this.getJdbcTemplate().update(
				" delete from um_role_resource where role_id=?", roleId);
	}
	/**
	 * 获取用户授权的资源
	 * 
	 * @param userId
	 * @return
	 */
	public List<Resource> findAllEnableForUserId(Long roleId) {
		List list = new ArrayList();
		String queryString = "select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_role_resource rr where r.id=rr.resource_id and rr.role_id=?"+
						" order by r.order_code";
		List resultList = this.getJdbcTemplate().queryForList(queryString,roleId);
		Map map = null;
		for (int i = 0; i < resultList.size(); i++) {
			Resource resource = new Resource();
			map = (Map) resultList.get(i);
			resource.setId(StrUtil.ObjectToLong(map.get("id")));
			resource.setParentId(StrUtil.ObjectToLong(map.get("parent_id")));
			resource.setName(StrUtil.ObjectToString(map.get("name")));
			resource.setUrl(StrUtil.ObjectToString(map.get("url")));
			resource.setType(StrUtil.ObjectToInteger(map.get("type")));
			resource.setOrderCode(StrUtil.ObjectToInteger(map
					.get("order_code")));
			resource.setIsLeaf(StrUtil.ObjectToInteger(map.get("is_leaf")));
			list.add(resource);
		}
		return list;
	}
	/**
	 * 分页获取对象,根据角色名称获得用户集合
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findUserByPage(Page page, Map searchMap,Long roleId) {
		StringBuffer query = new StringBuffer("from User where id in (select userId from UserRole where roleId = "+roleId+")");
		List valueList = new ArrayList();
		OrmConverter.assemblyQuery(query, searchMap, valueList);
		return super.findByHql(page, query, valueList);
	}
	/**
	 * 分页获取对象,根据部门ID获得用户集合
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findOrgUserByPage(Page page, Map searchMap,Long orgId) {
		StringBuffer query = new StringBuffer("from User where orgId="+orgId+"");
		List valueList = new ArrayList();
		OrmConverter.assemblyQuery(query, searchMap, valueList);
		return super.findByHql(page, query, valueList);
	}
	
	/**
	 * 根据用户id获取用户授权的资源
	 * 
	 * @param userId
	 * @return
	 */
	/*public List<Resource> findAllEnableByUserId(Long userId) {
		List list = new ArrayList();
		String queryString = "select * from (select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro,um_user u where ro.is_deleted=0 and r.id=ro.resource_id and " +
						"ro.owner_id=u.org_id and ro.assign_type=1 and u.id=? "+
						"union "+
						"select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro where ro.is_deleted=0 and r.id=ro.resource_id and ro.assign_type=2 and ro.owner_id=? "+
						"union " +
						"select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro,um_user_role ur where ro.is_deleted=0 and r.id=ro.resource_id and " +
						"ro.owner_id=ur.role_id and ro.assign_type=3 and ur.user_id=? "+
						") t order by t.order_code";
		System.out.println(queryString);
		List resultList = this.getJdbcTemplate().queryForList(queryString,userId,userId,userId);
		Map map = null;
		for (int i = 0; i < resultList.size(); i++) {
			Resource resource = new Resource();
			map = (Map) resultList.get(i);
			resource.setId(StrUtils.ObjectToLong(map.get("id")));
			resource.setParentId(StrUtils.ObjectToLong(map.get("parent_id")));
			resource.setName(StrUtils.ObjectToString(map.get("name")));
			resource.setUrl(StrUtils.ObjectToString(map.get("url")));
			resource.setType(StrUtils.ObjectToInteger(map.get("type")));
			resource.setOrderCode(StrUtils.ObjectToInteger(map
					.get("order_code")));
			resource.setIsLeaf(StrUtils.ObjectToInteger(map.get("is_leaf")));
			list.add(resource);
		}
		return list;
	}*/
	/**
	 * 根据用户id获取用户授权的资源
	 * 
	 * @param userId
	 * @return
	 */
	public List<Resource> findAllEnableByUserId(Long userId,String userName) {
		List list = new ArrayList();
		String queryString = "select * from (select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro,um_user u where ro.is_deleted=0 and r.id=ro.resource_id and " +
						"ro.owner_id=u.org_id and ro.assign_type=1 and u.id=? "+
						"union "+
						"select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro where ro.is_deleted=0 and r.id=ro.resource_id and ro.assign_type=2 and ro.owner_id=? "+
						"union " +
						"select distinct r.id,r.name,r.url,r.is_leaf,r.parent_id,r.type,r.order_code "+
						"from sys_resource r,um_resource_owner ro,um_user_role ur where ro.is_deleted=0 and r.id=ro.resource_id and " +
						"ro.owner_id=ur.role_id and ro.assign_type=3 and ur.user_id=? "+
						") t order by t.order_code";
		
		List resultList = this.getJdbcTemplate().queryForList(queryString,userId,userName,userId);
		Map map = null;
		for (int i = 0; i < resultList.size(); i++) {
			Resource resource = new Resource();
			map = (Map) resultList.get(i);
			resource.setId(StrUtil.ObjectToLong(map.get("id")));
			resource.setParentId(StrUtil.ObjectToLong(map.get("parent_id")));
			resource.setName(StrUtil.ObjectToString(map.get("name")));
			resource.setUrl(StrUtil.ObjectToString(map.get("url")));
			resource.setType(StrUtil.ObjectToInteger(map.get("type")));
			resource.setOrderCode(StrUtil.ObjectToInteger(map.get("order_code")));
			resource.setIsLeaf(StrUtil.ObjectToInteger(map.get("is_leaf")));
			list.add(resource);
		}
		return list;
	}
}
