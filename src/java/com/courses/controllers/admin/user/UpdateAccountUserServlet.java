package com.courses.controllers.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.admin.user.AccountService;

@WebServlet(urlPatterns = { "/admin/users/edit/update-account", "/admin/users/edit/update-account/" })
public class UpdateAccountUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateAccountUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		AccountService accountService = new AccountService(request, response);
		accountService.handlePostAccount();
	}

}
