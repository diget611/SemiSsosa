package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.vo.BoardVo;
import board.model.vo.LikeVo;
import reply.model.vo.ReplyVo;

import static common.jdbc.JDBCTemplate.*;

public class BoardDao {

	public List<BoardVo> getBoard(Connection conn, String idx, int start, int end) {
		List<BoardVo> result = null;
		
		String sql = "SELECT ORD, IDX, POSTNAME, CREATEDATE, WRITER, VIEWS FROM "
				+ " (SELECT ROWNUM AS ORD, IDX, POSTNAME, CREATEDATE, WRITER, VIEWS FROM( "
				+ " SELECT IDX, POSTNAME, CREATEDATE, WRITER, VIEWS FROM BOARD_T WHERE CATEGORY = ? AND DELETEDATE IS NULL ORDER BY IDX DESC)) "
				+ "WHERE ORD BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
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
		
		String sql = "SELECT IDX, CATEGORY, POSTNAME, CONTENT, WRITER, CREATEDATE, VIEWS FROM BOARD_T WHERE IDX = ?";
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
				result.setCategory(rs.getInt("category"));
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
	
	public int writePost(Connection conn, BoardVo vo) {
		int result = -1;
		String sql = "INSERT INTO BOARD_T VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, DEFAULT, DEFAULT, DEFAULT)";
		
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
		
		String sql = "SELECT IDX, POSTNUMBER, CONTENT, CREATEDATE, ORDERS, FLOOR, GROUPNUM, WRITER, HAVR FROM REPLY WHERE POSTNUMBER = ? AND DELETEDATE IS NULL ORDER BY ORDERS";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			result = new ArrayList<ReplyVo>();
			while(rs.next()) {
				ReplyVo vo = new ReplyVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("createDate"));
				vo.setFloor(rs.getInt("floor"));
				vo.setGroupNum(rs.getInt("groupNum"));
				vo.setOrders(rs.getInt("orders"));
				vo.setWriter(rs.getString("writer"));
				vo.setPostNumber(rs.getInt("postNumber"));
				vo.setHavr(rs.getInt("havr"));
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
		String sql = "INSERT INTO REPLY VALUES(SEQ_REPLY.NEXTVAL, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, 0, ?, ?, DEFAULT)";
		String sqlUp = "UPDATE BOARD_T SET HAVR = 1 WHERE IDX = ?";
		PreparedStatement pstmt = null;
		PreparedStatement pstmtUp = null;
		try {
			pstmtUp = conn.prepareStatement(sqlUp);
			pstmtUp.setInt(1, vo.getPostNumber());
			pstmtUp.executeUpdate();
			
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
		int resultHavr = -1;
		int resultOrder = -1;
		//ReplyVo [idx=0, postNumber=13, content=, createDate=null, updateDate=null, deleteDate=null, orders=1, floor=0, groupNum=1, writer=user2]
			String sql = "INSERT INTO REPLY VALUES(SEQ_REPLY.NEXTVAL, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?, DEFAULT)";
			String sqlHavr = "UPDATE REPLY SET HAVR = 1 WHERE ORDERS = ?";
			String sqlOrder = "UPDATE REPLY SET ORDERS = ORDERS + 1 WHERE ORDERS > ?"; 
			PreparedStatement pstmt = null;
			PreparedStatement pstmtHavr = null;
			PreparedStatement pstmtOrder = null;
			try {
				pstmtHavr = conn.prepareStatement(sqlHavr);
				pstmtHavr.setInt(1, vo.getOrders());
				resultHavr = pstmtHavr.executeUpdate();
				
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
				
				if(result > 0 && resultOrder > 0 && resultHavr > 0) {
					commit(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
	}

	public int updateBoard(Connection conn, BoardVo vo) {
		int result = -1;
		String sql = "UPDATE BOARD_T SET POSTNAME = ?, CONTENT = ?, UPDATEDATE = SYSTIMESTAMP WHERE IDX = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPostName());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getIdx());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int idx) {
		int result = -1;
		String sql = "UPDATE BOARD_T SET DELETEDATE = SYSTIMESTAMP WHERE IDX = ? AND HAVR = 0";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int havRep(Connection conn, int idx) {
		int result = -1;
		String sql = "SELECT COUNT(*) CNT FROM REPLY WHERE POSTNUMBER = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(Connection conn, int idx) {
		int result = -1;
		String sql = "UPDATE REPLY SET DELETEDATE = SYSTIMESTAMP WHERE IDX = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getBoardCnt(Connection conn, String idx) {
		int result = 0;
		String sql = "SELECT COUNT(*) CNT FROM BOARD_T WHERE CATEGORY = ? AND DELETEDATE IS NULL";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int cntLike(Connection conn, int idx, String id) {
		int result = 0;
		String sql = "SELECT COUNT(*) CNT FROM BOARD_VOTE WHERE POST_IDX = ? AND ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int insertLike(Connection conn, LikeVo vo) {
		int result = -1;
		String sql = "INSERT INTO BOARD_VOTE VALUES(?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setInt(2, vo.getVote());
			pstmt.setString(3, vo.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkLike(Connection conn, int idx, String id) {
		int result = 0;
		String sql = "SELECT VOTE FROM BOARD_VOTE WHERE POST_IDX = ? AND ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("VOTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int deleteLike(Connection conn, LikeVo vo) {
		int result = -1;
		String sql = "DELETE FROM BOARD_VOTE WHERE POST_IDX = ? AND ID = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateLike(Connection conn, LikeVo vo) {
		int result = -1;
		String sql = "UPDATE BOARD_VOTE SET VOTE = ? WHERE POST_IDX = ? AND ID = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getVote());
			pstmt.setInt(2, vo.getIdx());
			pstmt.setString(3, vo.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countLike(Connection conn, int idx, int i) {
		int result = 0;
		String sql = "SELECT COUNT(*) CNT FROM BOARD_VOTE WHERE POST_IDX = ? AND VOTE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setInt(2, i);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


}
