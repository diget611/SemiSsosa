package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class ReplyDeleteController
 */
@WebServlet("/deleteReply")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String writer = request.getParameter("writer");
		int havr = Integer.parseInt(request.getParameter("havr"));
		
		if(request.getSession().getAttribute("login") != null) {
			String id = ((MemberVo)request.getSession().getAttribute("login")).getId();
			if(id.equals(writer) && havr == 0) {
				int result = new BoardService().deleteReply(idx);
				if(result == 1) {
					response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + request.getParameter("board"));
				} else {
					System.out.println("어디로 가야하오");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + request.getParameter("board"));
				System.out.println("작성자가 아니거나 댓글이 달려있습니다.");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + request.getParameter("board"));
			System.out.println("로그인 중이 아닙니다.");
		}
		
	}
}
