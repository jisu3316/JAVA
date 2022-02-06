package mission.mvc.model;


import static mission.mvc.model.LoginSQL.LOGINID;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvc.domain.Member;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
		
		ArrayList<Member> emaillist(String email){
			ArrayList<Member> emaillist = new ArrayList<Member>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection con = null;
				try{
					con = ds.getConnection();
					pstmt = con.prepareStatement(LOGINID);	
					pstmt.setString(1, email);
					rs = pstmt.executeQuery();
					 while(rs.next()){
						int seq = rs.getInt(1);
						String name  = rs.getString(2);
						 email  = rs.getString(3);
						String pwd  = rs.getString(4);		
						String phone  = rs.getString(5);	
						Date rdate = rs.getDate(6);
						Date udate = rs.getDate(7);
						emaillist.add(new Member(seq,name, email,pwd,phone,rdate,udate));
					 	}
					 return emaillist;
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
		
		//¼±»ý´ÔÄÚµå
		/*Member getMember(String email) {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         con = ds.getConnection();
		         pstmt = con.prepareStatement(SEL);
		         pstmt.setString(1, email);
		         rs = pstmt.executeQuery();
		         if(rs.next()) {
		            long seq = rs.getLong(1);
		            String name = rs.getString(2);
		            //String email = rs.getString(3);
		            String pwd = rs.getString(4);
		            String phone = rs.getString(5);
		            Date rdate = rs.getDate(6);
		            Date udate = rs.getDate(7);
		            
		            return new Member(seq, name, email, pwd, phone, rdate, udate);
		         }else {
		            return null;
		         }
		      }catch(SQLException se) {
		         return null;
		      }finally {
		         try {
		            if(rs != null) rs.close();
		            if(pstmt != null) pstmt.close();
		            if(con != null) con.close();
		         }catch(SQLException se) {}
		      }
		   }*/

	
}
