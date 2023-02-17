package member.service;

import member.dao.MemberDao;
import member.vo.MemberVo;

import static common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {

	public MemberVo login(MemberVo vo) {
		MemberVo result = null;
		Connection conn = getConnection();
		result = new MemberDao().login(conn, vo);
		close(conn);
		return result;
	}

	public int enroll(MemberVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().enroll(conn, vo); 
		close(conn);
		return result;
	}
	
}
