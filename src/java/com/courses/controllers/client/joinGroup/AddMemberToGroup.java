package com.courses.controllers.client.joinGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.GroupStudentDAO;
import com.courses.dao.JoinGroupDAO;
import com.courses.dao.StudentDAO;
import com.courses.models.GroupStudent;
import com.courses.models.JoinGroup;
import com.courses.models.JoinGroupPK;
import com.courses.models.Student;
import com.courses.services.GroupService;
import com.courses.services.JoinGroupService;
import com.courses.services.admin.user.StudentService;

/**
 * Servlet implementation class AddMemberToGroup
 */
@WebServlet(urlPatterns = { "/add-member-to-group", "/add-member-to-group/" })
public class AddMemberToGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddMemberToGroup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GroupService groupService = new GroupService(request, response);
		groupService.addMemberToGroup();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
