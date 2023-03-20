package com.courses.controllers.admin.registrationPeriod;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.RegistrationPriodService;


@WebServlet(urlPatterns = {"/admin/registration-priods/create", "/admin/registration-priods/create/"})
public class CreateRegistrationPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreateRegistrationPeriodServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationPriodService registrationPriodService = new RegistrationPriodService(request, response);
		registrationPriodService.createRegistrationPeriod();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
