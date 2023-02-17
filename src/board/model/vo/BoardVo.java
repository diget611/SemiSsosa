package board.model.vo;

import java.sql.Date;

public class BoardVo {
	
	private int idx;
	private String boardName;
	private String boardContent;
	private String boardWriter;
	private Date boardDate;
	
	@Override
	public String toString() {
		return "BoardVo [idx=" + idx + ", boardName=" + boardName + ", boardContent=" + boardContent + ", boardWriter="
				+ boardWriter + ", boardDate=" + boardDate + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
}
