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
		//pw.println("<script> alert('����');</script>");
		pw.println("<h3 style='color:red'>Hi Servlet~^^ �ѱ۵� �����ǳ�?</h3>");
		//pw.println("<a href='http://www.naver.com'>���̹�</a>");
		pw.println("<a href='index.html' style='text-decoration:none'>�ε���</a>");
		pw.println("</center>");
	}
}

//HTML / CSS / Javascript 
