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
		
		String sql = "SELECT IDX, BOARD_NAME, BOARD_CONTENT, BOARD_WRITER, BOARD_DATE FROM BOARD_TEMP ORDER BY IDX DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			result = new ArrayList<BoardVo>();
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setBoardContent(rs.getString("board_Content"));
				vo.setBoardDate(rs.getDate("board_Date"));
				vo.setBoardName(rs.getString("board_Name"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setIdx(rs.getInt("idx"));
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
		
		String sql = "SELECT IDX, BOARD_NAME, BOARD_CONTENT, BOARD_WRITER, BOARD_DATE FROM BOARD_TEMP WHERE IDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				result.setBoardContent(rs.getString("board_Content"));
				result.setBoardDate(rs.getDate("board_Date"));
				result.setBoardWriter(rs.getString("board_Writer"));
				result.setBoardName(rs.getString("board_Name"));
				result.setIdx(rs.getInt("idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
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
