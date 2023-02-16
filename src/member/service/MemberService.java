package member.service;

import member.dao.MemberDao;
import member.vo.MemberVo;

import static common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {

	public MemberVo login(MemberVo vo) {
		Connection conn = getConnection();
		MemberVo result = new MemberDao().login(conn, vo);
		close(conn);
		return result;
	}
	
}
