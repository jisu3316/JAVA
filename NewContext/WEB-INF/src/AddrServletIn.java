package su.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletIn extends HttpServlet {
	Connection con;
	PreparedStatement pstmt;
	String sql = "insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
	public void init(){ //DB���� 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String usr = "servlet";
		String pwd = "java";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
			System.out.println("#Oracle driver loading failed");
		}catch(SQLException se){
			System.out.println("#init() se: " + se);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");
		try{
			pstmt.setString(1,name);
			pstmt.setString(2,addr);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('�Է¼���')");
			}else pw .println("alert('�Է½���')");
		}catch(SQLException se){
			pw .println("alert('�Է½���')");
		}
		pw.println("location.href='list.do'");
		pw.println("</script>");
        System.out.println("AddrServletIn name:"+name+", addr:"+addr);		
	}
	public void destroy(){ //DB�������� 
        try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
}

