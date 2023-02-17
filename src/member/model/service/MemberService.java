package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.MemberVo;

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

	public MemberVo getInfo(String id) {
		MemberVo result = null;
		Connection conn = getConnection();
		result = new MemberDao().getInfo(conn, id);
		close(conn);
		return result;
	}

	public int updateInfo(MemberVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().updateInfo(conn, vo);
		close(conn);
		return result;
	}
	
}
