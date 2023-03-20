package com.courses.controllers.client.head.approvalTopic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TopicDAO;
import com.courses.models.Topic;

@WebServlet(urlPatterns = { "/teacher/approval", "/teacher/approval/" })
public class HeadApprovalTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TopicDAO topicDAO = null;

	public HeadApprovalTopic() {
		super();
		topicDAO = new TopicDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = "/pages/client/teacher/head/listTopic.jsp";

		byte status = 0;
		byte isDeleted = 0;
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("isDeleted", isDeleted);

		List<Topic> topics = this.topicDAO.findByStatusAndIsDeleted(params);
		request.setAttribute("topics", topics);
		request.getRequestDispatcher(url).forward(request, response);
		request.getSession().setAttribute("acceptApprovalTopicStatus", null);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
