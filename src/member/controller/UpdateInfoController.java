package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class UpdateInfoController
 */
@WebServlet("/updateinfo")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVo vo = new MemberVo();
		String id = ((MemberVo)request.getSession().getAttribute("login")).getId();
		vo.setId(id);
		vo.setPassword(request.getParameter("pwChange"));
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		
		int result = new MemberService().updateInfo(vo);
		
		if(result == 1) {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.getRequestDispatcher("/WEB-INF/view/member/mypage.jsp").forward(request, response);;
		}
	}

}
