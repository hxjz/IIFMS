package com.iif.system.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.hxjz.common.utils.Page;
import com.iif.system.log.dao.IOperationLogDao;
import com.iif.system.log.service.IOperationLogService;

@Service(value = "com.iif.system.log.service.OperationLogService")
@Transactional()
public class OperationLogServiceImpl extends BaseService implements IOperationLogService {

	@Autowired
	private IOperationLogDao operationLogDao = null;

	/**
	 * 分页获取操作日志
	 * 
	 * @param page
	 * @param searchMap
	 * @return
	 */
	public List findByPage(Page page, Map searchMap) {
		return operationLogDao.findByPage(page, searchMap);
	}
}
