package su.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import su.db.ConnectionPoolBean;

public class AddrServletInPool extends HttpServlet {
	
	private ConnectionPoolBean getPool() throws SQLException {
		ServletContext application = this.getServletContext();
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool");
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();
				application.setAttribute("pool", pool);
			}catch(ClassNotFoundException cnfe){
				System.out.println("드라이버로딩 실패");
			}
		}
		return pool;
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");
		
		ConnectionPoolBean pool = null;
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql = "insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
		try{
			pool =getPool();
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,addr);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('입력성공with Pool')");
			}else pw .println("alert('입력실패with Pool')");
		}catch(SQLException se){
			pw .println("alert('입력실패with Pool')");
		}finally{
					try{
						if(pstmt != null) pstmt.close();
						if(con != null) pool.returnConnection(con);
 					}catch(SQLException se){}
				}
		pw.println("location.href='list.do'");
		pw.println("</script>");
        System.out.println("AddrServletIn name:"+name+", addr:"+addr);		
	}
	
}

