package com.courses.services;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;

public class PersonService  extends SuperService{

	PersonDAO personDAO;
	public PersonService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		personDAO = new PersonDAO();
	}
	
//	public void updatePerson()  {
//		try {
//			super.setEncoding();
//			String fullname = (String)request.getAttribute("fullname");
//			String phonenumber = (String) request.getAttribute("phonenumber");
//			String address = (String) request.getAttribute("address");
//			String desc = (String) request.getAttribute("desc");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}

}
