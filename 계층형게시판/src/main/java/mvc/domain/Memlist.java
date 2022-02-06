package mvc.domain;

import java.sql.Date;

public class Memlist {
	private int seq;
	private String subject;
	private String content;
	private String name;
	private String email;
	private String homepage;
	private String pwd;
	private int views;
	private int ref;
	private int step;
	private int depth;
	private Date rdate;
	
	public Memlist() {}
	public Memlist(int seq,String subject, String content, String name, String email,String homepage,String pwd,int views,int ref,int step,int depth,Date rdate) {
		super();
		this.seq = seq;
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.homepage=homepage;
		this.pwd = pwd;
		this.views = views;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.rdate = rdate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
}
