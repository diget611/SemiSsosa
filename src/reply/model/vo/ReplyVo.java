package reply.model.vo;

import java.sql.Date;

public class ReplyVo {
	
	/*
	 * CREATE TABLE REPLY(
       IDX NUMBER CONSTRAINT REPLY_ID_PK PRIMARY KEY
     , POSTNUMBER NUMBER CONSTRAINT REPLY_PN_FK REFERENCES BOARD_T(IDX)
     , CONTENT VARCHAR2(100 CHAR)
     , CREATEDATE DATE DEFAULT SYSTIMESTAMP
     , UPDATEDATE DATE DEFAULT NULL
     , DELETEDATE DATE DEFAULT NULL
     , ORDERS NUMBER
     , FLOOR NUMBER
     , GROUPNUM NUMBER
     , WRITER VARCHAR2(20) CONSTRAINT REPLY_WR_FK REFERENCES MEMBER(ID)
	);
	 */
	
	private int idx;
	private int postNumber;
	private String content;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private int orders;
	private int floor;
	private int groupNum;
	private String writer;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
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
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getHavr() {
		return havr;
	}
	public void setHavr(int havr) {
		this.havr = havr;
	}
	@Override
	public String toString() {
		return "ReplyVo [idx=" + idx + ", postNumber=" + postNumber + ", content=" + content + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", orders=" + orders
				+ ", floor=" + floor + ", groupNum=" + groupNum + ", writer=" + writer + ", havr=" + havr + "]";
	}
	private int havr;
	
	
}
