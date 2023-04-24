package com.courses.services.admin.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TeacherBoardDAO;
import com.courses.models.Teacher;
import com.courses.models.TeacherBoard;
import com.courses.services.SuperService;

public class TeacherBoardService extends SuperService{
	private static TeacherBoardDAO teacherBoardDAO = new TeacherBoardDAO();
	
	public TeacherBoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);

	}

	public TeacherBoardService() {
	}
	
	public List<TeacherBoard> findByTeacher(Teacher teacher) {
		List<TeacherBoard> teacherBoards = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		teacherBoards = teacherBoardDAO.findWithNamedQuery("Topic.findTopicByTeacher", map);
		return teacherBoards;
	}
}
