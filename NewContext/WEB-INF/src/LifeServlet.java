package su.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LifeServlet extends HttpServlet  
{
	public void init(){//첫번째 요청에 의해 메모리에 로딩
		System.out.println("init()");
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {//클라이언트가 요청할때마다
		System.out.println("service()");
	}
	public void destroy(){//메모리에 언로딩(삭제될때)수행(shutdown or 서버정책
        System.out.println("destroy()");
		FileWriter fw = null;
		PrintWriter pw =null;
		try{	
		fw =new FileWriter("log.txt");
		pw= new PrintWriter(fw,true);
		pw.println("destroy()수행됨");
		pw.close();
		fw.close();
		}catch(IOException ie){}
	}
}
