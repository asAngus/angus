package com.javachen.dao.impl;

import com.javachen.dao.GenericDAO;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private Class<T> persistentClass;

	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	protected Class<T> getPersistentClass() {
		return this.persistentClass;
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false)
	public void persist(T entity) {
		getHibernateTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public T merge(T entity) {
		return getHibernateTemplate().merge(entity);
	}

	@Override
	public T findById(ID id) {
		return getHibernateTemplate().get(getPersistentClass(), id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().find(
				"from " + getPersistentClass().getName());
	}

	@Override
	@Transactional(readOnly = false)
	public T flush(T entity) {
		getHibernateTemplate().flush();
		return entity;
	}

	protected List<T> findAllWithLucene(final Query query) {
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return Search.getFullTextSession(session)
						.createFullTextQuery(query, getPersistentClass())
						.list();
			}
		});
	}

	protected T findUniqueWithLucene(final Query query) {
		return getHibernateTemplate().execute(new HibernateCallback<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				return (T) Search.getFullTextSession(session)
						.createFullTextQuery(query, getPersistentClass())
						.uniqueResult();
			}
		});
	}

	@Override
	public List<T> findByParameter(String field, String value) {
		return findAllWithLucene(new TermQuery(new Term(field, value)));
	}

	@Override
	public List<T> findByWildcard(String field, String value) {
		return findAllWithLucene(new WildcardQuery(new Term(field, "*" + value
				+ "*")));
	}

	@Override
	public List<T> findByFuzzy(String field, String value) {
		return findAllWithLucene(new FuzzyQuery(new Term(field, value)));
	}

	@Override
	public void updateIndexes() {
		final List<T> entities = findAll();
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) {
				FullTextSession fSess = Search.getFullTextSession(session);

				fSess.purgeAll(getPersistentClass());
				for (T e : entities) {
					fSess.index(e);
				}
				fSess.getSearchFactory().optimize();
				return null;
			}
		});
	}
}
