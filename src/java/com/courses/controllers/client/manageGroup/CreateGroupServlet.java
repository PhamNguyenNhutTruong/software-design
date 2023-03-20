package com.courses.controllers.client.manageGroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.RegisterGroupService;
import com.courses.services.admin.user.StudentService;

@WebServlet(urlPatterns = { "/student/group-manage/create-group",  "/student/group-manage/create-group/"})
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreateGroupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService studentService = new StudentService(request, response);
		RegisterGroupService registerGroupService = new RegisterGroupService(request, response);
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		registerGroupService.createGroupStudent(studentId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
