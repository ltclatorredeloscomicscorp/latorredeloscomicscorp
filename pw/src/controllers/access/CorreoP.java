package controllers.access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 

import javax.servlet.http.*;
@SuppressWarnings("serial")
public class CorreoP extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// Company's name and e-mail 
		String senderEmail = "latorredeloscomicscorp@gmail.com";
		String senderName = "La Torre de los Comics";
		
		// arrayList with the e-mails
		ArrayList <String> emails = (ArrayList<String>)req.getAttribute("emails");
		
		// subject
		String subject = "Nuevos accesos!!";
		
		// message
		String message = "Usted ahora tiene acceso a "+req.getAttribute("access");
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
			
		for(int i = 0; i<emails.size(); i++){
				try {
					Message emailMessage = new MimeMessage(session);
					emailMessage.setFrom(new InternetAddress(senderEmail, senderName));
					emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emails.get(i), "Dear client"));
					emailMessage.setSubject(subject);
					emailMessage.setText(message);
					Transport.send(emailMessage);
				} catch (AddressException e) {
					resp.setContentType("text/html");
					resp.getOutputStream().println(e.getMessage()+" ");
				} catch (MessagingException e) {
					resp.setContentType("text/html");
					resp.getOutputStream().println(e.getMessage()+" ");
				}
		}
		resp.sendRedirect("/access");
	}
}
