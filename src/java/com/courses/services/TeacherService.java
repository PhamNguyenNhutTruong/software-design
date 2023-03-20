package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.courses.dao.TeacherDAO;
import com.courses.models.Person;
import com.courses.models.Teacher;




public class TeacherService extends SuperService {

	private static TeacherDAO teacherDAO = new TeacherDAO();
	
	public TeacherService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public TeacherService() {}
	
	public void handleGetTeacher() throws ServletException, IOException{
		// define default url
		String url = "/pages/client/teacher/teacherInfor.jsp";
		// forward to specified url
		super.forwardToPage(url);
	}
		
	public static Teacher getTeacherByPerson(Person person) {
		Teacher foundTeacher = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person", person);
		if (teacherDAO.findWithNamedQuery("Teacher.findTeacherByPerson", map).size() >0 )
		{
			foundTeacher = teacherDAO.findWithNamedQuery("Teacher.findTeacherByPerson", map).get(0);
		}
		return foundTeacher;
	}
	
}
