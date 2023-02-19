package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.vo.BoardVo;
import reply.model.vo.ReplyVo;

import static common.jdbc.JDBCTemplate.*;

public class BoardDao {

	public List<BoardVo> getBoard(Connection conn) {
		List<BoardVo> result = null;
		
		String sql = "SELECT IDX, POSTNAME, CREATEDATE, WRITER, VIEWS FROM BOARD ORDER BY IDX DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			result = new ArrayList<BoardVo>();
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setCreateDate(rs.getDate("createDate"));
				vo.setIdx(rs.getInt("idx"));
				vo.setPostName(rs.getString("postName"));
				vo.setViews(rs.getInt("views"));
				vo.setWriter(rs.getString("writer"));
				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public BoardVo getBoardDetail(Connection conn, String idx) {
		BoardVo result = null;
		
		String sql = "SELECT IDX, POSTNAME, CONTENT, WRITER, CREATEDATE, VIEWS FROM BOARD WHERE IDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				result.setContent(rs.getString("content"));
				result.setCreateDate(rs.getDate("createDate"));
				result.setIdx(rs.getInt("idx"));
				result.setPostName(rs.getString("postName"));
				result.setViews(rs.getInt("views"));
				result.setWriter(rs.getString("writer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public int writePost(Connection conn, BoardVo vo) {
		int result = -1;
		String sql = "INSERT INTO BOARD VALUES(SEQ_BOARD.NEXTVAL, 1, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPostName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ReplyVo> getReplyList(Connection conn, String idx) {
		List<ReplyVo> result = null;
		
		String sql = "SELECT REPLY_CONTENT, REPLY_WRITER, REPLY_DATE FROM REPLY WHERE BOARD_REF_NUM = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			System.out.println(rs.next());
			result = new ArrayList<ReplyVo>();
			while(rs.next()) {
				ReplyVo vo = new ReplyVo();
				vo.setReplyContent(rs.getString("reply_Content"));
				vo.setReplyDate(rs.getDate("reply_Date"));
				vo.setReplyWriter(rs.getString("reply_Writer"));
				result.add(vo);
			}
			
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


}
