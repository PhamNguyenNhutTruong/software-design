package com.courses.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService extends SuperService{

	public LogoutService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public LogoutService () {}
	
	public void handleGetLogout() throws ServletException, IOException {
		// define url
		String url = "/login";
		
		// destroy cookie
		Cookie userId = new Cookie("userIdCookie","");  
		userId.setPath("/");
        userId.setMaxAge(0);  
        response.addCookie(userId);  
        
        // destroy session
        HttpSession session = request.getSession(false);
        if (session != null) {
        	session.invalidate();
        }
        
        // forward to jsp file
		super.redirectToPage(request.getContextPath() + url);
	}
	
	public void handlePostLogout() throws ServletException, IOException{
		
	}

}
