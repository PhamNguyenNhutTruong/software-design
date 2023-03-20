package com.courses.controllers.client.notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.NotificationService;

@WebServlet(urlPatterns = { "/student/show-detail-notification", "/student/show-detail-notification/" })
public class ShowDetailOneNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowDetailOneNotificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NotificationService notificationService = new NotificationService(request, response);
		notificationService.showDetailOneNotification();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
