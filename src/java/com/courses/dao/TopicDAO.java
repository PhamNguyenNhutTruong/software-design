package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Topic;

public class TopicDAO extends JpaDAO<Topic> implements GenericDAO<Topic> {
	public TopicDAO() {
		super();
	}

	@Override
	public Topic create(Topic entity) {
		return super.create(entity);
	}

	@Override
	public Topic update(Topic entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Topic.class, id);
	}

	@Override
	public Topic find(Object primaryKey) {
		return super.find(Topic.class, primaryKey);
	}

	@Override
	public List<Topic> findAll() {
		String queryName = "Topic.findAll";
		return super.findAll(queryName, Topic.class);
	}

	@Override
	public List<Topic> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Topic> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("TO");
	}
	
	public List<Topic> findByStatusAndIsDeleted(Map<String, Object> params) {
		String queryName = "Topic.findByStatusAndIsDeleted";
		return super.findWithNamedQuery(queryName, params);	
	}
	
	
}
