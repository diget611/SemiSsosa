package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.LikeVo;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class BoardLikeController
 */
@WebServlet("/like")
public class BoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeController() {
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
		  
		int like = Integer.parseInt(request.getParameter("like"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		LikeVo vo = new LikeVo();
		vo.setId(id);
		vo.setVote(like);
		vo.setIdx(idx);
		// 추천을 한 데이터가 남아있는가 확인
		int likeData = new BoardService().cntLike(idx, id);
		if(likeData == 0) {
			// 인서트
			int result = new BoardService().insertLike(vo);
			if(result == 1) {
				response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + idx);
			} else {
				System.out.println("추천/비추천 실패");
			}
		} else {
			// select로 추천인지 비추천인지 확인하고 같으면 삭제 다르면 업데이트
			int checkLike = new BoardService().checkLike(idx, id);
			if(checkLike == like) {
				int deleteResult = new BoardService().deleteLike(vo);
				if(deleteResult == 1) {
					response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + idx);
				} else {
					System.out.println("추천/비추천 실패");
				}
			} else {
				int updateResult = new BoardService().updateLike(vo);
				if(updateResult == 1) {
					response.sendRedirect(request.getContextPath() + "/board/detail?idx=" + idx);
				} else {
					System.out.println("추천/비추천 실패");
				}
			}
		}
		
	}
}
