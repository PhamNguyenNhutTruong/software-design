package com.courses.controllers.admin.registrationPeriod;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.RegistrationPriodService;


@WebServlet(urlPatterns = {"/admin/registration-priods/is-deleted", "/admin/registration-priods/is-deleted/"})
public class ListRegistrationPeriodIsDeletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ListRegistrationPeriodIsDeletedServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("=====================/admin/registration-priods/restore=============");
		RegistrationPriodService registrationPriodService = new RegistrationPriodService(request, response);
		registrationPriodService.handleGetListIsDeleted();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
