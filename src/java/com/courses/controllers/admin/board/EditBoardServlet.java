package com.courses.controllers.admin.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.BoardService;

@WebServlet(urlPatterns = { "/admin/boards/edit", "/admin/boards/edit/" })
public class EditBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditBoardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService boardService = new BoardService(request, response);
		boardService.getEditBoardForm();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService boardService = new BoardService(request, response);
		boardService.submitEditBoardForm();
	}

}
