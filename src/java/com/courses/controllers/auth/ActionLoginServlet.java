package com.courses.controllers.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.LoginService;

@WebServlet(urlPatterns = { "/login", "/login/" })
public class ActionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActionLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginService loginService = new LoginService(request, response);
		loginService.handleGetLogin();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginService loginService = new LoginService(request, response);
		loginService.handlePostLogin();
	}

}
