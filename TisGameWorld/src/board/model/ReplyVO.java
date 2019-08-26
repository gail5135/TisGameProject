package board.model;
import java.io.*;
import java.sql.Date;

public class ReplyVO implements Serializable{//-직렬화
	
	private String num;//댓글 번호
	private String userid;//작성자
	private String content;//글내용
	private java.sql.Date wdate;//작성일
	private String idx_fk;//원 게시글 번호
	
	public ReplyVO() {//-기본 생성자는 반드시 생성
		
	}

	public ReplyVO(String num, String userid, String content, Date wdate, String idx_fk) {//-인자 생성자
		super();
		this.num = num;
		this.userid = userid;
		this.content = content;
		this.wdate = wdate;
		this.idx_fk = idx_fk;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}

	public String getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(String idx_fk) {
		this.idx_fk = idx_fk;
	}
	
	
	

}//////////////////////////////////////////
