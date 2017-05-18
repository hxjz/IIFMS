package com.iif.system.code.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.util.TemplateUtil;
import com.iif.system.code.entity.Dictionary;
import com.iif.system.code.service.IDictionaryService;
@Controller
@RequestMapping("/system/dictionary.action")
public class DictionaryAction extends BaseAction{
	@Autowired
	private IDictionaryService dictionaryService;
	@SuppressWarnings("rawtypes")
	private Page page;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping(params="method=tolistDictionary")
	public String tolistDictionary() {
		return "/jsp/system/dictionary/listDictionary";
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(params="method=showDictionary")
	@ResponseBody
	public Map showDictionary(){
		// 分页
		int pageNum = HttpTool.getIntegerParameter("page");
		int rows = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, rows);
		// searchMap
		Map searchMap = super.buildSearch();
		searchMap.put("order_updateTime", "desc");
		
		// 查询数据
		List<Dictionary> dic = dictionaryService.findByPage(page, searchMap);
		
		return TemplateUtil.toDatagridMap(page, dic);
	}
	
	/**
	 * 添加枚举信息
	 * @param dic Dictionary实体
	 * @return 提示信息
	 */
	@RequestMapping(params="method=addDictionary")
	public void addDictionary(Dictionary dic){
		dic.setCreateTime(new Date());
		dic.setUpdateTime(new Date());
		dic.setIsDel(0);
		dictionaryService.save(dic);
	}
}
