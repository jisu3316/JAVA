package mission.mvc.model;

import static mission.mvc.model.ListSQL.LIST;
import static mission.mvc.model.ListSQL.INSERT2;
import static mission.mvc.model.ListSQL.INSERT;
import static mission.mvc.model.ListSQL.UPDATE;
import static mission.mvc.model.ListSQL.LIST2;

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

import mvc.domain.Memlist;
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
	ArrayList<Memlist> list(){
		ArrayList<Memlist> list = new ArrayList<Memlist>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(LIST);
				while(rs.next()){
					int seq = rs.getInt(1);
					String subject = rs.getString(2);
					String content = rs.getString(3);
					String name = rs.getString(4);
					String email = rs.getString(5);
					String homepage = rs.getString(6);
					String pwd  = rs.getString(7);
					int views = rs.getInt(8);
					int ref = rs.getInt(9);
					int step = rs.getInt(10);
					int depth = rs.getInt(11);
					Date rdate = rs.getDate(12);
					list.add(new Memlist(seq,subject, content,name,email,homepage,pwd,views,ref,step,depth,rdate));
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
	ArrayList<Memlist> list(int index_no,int pageselect){
		ArrayList<Memlist> list = new ArrayList<Memlist>();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(LIST2);
			pstmt.setInt(1,index_no);
			pstmt.setInt(2,pageselect);
			rs = pstmt.executeQuery();
				while(rs.next()){
					int seq = rs.getInt(1);
					String subject = rs.getString(2);
					String content = rs.getString(3);
					String name = rs.getString(4);
					String email = rs.getString(5);
					String homepage = rs.getString(6);
					String pwd  = rs.getString(7);
					int views = rs.getInt(8);
					int ref = rs.getInt(9);
					int step = rs.getInt(10);
					int depth = rs.getInt(11);
					Date rdate = rs.getDate(12);
					list.add(new Memlist(seq,subject, content,name,email,homepage,pwd,views,ref,step,depth,rdate));
				}
				return list;
		}catch(SQLException se){
			System.out.println("#PAGE list() se: " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	
	boolean insert(Memlist dto) { //구현
		Connection con = null;
		PreparedStatement pstmt ;	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1,dto.getSubject());
			pstmt.setString(2,dto.getContent());
			pstmt.setString(3,dto.getName());
			pstmt.setString(4,dto.getEmail());
			pstmt.setString(5,dto.getHomepage());	
			pstmt.setString(6,dto.getPwd());	
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
		}catch(SQLException se){
			return false;
		}
	}
	boolean answer(Memlist dto) { //구현
		Connection con = null;
		PreparedStatement pstmt ;	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT2);
			pstmt.setString(1,dto.getSubject());
			pstmt.setString(2,dto.getContent());
			pstmt.setString(3,dto.getName());
			pstmt.setString(4,dto.getEmail());
			pstmt.setString(5,dto.getHomepage());	
			pstmt.setString(6,dto.getPwd());
			pstmt.setInt(7,dto.getRef());
			pstmt.setInt(8,dto.getStep());
			pstmt.setInt(9,dto.getDepth());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
		}catch(SQLException se){
			return false;
		}
	}
	boolean update(Memlist dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = UPDATE;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,dto.getViews());
			pstmt.setInt(2,dto.getSeq());
			int i =pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
			}catch(SQLException se){
			return false;
		}
	}
}
