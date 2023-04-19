package com.courses.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.courses.models.Account;
import com.courses.models.Person;

public class AccountDAO extends JpaDAO<Account> implements GenericDAO<Account> {
	public AccountDAO() {
		super();
	}

	@Override
	public Account create(Account entity) {
		return super.create(entity);
	}

	@Override
	public Account update(Account entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Account.class, id);
	}

	@Override
	public Account find(Object primaryKey) {
		return super.find(Account.class, primaryKey);
	}

	@Override
	public List<Account> findAll() {
		String queryName = "Account.findAll";
		return super.findAll(queryName, Account.class);
	}

	@Override
	public List<Account> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	public Account findByPerson(Person person) {
		String queryName = "Account.findByPerson";
		Map<String, Object> param = new HashMap<>();
		param.put("person", person);
		List<Account> accounts = this.findWithNamedQuery(queryName, param);
		if (accounts != null && accounts.size() > 0) {
			System.out.println(accounts.size());
			return accounts.get(0);
		}
		return null;
	}

	@Override
	public List<Account> pagination(int currentPage, int pageSize) {
		String queryName = "Account.findAll";
		return super.pagination(queryName, currentPage, pageSize);
	}

	@Override
	public int count() {
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("AC");
	}
}
