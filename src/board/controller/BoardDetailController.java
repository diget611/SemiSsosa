package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		int idxInt = Integer.parseInt(request.getParameter("idx"));
		
		// 추천 비추천 개수 확인용 쿼리
		int likeCount = new BoardService().countLike(idxInt, 1);
		int dislikeCount = new BoardService().countLike(idxInt, -1);

		int calLike = likeCount - dislikeCount;
		System.out.println(likeCount + "|" + dislikeCount + "|" + calLike);
				
		request.setAttribute("like", calLike);
		request.setAttribute("boardDetail", new BoardService().getBoardDetail(idx));
		request.setAttribute("replyList", new BoardService().getReplyList(idx));
		request.getRequestDispatcher("/WEB-INF/view/board/boardDetail.jsp").forward(request, response);
	}

}
