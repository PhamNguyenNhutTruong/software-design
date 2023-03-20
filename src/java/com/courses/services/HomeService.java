package com.courses.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;

public class HomeService extends SuperService {

	public HomeService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public HomeService() {
	}

	public void handleGetStudentHome() throws ServletException, IOException {
		// define url
		String url = "/pages/client/student/home.jsp";

		// forward information to jsp file
		super.forwardToPage(url);
	}

	public void handlePostHome() throws ServletException, IOException {

	}

	public void handleGetTeacherHomeService() throws ServletException, IOException {
		// define url for teacher home page
		try {
			String url = "/pages/client/teacher/home.jsp";
			NotificationService ns = new NotificationService(request, response);
			Person person = getPersonFromCookie();
			ns.getNotificationsByPerson(person);
			// forward information to jsp file
			super.forwardToPage(url);

			request.getSession().setAttribute("registrationPeriodForTeacherStatus", null);
			request.getSession().removeAttribute("registrationPeriodForTeacherStatus");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			String url = "/pages/500.jsp";
			response.sendRedirect(request.getContextPath() + url);
		}

	}

	public Person getPersonFromCookie() {
		Person foundPerson = null;
		String personId = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userIdCookie")) {
					personId = cookies[i].getValue();
				}
			}
		}

		if (!personId.equals("")) {
			PersonDAO personDAO = new PersonDAO();
			foundPerson = personDAO.find(Person.class, personId);
		}
		return foundPerson;
	}
}
