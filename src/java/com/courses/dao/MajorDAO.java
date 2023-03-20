package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Major;

public class MajorDAO extends JpaDAO<Major> implements GenericDAO<Major>{
	public MajorDAO( ) {
		super();
	}
	
	@Override
	public Major create(Major entity) {
		return super.create(entity);
	}
	
	@Override
	public Major update(Major entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Major.class, id);
	}
	
	@Override
	public Major find(Object primaryKey) {
		return super.find(Major.class, primaryKey);
	}
	
	@Override
	public List<Major> findAll(){
		String queryName = "Major.findAll";
		return super.findAll(queryName, Major.class);
	}
	
	@Override
	public List<Major> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Major> pagination(int currentPage, int pageSize) {
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
		return super.randomId("MA");
	}
}
