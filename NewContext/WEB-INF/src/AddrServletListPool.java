package su.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import su.db.ConnectionPoolBean;

public class AddrServletListPool extends HttpServlet {
	Connection con;
	Statement stmt;
    
	private ConnectionPoolBean getPool() throws SQLException {
		ServletContext application = this.getServletContext();//���ø����̼� ������ ��Ÿ��
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool");//��ϵ� pool�� ������ "pool"�� ����
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();//pool�� �����
				application.setAttribute("pool", pool); //"pool"�̶��̸����� ����
			}catch(ClassNotFoundException cnfe){
				System.out.println("����̹��ε� ����");
			}
		}
		return pool;
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { //SQL������ -> list.html 
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
				pw.println("Address List with Pool");
			pw.println("</h1>");
			pw.println("<a href='input.html'>�Է�</a>");
			pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='../'>�ε���</a>");
			pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
				pw.println("<tr>");
					pw.println("<th>��ȣ</th>");
					pw.println("<th>�̸�</th>");
					pw.println("<th>�ּ�</th>");
					pw.println("<th>��¥</th>");
					pw.println("<th>����</th>");
				pw.println("</tr>");

                ConnectionPoolBean pool = null;
				Connection con = null;
				Statement stmt = null;
                ResultSet rs = null;
				String sql = "select * from address order by seq desc";
				try{
					pool = getPool();
					con = pool.getConnection();
					stmt = con.createStatement();
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
							pw.println("<td align='center'><a href='del2.do?seq="+seq+"'>����</a></td>");
						pw.println("</tr>");
					}
				}catch(SQLException se){
					System.out.println("#service() se: " + se);
				}finally{
					try{
						if(rs != null) rs.close();
						if(stmt != null) stmt.close();
						if(con != null) pool.returnConnection(con);
 					}catch(SQLException se){}
				}
				
			pw.println("</table>");
		pw.println("</center>");
	}
}

