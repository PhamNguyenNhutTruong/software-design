package com.courses.services.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.BoardDAO;
import com.courses.dao.GroupStudentDAO;
import com.courses.dao.PersonDAO;
import com.courses.dao.TeacherBoardDAO;
import com.courses.dao.TeacherDAO;
import com.courses.models.Board;
import com.courses.models.GroupStudent;
import com.courses.models.Person;
import com.courses.models.Teacher;
import com.courses.models.TeacherBoard;
import com.courses.models.TeacherBoardPK;
import com.courses.services.SuperService;

public class BoardService extends SuperService {
	BoardDAO boardDAO = null;
	TeacherBoardDAO teacherBoardDAO = null;
	TeacherDAO teacherDAO = null;
	GroupStudentDAO groupStudentDAO = null;
	PersonDAO personDAO = null;

	// Constructor
	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.boardDAO = new BoardDAO();
		this.teacherBoardDAO = new TeacherBoardDAO();
		this.teacherDAO = new TeacherDAO();
		this.groupStudentDAO = new GroupStudentDAO();
		this.personDAO = new PersonDAO();
	}

	// [GET] ListHeadBoardServlet => Head
	public void getListHeadBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/boardManage.jsp";

			List<Board> boards = this.boardDAO.findAll();
			request.setAttribute("boards", boards);

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] GetDetailHeadBoardServlet => Head
	public void getDetailHeadBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/detailHeadBoard.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			Board board = this.boardDAO.find(boardId);

			List<TeacherBoard> teacherBoards = new ArrayList<>();
			List<GroupStudent> groupStudents = new ArrayList<>();

			if (board != null) {
				Map<String, Object> params = new HashMap<>();
				params.put("board", board);
				teacherBoards = this.teacherBoardDAO.findByBoard(params);
				groupStudents = this.groupStudentDAO.findByBoard(params);
			}

			// Set board
			request.setAttribute("board", board);
			request.setAttribute("boardId", boardId);
			request.setAttribute("teacherBoards", teacherBoards);
			request.setAttribute("groupStudents", groupStudents);

			super.forwardToPage(url);

			// Remove status add member to board
			request.getSession().setAttribute("addedTeacherBoardStatus", null);
			request.getSession().setAttribute("addedGroupBoardStatus", null);
			request.getSession().setAttribute("deletedTeacherBoardStatus", null);
			request.getSession().setAttribute("deletedGroupBoardStatus", null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] AddMemberToBoardServlet => Head
	public void getAddMemberToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/addMemberForm.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			// Set board
			request.setAttribute("boardId", boardId);

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [POST] AddMemeberToBoardServlet => Head
	public void submitAddMemberToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();

			// Get param
			String boardId = request.getParameter("boardId");
			String teacherId = super.getParameter("teacherId");
			String url = super.getContextPath() + "/teacher/board/head/detail/?board_id=" + boardId;
			String addedTeacherBoardStatus = "";

			// Get data
			Board board = this.boardDAO.find(boardId);
			Teacher teacher = this.teacherDAO.find(teacherId);
			if (teacher != null && teacher.getPerson().getIsDeleted() == 0) {
				// Make primary key
				TeacherBoardPK teacherBoardPK = new TeacherBoardPK();
				teacherBoardPK.setBoardId(boardId);
				teacherBoardPK.setTeacherId(teacherId);

				TeacherBoard teacherBoardExist = this.teacherBoardDAO.find(teacherBoardPK);
				Map<String, Object> params = new HashMap<>();
				params.put("board", board);
				int countTeacherInBoard = this.teacherBoardDAO.countByBoard(params);

				if (teacherBoardExist == null && countTeacherInBoard < board.getNoMember()) {
					// Make record
					TeacherBoard teacherBoard = new TeacherBoard();
					teacherBoard.setId(teacherBoardPK);
					teacherBoard.setBoard(board);
					teacherBoard.setTeacher(teacher);
					teacherBoard.setIsDeleted((byte) 0);
					// create
					this.teacherBoardDAO.create(teacherBoard);
					addedTeacherBoardStatus = "success";
				} else {
					addedTeacherBoardStatus = "fail";
				}
			} else {
				addedTeacherBoardStatus = "fail";
			}
			request.getSession().setAttribute("addedTeacherBoardStatus", addedTeacherBoardStatus);
			// redirect
			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] AddGroupToBoardFormServlet => Head
	public void getAddGroupToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/addGroupForm.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			// Set board
			request.setAttribute("boardId", boardId);

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [POST] AddGroupToBoardServlet => Head
	public void submitAddGroupToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Get param
			String boardId = request.getParameter("boardId");
			String groupId = super.getParameter("groupId");
			String url = super.getContextPath() + "/teacher/board/head/detail/?board_id=" + boardId;
			String addedGroupBoardStatus = "";

			// Get data
			Board board = this.boardDAO.find(boardId);
			GroupStudent groupStudent = this.groupStudentDAO.find(groupId);

			if (groupStudent != null && groupStudent.getBoard() == null) {
				groupStudent.setBoard(board);
				this.groupStudentDAO.update(groupStudent);
				addedGroupBoardStatus = "success";
			} else {
				addedGroupBoardStatus = "fail";
			}
			request.getSession().setAttribute("addedGroupBoardStatus", addedGroupBoardStatus);
			// redirect
			super.redirectToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] ListBoardServlet => Normal Teacher
	public void getListTeacherBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/boardManage.jsp";
			String personId = "";

			// Get teacher from cookies
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userIdCookie")) {
						personId = cookie.getValue();
						break;
					}
				}
			}

			// Get person
			if (!personId.equals("")) {
				// Get teacher using personId;
				Person person = this.personDAO.find(personId);
				if (person != null) {
					// Get teacher
					Map<String, Object> params = new HashMap<>();
					params.put("person", person);
					Teacher teacher = this.teacherDAO.findByPerson(params);

					// remove old param;
					params.remove("person");
					// add new param
					params.put("teacher", teacher);
					// Get list teacher board contain this teacher
					List<TeacherBoard> teacherBoards = this.teacherBoardDAO.findByTeacher(params);
					request.setAttribute("teacherBoards", teacherBoards);
				}
			}

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] DetailBoardServlet => Normal Teacher
	public void getDetailBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			System.out.println("Hello world");
			// Url
			String url = "/pages/client/teacher/detailBoard.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			Board board = this.boardDAO.find(boardId);

			List<TeacherBoard> teacherBoards = new ArrayList<>();
			List<GroupStudent> groupStudents = new ArrayList<>();

			if (board != null) {
				Map<String, Object> params = new HashMap<>();
				params.put("board", board);
				teacherBoards = this.teacherBoardDAO.findByBoard(params);
				groupStudents = this.groupStudentDAO.findByBoard(params);
			}

			// Set board
			request.setAttribute("board", board);
			request.setAttribute("boardId", boardId);
			request.setAttribute("teacherBoards", teacherBoards);
			request.setAttribute("groupStudents", groupStudents);

			super.forwardToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] DeleteGroupToBoardServlet => Head
	public void deleteMemberToBoard() throws ServletException, IOException {
		try {
			super.setEncoding();

			// Get Board
			String boardId = request.getParameter("board_id");
			String teacherId = request.getParameter("teacher_id");
			String deletedTeacherBoardStatus = "";

			// Get data
			Board board = this.boardDAO.find(boardId);
			Teacher teacher = this.teacherDAO.find(teacherId);

			// Url
			String url = super.getContextPath() + "/teacher/board/head/detail/?board_id=" + boardId;

			if (teacher != null && board != null) {
				// Make primary key
				TeacherBoardPK teacherBoardPK = new TeacherBoardPK();
				teacherBoardPK.setBoardId(boardId);
				teacherBoardPK.setTeacherId(teacherId);

				// Delete if exits
				this.teacherBoardDAO.delete(teacherBoardPK);
				deletedTeacherBoardStatus = "success";
			} else {
				deletedTeacherBoardStatus = "fail";
			}

			request.getSession().setAttribute("deletedTeacherBoardStatus", deletedTeacherBoardStatus);
			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	// [GET] DeleteGroupToBoardServlet => Head
	public void deleteGroupToBoard() throws ServletException, IOException {
		try {
			super.setEncoding();

			// Get Board
			String boardId = request.getParameter("board_id");
			String groupId = request.getParameter("group_id");
			String deletedGroupBoardStatus = "";

			// Get data
			Board board = this.boardDAO.find(boardId);
			GroupStudent groupStudent = this.groupStudentDAO.find(groupId);

			// Url
			String url = super.getContextPath() + "/teacher/board/head/detail/?board_id=" + boardId;

			if (groupStudent != null && board != null) {
				// Update board if exits
				groupStudent.setBoard(null);
				this.groupStudentDAO.update(groupStudent);
				deletedGroupBoardStatus = "success";

			} else {

				deletedGroupBoardStatus = "fail";
			}

			request.getSession().setAttribute("deletedGroupBoardStatus", deletedGroupBoardStatus);
			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}
}
