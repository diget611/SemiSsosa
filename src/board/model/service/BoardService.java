package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.BoardVo;
import static common.jdbc.JDBCTemplate.*;

public class BoardService {

	public List<BoardVo> getBoard() {
		List<BoardVo> result = null;
		Connection conn = getConnection();
		result = new BoardDao().getBoard(conn);
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
}
