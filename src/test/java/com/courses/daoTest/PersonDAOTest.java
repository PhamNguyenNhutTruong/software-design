package com.courses.daoTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;

public class PersonDAOTest {

	private PersonDAO personDAO = null;
	@Before
	public void setUp() throws Exception {
		this.personDAO = new PersonDAO();
	}

	@Test(expected = Exception.class)
	public void testCreateWithValidModel() {
		byte gender = 1;
		Person person = new Person();
		person.setPersonId("12346");
		person.setFullName("Thái Tuấn");
		person.setGender(gender);
		person.setPhonenumber("0986754321");
		person.setEmail("@gmail.com");
		person.setAddress("Address");
		person.setDescription("desc");
		this.personDAO.create(person);
	}

}
