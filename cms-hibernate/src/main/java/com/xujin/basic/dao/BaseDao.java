package com.xujin.basic.dao;


/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * @author Administrator
 *
 * @param <T>
 */

public interface BaseDao<T> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(int id);
	
	
	
}

