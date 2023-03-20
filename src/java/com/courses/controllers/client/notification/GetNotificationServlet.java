package com.courses.controllers.client.notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.HomeService;
import com.courses.services.NotificationService;

@WebServlet(urlPatterns = { "/student/home", "/student/home/" })
public class GetNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNotificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NotificationService notificationService = new NotificationService(request, response);
		notificationService.getNotificationByLoginAccount();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
