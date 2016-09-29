

package com.copy4dev.sshBase.dao.impl.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.copy4dev.sshBase.dao.base.IHibernateDao;

/**
 * 
 * @author Admin
 * @version 2016年9月27日
 *
 */
@SuppressWarnings("unchecked")
public class HibernateDaoImpl extends HibernateDaoSupport implements IHibernateDao {
	/**
	 * 统计总条数
	 * 
	 * @param hql 查询语句
	 */
	@Override
	public Integer count(final String hql) {
		if (StringUtils.isEmpty(hql)) {
			throw new IllegalStateException("hql is null");
		}
		Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery(hql).setCacheable(true).uniqueResult();
			}
		});
		return ((Long) result).intValue();
	}

	/**
	 * 批量修改或删除
	 * 
	 * @param queryString
	 * @param values
	 */
	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	public <T> void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 按条件统计总条数
	 * 
	 * @param hql
	 * @param obj
	 */
	@Override
	public Integer count(final String hql, final Object... obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return count(hql);
		} else {
			if (StringUtils.isEmpty(hql)) {
				throw new IllegalStateException("hql is null");
			}
			Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					for (int i = 0; i < obj.length; i++) {
						query.setParameter(i, obj[i]);
					}
					return query.uniqueResult();
				}
			});
			return ((Long) result).intValue();
		}
	}

	/**
	 * 删除
	 * 
	 * @param entities
	 */
	@Override
	public <T> void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 判断是否存在
	 * 
	 * @param entities
	 */
	@Override
	public <T> boolean exist(Class<T> c, Serializable id) {
		if (get(c, id) != null)
			return true;
		return false;
	}

	/**
	 * 查询全部
	 * 
	 * @param entities
	 */
	@Override
	public <T> List<T> find(String queryString) {
		return (List<T>) getHibernateTemplate().find(queryString);
	}

	/**
	 * Execute an HQL query.
	 * 
	 * @param bean
	 * @return
	 */
	@Override
	public <T> List<T> find(Class<T> bean) {
		String hql = "FROM " + bean.getSimpleName();
		return find(hql);
	}

	/**
	 * 按条件查询全部
	 * 
	 * @param queryString
	 * @param values
	 */
	@Override
	public List<?> find(String queryString, Object[] values) {
		if (ObjectUtils.isEmpty(values)) {
			return find(queryString);
		} else {
			return getHibernateTemplate().find(queryString, values);
		}
	}

	/**
	 * 获取唯一实体
	 * 
	 * @param queryString HQL query string
	 * @param params query object array params
	 * @return unique object
	 * @see org.hibernate#Session
	 * @throws java.lang.IllegalArgumentException if queryString is null
	 */
	@Override
	public <T> T findUniqueEntity(final String queryString, final Object... params) {
		if (StringUtils.isEmpty(queryString)) {
			throw new IllegalStateException("queryString is null");
		}
		if (ObjectUtils.isEmpty(params)) {
			return (T) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) {
					return session.createQuery(queryString).uniqueResult();
				}
			});
		} else {
			return (T) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) {
					Query query = session.createQuery(queryString);
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
					return query.uniqueResult();
				}
			});
		}
	}

	/**
	 * 命名查询
	 * 
	 * @param queryName
	 */
	@Override
	public <T> List<T> findByNamedQuery(String queryName) {
		if (StringUtils.isEmpty(queryName)) {
			throw new IllegalArgumentException("queryName is null");
		}
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * 条件命名查询
	 * 
	 * @param queryName
	 * @param values
	 */
	@Override
	public <T> List<T> findByNamedQuery(String queryName, Object... values) {
		if (ObjectUtils.isEmpty(values)) {
			return this.findByNamedQuery(queryName);
		}
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	/**
	 * 多条件分页查询
	 * 
	 * @param hql query string
	 * @param startRow begin row
	 * @param pageSize page number
	 * @param params query object params array
	 * @return the query list<?> result
	 * @see org.hibernate#Session
	 * @throws java.lang.IllegalArgumentException if queryString is null
	 */
	@SuppressWarnings("deprecation")
	@Override
	public <T> List<?> findByPage(final String hql, final Integer startRow, final Integer pageSize, final Object... params) {
		if (StringUtils.isEmpty(hql)) {
			throw new IllegalStateException("hql is null");
		}
		if (ObjectUtils.isEmpty(params)) {
			return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) {
					return session.createQuery(hql).setCacheable(true).setFirstResult(startRow).setMaxResults(pageSize).list();
				}
			});
		} else {
			return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) {
					Query query = session.createQuery(hql);
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
					return query.setFirstResult(startRow).setMaxResults(pageSize).list();
				}
			});
		}
	}

	/**
	 * 获取一个实体
	 * 
	 * @param entityClass
	 * @param id
	 */
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public <T> Iterator<T> iterate(String queryString) {
		return (Iterator<T>) getHibernateTemplate().iterate(queryString);
	}

	@Override
	public <T> Iterator<T> iterate(String queryString, Object... values) {
		return (Iterator<T>) getHibernateTemplate().iterate(queryString, values);
	}

	/**
	 * 加载一个实体
	 * 
	 * @param entityClass
	 * @param id
	 */
	@Override
	public <T> T load(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	@Override
	public <T> void persist(T entity) {
		getHibernateTemplate().persist(entity);
	}

	@Override
	public <T> void refresh(T entity) {
		getHibernateTemplate().refresh(entity);
	}

	/**
	 * 保存
	 * 
	 * @param entities
	 * @throws java.lang.IllegalArgumentException if entity is null
	 */
	@Override
	public <T> Serializable save(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("entity is null");
		}
		return getHibernateTemplate().save(entity);
	}

	/**
	 * 保存与修改
	 * 
	 * @param entities
	 */
	@Override
	public <T> void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 保存与修改全部
	 * 
	 * @param entities
	 */
	@Override
	public <T> void saveOrUpdateAll(Collection<T> entities) {
		// getHibernateTemplate().saveOrUpdate(entities);
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	@Override
	public <T> void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 修改所有的实体
	 * 
	 * @param entities
	 * @throws java.lang.IllegalArgumentException if entities is null
	 */
	@Override
	public <T> void updateAll(Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			throw new IllegalArgumentException("entities is null");
		}
		int i = 0;
		for (Object obj : entities) {
			if (i % 30 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
			getHibernateTemplate().update(obj);
			i++;
		}
	}

	/**
	 * 保存所有的实体
	 * 
	 * @param entities
	 * @throws java.lang.IllegalArgumentException if entities is null
	 */
	@Override
	public <T> void saveAll(Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			throw new IllegalArgumentException("entities is null");
		}
		int i = 0;
		for (T obj : entities) {
			if (i % 30 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
			save(obj);
			i++;
		}
	}

	/**
	 * in查询语句
	 */
	public <T> List<?> findByIn(final String hql, final Map<String, Object> values) {
		if (StringUtils.isEmpty(hql)) {
			throw new IllegalStateException("queryString is null");
		}

		if (values == null || values.size() <= 0) {
			return (List<?>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) {
					Long ids[] = new Long[] { 1L, 2L, 3L };
					return session.createQuery(hql).setParameter("ids", new Object[] { ids }).list();
				}
			});
		} else {
			Set<String> keySet = values.keySet();
			for (final String string : keySet) {
				final Object obj = values.get(string);
				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					return (List<?>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
						public Object doInHibernate(Session session) {
							return session.createQuery(hql).setParameterList(string, (Collection<?>) obj).list();
						}
					});
				} else if (obj instanceof Object[]) {
					return (List<?>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
						public Object doInHibernate(Session session) {
							return session.createQuery(hql).setParameterList(string, (Object[]) obj).list();
						}
					});
				} else {
					return (List<?>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
						public Object doInHibernate(Session session) {
							return session.createQuery(hql).setParameter(string, obj).list();
						}
					});
				}
			}

		}
		return null;
	}

	/**
	 * 多条件分页查询
	 * 
	 * @param queryString HQL语句
	 * @param pageModel 分页实体
	 * @param params 参数集合，没有参数可为NULL
	 * @return 分页查询后集合对象
	 * @see #findByPage(String, Integer, Integer, List)
	 */
	/*
	 * @Override public <T> List<T> findByPage(String queryString, PageModel
	 * pageModel, List<?> params){
	 *//** 处理不传where条件只传and的条件查询 (多条件查询时) where 1=1 */
	/*
	 * String hql = queryString; if (queryString.toLowerCase().indexOf("where")
	 * == -1){ Matcher m = Pattern.compile("and").matcher(queryString); if
	 * (m.find()){ hql = m.replaceFirst("where"); }else{ m =
	 * Pattern.compile("AND").matcher(queryString); if (m.find()){ hql =
	 * m.replaceFirst("WHERE"); } } } // -----------查询总记录条数 int fromIndex =
	 * hql.toLowerCase().indexOf("from"); int orderIndex =
	 * hql.toLowerCase().indexOf("group by"); String hqlCount =
	 * "select count(*) " + hql .substring(fromIndex, orderIndex > 0 ?
	 * orderIndex : hql.length()); int totalCount = (params == null ||
	 * params.isEmpty()) ? count(hqlCount) : count(hqlCount, params.toArray());
	 * // 设置总记录条数 pageModel.setRecordCount(totalCount); if(totalCount == 0){
	 * return new ArrayList<T>(); } // -------------分页查询 Object[] temps =
	 * (params == null || params.isEmpty()) ? new Object[]{} : params.toArray();
	 * return this.findByPage(hql, pageModel.getStartRow(),
	 * pageModel.getPageSize(), temps); }
	 */

	/**
	 * 执行Hibernate Sql语句，只返回执行sql的结果，不做其他处理
	 * 
	 * @param queryStr 查询sql语句
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List performHSql(final String querysql) {
		return getHibernateTemplate().find(querysql);
	}

	/**
	 * 执行Primitive Sql语句，主要用来执行原生sql语句，返回list数组列表
	 * 
	 * @param querysql 查询sql语句
	 * @param params 参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List performPureSql(final String querysql, final Object... params) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				try {

					SQLQuery sqlQuery = session.createSQLQuery(querysql);

					// 参数
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							sqlQuery.setParameter(i, params[i]);
						}
					}

					return sqlQuery.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException("perform Primitive Sql error");
				}
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	};

	/**
	 * 执行执行Primitive Sql语句，可以分页查询
	 * 
	 * @param querysql 查询sql语句
	 * @param startRow 开始行
	 * @param pageSize 为0不做分页查询 每页数量
	 * @param params 参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findPageBySql(final String querysql, final Integer startRow, final Integer pageSize, final Object... params) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery sqlQuery = session.createSQLQuery(querysql);
					// pageSize 不为0 时做分页查询
					if (pageSize.intValue() != 0) {
						sqlQuery.setFirstResult(startRow);
						sqlQuery.setMaxResults(pageSize.intValue());
					}

					// 参数
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							sqlQuery.setParameter(i, params[i]);
						}
					}

					return sqlQuery.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException("perform Primitive Sql error");
				}
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/**
	 * 执行执行Primitive Sql语句,对返回结果进行对象实体的映射，返回list对象实体列表
	 * 
	 * @param querysql 查询sql
	 * @param startRow 开始行
	 * @param pageSize 为0不做分页查询 每页数量
	 * @param params 参数 下标从0开始
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findModelPageBySql(final String querysql, final Integer startRow, final Integer pageSize, final Class className, final String alias, final Object... params) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery sqlQuery = session.createSQLQuery(querysql);

					// 分页
					if (pageSize.intValue() != 0) {
						sqlQuery.setFirstResult(startRow);
						sqlQuery.setMaxResults(pageSize.intValue());
					}

					// alias 别名
					if (StringUtils.isNotBlank(alias)) {
						sqlQuery.addEntity(alias, className);
					} else {
						sqlQuery.addEntity(className);
					}

					// 参数
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							sqlQuery.setParameter(i, params[i]);
						}
					}

					return sqlQuery.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException("getPSqlModelList method error");
				}
			}
		};
		return (List) this.getHibernateTemplate().execute(callback);
	}

	/**
	 * 按条件统计总条数
	 * 
	 * @param sql 查询语句
	 * @param obj 参数 Object... obj
	 */
	@Override
	public Integer countBySql(final String sql, final Object... obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return countSql(sql);
		} else {
			if (StringUtils.isEmpty(sql)) {
				throw new IllegalStateException("sql is null");
			}
			Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createSQLQuery(sql);
					for (int i = 0; i < obj.length; i++) {
						query.setParameter(i, obj[i]);
					}
					return query.uniqueResult();
				}
			});
			return ((BigInteger) result).intValue();
		}
	}

	/**
	 * 统计总条数
	 * 
	 * @param sql 查询语句
	 */
	@Override
	public Integer countSql(final String sql) {
		if (StringUtils.isEmpty(sql)) {
			throw new IllegalStateException("sql is null");
		}

		// 查询返回的是BigInteger
		Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).uniqueResult();
			}
		});
		return ((BigInteger) result).intValue();
	}

}