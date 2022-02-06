package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	public void mailSender(EmailDTO dto) throws Exception{
		String host="smtp.gmail.com";
		String username="kjs764645";
		String password="wltnguddn1.!";
		int port=587;
		String senderMail=dto.getSenderMail();
		String senderName=dto.getSenderName();
		String recipient=dto.getReceiveMail();
		String subject=dto.getSubject();
		String body=dto.getMessage();
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable","true");
		Session session =Session.getDefaultInstance(props,new Authenticator() {
			String un=username;
			String pw = password;
			protected PasswordAuthentication getPasswordAuthenticator() {
				return new PasswordAuthentication(un,pw);
				
			}
		});
		session.setDebug(true);//디버깅정보출력
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.addFrom(new InternetAddress[] {new InternetAddress(senderMail,senderName)});//보내는사람 정보
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(subject);;
		mimeMessage.setText(body);
		Transport.send(mimeMessage);
	}
}
