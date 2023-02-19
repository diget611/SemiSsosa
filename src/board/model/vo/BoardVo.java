package board.model.vo;

import java.sql.Date;

public class BoardVo {
	
	/*
	 * IDX NUMBER CONSTRAINT BOARD_IDX_PK PRIMARY KEY
     , CATEGORY NUMBER CONSTRAINT BOARD_CA_FK REFERENCES CATEGORY(IDX)
     , POSTNAME VARCHAR2(100) NOT NULL
     , CONTENT VARCHAR2(1000 CHAR)
     , CREATEDATE DATE DEFAULT SYSTIMESTAMP
     , UPDATEDATE DATE DEFAULT NULL
     , DELETEDATE DATE DEFAULT NULL
     , WRITER VARCHAR2(20) CONSTRAINT BOARD_WR_FK REFERENCES MEMBER(ID)
     , HAVFILE NUMBER DEFAULT 0 CONSTRAINT BOARD_FILE_CK CHECK (HAVFILE IN(0, 1))
     , VIEWS NUMBER DEFAULT 0
	 */
	
	private int idx;
	private String postName;
	private String content;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private String writer;
	private int havFile;
	private int views;
	
	@Override
	public String toString() {
		return "BoardVo [idx=" + idx + ", postName=" + postName + ", content=" + content + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", writer=" + writer + ", havFile="
				+ havFile + ", views=" + views + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getHavFile() {
		return havFile;
	}

	public void setHavFile(int havFile) {
		this.havFile = havFile;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
}
