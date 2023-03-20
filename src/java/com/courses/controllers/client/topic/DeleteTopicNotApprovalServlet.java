package com.courses.controllers.client.topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TopicDAO;
import com.courses.models.GroupStudent;
import com.courses.models.Student;
import com.courses.models.Topic;
import com.courses.services.GroupService;
import com.courses.services.TopicService;
import com.courses.services.admin.user.StudentService;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
