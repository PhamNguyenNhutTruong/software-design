package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.TeacherBoard;

public class TeacherBoardDAO extends JpaDAO<TeacherBoard> implements GenericDAO<TeacherBoard> {

	public TeacherBoardDAO() {
	}

	@Override
	public TeacherBoard create(TeacherBoard entity) {
		return super.create(entity);
	}

	@Override
	public TeacherBoard update(TeacherBoard entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(TeacherBoard.class, id);
	}

	@Override
	public TeacherBoard find(Object primaryKey) {
		return super.find(TeacherBoard.class, primaryKey);
	}

	@Override
	public List<TeacherBoard> findAll() {
		String queryName = "TeacherBoard.findAll";
		return super.findAll(queryName, TeacherBoard.class);
	}

	@Override
	public List<TeacherBoard> pagination(int currentPage, int pageSize) {
		String queryName = "TeacherBoard.pagination";
		return super.pagination(queryName, currentPage, pageSize);
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public String randomId() {
		return null;
	}
	
	
	public List<TeacherBoard> findByBoard(Map<String, Object> params) {
		String queryName = "TeacherBoard.findByBoard";
		return super.findWithNamedQuery(queryName, params);
	}
	
	public int countByBoard(Map<String, Object> params) {
		String queryName = "TeacherBoard.countByBoard";
		return super.countByNamedQuery(queryName, params);
	}
	
	public List<TeacherBoard> findByTeacher(Map<String, Object> params) {
		String queryName = "TeacherBoard.findByTeacher";
		return super.findWithNamedQuery(queryName, params);
	}


}
