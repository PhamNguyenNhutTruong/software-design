package com.courses.controllers.admin.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.GroupService;

@WebServlet(urlPatterns = { "/admin/group/edit", "/admin/group/edit/" })
public class EditGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditGroupServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupService groupService = new GroupService(request, response);
		groupService.handleGetCreateGroupForm();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
