package email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailController
 */
@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender_name = request.getParameter("sender_name");
		String sender_mail = request.getParameter("sender_mail");
		String receive_mail = request.getParameter("receive_mail");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		
		EmailDTO dto =new EmailDTO();
		dto.setSenderName(sender_name);
		dto.setSenderMail(sender_mail);
		dto.setReceiveMail(receive_mail);
		dto.setSubject(subject);
		dto.setMessage(message);
		EmailService service = new EmailService();
		try {
			service.mailSender(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/email/write.jsp?message=OK");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
