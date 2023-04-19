package com.courses.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.TeacherDAO;
import com.courses.dao.TopicDAO;
import com.courses.models.GroupStudent;
import com.courses.models.Person;
import com.courses.models.RegistrationPeriod;
import com.courses.models.Student;
import com.courses.models.Teacher;
import com.courses.models.Topic;
import com.courses.services.admin.user.StudentService;

public class TopicService extends SuperService {

	private static TopicDAO topicDAO = new TopicDAO();
	private static GroupStudentDAO groupDAO = new GroupStudentDAO();
	private static TeacherDAO teacherDAO = new TeacherDAO();
	TeacherDAO teacherDAO = new TeacherDAO();

	public TopicService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);

	}

	public TopicService() {
	}

	public void getTopic(byte option) throws ServletException, IOException {
		String activeButtonUnselected = "";
		String activeButtonSelected = "topic_registration-filter-active";
		String url = "/pages/client/student/topicRegistration.jsp";
		List<Topic> topics = new ArrayList<Topic>();
		TopicService topicService = new TopicService(request, response);

		if (option == 0) {
			activeButtonUnselected = "topic_registration-filter-active";
			activeButtonSelected = "";
		} else if (option == 1) {
			activeButtonUnselected = "";
			activeButtonSelected = "topic_registration-filter-active";
		}
		topics = topicService.getTopicByOptionSelect(option);
		this.request.setAttribute("activeButtonUnselected", activeButtonUnselected);
		this.request.setAttribute("activeButtonSelected", activeButtonSelected);
		this.request.setAttribute("topics", topics);
		this.request.getRequestDispatcher(url).forward(request, response);
	}

	public void registerTopic() throws ServletException, IOException {
		String isRegistrationTopic = "";
		try {
			String url = "/student/topic-registration";
			Topic topic = new Topic();
			GroupService groupService = new GroupService(request, response);
			StudentService studentService = new StudentService(request, response);
			Map<String, Object> map = new HashMap<String, Object>();
			List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
			String topicdId = request.getParameter("topic-id");
			String studentId = studentService.getStudentByPersonToLoginData().getStudentId();

			map.put("leaderId", studentId);
			groupStudents = groupService.checkLeader(map);

			if (groupStudents.size() > 0) {
				topic = topicDAO.find(Topic.class, topicdId);
				if (topic.getMaxMoMember() >= groupStudents.get(0).getCurrentNumber()) {
					groupService.choiceTopic(studentId, topic);
					// this.getTopic((byte)0);
					isRegistrationTopic = "SUCCESS";
				} else {
					isRegistrationTopic = "FAILED";
				}

			} else {
				isRegistrationTopic = "FAILED";
			}
			this.request.setAttribute("isRegistrationTopic", isRegistrationTopic);
			this.request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			String url = "/pages/client/student/topicRegistration.jsp";
//			String pageUrl = "/pages/500.jsp";
			System.out.print(e.toString());
			isRegistrationTopic = "FAILED";
			this.request.setAttribute("isRegistrationTopic", isRegistrationTopic);
			this.request.getRequestDispatcher(url).forward(request, response);
		}
	}

	public void handleGetListTopic() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/topic/topic.jsp";
			List<Topic> topics = topicDAO.findAll();
			this.request.setAttribute("topics", topics);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}

	public List<Topic> getTopicByOptionSelect(byte option) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_selected", option);
		List<Topic> topics = new ArrayList<Topic>();
		topics = TopicService.topicDAO.findWithNamedQuery("Topic.getTopicByConditionSelect", map);
		return topics;
	}

	public void handleGetTeacherAddTopic() throws ServletException, IOException {
		String url = "/pages/client/teacher/addTopic.jsp";
		super.forwardToPage(url);
	}

	public void handlePostTeacherAddTopic() throws ServletException, IOException {
		this.request.setCharacterEncoding("UTF-8");
		try {
			// get saved information in session
			HttpSession session = this.request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			RegistrationPeriod period = (RegistrationPeriod) session.getAttribute("period");

			// get parameter from the add topic form
			String topicId = this.request.getParameter("topicId");
			System.out.println("topic Id is: " + topicId);
			String topicName = this.request.getParameter("topicName");
			int noOfMember = Integer.parseInt(request.getParameter("numberOfMember"));
			String description = this.request.getParameter("topicDescription");

			// set topic properties
			Topic newTopic = new Topic();
			newTopic.setTopicId(topicId);
			newTopic.setTeacher(teacher);
			newTopic.setMajor(teacher.getMajor());
			newTopic.setRegistrationperiod(period);
			newTopic.setTopicName(topicName);
			newTopic.setMaxMoMember(noOfMember);
			newTopic.setDescription(description);

			// save
			TopicDAO td = new TopicDAO();
			td.create(newTopic);

			this.request.setAttribute("isAdded", "1");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void getGroupRegisteredTopic() throws ServletException, IOException {
		String url = "/pages/client/teacher/detailTopic.jsp";
		try {
			// get topic id from url parameter
			String topicId = this.request.getParameter("topic");
			// find the topic by id
			Topic foundTopic = null;
			foundTopic = topicDAO.find(Topic.class, topicId);
			// get List of group registered the topic
			if (foundTopic != null) {
				// define a map
				Map<GroupStudent, List<Student>> groupStudentMap = new HashMap<GroupStudent, List<Student>>();
				// find student information of group
				GroupService groupService = new GroupService();
				groupStudentMap = groupService.getGroupStudentInfomation(foundTopic);
				// test
				for (GroupStudent key : groupStudentMap.keySet()) {
					System.out.println(key.getLeaderId());
					for (Student student : groupStudentMap.get(key)) {
						System.out.println(student.getPerson().getFullName());
					}
				}

				this.request.setAttribute("groupStudentMap", groupStudentMap);
				this.request.setAttribute("topicName", foundTopic.getTopicName());
			}
			super.forwardToPage(url);

		} catch (Exception ex) {
			System.out.println(ex);
			url = "/pages/500.jsp";
			super.redirectToPage(this.request.getContextPath() + url);
		}
	}

	public void getTeacherRecommendTopic() throws ServletException {

		try {
			String url = "/pages/client/teacher/topicManage.jsp";
			// get session
			HttpSession session = request.getSession();
			// get infor in session
			Person person = (Person) session.getAttribute("person");
			String isSelected = request.getParameter("select");
			String isActive = request.getParameter("status");
			if (isSelected == null) {
				isSelected = "0";
			}
			// get teacher
			Teacher teacher = TeacherService.getTeacherByPerson(person);
			// get list teacher's topic
			List<Topic> topics = null;
			if (teacher != null) {
				if (isActive == null) {
					// if isActive null, choose the topics that were not selected by student
					topics = getSpecifiedTopic(teacher, Byte.valueOf(isSelected));
				} else {
					topics = getTopicByTeacherAndStatus(teacher);
				}
				request.setAttribute("topics", topics);
			}
			super.forwardToPage(url);
			
			// remove session
			request.getSession().setAttribute("deletedTopicStatus", null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Topic> getTopicByTeacherAndStatus(Teacher teacher) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		topics = topicDAO.findWithNamedQuery("Topic.findTopicByTeacherAndStatus", map);
		return topics;
	}

	public static List<Topic> getSpecifiedTopic(Teacher teacher, Byte isSelected) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		map.put("isSelected", isSelected);
		topics = topicDAO.findWithNamedQuery("Topic.findSpecifiedTopic", map);
		return topics;
	}

	public void deleteTopicNotApproval() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String pageUrl = super.getContextPath() + "/teacher/topic-manage?status=0";

			// Get param
			String topicId = request.getParameter("topic");
			String deletedTopicStatus = "";

			// Get data
			Topic topic = TopicService.topicDAO.find(topicId);

			// Check
			if (topic != null && topic.getStatus() == 0) {
				TopicService.topicDAO.delete(topicId);
				deletedTopicStatus = "success";
			} else {
				deletedTopicStatus = "fail";
			}

			request.getSession().setAttribute("deletedTopicStatus", deletedTopicStatus);
			// redirect
			super.redirectToPage(pageUrl);
		} catch (Exception e) {
			String pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}
	
	public List<Topic> getTopicByTeacher(Teacher teacher) {
		List<Topic> topics = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		topics = topicDAO.findWithNamedQuery("Topic.findTopicByTeacher", map);
		return topics;
	}

}
