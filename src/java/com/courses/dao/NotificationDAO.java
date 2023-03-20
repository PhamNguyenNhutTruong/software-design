package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Notification;


public class NotificationDAO extends JpaDAO<Notification> implements GenericDAO<Notification> {
	public NotificationDAO() {
		super();
	}

	@Override
	public Notification create(Notification entity) {
		return super.create(entity);
	}

	@Override
	public Notification update(Notification entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Notification.class, id);
	}

	@Override
	public Notification find(Object primaryKey) {
		return super.find(Notification.class, primaryKey);
	}

	@Override
	public List<Notification> findAll() {
		String queryName = "Notification.findAll";
		return super.findAll(queryName, Notification.class);
	}

	@Override
	public List<Notification> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Notification> pagination(int currentPage, int pageSize) {
		String queryName = "Notification.findAll";
		return super.pagination(queryName, currentPage, pageSize);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("NO");
	}
}
