package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Admin;

public class AdminDAO extends JpaDAO<Admin> implements GenericDAO<Admin> {

	public AdminDAO() {
	}

	@Override
	public Admin create(Admin entity) {
		return super.create(entity);
	}

	@Override
	public Admin update(Admin entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Admin.class, id);
	}

	@Override
	public Admin find(Object primaryKey) {
		return super.find(Admin.class, primaryKey);
	}

	@Override
	public List<Admin> findAll() {
		String queryName = "Admin.findAll";
		return super.findAll(queryName, Admin.class);
	}

	@Override
	public List<Admin> pagination(int currentPage, int pageSize) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("AD");
	}
	
	public Admin findByPerson(Map<String, Object> params) {
		String queryName = "Admin.findByPerson";
		return super.findSingleWithNamedQuery(queryName, params);
	}
}
