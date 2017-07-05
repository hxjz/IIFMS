package com.iif.system.code.web;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.enums.SystemTypeEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.system.code.entity.Dictionary;
import com.iif.system.code.service.IDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
  * 数据字典 Action
  * @Author LiuM
  * @Date 2017
  * @Version V0.1
 */
@Controller
@RequestMapping("/system/*")
public class DictionaryAction extends BaseAction{
	@Autowired
	private IDictionaryService dictionaryService;
	@SuppressWarnings("rawtypes")
	private Page page;
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@RequestMapping("tolistDictionary.action")
	public String tolistDictionary() {
		List<?> systemTypeList = InitSelect.getSelectList(SystemTypeEnum.class);
		HttpTool.setAttribute("systemTypeList", systemTypeList);
		return "/jsp/system/dictionary/listDictionary";
	}
	
	/**
	 * 跳转列表页面
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("showDictionary.action")
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
	@RequestMapping("addDictionary.action")
	public void addDictionary(Dictionary dic){
		dic.setCreateTime(new Date());
		dic.setUpdateTime(new Date());
		dic.setIsDel(0);
		dictionaryService.save(dic);
	}

    /**
     * 删除数据字典，逻辑删除
     * @return
     */
	@ResponseBody
	@RequestMapping("delDictionary.action")
	public Map deleteDictionary(){
		String dictionaryId=HttpTool.getParameter("id");
		try{
			if(StringUtils.isNotEmpty(dictionaryId)) {
				Dictionary dictionary= (Dictionary) dictionaryService.findById(dictionaryId);
				dictionary.setIsDel(SysConstant.IS_DEL); //删除标示
				dictionary.setUpdater("admin");
				dictionary.setUpdateTime(new Date());
				dictionaryService.save(dictionary);
			}
			return TemplateUtil.toSuccessMap("操作成功！");
		}catch(Exception e){
			e.printStackTrace();
			return TemplateUtil.toSuccessMap("操作失败！");
		}
	}
}