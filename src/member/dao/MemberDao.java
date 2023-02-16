package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberVo;
import static common.jdbc.JDBCTemplate.*;

public class MemberDao {

	public MemberVo login(Connection conn, MemberVo vo) {
		MemberVo result = null;
		String sql = "SELECT ID, NAME, EMAIL FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new MemberVo();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setEmail(rs.getString("email"));
			}
			
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			commit(conn);
			close(pstmt);
			close(rs);
		}
		return result;
	}

}
