package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dao.BoardDao;
import board.model.service.BoardService;
import member.model.vo.MemberVo;
import reply.model.vo.ReplyVo;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/deleteBoard")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "";
		String writer = request.getParameter("writer");
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect(request.getHeader("referer"));
		} else {
			id = ((MemberVo)request.getSession().getAttribute("login")).getId();
		}
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		int havRep = new BoardService().havRep(idx);
		
		if(havRep == 0 && id.equals(writer)) {
			int result = new BoardService().deleteBoard(idx);
			if(result == 1) {
				response.sendRedirect(request.getContextPath() + "/");
			}
		} else {
			System.out.println("abc");
		}
	}
}
