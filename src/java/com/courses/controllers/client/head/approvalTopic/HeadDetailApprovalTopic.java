package com.courses.controllers.client.head.approvalTopic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TopicDAO;
import com.courses.models.Topic;

@WebServlet(urlPatterns = { "/teacher/approval/deail", "/teacher/approval/deail/" })
public class HeadDetailApprovalTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TopicDAO topicDAO = null;

	public HeadDetailApprovalTopic() {
		super();
		this.topicDAO = new TopicDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String url = "/pages/client/teacher/head/detailApprovalTopic.jsp";

			String topicId = request.getParameter("topic_id").trim();
			if (topicId != null) {
				Topic topic = this.topicDAO.find(topicId);
				request.setAttribute("topic", topic);
			}
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
