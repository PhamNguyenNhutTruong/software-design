package com.courses.dao;

import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {
	public T create(T entity);
	public T update(T entity);
	public void delete(Object id);
	public T find(Object primaryKry);
	public List<T> findAll();
	public List<T> findWithNamedQuery(String queryName, Map<String, Object> parameters);
	public List<T> pagination(int currentPage, int pageSize);
	public int count();
	public String randomId();
}
