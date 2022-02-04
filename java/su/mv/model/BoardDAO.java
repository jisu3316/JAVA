package su.mv.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.sql.*;

public class BoardDAO {
	private DataSource ds;
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from BOARD order by seq desc";
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
				while(rs.next()){
					int seq = rs.getInt(1);
					String WRITER = rs.getString(2);
					String EMAIL  = rs.getString(3);
					String SUBJECT = rs.getString(4);
					String CONTENT = rs.getString(5);
					Date RDATE = rs.getDate(6);
					list.add(new BoardDTO(seq, WRITER,EMAIL,SUBJECT,CONTENT,RDATE));
				}
				return list;
		}catch(SQLException se){
			System.out.println("#AddrDAO list() se: " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	public boolean insert(BoardDTO dto) { //±¸Çö
		String sql = "insert into BOARD values(BOARD_SEQ.nextval,?,?, ?, ?, SYSDATE)";
		Connection con = null;
		PreparedStatement pstmt ;	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,dto.getWriter());
			pstmt.setString(2,dto.getEmail());
			pstmt.setString(3,dto.getSubject());
			pstmt.setString(4,dto.getContent());	
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
		}catch(SQLException se){
			return false;
		}
	}
	public boolean update(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update BOARD set email = ?, subject = ?, content = ? where SEQ = ?";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,dto.getEmail());
			pstmt.setString(2,dto.getSubject());
			pstmt.setString(3,dto.getContent());
			pstmt.setInt(4, dto.getSeq());
			int i =pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
			}catch(SQLException se){
			return false;
		}
	}
	public void delete(int seq) {
			String sql = "delete from BOARD where SEQ=?";
			Connection con =null;
			PreparedStatement pstmt = null;
	        try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, seq);
				pstmt.executeUpdate();	
			}catch(SQLException se){
			}finally{
				try{
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
	

	
}
