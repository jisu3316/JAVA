package board.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mvc.domain.Board;

import static board.mvc.model.BoardSQL.LIST;
import static board.mvc.model.BoardSQL.INSERT;
import static board.mvc.model.BoardSQL.UPDATE;
import static board.mvc.model.BoardSQL.CONTENT;

class BoardDAO {
	private DataSource ds;
	 BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	 ArrayList<Board> list(){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = LIST;
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
					list.add(new Board(seq, WRITER,EMAIL,SUBJECT,CONTENT,RDATE));
				}
				return list;
		}catch(SQLException se){
			System.out.println("#boardDAO list() se: " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	 ArrayList<Board> contentlist(int seq){
			ArrayList<Board> contentlist = new ArrayList<Board>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection con = null;
			String sql=CONTENT;
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, seq);
					rs = pstmt.executeQuery();
					 while(rs.next()){
						seq = rs.getInt(1);
						String WRITER = rs.getString(2);
						String EMAIL  = rs.getString(3);
						String SUBJECT = rs.getString(4);
						String CONTENT = rs.getString(5);
						Date RDATE = rs.getDate(6);
						contentlist.add(new Board(seq, WRITER,EMAIL,SUBJECT,CONTENT,RDATE));
					 	}
					 return contentlist;
				}catch(SQLException se){
					System.out.println("#service() se: " + se);
					return null;
				}finally{
					try{
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
					}catch(SQLException se){}
				}	
	}
	 boolean insert(Board dto) { //±¸Çö
		String sql = INSERT;
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
	 boolean update(Board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE;
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
	 void delete(int seq) {
			String sql = BoardSQL.DELETE;
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
