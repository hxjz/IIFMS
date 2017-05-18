package com.iif.system.logTemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.iif.system.logTemplate.dao.ILogTemplateDao;

/**
 * 日志模板dao
 * 
 * @author LiuM
 * @date 2014-08-28
 */
@Repository()
public class LogTemplateDaoImpl extends BaseDao implements ILogTemplateDao {
	
	@Override
	@SuppressWarnings("rawtypes")
	public List findAllLogTemplate(){
		String sql = "select tdt.fdatatype DATATYPE,tdt.fdealtype DEALTYPE, tdt.is_open ISOPEN, tdt.ftemplate TEMPLATE from tdealtemplate tdt where tdt.is_del = 0";
		return getJdbcTemplate().queryForList(sql);
	}

}
