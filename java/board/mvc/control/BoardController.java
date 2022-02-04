package board.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import board.mvc.model.BoardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.domain.Board;

@WebServlet("/board_mvc/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String m =request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
				case "list": list(request,response); break;
				case "input": input(request,response);break;
				case "insert":insert(request,response);break;
				case "del":del(request,response);break;
				case "content":content(request,response);break;
				case "update":update(request,response);break;
				case "update2":update2(request,response);break;
			}
		}else {
			list(request,response);
		}
		//MOdel 핸들링(java)
		//View 지정(jsp)
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listS();
		request.setAttribute("list", list);//요청이오면 
		
		//(2)view 지정(jsp)
		String view ="../board_mvc/list.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service =BoardService.getInstance();
		int seq =getSeq(request);
		ArrayList<Board> list = service.contentlistS(seq);
		request.setAttribute("content", list);//요청이오면 
		request.setAttribute("seq", seq);//요청이오면 
		//(2)view 지정(jsp)
		String view ="../board_mvc/content.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service =BoardService.getInstance();
		int seq =getSeq(request);
		ArrayList<Board> contentlist = service.contentlistS(seq);
		request.setAttribute("update", contentlist);//요청이오면 
		//(2)view 지정(jsp)
		String view ="../board_mvc/update.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String view ="../board_mvc/input.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
		
		//response.sendRedirect("../addr_mvc/input.jsp");
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service =BoardService.getInstance();
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(-1,writer,email,subject,content,null);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);//요청이오면 값을 넘겨준다
		
		//(2)view 지정(jsp)
		String view ="../board_mvc/insert.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service =BoardService.getInstance();
		int seq =getSeq(request);
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(seq,null,email,subject,content,null);
		boolean flag = service.updateS(dto);
		request.setAttribute("flag", flag);//요청이오면 값을 넘겨준다
		
		//(2)view 지정(jsp)
		String view ="../board_mvc/update2.jsp";//list.jsp쓰고싶으면 폴더를 addr로바꿈
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl 핸들링(java)
		BoardService service =BoardService.getInstance();
		int seq =getSeq(request);
		service.deleteS(seq);
		//list(request,response);
		//response.sendRedirect("list");
		response.sendRedirect("board.do");
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