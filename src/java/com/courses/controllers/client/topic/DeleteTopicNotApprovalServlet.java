package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.TopicService;

/**
 * Servlet implementation class TopicRegistration
 */
@WebServlet(urlPatterns = { "/teacher/topic-manage/delete", "/teacher/topic-manage/delete/" })
public class DeleteTopicNotApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteTopicNotApprovalServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicService topicService = new TopicService(request, response);
		topicService.deleteTopicNotApproval();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
