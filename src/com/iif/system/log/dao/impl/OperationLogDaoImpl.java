package com.iif.system.log.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.hxjz.common.utils.Page;
import com.iif.system.log.dao.IOperationLogDao;

/**
 * <p>@desc : 操作日志处理Dao</p>
 * <p>@see : </p>
 *
 * <p>@author : LiuM</p>
 * <p>@createDate : 2017</p>
 * <p>@version : V0.1 </p>
 * <p>HXJZ</p>
 */

@Repository()
public class OperationLogDaoImpl extends BaseDao implements IOperationLogDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findByPage(Page page, Map searchMap) {
		StringBuffer query = new StringBuffer(" from OperationLog where 1=1");
		List valueList = new ArrayList();
		OrmConverter.getQuery(query, searchMap, "filter_and_loginLogId_EQ_L", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_userName_LIKE_S", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_operationDate_GE_T", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_operationDate_LE_T", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_businessName_LIKE_S", valueList);
		query.append(" order by operationTime desc");
		return super.findByHql(page, query, valueList);
	}

}
