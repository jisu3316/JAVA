package mvc.domain;

import java.sql.Date;

public class SubList {
	private int seq;
	private String nickname;
	private String id;
	private String subject;
	private String content;
	private Date rdate;
	
	public SubList() {}
	public SubList(int seq,String nickname, String id, String subject, String content,Date rdate) {
		super();
		this.seq = seq;
		this.nickname = nickname;
		this.id = id;
		this.subject=subject;
		this.content = content;
		this.rdate = rdate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	
}