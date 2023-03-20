package com.courses.controllers.client.manageGroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/student/group-manage/add-memeber", "/student/group-manage/add-memeber/"})
public class AddMemberToGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddMemberToGroupServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url = "/pages/client/student/addMemberForm.jsp";
		 request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
