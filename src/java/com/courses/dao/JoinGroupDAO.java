package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.JoinGroup;

public class JoinGroupDAO extends JpaDAO<JoinGroup> implements GenericDAO<JoinGroup> {
	public JoinGroupDAO() {
		super();
	}

	@Override
	public JoinGroup create(JoinGroup entity) {
		return super.create(entity);
	}

	@Override
	public JoinGroup update(JoinGroup entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(JoinGroup.class, id);
	}

	@Override
	public JoinGroup find(Object primaryKey) {
		return super.find(JoinGroup.class, primaryKey);
	}

	@Override
	public List<JoinGroup> findAll() {
		String queryName = "JoinGroup.findAll";
		return super.findAll(queryName, JoinGroup.class);
	}

	@Override
	public List<JoinGroup> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<JoinGroup> pagination(int currentPage, int pageSize) {
		String queryName = "JoinGroup.findAll";
		return super.pagination(queryName, currentPage, pageSize);
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public String randomId() {
		return super.randomId("JG");
	}
}
