package mission.mvc.model;

public class ListSQL {
	final static String LIST = "select * from MEMLIST order by ref desc, step asc ";
	final static String LIST2 = "select * from MEMLIST order by ref desc, step asc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	final static String PAGELIST = "select * from MEMLIST order by ref desc, step asc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
	final static String UPDATE = "update MEMLIST set views = ? where SEQ = ?";
	final static String INSERT = "insert into MEMLIST values(MEMLIST_SEQ.nextval,?,?,?,?,?,?,0,MEMLIST_SEQ.nextval,1,0,SYSDATE)";
	final static String INSERT2 = "insert into MEMLIST values(MEMLIST_SEQ.nextval,?,?,?,?,?,?,0,?,?,?,SYSDATE)";
	final static String TOTAL = "select count(*) from Memlist";														
	
}


