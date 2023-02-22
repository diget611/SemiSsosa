package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardVo;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class UpdateBoardController
 */
@WebServlet("/updateBoard")
public class UpdateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "";
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect(request.getHeader("referer"));
			return;
		} else {
			id = ((MemberVo)request.getSession().getAttribute("login")).getId();
		}
		String writer = request.getParameter("writer");
		
		if(request.getSession().getAttribute("login") != null && id.equals(writer)) {
			request.getRequestDispatcher("/WEB-INF/view/board/updateBoard.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		vo.setIdx(Integer.parseInt(request.getParameter("idx")));
		vo.setCategory(Integer.parseInt(request.getParameter("category")));
		vo.setWriter(request.getParameter("writer"));
		vo.setPostName(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		int result = new BoardService().updateBoard(vo);
		
		if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/board?idx=" + request.getParameter("category"));
		} else {
			
		}
	}

}
