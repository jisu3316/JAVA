package login.jstl.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

@WebServlet(urlPatterns ="/list/list.do",loadOnStartup = 2)
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
			case "list":list(request,response);break;//글 리스트 창
			case "input":input(request,response);break;//글작성 창
			case "listinsert":listinsert(request,response);break;//글작성 창
			case "content":content(request,response);break;//글 내용창
			case "update":update(request,response);break;//content창에서 수정누르는창
			case "update2":update2(request,response);break;//update창에서 수정버튼
			case "del":del(request,response);break;//삭제버튼
			case "like":like(request,response);break;//삭제버튼
			}
		}else {
			list(request,response);
		}
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		ListService service = ListService.getInstance();
		ArrayList<SubList> list = service.listS();
		request.setAttribute("list", list);

		
		//(2)view 지정(jsp)
		String view ="list.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginService service = LoginService.getInstance();
		ArrayList<Login> list = service.listS();
		request.setAttribute("list", list);//요청이오면
		
		String view="input.jsp";//로그인 확인창
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		//response.sendRedirect("input.jsp");		
	}
	private void listinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		ListService service = ListService.getInstance();
		String nickname = request.getParameter("NICKNAME");
		String id = request.getParameter("ID");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		SubList dto = new SubList(-1,nickname,id,subject,content,null);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);//요청이오면 값을 넘겨준다
		
		String view ="listinsert.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		//response.sendRedirect("listinsert.jsp");
		
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		ListService service =ListService.getInstance();
		int seq =getSeq(request);
		ArrayList<SubList> list = service.listS();
		request.setAttribute("content", list);//요청이오면 
		request.setAttribute("seq", seq);//요청이오면 
		//(2)view 지정(jsp)
		String view ="content.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		String id = request.getParameter("id");
		HttpSession session = request.getSession(true);
		String userid =(String)session.getAttribute("id");
		if(id.equals(userid)||userid.equals("admin")) {
			ListService service =ListService.getInstance();
			int seq =getSeq(request);
			ArrayList<SubList> list = service.listS();
			request.setAttribute("update", list); 
			request.setAttribute("seq", seq); 
			String view ="update.jsp";
			RequestDispatcher rd =request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('본인이 쓴 글만 수정 or 삭제 가능합니다');");
			pw.println("location.href='list.do';");
			pw.println("</script>");
			pw.close();
		}
	}
	private void update2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		ListService service =ListService.getInstance();
		int seq =getSeq(request);
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		SubList dto = new SubList(seq,null,null,subject,content,null);
		boolean flag = service.updateS(dto);
		request.setAttribute("flag", flag);//요청이오면 값을 넘겨준다
		
		//(2)view 지정(jsp)
		String view ="update2.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		HttpSession session = request.getSession(true);
		String userid =(String)session.getAttribute("id");
		if(id.equals(userid)) {
			ListService service =ListService.getInstance();
			int seq =getSeq(request);
			service.deleteS(seq);
			response.sendRedirect("list.do");
		}else {
			String view ="alert.jsp";
			RequestDispatcher rd =request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		String search = request.getParameter("search");
		String searchtext = request.getParameter("searchtext");
		request.setAttribute("searchtext", searchtext);
		if(search.length()==0) {
			response.sendRedirect("list.do");
		}else {
		if(search.equals("id")) {
			ListService service = ListService.getInstance();
			ArrayList<SubList> searchlist = service.searchlistS(searchtext);	
			request.setAttribute("list", searchlist);
			
	
		}else if(search.equals("nickname")) {
			ListService service = ListService.getInstance();
			ArrayList<SubList> searchlist = service.searchlistS2(searchtext);	
			request.setAttribute("list", searchlist);
	
		}else if(search.equals("subject")) {
			ListService service = ListService.getInstance();
			ArrayList<SubList> searchlist = service.searchlistS3(searchtext);	
			request.setAttribute("list", searchlist);
	
		}
		

		//(2)view 지정(jsp)
		String view ="search.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		}
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
