package board.mvc.model;

 class BoardSQL {
	 final static String LIST = "select * from BOARD order by seq desc";
	 final static String INSERT = "insert into BOARD values(BOARD_SEQ.nextval,?,?, ?, ?, SYSDATE)";
	 final static String UPDATE = "update BOARD set email = ?, subject = ?, content = ? where SEQ = ?";
	 final static String DELETE = "delete from BOARD where SEQ=?";
	 final static String CONTENT = "select * from BOARD where SEQ =?";
}
