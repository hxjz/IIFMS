package com.iif.office;

/**
 * 
 * @author LiuM
 * 
 * @param <T>
 */
public interface PersistenceComponent<T> {

	/**
	 * 持久化实体
	 * 
	 * @param entity
	 * @throws Exception
	 */
	void persist(T entity) throws Exception;

}
