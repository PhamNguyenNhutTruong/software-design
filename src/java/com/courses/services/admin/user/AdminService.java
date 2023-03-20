package com.courses.services.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.SuperService;

public class AdminService extends SuperService {

	public AdminService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public AdminService() {}
	
	public void handleGetAdmin() {
		
	}
	
}
