package com.courses.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.courses.models.RegistrationPeriod;

public class RegistrationPeriodDAO extends JpaDAO<RegistrationPeriod> implements GenericDAO<RegistrationPeriod> {
	public RegistrationPeriodDAO() {
		super();
	}

	@Override
	public RegistrationPeriod create(RegistrationPeriod entity) {
		return super.create(entity);
	}

	@Override
	public RegistrationPeriod update(RegistrationPeriod entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(RegistrationPeriod.class, id);
	}

	@Override
	public RegistrationPeriod find(Object primaryKey) {
		return super.find(RegistrationPeriod.class, primaryKey);
	}

	@Override
	public List<RegistrationPeriod> findAll() {
		String queryName = "RegistrationPeriod.findAll";
		return super.findAll(queryName, RegistrationPeriod.class);
	}

	@Override
	public List<RegistrationPeriod> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	public List<RegistrationPeriod> findByIsRegistrationTeacher(byte isRegistrationTeacher) {
		String queryName = "RegistrationPeriod.findByIsTeacher";

		Map<String, Object> param = new HashMap<>();
		param.put("isRegistrationTeacher", isRegistrationTeacher);

		return super.findWithNamedQuery(queryName, param);
	}

	public List<RegistrationPeriod> findByIsRegistrationTeacherIsDeleted(byte isRegistrationTeacher) {
		String queryName = "RegistrationPeriod.findByIsTeacherIsDeleteed";

		Map<String, Object> param = new HashMap<>();
		param.put("isRegistrationTeacher", isRegistrationTeacher);

		return super.findWithNamedQuery(queryName, param);
	}

	@Override
	public List<RegistrationPeriod> pagination(int currentPage, int pageSize) {
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
		return super.randomId("RP");
	}

	public int countBySchoolYearAndIsHead(Map<String, Object> param) {
		String queryName = "RegistrationPeriod.countBySchoolYearAndIsHead";
		return super.countByNamedQuery(queryName, param);
	}

	public int countByIsActive(Map<String, Object> param) {
		String queryName = "RegistrationPeriod.countByIsActive";
		return super.countByNamedQuery(queryName, param);
	}
	
	public RegistrationPeriod findByIsActive(Map<String, Object> param) {
		String queryName = "RegistrationPeriod.findByStatus";
		return super.findSingleWithNamedQuery(queryName, param);
	}
}
