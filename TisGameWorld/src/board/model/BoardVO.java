package board.model;
import java.io.*;
import java.sql.Date;

public class BoardVO implements Serializable{
	private int rn;//글의 행번호
	private String idx;//글 번호
	private String name;//작성자
	private String pwd;//글 비번
	private String subject;//글 제목
	private String content;//글 내용
	private java.sql.Date wdate;//작성일
	private int readnum;//조회수
	private String filename;//첨부파일명
	private long filesize;//첨부파일 크기
	private int replyCount;//게시글에 대한 댓글수
	
	public BoardVO() {//-기본생성자 필수
		
	}
	
	public BoardVO(String idx, String name, String pwd, 
			String subject, String content, Date wdate, int readnum,
			String filename, long filesize) {
		super();
		this.idx = idx;
		this.name = name;
		this.pwd = pwd;
		this.subject = subject;
		this.content = content;
		this.wdate = wdate;
		this.readnum = readnum;
		this.filename = filename;
		this.filesize = filesize;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	
}///////////////////////////////////////////
