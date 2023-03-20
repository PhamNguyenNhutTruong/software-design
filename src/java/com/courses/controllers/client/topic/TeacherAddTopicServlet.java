package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.TopicService;

@WebServlet(urlPatterns = { "/teacher/topic-manage/add", "/teacher/topic-manage/add/" })
public class TeacherAddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherAddTopicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicService ts = new TopicService(request, response);
		ts.handleGetTeacherAddTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicService ts = new TopicService(request, response);
		ts.handlePostTeacherAddTopic();
		doGet(request, response);
	}

}
