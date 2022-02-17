package login.jstl.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.mvc.model.ListService;
import login.mvc.model.LoginService;
import mvc.domain.Login;
import mvc.domain.SubList;

@WebServlet(urlPatterns ="/login/login.do",loadOnStartup = 1)
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
			case "login":login(request,response);break;//�α��� â
			case "insert" :insert(request,response);break;//ȸ������ ����â
			case "list":list(request,response);break;//�� ����Ʈ â
			case "succeed":succeed(request,response);break;//�� ����Ʈ â
			case "MM":MM(request,response);break;//ȸ������ â
			case "del":del(request,response);break;//������ư
			}
		}else {
			list(request,response);
		}	
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginService service = LoginService.getInstance();	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		ArrayList<Login> idlist = service.idS(id);
		request.setAttribute("idlist",idlist);
		request.setAttribute("pw",pw);
		request.setAttribute("id", id);
		for(Login dto :idlist) {
			if(pw.equals(dto.getPw()) && id.equals(dto.getId())){
				HttpSession session = request.getSession();
				session.setAttribute("id",id);
			}
		}
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    System.err.println(attribute+" : "+request.getSession().getAttribute(attribute));
		}
		String view="../login/Succeed.jsp";//�α��� Ȯ��â
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		//response.sendRedirect("Succeed.jsp");
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service = ListService.getInstance();
		ArrayList<SubList> list = service.listS();
		request.setAttribute("list", list);//��û�̿��� 
		
		//(2)view ����(jsp)
		String view ="../login/list.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void MM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		HttpSession session = request.getSession(true);
		String id=(String)session.getAttribute("id");
		LoginService service = LoginService.getInstance();
		ArrayList<Login> list = service.listS();
		request.setAttribute("list", list);//��û�̿��� 
		if(id.equals("admin")) {
			//(2)view ����(jsp)
			String view ="MM.jsp";
			RequestDispatcher rd =request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('�����ڸ� �����մϴ�.');");
			pw.println("location.href='../';");
			pw.println("</script>");
			pw.close();
		}		
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginService service =LoginService.getInstance();
		String nickname = request.getParameter("nickname");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String gender = request.getParameter("gender");
		Login dto = new Login(-1,nickname,id,pw,gender,null);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);
		
		String view ="insert.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void succeed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service = ListService.getInstance();
		ArrayList<SubList> list = service.listS();
		request.setAttribute("list", list);//��û�̿��� 
		
		//(2)view ����(jsp)
		String view ="../";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			LoginService service = LoginService.getInstance();
			int seq =getSeq(request);
			service.deleteS(seq);
		
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('�����Ϸ�');");
			pw.println("location.href='login.do?m=MM';");
			pw.println("</script>");
			pw.close();	
	}
	private int getSeq(HttpServletRequest request){
	    int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr); 
					return seq;
				}catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
	
	
}
