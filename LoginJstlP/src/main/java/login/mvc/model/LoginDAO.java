package login.mvc.model;

import static login.mvc.model.LoginSQL.DELETE;
import static login.mvc.model.LoginSQL.INSERT;
import static login.mvc.model.LoginSQL.LIST;
import static login.mvc.model.LoginSQL.LOGINID;

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

import mvc.domain.Login;

public class LoginDAO {
		private DataSource ds;
		LoginDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {		
		}
	}
		ArrayList<Login> list(){
			ArrayList<Login> list = new ArrayList<Login>();
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(LIST);
					while(rs.next()){
						int seq = rs.getInt(1);
						String nickname = rs.getString(2);
						String id = rs.getString(3);
						String pw  = rs.getString(4);
						String gender = rs.getString(5);
						Date rdate = rs.getDate(6);
						list.add(new Login(seq,nickname, id,pw,gender,rdate));
					}
					return list;
			}catch(SQLException se){
				System.out.println("#Login list() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
		ArrayList<Login> idlist(String id){
			ArrayList<Login> idlist = new ArrayList<Login>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection con = null;
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(LOGINID);	
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					 while(rs.next()){
						int seq = rs.getInt(1);
						String nickname  = rs.getString(2);
						String pw  = rs.getString(4);
						String gender  = rs.getString(5);		
						Date rdate = rs.getDate(6);
						idlist.add(new Login(seq,nickname, id,pw,gender, rdate));
					 	}
					 return idlist;
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
	 boolean insert(Login dto) {
		 Connection con = null;
		 PreparedStatement pstmt =null;
		 try {
			 con =ds.getConnection();
			 pstmt=con.prepareStatement(INSERT);
			 pstmt.setString(1, dto.getNickname());
			 pstmt.setString(2, dto.getId());
			 pstmt.setString(3, dto.getPw());
			 pstmt.setString(4, dto.getGender());
			 int i =pstmt.executeUpdate();
			 if(i>0) {
				 return true;
			 }else return false;
		 }catch(SQLException se) {
			 return false;
		 }
	}
	 void delete(int seq) {
			Connection con =null;
			PreparedStatement pstmt = null;
	        try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
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
