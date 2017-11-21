/** 广州哇宝信息技术有限公司 */
package com.wabao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wabao.bean.PageBean;

/**
 * 
 * @since 2017年11月21日 下午1:35:16
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> clazz; // 对象类

	public BaseDaoImpl() {
		super();
		// 获取超类
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericSuperclass;
			this.clazz = (Class<T>) type.getActualTypeArguments()[0];
		}
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		Serializable result = this.getCurrentSession().save(o);
		return result;
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void delete(Serializable id) {
		Session session = this.getCurrentSession();
		Object o = session.load(clazz, id);
		session.delete(o);
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public T get(Serializable id) {
		Object result = this.getCurrentSession().get(clazz, id);
		return (T) result;
	}

	@Override
	public T get(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		List<T> list = query.list();
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		if (null == list || 0 == list.size()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<T> find(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> findAll() {
		Query query = this.getCurrentSession()
				.createQuery("FROM " + clazz.getName());
		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		return list;
	}

	@Override
	public PageBean<T> find(String hql, int currentPage, int pageSize) {
		String countHql = getCountHqlFromHql(hql);
		Long count = count(countHql);
		if (count <= 0L) {
			PageBean<T> pb = new PageBean<T>();
			pb.setPageSize(pageSize);
			return pb;
		}
		PageBean<T> resultPage = new PageBean<T>(currentPage, pageSize,
				count.intValue());
		Query query = this.getCurrentSession().createQuery(hql);
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<T> result = query.list();
		resultPage.setResult(result);
		return resultPage;
	}

	@Override
	public PageBean<T> find(String hql, Map<String, Object> params,
			int currentPage, int pageSize) {
		String countHql = getCountHqlFromHql(hql);
		Long count = count(countHql, params);
		if (count <= 0L) {
			PageBean<T> pb = new PageBean<T>();
			pb.setPageSize(pageSize);
			return pb;
		}
		PageBean<T> resultPage = new PageBean<T>(currentPage, pageSize,
				count.intValue());
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<T> result = query.list();
		resultPage.setResult(result);
		return resultPage;
	}

	@Override
	public Long count() {
		String hql = "SELECT COUNT(*) FROM " + clazz.getName();
		Query query = this.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		int flag = query.executeUpdate();
		return flag;
	}

	private String getCountHqlFromHql(String hql) {
		int index = hql.toUpperCase().indexOf(" ORDER BY ");
		String countHql = hql;
		if (index > 0) {
			countHql = hql.substring(0, index);
		}
		int idx = countHql.toUpperCase().indexOf("FROM");
		if (idx > 0) {
			countHql = countHql.substring(idx);
		}
		return "SELECT COUNT(*) " + countHql;
	}
}
