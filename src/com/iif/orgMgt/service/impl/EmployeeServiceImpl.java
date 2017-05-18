package com.iif.orgMgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.iif.orgMgt.dao.IEmployeeDao;
import com.iif.orgMgt.service.IEmployeeService;

/**
 * 员工Service
 * @author lxp
 * @date   2014-11-19
 */
@Service
@Transactional()
public class EmployeeServiceImpl extends BaseService implements IEmployeeService{
	
	@Autowired
	private IEmployeeDao employeeDao = null;
}
