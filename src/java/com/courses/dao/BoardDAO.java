package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Board;

public class BoardDAO extends JpaDAO<Board> implements GenericDAO<Board> {

	public BoardDAO() {
		super();
	}

	@Override
	public Board create(Board entity) {
		return super.create(entity);
	}

	@Override
	public Board update(Board entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Board.class, id);
	}

	@Override
	public Board find(Object primaryKey) {
		return super.find(Board.class, primaryKey);

	}

	@Override
	public List<Board> findAll() {
		String queryName = "Board.findAll";
		return super.findAll(queryName, Board.class);
	}

	@Override
	public List<Board> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Board> pagination(int currentPage, int pageSize) {
		String queryName = "Board.findAll";
		return super.pagination(queryName, currentPage, pageSize);
	}

	@Override
	public int count() {
		String queryName = "Board.count";
		return super.count(queryName);
	}
	
	@Override
	public String randomId() {
		return super.randomId("BO");
	}
}