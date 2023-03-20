package com.courses.controllers.client.joinGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.models.GroupStudent;
import com.courses.services.GroupService;
import com.courses.services.JoinGroupService;
import com.courses.services.admin.user.StudentService;

@WebServlet(urlPatterns = { "/student/join-group", "/student/join-group/" })
public class JoinGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinGroupServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JoinGroupService joinGroupService = new JoinGroupService(request, response);
		joinGroupService.handleJoinGroup();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
