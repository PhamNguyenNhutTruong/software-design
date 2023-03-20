package com.courses.controllers.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.HomeService;

@WebServlet(urlPatterns = { "/teacher/home", "/teacher/home/" })
public class TeacherHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherHomeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HomeService hs = new HomeService(request, response);
		hs.handleGetTeacherHomeService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
