package com.courses.controllers.client.head.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.client.BoardService;

@WebServlet(urlPatterns = { "/teacher/board/head/add-member", "/teacher/board/head/add-member/" })
public class AddMemberToBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddMemberToBoardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService boardService = new BoardService(request, response);
		boardService.getAddMemberToBoardForm();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService boardService = new BoardService(request, response);
		boardService.submitAddMemberToBoardForm();
	}
}
