package com.courses.controllers.admin.dashboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.models.Person;

@WebServlet(urlPatterns = {"/admin", "/admin/"})
public class ShowDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowDashboard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/admin/dashboard/dashboard.jsp";
		request.getRequestDispatcher(url).forward(request, response);
		Person p = (Person)request.getSession().getAttribute("person");
		if(p == null) {
			System.out.println("NULL");
		}
		else {
			System.out.println("NOT NULL");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
