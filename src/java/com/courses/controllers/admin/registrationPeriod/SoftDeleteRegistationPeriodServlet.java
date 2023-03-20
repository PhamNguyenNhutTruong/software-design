package com.courses.controllers.admin.registrationPeriod;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.RegistrationPriodService;


@WebServlet(urlPatterns = {"/admin/registration-priods/delete", "/admin/registration-priods/delete/"})
public class SoftDeleteRegistationPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SoftDeleteRegistationPeriodServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationPriodService registrationPriodService = new RegistrationPriodService(request, response);
		registrationPriodService.softDeleteRegistrationPeriod();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
