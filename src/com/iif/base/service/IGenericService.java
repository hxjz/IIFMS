package com.iif.base.service;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.iif.base.entity.Options;
import com.iif.base.type.OptionType;

public interface IGenericService {

	/**
	 * 
	 * @param clazz
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	<T> List<T> findPage(Class<T> clazz, int firstResult, int maxResults);

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	<T> List<T> findAll(Class<T> clazz);

	/**
	 * 
	 * @param list
	 */
	<T> void batchInserts(List<T> list);

	/**
	 * 
	 * @param dc
	 * @param keyField
	 * @param valueField
	 * @param type
	 * @return
	 */
	List<? extends Options> findOptions(DetachedCriteria dc, String keyField,
			String valueField, OptionType type);

}
