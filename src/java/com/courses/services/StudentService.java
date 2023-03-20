package com.courses.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.GroupStudentDAO;
import com.courses.dao.StudentDAO;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.services.admin.user.PersonService;


public class StudentService extends SuperService{
	private static StudentDAO studentDAO = new StudentDAO();
	private static GroupStudentDAO groupDAO = new GroupStudentDAO();
	
	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public StudentService() {
		
	};
	
	public static Student getStudentByPerson(Person person) {
		Student foundStudent = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person", person);
		if (studentDAO.findWithNamedQuery("Student.findStudentByPerson", map).size() >0 )
		{
			foundStudent = studentDAO.findWithNamedQuery("Student.findStudentByPerson", map).get(0);
		}
		return foundStudent;
	}

	
	public List<Student> checkStudentAndGroup(Map<String,Object> map) {
		List<Student> students = studentDAO.findWithNamedQuery("Student.checkStudentAndGroup", map);
		return students;
	}
	
	public Student getStudentByPersonToLoginData() {
		Map<String, Object> map = new HashMap<String, Object>();	
		Student student = new Student();
		Person person = new Person();
		PersonService personService = new PersonService(request, response);
		StudentDAO studentDAO = new StudentDAO();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		person = personService.getPersonByEmail(username);
		
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		return student;
	}
	
}
