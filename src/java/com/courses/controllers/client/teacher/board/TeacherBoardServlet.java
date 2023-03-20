package com.courses.controllers.client.teacher.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/teacher/board")
public class TeacherBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TeacherBoardServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/client/teacher/teacherBoard.jsp";
		response.sendRedirect(request.getContextPath() + url);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
