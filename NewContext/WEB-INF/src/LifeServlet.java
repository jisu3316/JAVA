package su.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LifeServlet extends HttpServlet  
{
	public void init(){//ù��° ��û�� ���� �޸𸮿� �ε�
		System.out.println("init()");
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {//Ŭ���̾�Ʈ�� ��û�Ҷ�����
		System.out.println("service()");
	}
	public void destroy(){//�޸𸮿� ��ε�(�����ɶ�)����(shutdown or ������å
        System.out.println("destroy()");
		FileWriter fw = null;
		PrintWriter pw =null;
		try{	
		fw =new FileWriter("log.txt");
		pw= new PrintWriter(fw,true);
		pw.println("destroy()�����");
		pw.close();
		fw.close();
		}catch(IOException ie){}
	}
}
