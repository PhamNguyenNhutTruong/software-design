package com.courses.controllers.admin.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;

@WebServlet(urlPatterns = { "/admin/users/delete", "/admin/users/delete/" })
public class SoftDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PersonDAO personDAO = null;

	public SoftDeleteUserServlet() {
		super();
		this.personDAO = new PersonDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String personId = request.getParameter("personId");

		// Get person
		Person p = this.personDAO.find(personId);
		if (p != null) {
			p.setIsDeleted((byte) 1);
			this.personDAO.update(p);
			
			PrintWriter pw = response.getWriter();
			pw.append("Delete User Success");
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
