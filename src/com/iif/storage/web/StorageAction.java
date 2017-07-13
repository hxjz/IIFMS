package com.iif.storage.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.enums.StorageTypeEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.SysConstant;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("tolistStorage.action")
	public String tolistStorage() {
		Map paramMap = new HashMap();
		paramMap.put("filter_and_parentId_EQ_S", SysConstant.SYSTEM_CON_ZER);
		paramMap.put("filter_and_isDel_EQ_I", SysConstant.SYSTEM_CON_ZER);
		List<?> rtnList = storageService.findByFilterMap(paramMap);
		List storageNameList = new ArrayList();
		if(!CollectionUtils.isEmpty(rtnList)){
			for(int i=0; i<rtnList.size(); i++) {
				Storage tempStorage = new Storage();
				tempStorage = (Storage)rtnList.get(i);
				
				Map addMap = new HashMap();
				addMap.put(SysConstant.SELECT_OPTION_VALUE, tempStorage.getId());
				addMap.put(SysConstant.SELECT_OPTION_TEXT, tempStorage.getName());
				storageNameList.add(addMap);
			}
		}
		HttpTool.setAttribute("storageNameList", storageNameList);
		
		return "/jsp/storage/listStorage";
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	@RequestMapping("showStorage.action")
	@ResponseBody
	public Map showStorage(){
		// 分页
		int pageNum = HttpTool.getIntegerParameter("page");
		int rows = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, rows);
		// searchMap
		Map searchMap = super.buildSearch();
		
		List lstRtn = storageService.findByPage(page, searchMap);
		
		// 返回页面显示
		return TemplateUtil.toDatagridMap(page, lstRtn);
	}

	/**
	 * 跳转新增/修改页面
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("toEditStorage.action")
	public String toEditStorage() {
		String strId = HttpTool.getParameter("id");
		HttpTool.setAttribute("id", strId);
		
		List lstStorageType = InitSelect.getSelectList(StorageTypeEnum.class);
		HttpTool.setAttribute("storageTypeList", lstStorageType);
		
	    return "jsp/storage/editStorage";
	}
	
	/**
	 * 保存存储位置信息
	 * @param Storage实体
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("saveStorage.action")
	@ResponseBody
	public Map saveStorage(Storage storage){
		// 新增或修改判断
		if(StringUtils.isNotBlank(storage.getId())) {
			
		}else {
			storage.setId(null);
			storage.setCreateTime(new Date());
			storage.setUpdateTime(new Date());
			storage.setIsDel(SysConstant.IS_NOT_DEL); // 删除标示
			storage.setLevel(SysConstant.SYSTEM_CON_ZER);// 存储层级
			storage.setIndex(0); // 排序
			storage.setIsAvail(1); // 默认启用
			storage.setStatus(0); //使用状态
			storage.setParentId(SysConstant.SYSTEM_CON_ZER);// 存储父节点
			storageService.save(storage);// 根存储保存
			
			String strLevel1 = HttpTool.getParameter("storageLevel1");
			if(StringUtils.isNotEmpty(strLevel1)) {
				String strLevel2 = HttpTool.getParameter("storageLevel2");
				
				for(int i=1; i<=Integer.parseInt(strLevel1); i++) {
					Storage storage1 = new Storage();
					storage1.setId(null);
					storage1.setParentId(storage.getId()); // 父节点Id
					storage1.setName(i+"");
					storage1.setComment(storage.getComment());
					storage1.setDevice(storage.getDevice()+(storage.getType().equals(SysConstant.SYSTEM_CON_ONE)?"排":"列"));
					storage1.setConUrl(null);
					storage1.setCreateTime(new Date());
					storage1.setUpdateTime(new Date());
					storage1.setType(storage.getType());
					storage1.setIsDel(SysConstant.IS_NOT_DEL); // 删除标示
					storage1.setLevel(SysConstant.SYSTEM_CON_ONE);// 存储层级
					storage1.setConUrl(null);
					storage1.setIndex(1); // 排序
					storage1.setIsAvail(1); // 默认启用
					storage1.setStatus(0); //使用状态
					storageService.save(storage1);// 一级存储保存
					
					if(StringUtils.isNotEmpty(strLevel2)) {
						String strLevel3 = HttpTool.getParameter("storageLevel3");
						int cnt = 1; 
						if(SysConstant.SYSTEM_CON_TWO.equals(storage.getType())&&SysConstant.SYSTEM_CON_ONE.equals(storage.getAbSide())) { // AB面控制是否选中
							cnt = 2;
						}
						
						for(int j=1; j<=Integer.parseInt(strLevel2); j++) {
							for(int k=1; k<=cnt; k++) {
								Storage storage2 = new Storage();
								storage2.setId(null);
								storage2.setParentId(storage1.getId()); // 父节点Id
								if(cnt==2) {
									if(k==1) {
										storage2.setName("A"+i);
									}else {
										storage2.setName("B"+i);
									}
								}else {
									storage2.setName(j+"");
								}
								storage2.setComment(storage.getComment());
								storage2.setDevice(storage.getDevice()+"组");
								storage2.setConUrl(null);
								storage2.setCreateTime(new Date());
								storage2.setUpdateTime(new Date());
								storage2.setType(storage.getType());
								storage2.setIsDel(SysConstant.IS_NOT_DEL); // 删除标示
								storage2.setLevel(SysConstant.SYSTEM_CON_TWO);// 存储层级
								storage2.setConUrl(null);
								storage2.setIndex(2); // 排序
								storage2.setIsAvail(1); // 默认启用
								storage2.setStatus(0); //使用状态
								storageService.save(storage2);// 二级存储保存
								
								if(StringUtils.isNotEmpty(strLevel3)) {
									Storage storage3 = new Storage();
									storage3.setId(null);
									storage3.setParentId(storage2.getId()); // 父节点Id
									storage3.setName(i+"");
									storage3.setComment(storage.getComment());
									storage3.setDevice(storage.getDevice()+(storage.getType().equals(SysConstant.SYSTEM_CON_ONE)?"门":"层"));
									storage3.setCreateTime(new Date());
									storage3.setUpdateTime(new Date());
									storage3.setType(storage.getType());
									storage3.setIsDel(SysConstant.IS_NOT_DEL); // 删除标示
									storage3.setLevel(SysConstant.SYSTEM_CON_THR);// 存储层级
									storage3.setConUrl(storage.getConUrl());
									storage3.setIndex(3); // 排序
									storage3.setIsAvail(1); // 默认启用
									storage3.setStatus(0); //使用状态
									storageService.save(storage3);// 三级存储保存
								}
							}
						}
					}
				}
			}
		}
		
		return TemplateUtil.toSuccessMap("操作成功！");
	}
	
	/**
	 * 存储位置删除功能
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("delStorage.action")
	@ResponseBody
	public Map delStorage() {
		String strId = HttpTool.getParameter("id");
		
		if(StringUtils.isNotEmpty(strId)) {
			Storage storage = (Storage)storageService.findById(strId);
			storage.setIsDel(SysConstant.IS_DEL);
			try{
				storageService.update(storage);
				return TemplateUtil.toSuccessMap("操作成功！");
			}catch(Exception e) {
				return TemplateUtil.toSuccessMap("操作失败！");
			}
		}
		
		return TemplateUtil.toSuccessMap("操作成功！");
	}
	
	/**
	 * 下拉列表联动获取
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping("getLevelInfo.action")
	@ResponseBody
	public Map getLevelInfo(){
		String paramStr = HttpTool.getParameter("parentId");
		Map paramMap = new HashMap();
		paramMap.put("filter_and_parentId_EQ_S", paramStr);
		paramMap.put("filter_and_isDel_EQ_I", SysConstant.SYSTEM_CON_ZER);
		List<?> lstRtn = storageService.findByFilterMap(paramMap);
		
		JSONArray ja = new JSONArray();
		String  strAfter= "";
		if(!CollectionUtils.isEmpty(lstRtn)) {
			for(int i=0; i<lstRtn.size(); i++) {
				Storage temStorage = new Storage();
				temStorage = (Storage)lstRtn.get(i);
				JSONObject jo = new JSONObject();
				jo.put(SysConstant.SELECT_OPTION_VALUE, temStorage.getId());
				// Type：1为存储区；2为密集柜/区
				if("1".equals(temStorage.getLevel())) {
					if("1".equals(temStorage.getType())) {
						strAfter = "排";
					}else {
						strAfter = "列";
					}
				}
				if("2".equals(temStorage.getLevel())) {
					strAfter = "组";
				}
				if("3".equals(temStorage.getLevel())) {
					if("1".equals(temStorage.getType())) {
						strAfter = "门";
					}else {
						strAfter = "层";
					}
				}
				jo.put(SysConstant.SELECT_OPTION_TEXT, "第"+temStorage.getName()+strAfter);
				ja.add(jo);
			}
		}
		
		// 返回页面显示
		return TemplateUtil.toSuccessMap(ja);
	}
}
