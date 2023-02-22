package board.model.vo;

public class LikeVo {
	
	int idx;
	int vote;
	String id;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LikeVo [idx=" + idx + ", vote=" + vote + ", id=" + id + "]";
	}
	
}
