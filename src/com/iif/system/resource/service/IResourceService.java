package com.iif.system.resource.service;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseService;
import com.hxjz.common.utils.Page;
import com.iif.system.resource.entity.Resource;

/**
 * 资源Service实现类
 * 
 * @author LiuM
 * @date 2014-08-28
 * 
 */
public interface IResourceService extends IBaseService {

	/**
	 * 获取菜单项
	 * @return
	 */
	public List<Resource> findMenuByType();
	
	/**
	 * 分页查询模块
	 * @param page
	 * @param paramsMap
	 * @return
	 */
	public List<Resource> findByModulePage(Page page , Map paramsMap);
	
	/**
	 * 获取所有可用的系统资源
	 */
	public List<Resource> findAllEnable();

	/**
	 * 获取可以访问的模块级别的资源
	 */
	public List<Resource> findAllEnableModuleResource();

	/**
	 * 修改资源
	 */
	public void update(Resource resource);

	/**
	 * 根据父ID获取可用的资源
	 */
	public List<Resource> findEnableMenuByParentId(Long parentId);
	
	/**
	 * 根据父ID获取可用的资源(包含按钮)
	 */
	public List<Resource> findEnableMenuAndButtonByParentId(Long parentId);

	/**
	 * 获取菜单的叶子节点下的按钮
	 * @param 父节点id，即菜单的叶子节点id
	 * @return
	 */
	public List<Resource> findEnableButtonByParentId(Long parentId);
	
	/**
	 * 修改资源状态
	 */
	public boolean updateEnable(Long menuId);

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public Resource findRootResource();

	/**
	 * 根据父ID获取集合
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Resource> findByParentId(Long parentId);

	public List<Resource> findEnableOperation();
	
	public List<Resource> findByparentId(Page page , Map paramsMap,Long parentId);
	
	public List<Resource> findBaseResource();

	/**
	 * 获取菜单权限
	 * @param page
	 * @param paramsMap
	 * @param parentId
	 */
	public String findResourceConfig(String account);
	
	/**
	 * 获取所有按钮资源
	 * @return
	 */
	public List<Resource> findAllButtonResource();
	
	public List<Resource> findByFilterMap(Map searchMap);
}
