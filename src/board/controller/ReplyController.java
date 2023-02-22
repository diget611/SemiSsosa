package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import member.model.vo.MemberVo;
import reply.model.vo.ReplyVo;

/**
 * Servlet implementation class ReplyController
 */
@WebServlet("/board/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNumber = request.getParameter("postNumber");
		String orders = request.getParameter("orders");
		String floor = request.getParameter("floor");
		String groupNum = request.getParameter("groupNum");
		String content = request.getParameter("reply");
				
		ReplyVo vo = new ReplyVo();
		int result = -1;
		
		if(request.getSession().getAttribute("login") != null) {
			String id = ((MemberVo)request.getSession().getAttribute("login")).getId();
			if(groupNum == null) {
				// 게시글 자체에 댓글을 달 때
				// groupNum이 null이면 1, 아니면 groupNum + 1
				// orders가 null이면 1, 아니면 orders + 1
				vo = new BoardService().getReplyInfo(postNumber);
				vo.setPostNumber(Integer.parseInt(postNumber));
				vo.setFloor(0);
				vo.setContent(content);
				vo.setWriter(id);
				
				System.out.println(vo);
				result = new BoardService().insertReply(vo);
			} else {
				// 게시글의 댓글에 댓글을 달 때
				// groupNum은 댓글의 groupNum을 받아오고
				// orders는 이후의 댓글을 orders+1로 업데이트하고 댓글을 달 댓글의 orders + 1, floor + 1 해서 작성
				vo.setContent(content);
				vo.setFloor(Integer.parseInt(floor));
				vo.setOrders(Integer.parseInt(orders));
				vo.setGroupNum(Integer.parseInt(groupNum));
				vo.setPostNumber(Integer.parseInt(postNumber));
				vo.setWriter(id);
				
				result = new BoardService().insertReplyTo(vo);
			}
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/board/detail" + "?idx=" + postNumber);
			}
		} else {
			response.sendRedirect(request.getHeader("referer"));
		}
		
		
	}

}
