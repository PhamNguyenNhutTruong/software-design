package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Student;

public class StudentDAO extends JpaDAO<Student> implements GenericDAO<Student>{
	public StudentDAO() {
		super();
	}
	
	@Override
	public Student create(Student entity) {
		return super.create(entity);
	}
	
	@Override
	public Student update(Student entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Student.class, id);
	}
	
	@Override
	public Student find(Object primaryKey) {
		return super.find(Student.class, primaryKey);
	}
	
	@Override
	public List<Student> findAll(){
		String queryName = "Student.findAll";
		return super.findAll(queryName, Student.class);
	}
	
	@Override
	public List<Student> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Student> pagination(int currentPage, int pageSize) {
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
		return super.randomId("ST");
	}
}
