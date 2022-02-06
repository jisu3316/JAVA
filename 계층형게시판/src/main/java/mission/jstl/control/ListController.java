package mission.jstl.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mission.mvc.model.ListService;

import mvc.domain.Memlist;

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
			case "list":list(request,response);break;//�� ����Ʈ â	
			case "write":write(request,response);break;//�� ���� â
			case "content":content(request,response);break;//�� ����â
			case "newinsert":newinsert(request,response);break;//�۾��� �μ�Ʈ�Ǵ¸޼ҵ�
			case "answervalue":answervalue(request,response);break;//�۾��� �μ�Ʈ�Ǵ¸޼ҵ�
			case "answer":answer(request,response);break;//�亯 �μ�Ʈ�Ǵ¸޼ҵ�
			//case "pagelist":pagelist(request,response);break;//�� ����Ʈ â
			}
		}else {
			list(request,response);
		}
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service = ListService.getInstance();
		ArrayList<Memlist> list = service.listS();

		int total=list.size();//�ѰԽù��� ���ϴ°�
		request.setAttribute("total", total);//����Ʈ ȭ�鿡 �Ѱ������ϴ� ��������
		int vpage = getVpage(request);//����Ʈ���� �ؿ� ������ ���޾ƿͼ� �����ϱ�
		request.setAttribute("vpage", vpage);
		
		String ps = request.getParameter("ps");//�������� ������ ���޾ƿ���
		System.out.println("ps:"+ps);
		if(ps==null) ps="3";
		if(ps.length()==0) {
			ps="3";
		}
		
		int pageselect = Integer.parseInt(ps);//�������� �־��ֱ����� �ٲ��ش�.
		request.setAttribute("pageselect", pageselect);
		
		
		int index_no =(vpage-1)*pageselect;
		ArrayList<Memlist> pagelist = service.listS(index_no,pageselect);
		request.setAttribute("list", pagelist);
		
		
		
		String view ="list.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		response.sendRedirect("write.jsp");
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service =ListService.getInstance();
		int seq =getSeq(request);
		ArrayList<Memlist> list = service.listS();
		request.setAttribute("content", list);//��û�̿��� 
		request.setAttribute("seq", seq);//��û�̿��� 
		
		int views =getViews(request);
		System.out.println(views);
		Memlist dto = new Memlist(seq,null,null,null,null,null,null,views,-1,-1,-1,null);
								
		service.updateS(dto);
		
		//(2)view ����(jsp)
		String view ="content.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void newinsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service = ListService.getInstance();
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String pwd = request.getParameter("pwd");
		Memlist dto = new Memlist(-1,subject, content,name,email,homepage,pwd,-1,-1,1,0,null);
		System.out.println("subject :"+subject);
		System.out.println("content :"+content);
		System.out.println("name :"+name);
		System.out.println("email :"+email);
		System.out.println("homepage :"+homepage);
		System.out.println("pwd :"+pwd);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);//��û�̿��� ���� �Ѱ��ش�
		
		String view ="newinsert.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);		
	}
	private void answervalue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int ref =getRef(request);//1������ �亯�̸� ref=1
		int step =getStep(request);//1������ step = 1
		int depth =getDepth(request);//1������ depth =0
		request.setAttribute("ref", ref);
		request.setAttribute("step", step);
		request.setAttribute("depth", depth);
		
		String view ="write2.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void answer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1Moderl �ڵ鸵(java)
		ListService service = ListService.getInstance();
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String pwd = request.getParameter("pwd");
		int ref =getRef(request);//1������ �亯�̸� ref=1
		int step =getStep(request);//1������ step = 1
		int depth =getDepth(request);//1������ depth =0
		step=step+1;
		depth=depth+1;
		
		Memlist dto = new Memlist(-1,subject, content,name,email,homepage,pwd,-1,ref,step,depth,null);
		System.out.println("subject :"+subject);
		System.out.println("content :"+content);
		System.out.println("name :"+name);
		System.out.println("email :"+email);
		System.out.println("homepage :"+homepage);
		System.out.println("pwd :"+pwd);
		System.out.println("ref :"+ref);
		System.out.println("step :"+step);
		System.out.println("depth :"+depth);
		boolean flag = service.answerS(dto);
		request.setAttribute("flag", flag);//��û�̿��� ���� �Ѱ��ش�
		
		String view ="newinsert.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);		
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
	private int getRef(HttpServletRequest request){
	    int ref = -1;
		String refStr = request.getParameter("ref");
		if(refStr != null){
			refStr = refStr.trim();
			if(refStr.length() != 0){
				try{
					ref = Integer.parseInt(refStr); 
					return ref;
				}catch(NumberFormatException ne){
				}
			}
		}
		return ref;
	}
	private int getViews(HttpServletRequest request){
	    int views = 0;
		String viewsStr = request.getParameter("views");
		if(viewsStr != null){
			viewsStr = viewsStr.trim();
			if(viewsStr.length() != 0){
				try{
					views = Integer.parseInt(viewsStr);
					views=views+1;
					return views;
				}catch(NumberFormatException ne){
				}
			}
		}
		return views;
	}
	private int getStep(HttpServletRequest request){
	    int step = 0;
		String stepStr = request.getParameter("step");
		if(stepStr != null){
			stepStr = stepStr.trim();
			if(stepStr.length() != 0){
				try{
					step = Integer.parseInt(stepStr);
					//step=step+1;
					return step;
				}catch(NumberFormatException ne){
				}
			}
		}
		return step;
	}
	private int getDepth(HttpServletRequest request){
	    int depth = 0;
		String depthsStr = request.getParameter("depth");
		if(depthsStr != null){
			depthsStr = depthsStr.trim();
			if(depthsStr.length() != 0){
				try{
					depth = Integer.parseInt(depthsStr);
					//depth=depth+1;
					return depth;
				}catch(NumberFormatException ne){
				}
			}
		}
		return depth;
	}
	private int getVpage(HttpServletRequest request){
	    int vpage = 0;
		String vpagesStr = request.getParameter("vpage");
		if(vpagesStr != null){
			vpagesStr = vpagesStr.trim();
			if(vpagesStr.length() != 0){
				try{
					vpage = Integer.parseInt(vpagesStr);
					return vpage;
				}catch(NumberFormatException ne){
				}
			}
		}
		return vpage;
	}

}
