package mission.jstl.control;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mission.mvc.model.LoginService;
import mvc.domain.Member;


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
			case "login":login(request,response);break;//로그인 창
			
			}
		}else {
			response.sendRedirect("../");
		}	
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginService service = LoginService.getInstance();	
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		ArrayList<Member> emaillist = service.emailS(id);
		request.setAttribute("emaillist",emaillist);
		request.setAttribute("pwd",pwd);
		request.setAttribute("id", id);
		for(Member dto :emaillist) {
			if(pwd.equals(dto.getPwd()) && id.equals(dto.getEmail())){
				HttpSession session = request.getSession();
				session.setAttribute("userid",id);
			}
		}
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    System.err.println(attribute+" : "+request.getSession().getAttribute(attribute));
		}
		String view="../login/Succeed.jsp";//로그인 확인창
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		//response.sendRedirect("Succeed.jsp");
	}
	
	
}
