package login.mvc.model;


import static login.mvc.model.ListSQL.INSERT;
import static login.mvc.model.ListSQL.UPDATE;
import static login.mvc.model.ListSQL.DELETE;
import static login.mvc.model.ListSQL.SEARCH;
import static login.mvc.model.ListSQL.SEARCH2;
import static login.mvc.model.ListSQL.SEARCH3;

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

import mvc.domain.SubList;

public class ListDAO {
		private DataSource ds;
		ListDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {		
		}
	}
		ArrayList<SubList> list(){
			ArrayList<SubList> list = new ArrayList<SubList>();
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String LIST = "select * from LIST order by seq desc";
			try{
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(LIST);
					while(rs.next()){
						int seq = rs.getInt(1);
						String nickname = rs.getString(2);
						String id = rs.getString(3);
						String subject  = rs.getString(4);
						String content = rs.getString(5);
						Date rdate = rs.getDate(6);
						list.add(new SubList(seq,nickname, id,subject,content,rdate));
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
		
		ArrayList<SubList> searchlist(String id){
			ArrayList<SubList> searchlist = new ArrayList<SubList>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(SEARCH);
				pstmt.setString(1,id);
				rs = pstmt.executeQuery();
					while(rs.next()){
						int seq = rs.getInt(1);
						String nickname = rs.getString(2);
						id = rs.getString(3);
						String subject  = rs.getString(4);
						String content = rs.getString(5);
						Date rdate = rs.getDate(6);
						searchlist.add(new SubList(seq,nickname, id,subject,content,rdate));
					}
					return searchlist;
			}catch(SQLException se){
				System.out.println("# list() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
		ArrayList<SubList> searchlist2(String nickname){
			ArrayList<SubList> searchlist = new ArrayList<SubList>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(SEARCH2);
				pstmt.setString(1,nickname);
				rs = pstmt.executeQuery();
					while(rs.next()){
						int seq = rs.getInt(1);
						 nickname = rs.getString(2);
						String id = rs.getString(3);
						String subject  = rs.getString(4);
						String content = rs.getString(5);
						Date rdate = rs.getDate(6);
						searchlist.add(new SubList(seq,nickname, id,subject,content,rdate));
					}
					return searchlist;
			}catch(SQLException se){
				System.out.println("# list() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
		ArrayList<SubList> searchlist3(String subject){
			ArrayList<SubList> searchlist = new ArrayList<SubList>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(SEARCH3);
				pstmt.setString(1,subject);
				rs = pstmt.executeQuery();
					while(rs.next()){
						int seq = rs.getInt(1);
						String nickname = rs.getString(2);
						String id = rs.getString(3);
						 subject  = rs.getString(4);
						String content = rs.getString(5);
						Date rdate = rs.getDate(6);
						searchlist.add(new SubList(seq,nickname, id,subject,content,rdate));
					}
					return searchlist;
			}catch(SQLException se){
				System.out.println("# list() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
		 boolean insert(SubList dto) { //±¸Çö
				Connection con = null;
				PreparedStatement pstmt ;	
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT);
					pstmt.setString(1,dto.getNickname());
					pstmt.setString(2,dto.getId());
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
		 boolean update(SubList dto) {
				Connection con = null;
				PreparedStatement pstmt = null;
				String sql = UPDATE;
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1,dto.getSubject());
					pstmt.setString(2,dto.getContent());
					pstmt.setInt(3,dto.getSeq());
					int i =pstmt.executeUpdate();
					if(i>0){
						return true;
					}else return false;
					}catch(SQLException se){
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
