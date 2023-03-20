package com.courses.controllers.client.manageGroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.GroupService;

@WebServlet(urlPatterns = { "/student/group-manage", "/student/group-manage/" })
public class GetGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetGroupServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupService groupService = new GroupService(request, response);
		groupService.handleGetGroupManage();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
