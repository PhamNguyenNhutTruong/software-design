package com.courses.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.courses.models.Person;

public class PersonDAO extends JpaDAO<Person> implements GenericDAO<Person>{
	public PersonDAO( ) {
		super();
	}
	
	@Override
	public Person create(Person entity) {
		return super.create(entity);
	}
	
	@Override
	public Person update(Person entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Person.class, id);
	}
	
	@Override
	public Person find(Object primaryKey) {
		return super.find(Person.class, primaryKey);
	}
	
	@Override
	public List<Person> findAll(){
		String queryName = "Person.findAll";
		return super.findAll(queryName, Person.class);
	}
	
	
	@Override
	public List<Person> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}
	
	public List<Person> findByIsDelete(byte isDeleted) {
		String queryName = "Person.findByIsDelete";
		Map<String, Object> param = new HashMap<>();
		param.put("isDeleted", isDeleted);
		return this.findWithNamedQuery(queryName, param);
	}
	
	public int bulkUpdate(List<Person> persons) {
		int rowCountUpdated = 0;
		for (Person person : persons) {
			this.update(person);
			rowCountUpdated++;
		}
		return rowCountUpdated;
	}

	@Override
	public List<Person> pagination(int currentPage, int pageSize) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("PE");
	}
}
