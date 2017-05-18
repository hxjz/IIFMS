package com.iif.system.priv.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.utils.StrUtil;
import com.iif.system.resource.entity.Resource;

/**
 * @author LiuM
 * @date 2014-08-28
 */
@Repository()
public class AssignPrivDao extends BaseDao {
	
	
	/**
	 * 删除权限与拥有者关系
	 * @param ids 主键
	 * @return
	 */
	public Integer deleteAssignByIds(String ownerId, Integer assignType, String ids){
		if(StringUtils.isBlank(ids)){
			return 0;
		}
		String sql="delete from um_resource_owner where owner_id='"+ownerId+"' and assign_type=? and resource_id in ("+ids+")";
		return super.getJdbcTemplate().update(sql,assignType);
	} 
	
	/**
	 * 根据父ID获取可用的菜单
	 * @param ownerId 权限拥有者
	 * @param assignType 拥有者类型
	 * @param parentId 父级菜单id
	 * @return
	 */
	public List<Resource> findEnableMenuByParentId(String ownerId, Integer assignType, Long parentId) {
		String queryString = " select r from Resource r,ResourceOwner ro where r.id=ro.resourceId and ro.ownerId='"+ownerId+"' and ro.assignType=? and r.parentId =? and ro.isDeleted=0 and r.isEnable!=0 order by r.orderCode";
		List list = super.getHibernateTemplate().find(queryString, assignType, parentId);
		return list;
	}
	
	/**
	 * 根据parentId获取用户的权限资源
	 */
	public List<Resource> findEnableMenuByUserIdAndParentId(Long userId, Long parentId) {
		String queryUser = "select u.username from um_user u where u.id=?";
		List resultList = this.getJdbcTemplate().queryForList(queryUser,userId);
		String userName = StrUtil.ObjectToString(((Map) resultList.get(0)).get("username"));
		
		String queryString = "select * from (select distinct r.id,r.name,r.is_leaf,r.order_code "+
		"from sys_resource r,um_resource_owner ro,um_user u where ro.is_deleted=0 and r.id=ro.resource_id and " +
		"to_number(ro.owner_id)=u.org_id and ro.assign_type=1 and u.id=? and r.parent_id=? "+
		"union "+
		"select distinct r.id,r.name,r.is_leaf,r.order_code "+
		"from sys_resource r,um_resource_owner ro where ro.is_deleted=0 and r.id=ro.resource_id and ro.assign_type=2 " +
		"and ro.owner_id='"+userName+"' and r.parent_id=?"+
		"union " +
		"select distinct r.id,r.name,r.is_leaf,r.order_code "+
		"from sys_resource r,um_resource_owner ro,um_user_role ur where ro.is_deleted=0 and r.id=ro.resource_id and " +
		"to_number(ro.owner_id)=ur.role_id and ro.assign_type=3 and ur.user_id=? and r.parent_id=? "+
		") t order by t.order_code";
		
		resultList = this.getJdbcTemplate().queryForList(queryString,userId,parentId,parentId,userId,parentId);
		List list = new ArrayList();
		Map map = null;
		for (int i = 0; i < resultList.size(); i++) {
			Resource resource = new Resource();
			map = (Map) resultList.get(i);
			resource.setId(StrUtil.ObjectToLong(map.get("id")));
			resource.setName(StrUtil.ObjectToString(map.get("name")));
			resource.setIsLeaf(StrUtil.ObjectToInteger(map.get("is_leaf")));
			list.add(resource);
		}
		
		return list;
	}
	
	/**
	 * 根据用户id和父ID获取从部门和角色中获取的菜单
	 * @param userId 用户id
	 * @param parentId 父级菜单id
	 * @return
	 */
	public List<Resource> findUserResExtendsDeptAndRoles(Long userId, Long parentId) {
		String queryString = "select * from (select distinct r.id,r.name,r.is_leaf,r.order_code "+
		"from sys_resource r,um_resource_owner ro,um_user u where ro.is_deleted=0 and r.id=ro.resource_id and " +
		"to_number(ro.owner_id)=u.org_id and ro.assign_type=1 and u.id=? and r.parent_id=? "+
		"union " +
		"select distinct r.id,r.name,r.is_leaf,r.order_code "+
		"from sys_resource r,um_resource_owner ro,um_user_role ur where ro.is_deleted=0 and r.id=ro.resource_id and " +
		"to_number(ro.owner_id)=ur.role_id and ro.assign_type=3 and ur.user_id=? and r.parent_id=? "+
		") t order by t.order_code";
		
		List resultList = this.getJdbcTemplate().queryForList(queryString,userId,parentId,userId,parentId);
		List list = new ArrayList();
		Map map = null;
		for (int i = 0; i < resultList.size(); i++) {
			Resource resource = new Resource();
			map = (Map) resultList.get(i);
			resource.setId(StrUtil.ObjectToLong(map.get("id")));
			resource.setName(StrUtil.ObjectToString(map.get("name")));
			resource.setIsLeaf(StrUtil.ObjectToInteger(map.get("is_leaf")));
			list.add(resource);
		}
		
		return list;
	}
}
