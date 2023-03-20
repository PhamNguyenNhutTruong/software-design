package com.courses.controllers.client.manageGroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.admin.user.StudentService;

@WebServlet(urlPatterns = { "/student/group-manage/delete-memeber", "/student/group-manage/delete-memeber/" })
public class DeleteMemberFromGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteMemberFromGroupServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentService studentService = new StudentService(request, response);
		studentService.deleteStudenFromStudentGroup();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
