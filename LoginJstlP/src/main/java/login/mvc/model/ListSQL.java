package login.mvc.model;

public class ListSQL {
	final static String LIST = "select * from LIST order by seq desc";
	final static String INSERT = "insert into LIST values(LIST_SEQ.nextval,?,?,?,?, SYSDATE)";
	final static String UPDATE = "update LIST set subject = ?, content = ? where SEQ = ?";
	final static String DELETE = "delete from LIST where SEQ=?";
	final static String SEARCH = "select * from LIST where id like ?";
	final static String SEARCH2 = "select * from LIST where nickname like ?";
	final static String SEARCH3 = "select * from LIST where subject like ?";
}
