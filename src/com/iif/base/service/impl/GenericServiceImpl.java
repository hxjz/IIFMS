package com.iif.base.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iif.base.dao.IGenericDao;
import com.iif.base.entity.Options;
import com.iif.base.service.IGenericService;
import com.iif.base.type.OptionType;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class GenericServiceImpl implements IGenericService {

	@Autowired
	private IGenericDao genericDao;

	@Override
	public <T> List<T> findPage(Class<T> clazz, int firstResult, int maxResults) {
		return genericDao.findPage(clazz, firstResult, maxResults);
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		return genericDao.findAll(clazz);
	}

	@Override
	public <T> void batchInserts(List<T> list) {
		genericDao.batchInserts(list);
	}

	@Override
	public List<? extends Options> findOptions(DetachedCriteria dc,
			String keyField, String valueField, OptionType ot) {
		return genericDao.findOptions(dc, keyField, valueField, ot.bean(), ot.Projections());
	}

}
