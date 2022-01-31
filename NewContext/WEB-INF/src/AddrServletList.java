package su.sv.addr;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddrServletList extends HttpServlet {
	Connection con;
	Statement stmt;
	public void init(){ //DB연결 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
		String usr = "servlet";
		String pwd = "java";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			stmt = con.createStatement();
		}catch(ClassNotFoundException cnfe){
			System.out.println("#Oracle driver loading failed");
		}catch(SQLException se){
			System.out.println("#init() se: " + se);
		}
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { //SQL문수행 -> list.html 
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		
		pw.println("<meta charset='utf-8'>");
		pw.println("<style>");
			pw.println("table, th, td {");
			   pw.println("border: 1px solid black;");
			   pw.println("border-collapse: collapse;");
			pw.println("}");
			pw.println("th, td {");
			   pw.println("padding: 5px;");
			pw.println("}");
			pw.println("a { text-decoration:none }");
		pw.println("</style>");
		pw.println("<center>");
			pw.println("<h1>");
				pw.println("Address List");
			pw.println("</h1>");
			pw.println("<a href='input.html'>입력</a>");
			pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='../'>인덱스</a>");
			pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
				pw.println("<tr>");
					pw.println("<th>번호</th>");
					pw.println("<th>이름</th>");
					pw.println("<th>주소</th>");
					pw.println("<th>날짜</th>");
					pw.println("<th>삭제</th>");
				pw.println("</tr>");

                ResultSet rs = null;
				String sql = "select * from address order by seq desc";
				try{
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						int seq = rs.getInt(1);
						String name = rs.getString(2);
						String addr = rs.getString(3);
						Date rdate = rs.getDate(4);
						pw.println("<tr>");
							pw.println("<td align='center'>"+seq+"</td>");
							pw.println("<td>"+name+"</td>");
							pw.println("<td>"+addr+"</td>");
							pw.println("<td>"+rdate+"</td>");
							pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>");
						pw.println("</tr>");
					}
				}catch(SQLException se){
					System.out.println("#service() se: " + se);
				}finally{
					try{
						if(rs != null) rs.close();
					}catch(SQLException se){}
				}
				
			pw.println("</table>");
		pw.println("</center>");
	}
	public void destroy(){ //DB연결해제 
        try{
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
}

