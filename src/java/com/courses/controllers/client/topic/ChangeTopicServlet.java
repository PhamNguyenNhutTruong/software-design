package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.GroupService;

@WebServlet(urlPatterns = {"/topic-registration/change-topic", "/topic-registration/change-topic/"})
public class ChangeTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ChangeTopicServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("================/topic-registration/update-topic=====================");
//		String url = "/pages/client/student/topicRegistration.jsp";
//		request.setAttribute("updateTopic", "updateTopic");
//		request.getRequestDispatcher(url).forward(request, response);
		GroupService groupService = new GroupService(request, response);
		groupService.removeSelectedTopic();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
