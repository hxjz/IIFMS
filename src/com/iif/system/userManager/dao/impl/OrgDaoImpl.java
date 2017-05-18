package com.iif.system.userManager.dao.impl ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.SystemConstant;
import com.iif.system.userManager.entity.Org;

/**
 * 部门DAO
 * 
 * @author LiuM
 * @date 2014-08-28
 */
@Repository()
public class OrgDaoImpl extends BaseDao {

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public Org findRootOrg() {
		String queryString = null ;
		// queryString = " from Org o where o.parentId is null and isDel=0";
		// String orgPath = SystemConstant.getSystemConstant("show_parent_org");
		queryString = " from Org o where effStatus = 'A' and o.parentPath is null and isDel=0" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString) ;
		//list = super.getHibernateTemplate().find(queryString) ;
		return list.get(0) ;
	}

	/**
	 * 根据部门编号得到部门信息
	 * 
	 * @param setid
	 *        公司setid
	 * @param deptid
	 *        部门编号
	 * @return
	 */
	public Org findOrgByDeptid(String deptid , String setid) {
		String queryString = " from Org o where o.isDel=0 and o.effStatus='A' and o.setid=? and o.deptid = ?" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString , setid , deptid) ;
		if (list.isEmpty()) {
			return null ;
		}
		return list.get(0) ;
	}

	/**
	 * 根据部门级别得到部门信息
	 * 
	 * @param setid
	 *        公司setid
	 * @param deptid
	 *        部门编号
	 * @param hlDeptGra
	 *        部门级别
	 * @return
	 */
	public Org findOrgByDeptGra(String deptid , String setid , String hlDeptGra) {
		String queryString = " from Org o where o.isDel=0 and o.effStatus='A' and o.setid=? and o.deptid = ? and o.hlDeptGra = ?" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString , setid , deptid , hlDeptGra) ;
		if (list.isEmpty()) {
			return null ;
		}
		return list.get(0) ;
	}

	/**
	 * 根据部门级别得到所有部门信息
	 * 
	 * @param setid
	 *        公司setid
	 * @param hlDeptGra
	 *        部门级别
	 * @return
	 */
	public List<Org> findAllOrgByDeptGra(String setid , String hlDeptGra) {
		String queryString = " from Org o where o.isDel=0 and o.effStatus='A' and o.setid=? and o.hlDeptGra = ?" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString , setid , hlDeptGra) ;
		if (list.isEmpty()) {
			return null ;
		}
		return list ;
	}

	/**
	 * 是否含有叶子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public Long hasCountChild(Long parentId) {
		String queryString = " select count(*) from Org where parentId=? and is_del=0" ;
		List list = this.getHibernateTemplate().find(queryString , parentId) ;
		Long count = (Long) list.get(0) ;
		return count ;
	}

	/**
	 * 初始化根节点
	 * 
	 * @return
	 */
	public Org initRootOrg() {
		String queryString = null ;
		queryString = " from Org o where o.parentId is null and isDel=0" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString) ;
		Org rootOrg = null ;
		// 如果根节点不存在，则初始化根节点信息
		if (list.size() == 0) {
			rootOrg = new Org() ;
			rootOrg.setName(SystemConstant.getSystemConstant("init_root_org_name")) ;
			rootOrg.setIsLeaf(1) ;
			rootOrg.setIsDel(0) ;
			save(rootOrg) ;
		} else {
			rootOrg = list.get(0) ;
			rootOrg.setName(SystemConstant.getSystemConstant("init_root_org_name")) ;
			update(rootOrg) ;
		}

		return rootOrg ;
	}

	/**
	 * 根据父ID获取对象
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Org> findByParentId(Long parentId) {
		if (null == parentId) {
			return (List<Org>)this.getHibernateTemplate().find(" from Org where parentId is null and isDel=0 order by orderCode asc") ;
		} else {
			return (List<Org>)this.getHibernateTemplate().find(" from Org where parentId=? and isDel=0 order by orderCode asc" , parentId) ;
		}
	}

	/**
	 * 根据父path获取对象
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Org> findByParentPath(String hlTreePath) {
		if (null == hlTreePath) {
			return (List<Org>)this.getHibernateTemplate().find(" from Org where effStatus = 'A' and parentPath is null and isDel = 0 order by orderCode asc") ;
		} else {
			return (List<Org>)this.getHibernateTemplate().find(" from Org where effStatus = 'A' and parentPath=? and isDel = 0 order by orderCode asc" , hlTreePath) ;
		}
	}

	/**
	 * 根据hlTreepath获取对象
	 * 
	 * @param hlTreepath
	 * @return
	 */
	public Org findOrgByTreepath(String hlTreepath , String setid) {
		String queryString = null ;
		queryString = " from Org o where o.effStatus = 'A' and o.isDel=0 and o.hlTreepath = ? and o.setid = ?" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString , hlTreepath , setid) ;
		if (list.isEmpty()) {
			return null ;
		}
		return list.get(0) ;
	}

	/**
	 * 根据hlPath取得树结构列表
	 * 
	 * @param pathList
	 * @param setId
	 * @return
	 */
	public List<Org> findOrgByTreepathList(List<String> pathList , String setId) {
		StringBuffer queryBuffer = new StringBuffer(" from Org o where o.effStatus = 'A' and o.isDel = 0 and o.hlTreelevel = 1" + " and ") ;
		queryBuffer.append(" o.setid = '").append(setId).append("' ") ;
		/*
		 * if(pathList != null && !pathList.isEmpty()){ queryBuffer.append(" and
		 * (") ; for(String str : pathList){ queryBuffer.append(" o.hlTreepath =
		 * '").append(str).append("' or ") ; } //queryBuffer.substring(0 ,
		 * queryBuffer.lastIndexOf(" or )) ; if
		 * (queryBuffer.toString().endsWith(" or ")) { queryBuffer =
		 * queryBuffer.delete(queryBuffer.length() - 4, queryBuffer.length()); }
		 * queryBuffer.append(")") ; }
		 */
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryBuffer.toString()) ;
		if (list == null || list.isEmpty()) {
			return null ;
		}
		return list ;
	}

	/**
	 * 根据hlTreepath和部门级别获取对象
	 * 
	 * @param hlTreepath
	 * @return
	 */
	public Org findOrgByPathDeptGra(String hlTreepath , String hlDeptGra , String setid) {
		String queryString = null ;
		queryString = " from Org o where o.effStatus = 'A' and o.isDel=0 and o.hlTreepath =? and o.hlDeptGra = ? and o.setid=?" ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryString , hlTreepath , hlDeptGra , setid) ;
		if (list.isEmpty()) {
			return null ;
		}
		return list.get(0) ;
	}

	/**
	 * 分页获取对象
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findAllByPage(Page page , Map searchMap) {
		StringBuffer query = new StringBuffer(" from Org where isDel=0 and effStatus = 'A' ") ;
		List valueList = new ArrayList() ;
		OrmConverter.assemblyQuery(query , searchMap , valueList) ;
		return super.findByHql(page , query , valueList) ;
	}

	/**
	 * 分页获取对象
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page , Map searchMap) {
		StringBuffer query = new StringBuffer(" from Org where isDel=0 and effStatus = 'A' ") ;
		List valueList = new ArrayList() ;
		Object parameter = searchMap.get("filter_and_parentPath_EQ_S") ;
		if (parameter == null) {
			query.append(" and parentPath is null ") ;
		} else {
			OrmConverter.getQuery(query , searchMap , "filter_and_parentPath_EQ_S" , valueList) ;
		}
		query.append(" order by orderCode asc") ;
		return super.findByHql(page , query , valueList) ;
	}

	/**
	 * 根据店名称获取店list
	 * 
	 * @param name
	 * @return
	 */
	public List<Org> getOrgListByName(String name) {
		String query = "from Org o where o.effStatus = 'A' and o.isDel=0 and o.descrshort = ? order by o.effdt desc" ;
		List<Org> orgList = (List<Org>)super.getHibernateTemplate().find(query , name) ;
		return orgList ;
	}

	/**
	 * 根据hlTreepath获取运营
	 * 
	 * @param hlTreepath
	 * @param setId
	 * @return
	 */
	public List<Org> findOrgOperationsByTreepathList(String hlTreepath , String setId) {
		String queryBuffer = " from Org o where o.effStatus = 'A' and o.isDel = 0" + " and o.setid = ? and o.hlTreepath = ?" ;
		// queryBuffer.append(" o.setid = '").append(setId).append("' ") ;
		// queryBuffer.append("and o.hlTreepath =
		// '").append(hlTreepath).append("' ") ;
		List<Org> list = (List<Org>)super.getHibernateTemplate().find(queryBuffer , setId , hlTreepath) ;
		if ( list == null || list.isEmpty()) {
			return null ;
		}
		return list ;
	}

	/**
	 * 获取运营下面的所有的区域
	 * 
	 * @author zhangyan
	 * @see SupplierAction.java-->"供应商选择区域"
	 * @param hlTreepath
	 *        运营的hlTreepath
	 * @param hlTreepath1
	 *        运营失效部门的hlTreepath
	 * @param hlTreepath2
	 *        人员待分配中心hlTreepath
	 * @param setId
	 * @return
	 */
	public List<Org> findByOrgAreaParentPath(String hlTreePath , String hlTreepath1 , String hlTreepath2) {

		return (List<Org>)this.getHibernateTemplate().find(" from Org where effStatus = 'A' and parentPath=? and isDel = 0 and hlTreelevel <= 5 and hlTreepath !=? and hlTreepath !=? order by orderCode asc" ,
				hlTreePath , hlTreepath1 , hlTreepath2) ;
	}
	/**
	 * 获取运营下所有店组
	 * @param hlTreePath
	 * @param hlTreepath1
	 * @param hlTreepath2
	 * @return
	 */
	public List<Org> findByParentPathAndHlTreepath(String hlTreePath , String hlTreepath1 , String hlTreepath2) {

		return (List<Org>)this.getHibernateTemplate().find(" from Org where effStatus = 'A' and parentPath=? and isDel = 0  and hlTreepath !=? and hlTreepath !=? order by orderCode asc" ,
				hlTreePath , hlTreepath1 , hlTreepath2) ;
	}


}
