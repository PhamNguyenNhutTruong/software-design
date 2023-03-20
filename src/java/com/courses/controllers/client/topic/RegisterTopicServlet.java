package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.TopicService;

@WebServlet(urlPatterns = { "/topic-registration/register-topic", "/topic-registration/register-topic/" })
public class RegisterTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterTopicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicService topicService = new TopicService(request, response);
		topicService.registerTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
