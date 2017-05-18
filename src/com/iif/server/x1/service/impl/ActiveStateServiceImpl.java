package com.iif.server.x1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.iif.server.x1.dao.IActiveStateDao;
import com.iif.server.x1.service.IActiveStateService;

@Transactional
@Service
public class ActiveStateServiceImpl extends BaseService implements IActiveStateService {
	@Autowired
	private IActiveStateDao activeStateDao;
}
