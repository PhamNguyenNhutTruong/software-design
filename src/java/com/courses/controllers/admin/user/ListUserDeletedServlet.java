package com.courses.controllers.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.admin.user.UserService;

@WebServlet(urlPatterns = { "/admin/users/trash", "/admin/users/trash/" })
public class ListUserDeletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListUserDeletedServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService(request, response);
		userService.handleGetListUserDeleted();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
