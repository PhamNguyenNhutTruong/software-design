package com.courses.controllers.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.admin.user.UserService;


@WebServlet(urlPatterns = { "/admin/users/restore", "/admin/users/restore/" })
public class RestoreUserIsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestoreUserIsDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService(request, response);
		userService.restoreUser();
		userService.handleGetListUserDeleted();	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
