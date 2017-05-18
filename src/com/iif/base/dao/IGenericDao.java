package com.iif.base.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import com.hxjz.common.core.orm.IBaseDao;
import com.iif.base.entity.Options;

public interface IGenericDao extends IBaseDao {

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
	 * @param clazz
	 * @param proj
	 * @return
	 */
	<T extends Options> List<T> findOptions(DetachedCriteria dc,
			String keyField, String valueField, Class<T> clazz,
			Projection... proj);

}
