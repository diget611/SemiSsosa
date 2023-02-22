package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;


/*
 * 게시판 테이블 명 BOARD_TEMP로 진행. 변경할 것
 * 인덱스랑 카테고리로 분류해서 카테고리별 게시판
 */

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		
		int currPage = 1;	// 현재 선택된 페이지
		if(request.getParameter("page") != null) {
			currPage = Integer.parseInt(request.getParameter("page"));
		} else {
			currPage = 1;
		}
		int pageCount = 10;									// 한 페이지에 출력할 게시글 수
		int cnt = new BoardService().getBoardCnt(idx); 		// 게시판 카테고리 내 게시글 수
		int page = 0;
		if(cnt % pageCount == 0) {							// 게시판의 페이지 수  
			page = cnt / pageCount;
		} else {
			page = cnt / pageCount + 1;
		}
		int start = 0;
		int end = 0;
		
		if(currPage % page == 0) {
			start = ((currPage / page) - 1) * 10  + 1;
		} else {
			start = (currPage / page) * 10  + 1;
		}
		end = start + 9;
		
		int startBoard;
		int endBoard;
		
		request.setAttribute("page", page);
		request.setAttribute("category", idx);
		request.setAttribute("boardList", new BoardService().getBoard(idx, start, end));
		request.getRequestDispatcher("/WEB-INF/view/board/board.jsp").forward(request, response);
	}
}
