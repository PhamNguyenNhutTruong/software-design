package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.TopicService;

@WebServlet(urlPatterns = { "/teacher/topic-manage/detail", "/teacher/topic-manage/detail/" })
public class TeacherDetailTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherDetailTopicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/pages/client/teacher/detailTopic.jsp";
		TopicService topicService = new TopicService(request, response);
		topicService.getGroupRegisteredTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
