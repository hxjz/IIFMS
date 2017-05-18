package com.iif.base.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.hxjz.common.core.orm.BaseDao;
import com.iif.base.dao.IGenericDao;
import com.iif.base.entity.Options;

/**
 * 对BaseDao进行的二次封装
 * 
 * @author xlg
 * 
 */
@Repository(value = "genericTypeDao")
public class GenericDao extends BaseDao implements IGenericDao {

	@Override
	public <T> void batchInserts(List<T> list) {
		SessionFactory sessionFactory = getHibernateTemplate()
				.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		int i = 0;
		boolean flush = false;
		for (T t : list) {
			session.save(t);
			flush = ++i % 20 == 0;
			if (flush) {
				session.flush();
				session.clear();
			}
		}
		if (!flush) {
			session.flush();
			session.clear();
		}
		tx.commit();
		session.close();
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Options> List<T> findOptions(DetachedCriteria dc,
			String keyField, String valueField, Class<T> clazz,
			Projection... proj) {
		ProjectionList pl = Projections.projectionList();
		pl.add(Projections.property(keyField).as("key"));
		pl.add(Projections.property(valueField).as("value"));
		if (proj != null)
			for (Projection pro : proj)
				pl.add(pro);
		dc.setProjection(pl);
		dc.setResultTransformer(Transformers.aliasToBean(clazz));
		return getHibernateTemplate().findByCriteria(dc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findPage(Class<T> clazz, int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(
				criteria, firstResult, maxResults);
		return list;
	}

}
