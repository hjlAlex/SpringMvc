package com.wabao.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wabao.bean.PageBean;

/**
 * BaseDao的接口
 * 
 * @param <T>
 * @since 2017年11月16日 下午4:42:23
 * @author Administrator
 */
public interface BaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 * @since 2017年11月16日 下午4:42:39
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 * @since 2017年11月16日 下午4:43:14
	 */
	public void delete(T o);

	/**
	 * 通过ID删除一个对象
	 * 
	 * @param id
	 * @since 2017年11月16日 下午4:43:30
	 */
	public void delete(Serializable id);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 * @since 2017年11月16日 下午4:44:58
	 */
	public void update(T o);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param o
	 * @since 2017年11月16日 下午4:45:06
	 */
	public void saveOrUpdate(T o);

	/**
	 * 通过ID获取一个对象
	 * 
	 * @param id
	 * @return
	 * @since 2017年11月16日 下午4:45:15
	 */
	public T get(Serializable id);

	/**
	 * 获取一个对象(根据hql)
	 * 
	 * @param hql
	 * @return
	 * @since 2017年11月16日 下午4:45:40
	 */
	public T get(String hql);

	/**
	 * 通过条件获取一个对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @since 2017年11月16日 下午4:46:21
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * 查询多个对象
	 * 
	 * @param hql
	 * @return
	 * @since 2017年11月16日 下午4:46:35
	 */
	public List<T> find(String hql);

	/**
	 * 查询全部对象
	 * 
	 * @return
	 * @since 2017年11月16日 下午4:47:07
	 */
	public List<T> findAll();

	/**
	 * 通过条件查询多个对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @since 2017年11月16日 下午4:47:19
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 分页查询多个对象
	 * 
	 * @param hql
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @since 2017年11月16日 下午4:47:32
	 */
	public PageBean<T> find(String hql, int currentPage, int pageSize);

	/**
	 * 通过条件分页查询多个对象
	 * 
	 * @param hql
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @since 2017年11月16日 下午4:48:19
	 */
	public PageBean<T> find(String hql, Map<String, Object> params,
			int currentPage, int pageSize);

	/**
	 * 统计总数(全部)
	 * 
	 * @return
	 * @since 2017年11月16日 下午4:48:36
	 */
	public Long count();

	/**
	 * 统计总数
	 * 
	 * @param hql
	 * @return
	 * @since 2017年11月16日 下午6:22:59
	 */
	public Long count(String hql);

	/**
	 * 通过条件统计总数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @since 2017年11月16日 下午4:48:43
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行一个语句
	 * 
	 * @param hql
	 * @return
	 * @since 2017年11月16日 下午4:48:51
	 */
	public int executeHql(String hql);

}
