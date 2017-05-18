package com.iif.system.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxjz.common.core.orm.BaseService;
import com.iif.system.code.dao.IDictionaryDao;
import com.iif.system.code.service.IDictionaryService;

@Service
public class DictionaryServiceImpl extends BaseService implements IDictionaryService{
	@Autowired
	private IDictionaryDao dictionaryDao;
	
	
}
