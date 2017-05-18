package com.iif.system.resource.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;
import com.hxjz.common.utils.Page;
import com.iif.system.resource.entity.Resource;

/**
 * <p>@desc : 资源Dao</p>
 * <p>@see : </p>
 *
 * <p>@author : LiuM</p>
 * <p>@createDate : 2014-08-28</p>
 * <p>@version : v1.0 </p>
 * <p>ZiRoom</p>
 */
public interface IResourceDao extends IBaseDao {
	/**
	 * 获取菜单项
	 * @return
	 */
	public List<Resource> findMenuByType();
		
	/**
	 * 获取可用的菜单
	 * 
	 * @return
	 */
	public List<Resource> findAllEnable();
	
	/**
	 * 获取所有按钮资源
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> findAllButtonResource();

	/**
	 * 获取可以访问的模块
	 * 
	 * @return
	 */
	public List<Resource> findAllEnableModuleResource();

	/**
	 * 根据父ID获取可用的菜单
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Resource> findEnableMenuByParentId(Long parentId);
	
	/**
	 * 根据父ID获取可用的菜单和button
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Resource> findEnableMenuAndButtonByParentId(Long parentId);
	
	/**
	 * 获取菜单的叶子节点下的按钮
	 * @param 父节点id，即菜单的叶子节点id
	 * @return
	 */
	public List<Resource> findEnableButtonByParentId(Long parentId);

	/**
	 * 基于jdbc保存菜单
	 * 
	 * @param resource
	 */
	public void saveForJdbc(Resource resource);

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public Resource findRootResource();

	public List<Resource> findBaseResources();

	/**
	 * 获取用户可以访问的资源
	 * 
	 * @param userId
	 * @return
	 */
	public List<Resource> findAllEnableByUserId(Long userId);

	/**
	 * 根据ID获取父ID
	 * 
	 * @param menuId
	 * @return
	 */
	public Long findParentIdByIdForJdbc(Long menuId);

	/**
	 * 根据父ID显示资源树
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Resource> findByParentId(Long parentId);

	/**
	 * 删除菜单资源
	 */
	public void deleteAllResource();

	public List<Resource> findEnableOperation();

	/**
	 * 获取用户授权的资源
	 * 
	 * @param userId
	 * @return
	 */
	public List<Resource> findAllEnableForUserId(Long userId);
	
	public List<Resource> findByModulePage(Page page , Map paramsMap);
	
	public List<Resource> findByparentId(Page page , Map paramsMap,Long parentId);

	/**
	 * 获取菜单权限
	 * @param page
	 * @param paramsMap
	 * @param parentId
	 */
	public  String findResourceConfig(String account);
	
}
