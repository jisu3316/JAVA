package su.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet  
{
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
		//pw.println("<script> alert('수쌤');</script>");
		pw.println("<h3 style='color:red'>Hi Servlet~^^ 한글도 지원되나?</h3>");
		//pw.println("<a href='http://www.naver.com'>네이버</a>");
		pw.println("<a href='index.html' style='text-decoration:none'>인덱스</a>");
		pw.println("</center>");
	}
}

//HTML / CSS / Javascript 
