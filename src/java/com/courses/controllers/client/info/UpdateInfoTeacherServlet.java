package com.courses.controllers.client.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;
import com.mysql.cj.xdevapi.JsonParser;

@WebServlet("/teacher/user-profile/update")
public class UpdateInfoTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PersonDAO personDAO = null;
       	
    public UpdateInfoTeacherServlet() {
        super();
        personDAO = new PersonDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Called");
			// Get data from update form
			String personId = (String)request.getParameter("personId");
			String fullname = "";
			String phonenumber = "";
			String address = "";
			String desc = "";
			desc = (String) request.getParameter("desc");
			fullname = (String)request.getParameter("fullname");
			phonenumber = (String) request.getParameter("phonenumber");
			address = (String) request.getParameter("address");
			System.out.println(request.getParameter("personId"));
			
			if(personId == null) return;
			// Get by Id
			Person currentTeacher = personDAO.find(personId);
			if(currentTeacher == null) return;

			currentTeacher.setFullName(fullname);
			currentTeacher.setPhonenumber(phonenumber);
			currentTeacher.setAddress(address);
			currentTeacher.setDescription(desc);
			
			// Update
			personDAO.update(currentTeacher);
			
			
//			PrintWriter out = response.getWriter();
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			out.print("{\"\"}");
//			out.flush();  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
