package com.courses.controllers.client.notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.NotificationService;

@WebServlet(urlPatterns = { "/teacher/notification/detail", "/teacher/notification/detail/" })
public class TeacherSeenNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherSeenNotificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/pages/client/teacher/home.jsp";
		NotificationService ns = new NotificationService(request, response);
		ns.updateStatusNotification();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
