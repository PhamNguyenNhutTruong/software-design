package com.courses.controllers.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.LogoutService;

@WebServlet(urlPatterns = { "/logout", "/logout/" })
public class ActionLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActionLogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogoutService logoutService = new LogoutService(request, response);
		logoutService.handleGetLogout();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogoutService logoutService = new LogoutService(request, response);
		logoutService.handleGetLogout();
	}
}
