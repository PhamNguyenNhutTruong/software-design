package com.courses.controllers.admin.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.TopicService;

@WebServlet(urlPatterns = {"/admin/topics", "/admin/topics/"})
public class ListTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListTopicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TopicService topicService = new TopicService(request, response);
		topicService.handleGetListTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
