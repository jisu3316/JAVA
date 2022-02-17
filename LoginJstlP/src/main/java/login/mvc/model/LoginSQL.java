package login.mvc.model;

public class LoginSQL {
	final static String INSERT = "insert into LOGIN values(LOGIN_SEQ.nextval,?,?,?,?, SYSDATE)";
	final static String LIST = "select * from LOGIN order by seq desc";
	final static String LOGINID = "select * from LOGIN where id = ? ";
	final static String LOGINPW = "select pw from LOGIN where pw = ? ";
	final static String DELETE = "delete from LOGIN where SEQ=?";
}
