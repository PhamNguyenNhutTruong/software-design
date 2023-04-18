package com.courses.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuperService {
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;

	public SuperService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public SuperService() {}

	protected void setEncoding() throws UnsupportedEncodingException {
		this.request.setCharacterEncoding("UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}

	protected void forwardToPage(String path) throws ServletException, IOException {
		this.setEncoding();
		this.request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	protected void redirectToPage(String url) throws IOException {
		this.setEncoding();
		this.response.sendRedirect(url);
	}
	
	protected String getParameter(String key) {
		return this.request.getParameter(key);
	}

	protected String getContextPath() {
		return request.getContextPath();
	}
}
