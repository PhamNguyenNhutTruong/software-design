package com.courses.controllers.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.admin.user.PersonService;

@WebServlet(urlPatterns = { "/admin/users/edit/update-general", "/admin/users/edit/update-general/" })
public class UpdateGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateGeneralServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PersonService personService = new PersonService(request, response);
		personService.handlePostUpdatePerson();
	}

}
