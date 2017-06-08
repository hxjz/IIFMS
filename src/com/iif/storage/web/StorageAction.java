package com.iif.storage.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.util.TemplateUtil;
import com.iif.storage.entity.Storage;
import com.iif.storage.service.IStorageService;

/**
  * 存储位置管理Action
  * @Author LiuM
  * @Date 2017
  * @Version V0.1
 */
@Controller
@RequestMapping("/storage/*")
public class StorageAction extends BaseAction{
	@Autowired
	private IStorageService storageService;
	@SuppressWarnings("rawtypes")
	private Page page;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping("tolistStorage.action")
	public String tolistStorage() {
		return "/jsp/storage/listStorage";
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("showStorage.action")
	@ResponseBody
	public Map showStorage(){
		// 分页
		int pageNum = HttpTool.getIntegerParameter("page");
		int rows = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, rows);
		// searchMap
		Map searchMap = super.buildSearch();
		searchMap.put("order_updateTime", "desc");
		
		// 查询数据
		List<Storage> dic = storageService.findByPage(page, searchMap);
		
		return TemplateUtil.toDatagridMap(page, dic);
	}	
	
	///////////////////////////////////////
	//
	//
	//
	////////////////////////////////////////
	private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping("toChooseStorage.action")
	public String toChooseStorage() {
	    // 原始的数据
	    List<Storage> rootStorage = storageService.findAll();
	
	    // 查看结果
	    for (Storage storage : rootStorage) {
	        System.out.println(storage);
	    }
	    // 最后的结果
	    List<Storage> storageList = new ArrayList<Storage>();
	    // 先找到所有的一级菜单
	    for (int i = 0; i < rootStorage.size(); i++) {
	        // 一级菜单没有parentId
	        if (StringUtils.isBlank(rootStorage.get(i).getParentId())) {
	            storageList.add(rootStorage.get(i));
	        }
	    }
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (Storage storage : storageList) {
	        storage.setChild(getChild(storage.getKey(), rootStorage));
	    }
	    Map<String,Object> jsonMap = new HashMap();
	    jsonMap.put("storage", storageList);
	    System.out.println(gson.toJson(jsonMap));

        HttpTool.setAttribute("storageList", storageList);
	    
	    return "jsp/storage/chooseStorage";
	
	}
	
	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootStorage
	 *            要查找的列表
	 * @return
	 */
	private List<Storage> getChild(String key, List<Storage> rootStorage) {
	    // 子菜单
	    List<Storage> childList = new ArrayList();
	    for (Storage storage : rootStorage) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (StringUtils.isNotBlank(storage.getParentId())) {
	            if (storage.getParentId().equals(key)) {
	                childList.add(storage);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (Storage storage : childList) {// 没有Avail子菜单还有子菜单
	        if (3 == storage.getIsAvail()) {
	            // 递归
	            storage.setChild(getChild(storage.getId(), rootStorage));
	        }
	    } // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}

	////////////////////////////////////////
	//
	//
	//
	////////////////////////////////////////
	
	
	
	/**
	 * 添加枚举信息
	 * @param dic Storage实体
	 * @return 提示信息
	 */
	@RequestMapping("addStorage.action")
	public void addStorage(Storage dic){
		dic.setCreateTime(new Date());
		dic.setUpdateTime(new Date());
		dic.setIsDel(0);
		storageService.save(dic);
	}
}