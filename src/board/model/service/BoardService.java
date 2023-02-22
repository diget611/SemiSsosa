package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.BoardVo;
import reply.model.vo.ReplyVo;

import static common.jdbc.JDBCTemplate.*;

public class BoardService {

	public List<BoardVo> getBoard(String idx, int start, int end) {
		List<BoardVo> result = null;
		Connection conn = getConnection();
		result = new BoardDao().getBoard(conn, idx, start, end);
		close(conn);
		return result;
	}

	public BoardVo getBoardDetail(String idx) {
		BoardVo result = null;
		Connection conn = getConnection();
		result = new BoardDao().getBoardDetail(conn, idx);
		close(conn);
		return result;
	}
	
	public ReplyVo getReplyInfo(String postNumber) {
		ReplyVo result = null;
		Connection conn = getConnection();
		result = new BoardDao().getReplyInfo(conn, postNumber);
		close(conn);
		return result;
	}
	
	public List<ReplyVo> getReplyList(String idx) {
		List<ReplyVo> result = null;
		Connection conn = getConnection();
		result = new BoardDao().getReplyList(conn, idx);
		close(conn);
		return result;
	}

	public int writePost(BoardVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().writePost(conn, vo);
		close(conn);
		return result;
	}

	public int insertReply(ReplyVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().insertReply(conn, vo);
		close(conn);
		return result;
	}

	public int insertReplyTo(ReplyVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().insertReplyTo(conn, vo);
		close(conn);
		return result;
	}

	public int updateBoard(BoardVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().updateBoard(conn, vo);
		close(conn);
		return result;
	}

	public int deleteBoard(int idx) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().deleteBoard(conn, idx);
		close(conn);
		return result;
	}

	public int havRep(int idx) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().havRep(conn, idx);
		close(conn);
		return result;
	}

	public int deleteReply(int idx) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().deleteReply(conn, idx);
		close(conn);
		return result;
	}

	public int getBoardCnt(String idx) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().getBoardCnt(conn, idx);
		close(conn);
		return result;
	}
}
