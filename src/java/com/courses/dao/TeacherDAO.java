package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Admin;
import com.courses.models.Teacher;

public class TeacherDAO extends JpaDAO<Teacher> implements GenericDAO<Teacher>{
	public TeacherDAO( ) {
		super();
	}
	
	@Override
	public Teacher create(Teacher entity) {
		return super.create(entity);
	}
	
	@Override
	public Teacher update(Teacher entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Teacher.class, id);
	}
	
	@Override
	public Teacher find(Object primaryKey) {
		return super.find(Teacher.class, primaryKey);
	}
	
	@Override
	public List<Teacher> findAll(){
		String queryName = "Teacher.findAll";
		return super.findAll(queryName, Teacher.class);
	}
	
	@Override
	public List<Teacher> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Teacher> pagination(int currentPage, int pageSize) {
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
		return super.randomId("TE");
	}
	
	public Teacher findByPerson(Map<String, Object> params) {
		String queryName = "Teacher.findByPerson";
		return super.findSingleWithNamedQuery(queryName, params);
	}
}
