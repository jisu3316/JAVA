package su.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletDel extends HttpServlet {
	Connection con;
	PreparedStatement pstmt;
	String sql = "delete from ADDRESS where SEQ=?";
	public void init(){ //DB연결 
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
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		int seq = -1;
        String seqStr = req.getParameter("seq");
		if(seqStr != null) {
			seqStr = seqStr.trim();
			seq = Integer.parseInt(seqStr);
		}

        res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");
        try{
			pstmt.setInt(1, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('삭제성공')");
			}else{
				pw.println("alert('삭제실패')");
			}
		}catch(SQLException se){
			pw.println("alert('삭제실패')");
		}
		pw.println("location.href='list.do'");
		pw.println("</script>");
	}
	public void destroy(){ //DB연결해제 
        try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
}


