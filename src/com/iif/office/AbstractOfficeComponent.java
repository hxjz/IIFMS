package com.iif.office;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * adapter pattern
 * 
 * @author LiuM
 * 
 */
@SuppressWarnings("unchecked")
public abstract class AbstractOfficeComponent<T> implements OfficeComponent<T> {

	protected static final SimpleDateFormat DATE = new SimpleDateFormat(
			"yyyy-MM-dd");
	protected static final SimpleDateFormat DATETIME = new SimpleDateFormat(
			"yyyy-MM-dd mm:HH:ss");

	/**
	 * 是否启动重写
	 */
	private boolean rewrite;
	private Class<T> entityClass;

	{
		Type type = getClass().getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) type;
		entityClass = (Class<T>) p.getActualTypeArguments()[0];
	}

	// constructor

	public AbstractOfficeComponent() {
	}

	public AbstractOfficeComponent(boolean rewrite) {
		this.rewrite = rewrite;
	}

	/**
	 * 
	 * @return
	 */
	public T newEntity() {
		T t = null;
		try {
			t = entityClass.newInstance();
			// System.out.println(p);
			// System.out.println(entityClass);
			// System.out.println(t instanceof PublicItemsConfig);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	// API

	@Override
	public void create(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultEntity<T> readForList(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	// get set method

	public boolean isRewrite() {
		return rewrite;
	}

	public void setRewrite(boolean rewrite) {
		this.rewrite = rewrite;
	}

}
