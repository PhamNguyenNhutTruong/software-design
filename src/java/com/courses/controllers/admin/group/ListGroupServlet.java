package com.courses.controllers.admin.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.GroupService;

@WebServlet(urlPatterns = { "/admin/groups", "/admin/groups/" })
public class ListGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListGroupServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupService groupService = new GroupService(request, response);
		groupService.handleGetListGroup();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
