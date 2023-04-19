package com.courses.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.models.RegistrationPeriod;
import com.courses.services.RegistrationPriodService;

@WebFilter("/test-haha")
//@WebFilter("/teacher/topic-manage/add")
public class TopicRecommendFilter extends HttpFilter implements Filter {
       
    
	
	public TopicRecommendFilter() {
        super();
    }

	public void destroy() {
	
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		RegistrationPeriod period = null;
		RegistrationPriodService rps = new RegistrationPriodService(req, res);
		period = rps.getRegistrationPeriod(Byte.parseByte("1"));
		
		if (period != null) {
			System.out.println("exist registraition period");
			HttpSession session = req.getSession();
			session.setAttribute("period", period);
			chain.doFilter(request, response);
		}else {
			// forward to login page
			
			String url = "/teacher/topic-manage";
			req.setAttribute("notExistPeriod", "1");
			req.getRequestDispatcher(url).forward(req, res);
		}
				
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
}
