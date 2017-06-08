package com.iif.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxjz.common.core.orm.BaseService;
import com.iif.storage.dao.IStorageDao;
import com.iif.storage.service.IStorageService;

@Service
public class StorageServiceImpl extends BaseService implements IStorageService{
	@Autowired
	private IStorageDao storageDao;
	
	
}
