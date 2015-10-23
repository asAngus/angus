package com.javachen.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	@Transactional(readOnly = false)
	void persist(T entity);

	@Transactional(readOnly = false)
	void remove(T entity);

	@Transactional(readOnly = false)
	T merge(T entity);

	T findById(ID id);

	List<T> findAll();

	@Transactional(readOnly = false)
	T flush(T entity);

	List<T> findByParameter(String field, String value);

	List<T> findByWildcard(String field, String value);

	List<T> findByFuzzy(String field, String value);

	@Transactional(readOnly = false)
	void updateIndexes();
}