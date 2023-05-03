package com.courses.controllers.admin.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.services.admin.user.UserService;

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
			UserService userService = new UserService(request, response);
			String result = userService.softDeleteUser();
			PrintWriter pw = response.getWriter();
			pw.append(result);
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
