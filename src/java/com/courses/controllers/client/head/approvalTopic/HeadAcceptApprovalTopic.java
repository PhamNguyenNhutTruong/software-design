package com.courses.controllers.client.head.approvalTopic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.TopicDAO;
import com.courses.models.Teacher;
import com.courses.models.Topic;
import com.courses.services.NotificationService;

@WebServlet(urlPatterns = { "/teacher/approval/accept", "/teacher/approval/accept/" })
public class HeadAcceptApprovalTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TopicDAO topicDAO = null;
	public HeadAcceptApprovalTopic() {
		super();
		topicDAO = new TopicDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Teacher head = (Teacher)session.getAttribute("teacher");
			// Topic
			byte status = 1;
			String acceptApprovalTopicStatus = "";
			String topicId = request.getParameter("topic_id");
			System.out.println(topicId);
			if (topicId != null) {
				Topic topic = this.topicDAO.find(topicId);

				// Update
				topic.setStatus(status);
				this.topicDAO.update(topic);
				// create notification
				NotificationService ns = new NotificationService(request, response);
				if (ns.createApprovalTopicNotification(head.getPerson(), topic)) {
					System.out.println("Create notification successfully");
				}else {
					System.out.println("Create notification fail");
				}
				acceptApprovalTopicStatus = "success";
			}
			request.getSession().setAttribute("acceptApprovalTopicStatus", acceptApprovalTopicStatus);
			response.sendRedirect(request.getContextPath() + "/teacher/approval");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
