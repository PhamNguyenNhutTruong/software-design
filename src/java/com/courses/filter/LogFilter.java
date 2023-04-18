package com.courses.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class LogFilter implements Filter {

	public LogFilter() {}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		System.out.println("LogFilter init!");
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
		System.out.println("LogFilter destroy!");
	}
	
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println("#INFO " + " - ServletPath :" + servletPath //
				+ ", URL =" + req.getRequestURL());
		chain.doFilter(request, response);
	}

}
