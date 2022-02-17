package mvc.domain;

import java.sql.Date;

public class Login {
	private int seq;
	private String nickname;
	private String id;
	private String pw;
	private String gender;
	private Date rdate;
	
	public Login() {}
	public Login(int seq,String nickname, String id, String pw, String gender,Date rdate) {
		super();
		this.seq = seq;
		this.nickname = nickname;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	
}