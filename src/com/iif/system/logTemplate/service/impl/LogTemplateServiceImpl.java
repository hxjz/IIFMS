package com.iif.system.logTemplate.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxjz.common.core.orm.BaseService;
import com.iif.system.logTemplate.dao.ILogTemplateDao;
import com.iif.system.logTemplate.service.ILogTemplateService;

@Service(value="LogTemplateService")
public class LogTemplateServiceImpl extends BaseService implements ILogTemplateService {

	@Autowired
	private ILogTemplateDao logTemplateDao = null;
	
	/* (non-Javadoc)
	 * @seeILogTemplateService#initLogTemplate()
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void initLogTemplate(){
		List logTempList = logTemplateDao.findAllLogTemplate();
		
		for (Object temp : logTempList) {
			Map logTemp = (Map) temp;
			BigDecimal  data = (BigDecimal ) logTemp.get("DATATYPE");
			BigDecimal  deal = (BigDecimal ) logTemp.get("DEALTYPE");
			BigDecimal  isOpen = (BigDecimal ) logTemp.get("ISOPEN");
			String template = (String) logTemp.get("TEMPLATE");
			//redis的value值为map，放入isOpen和template
			Map tempMap = new HashMap();
			tempMap.put("isOpen", isOpen);
			tempMap.put("template", template);
			//存入redis的key规则与logUtil获取的key规则一样
			//RedisUtil.setObject(SysConstant.LOGTEMPLATE+data+SysConstant.SPLIT_CHAR_LINE+deal, tempMap);
		}
	}
}
