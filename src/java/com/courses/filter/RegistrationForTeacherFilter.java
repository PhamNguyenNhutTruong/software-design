package com.courses.filter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.RegistrationPeriodDAO;
import com.courses.models.RegistrationPeriod;
import com.courses.utils.helper.RandomUtils;

@WebFilter(urlPatterns = { "/test-haha"})
//@WebFilter(urlPatterns = { "/teacher/topic-manage/add", "/teacher/board/*", "/teacher/approval/*" })
public class RegistrationForTeacherFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	RegistrationPeriodDAO registrationPeriodDAO = null;

	@Override
	public void init() throws ServletException {
		super.init();
		registrationPeriodDAO = new RegistrationPeriodDAO();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Cast
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		try {

			// Get registration period active
			Map<String, Object> param = new HashMap<>();
			param.put("isActive", (byte) 1);
			RegistrationPeriod registrationPeriodActive = this.registrationPeriodDAO.findByIsActive(param);

			// Get current date
			Date date = new Date();
			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));

			if (registrationPeriodActive != null && registrationPeriodActive.getIsRegistrationTeacher() == 1) {
				// Get open and close date
				Date openDate = registrationPeriodActive.getOpenDate();
				Date closeDate = registrationPeriodActive.getCloseDate();

				if (currentDate.compareTo(openDate) >= 0 && currentDate.compareTo(closeDate) <= 0) {
					chain.doFilter(request, response);
				} else {
					request.getSession().setAttribute("registrationPeriodForTeacherStatus", "fail");
					response.sendRedirect(request.getContextPath() + "/teacher/home/");
				}
			} else {
				request.getSession().setAttribute("registrationPeriodForTeacherStatus", "fail");
				response.sendRedirect(request.getContextPath() + "/teacher/home/");
			}

		} catch (ParseException e) {
			e.printStackTrace();
			request.getSession().setAttribute("registrationPeriodForTeacherStatus", "error");
			response.sendRedirect(request.getContextPath() + "/teacher/home/");
		}

	}

}
