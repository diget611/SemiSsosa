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
 * Servlet implementation class WritePostController
 */
@WebServlet("/writePost")
public class WritePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WritePostController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("login") != null) {
			request.getRequestDispatcher("/WEB-INF/view/board/writePost.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = ((MemberVo)(request.getSession().getAttribute("login"))).getId();
		int category = Integer.parseInt(request.getParameter("category"));
		
		BoardVo vo = new BoardVo();
		vo.setPostName(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setWriter(id);
		vo.setCategory(category);
		
		System.out.println(vo.getPostName());
		
		int result = new BoardService().writePost(vo);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/board?idx=" + category);
		} else {
			
		}
	}

}
