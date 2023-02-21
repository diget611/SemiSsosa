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

	public List<BoardVo> getBoard(Connection conn, String idx) {
		List<BoardVo> result = null;
		
		String sql = "SELECT IDX, POSTNAME, CREATEDATE, WRITER, VIEWS FROM BOARD_T"
				+ " WHERE CATEGORY = ? AND DELETEDATE IS NULL ORDER BY IDX DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
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
		
		String sql = "SELECT IDX, POSTNAME, CONTENT, WRITER, CREATEDATE, VIEWS FROM BOARD_T WHERE IDX = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sqlUp = "UPDATE BOARD_T SET VIEWS = VIEWS + 1 WHERE IDX = ?";
		PreparedStatement pstmtUp = null;
		
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
				result.setViews(rs.getInt("views") + 1);
				result.setWriter(rs.getString("writer"));
				
				pstmtUp = conn.prepareStatement(sqlUp);
				pstmtUp.setString(1, idx);
				
				pstmtUp.executeUpdate();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	public void addViews() {
		
	}
	
	public int writePost(Connection conn, BoardVo vo) {
		int result = -1;
		String sql = "INSERT INTO BOARD_T VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCategory());
			pstmt.setString(2, vo.getPostName());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ReplyVo getReplyInfo(Connection conn, String postNumber) {
		ReplyVo result = null;
		
		String sql = "SELECT MAX(ORDERS) AS MAXORD, MAX(GROUPNUM) AS MAXGROUP FROM REPLY WHERE POSTNUMBER = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  postNumber);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new ReplyVo();
				result.setOrders(rs.getInt(1));
				result.setGroupNum(rs.getInt(2));
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
		
		String sql = "SELECT POSTNUMBER, CONTENT, CREATEDATE, ORDERS, FLOOR, GROUPNUM, WRITER FROM REPLY WHERE POSTNUMBER = ? AND DELETEDATE IS NULL ORDER BY ORDERS";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			result = new ArrayList<ReplyVo>();
			while(rs.next()) {
				ReplyVo vo = new ReplyVo();
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("createDate"));
				vo.setFloor(rs.getInt("floor"));
				vo.setGroupNum(rs.getInt("groupNum"));
				vo.setOrders(rs.getInt("orders"));
				vo.setWriter(rs.getString("writer"));
				vo.setPostNumber(rs.getInt("postNumber"));
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

	public int insertReply(Connection conn, ReplyVo vo) {
		int result = -1;
//ReplyVo [idx=0, postNumber=3, content=, createDate=null, updateDate=null, deleteDate=null, orders=5, floor=0, groupNum=1, writer=user2]
		String sql = "INSERT INTO REPLY VALUES(SEQ_REPLY.NEXTVAL, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, 0, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostNumber());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getOrders() + 1);
			pstmt.setInt(4, vo.getGroupNum() + 1);
			pstmt.setString(5, vo.getWriter());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				commit(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReplyTo(Connection conn, ReplyVo vo) {
		int result = -1;
		int resultOrder = -1;
		//ReplyVo [idx=0, postNumber=13, content=, createDate=null, updateDate=null, deleteDate=null, orders=1, floor=0, groupNum=1, writer=user2]
			String sql = "INSERT INTO REPLY VALUES(SEQ_REPLY.NEXTVAL, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?)";
			String sqlOrder = "UPDATE REPLY SET ORDERS = ORDERS + 1 WHERE ORDERS > ?"; 
			PreparedStatement pstmt = null;
			PreparedStatement pstmtOrder = null;
			try {
				pstmtOrder = conn.prepareStatement(sqlOrder);
				pstmtOrder.setInt(1, vo.getOrders());
				
				resultOrder = pstmtOrder.executeUpdate();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getPostNumber());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getOrders() + 1);
				pstmt.setInt(4, vo.getFloor() + 1);
				pstmt.setInt(5, vo.getGroupNum());
				pstmt.setString(6, vo.getWriter());
				
				result = pstmt.executeUpdate();
				
				if(result > 0 && resultOrder > 0) {
					commit(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
	}


}
