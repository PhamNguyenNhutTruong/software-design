package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;

import com.courses.dao.BoardDAO;
import com.courses.dao.GroupStudentDAO;
import com.courses.models.Board;
import com.courses.models.Topic;

public class BoardService extends SuperService {

	BoardDAO boardDAO = null;

	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.boardDAO = new BoardDAO();
	}

	public BoardService() {
	}

	public void handleGetListBoard() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		super.setEncoding();
		String pageUrl = "/pages/admin/board/board.jsp";
		try {
			String semester = this.request.getParameter("semester");
			String year = this.request.getParameter("year");
			Map<String, Object> map = new HashMap<>();
			map.put("isDeleted", (byte)0);
			List<Board> boards = this.boardDAO.findWithNamedQuery("Board.getListBoardWithCondiotionDeleted", map);
			int maxEntries = this.boardDAO.count();
			this.request.setAttribute("boards", boards);
			this.request.setAttribute("maxEntries", maxEntries);
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		super.forwardToPage(pageUrl);
		session.invalidate();

//		request.getSession().setAttribute("createdBoardStatus", null);
//		request.getSession().setAttribute("updatedBoardStatus", null);
	}
	
	public void handleGetListBoardIsDelete() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		super.setEncoding();
		String pageUrl = "/pages/admin/board/board.jsp";
		try {
			String semester = this.request.getParameter("semester");
			String year = this.request.getParameter("year");
			Map<String, Object> map = new HashMap<>();
			map.put("isDeleted", (byte)1);
			List<Board> boardsIdDeleted = this.boardDAO.findWithNamedQuery("Board.getListBoardWithCondiotionDeleted", map);
			int maxEntries = this.boardDAO.count();
			this.request.setAttribute("boardsIdDeleted", boardsIdDeleted);
			this.request.setAttribute("maxEntries", maxEntries);
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		super.forwardToPage(pageUrl);
		session.invalidate();

//		request.getSession().setAttribute("createdBoardStatus", null);
//		request.getSession().setAttribute("updatedBoardStatus", null);
	}
	
	public void restoreBoard() {
		HttpSession session = this.request.getSession();
		String isRestoreBoard = "failed";
		try {
			String board_id = this.request.getParameter("board-id");
			Board board = new Board();
			BoardDAO boardDAO = new BoardDAO();
			
			board = boardDAO.find(board_id);
			board.setIsDeleted((byte)0);
			boardDAO.update(board);
			isRestoreBoard = "success";
			session.setAttribute("isRestoreBoard", isRestoreBoard);
		} catch (Exception e) {
			isRestoreBoard = "failed";
			session.setAttribute("isRestoreBoard", isRestoreBoard);
		}
	}
	
	public String randomIdNotDuplicate() {
		BoardDAO boardDAO = new BoardDAO();
		String id = "";
		do {
			id = boardDAO.randomId();
		} while (boardDAO.find(id) != null);
		return id;
	}
	
	public void submitBoardForm() throws IOException, ServletException {
		try {
			super.setEncoding();
			// Url
			String url = super.getContextPath() + "/admin/boards/";

			// Get data
//			String boardId = super.getParameter("boardId");
			String boardName = super.getParameter("boardName");
			String noOfMemberStr = super.getParameter("noOfMember");
			String description = super.getParameter("description");
			String createdBoardStatus = "";
			int noOfMember = 0;

			if (noOfMemberStr != null) {
				noOfMember = Integer.parseInt(noOfMemberStr);
			}
			// Board

			Board board = new Board();
			board.setBoardId(this.randomIdNotDuplicate());
			board.setBoardName(boardName);
			board.setNoMember(noOfMember);
			board.setDescription(description);
			this.boardDAO.create(board);
			createdBoardStatus = "success";

			request.getSession().setAttribute("createdBoardStatus", createdBoardStatus);

			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void getEditBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/admin/board/editBoard.jsp";

			String boardId = super.getParameter("board_id");
			System.out.println(boardId);
			Board board = this.boardDAO.find(boardId);

			request.setAttribute("board", board);
			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void submitEditBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = super.getContextPath() + "/admin/boards/";

			String boardId = super.getParameter("boardId");
			String boardName = super.getParameter("boardName");
			String noOfMemberStr = super.getParameter("noOfMember");
			String description = super.getParameter("description");
			String updatedBoardStatus = "";

			int noOfMember = 0;

			if (noOfMemberStr != null) {
				noOfMember = Integer.parseInt(noOfMemberStr);
			}

			// Update
			Board board = this.boardDAO.find(boardId);
			board.setBoardName(boardName);
			board.setNoMember(noOfMember);
			board.setDescription(description);

			this.boardDAO.update(board);
			updatedBoardStatus = "success";

			request.getSession().setAttribute("updatedBoardStatus", updatedBoardStatus);
			super.redirectToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}
}
