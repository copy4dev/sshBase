package com.copy4dev.sshBase.base.service;

import java.io.Serializable;

/**
 * 抽象基础service层
 * @author Admin
 * @version 2016年9月27日
 * 
 * @param <T>
 */
public interface IBaseService<T> {

	/**
	 * 保存一个实体
	 * 
	 * @param entity
	 * @return Serializable
	 */
	public Serializable save(T entity);

	/**
	 * 根据id获取一个实体
	 * 
	 * @param entityClass
	 * @param id
	 * @return T
	 */
	public T get(Class<T> entityClass, Serializable id);

	/**
	 * 更新一个实体
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 删除一个实体
	 * 
	 * @param entity
	 */
	public void delete(T entity);

}
