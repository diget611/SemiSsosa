package reply.model.vo;

import java.sql.Date;

public class ReplyVo {
	
	private int idx;
	private int boardRefNum;
	private String replyContent;
	private String replyWriter;
	private Date replyDate;
	
	@Override
	public String toString() {
		return "ReplyVo [idx=" + idx + ", boardRefNum=" + boardRefNum + ", replyContent=" + replyContent
				+ ", replyWriter=" + replyWriter + ", replyDate=" + replyDate + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getBoardRefNum() {
		return boardRefNum;
	}

	public void setBoardRefNum(int boardRefNum) {
		this.boardRefNum = boardRefNum;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

}
