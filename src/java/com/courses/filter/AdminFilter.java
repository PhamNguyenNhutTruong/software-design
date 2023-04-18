package com.courses.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;
import com.courses.services.HomeService;
import com.courses.services.NotificationService;
import com.courses.utils.constants.RoleConstants;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private Cookie[] cookies;
	public AdminFilter() {
        super();
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// get cookie is existing 
		String personId = "";
		cookies = req.getCookies();
		if (cookies == null) {					
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		for (int i = 0; i < cookies.length; i++ ) {
			if (cookies[i].getName().equals("userIdCookie")) {
				personId = cookies[i].getValue();
				break;
			}
		}
		Person person = null;
		if (personId.equals("")) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		PersonDAO personDAO = new PersonDAO();
		person = personDAO.find(Person.class, personId);
		if (person == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		switch (person.getRole()) {
			case RoleConstants.TEACHER:
				res.sendRedirect(req.getContextPath() + "/teacher/home");
				break;
			case RoleConstants.ADMIN:
				chain.doFilter(request, response);
				break;
			case RoleConstants.STUDENT:
				res.sendRedirect(req.getContextPath() + "/student/home");
				break;
		}
	}
}
