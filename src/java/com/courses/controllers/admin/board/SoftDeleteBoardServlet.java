package com.courses.controllers.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.BoardDAO;
import com.courses.models.Board;
import com.courses.models.Person;


@WebServlet(urlPatterns = { "/admin/board/delete", "/admin/board/delete/" })
public class SoftDeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SoftDeleteBoardServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardId = request.getParameter("boardId");
		try {
		// Get person
		BoardDAO boardDAO = new BoardDAO();
		Board board = new Board();
		
		board = boardDAO.find(boardId);
		if (board != null) {
			board.setIsDeleted((byte)1);
			boardDAO.update(board);
			PrintWriter pw = response.getWriter();
			pw.append("Delete User Success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
