package com.iif.system.userManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.iif.system.priv.entity.ResourceOwner;
import com.iif.system.userManager.entity.UserRole;

@Repository()
public class UserRoleDaoImpl extends BaseDao {
	public UserRole findByUserAnaRoleId(Long userId,Long roleId){
		String sql = "from UserRole where userId =? and roleId = ?";
		List<UserRole> list = (List<UserRole>)this.getHibernateTemplate().find(sql,userId,roleId);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 获取权限与拥有者关系
	 * @param ownerId 分配对象的id（部门id 用户name 或角色id）
	 * @param assignType 权限分配类型：1部门，2用户，3角色
	 * @return
	 * zhangyan
	 */
	public List<ResourceOwner> findByOwnerIdAndAssignType(String ownerId,Integer assignType){
		return (List<ResourceOwner>)this.getHibernateTemplate().find("from ResourceOwner where ownerId = ? and assignType = ?",ownerId,assignType);
	}
}
