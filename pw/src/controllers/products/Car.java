package controllers.products;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controllers.PMF;
import models.Product;

public class Car extends HttpServlet {
	HashMap<String,ArrayList<Product>>nuevo=new HashMap<String,ArrayList<Product>>();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	com.google.appengine.api.users.User user = UserServiceFactory.getUserService().getCurrentUser();
	ArrayList<Product> a=null;
	if(user!=null){
			if(nuevo.containsKey(user.toString())){
				a=nuevo.get(user.toString());
			}
			else{
				ArrayList<Product>tem=new ArrayList<Product>();
				a=tem;
				nuevo.put(user.toString(), tem);
			}
	
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String con =req.getParameter("id");
		String Mensaje ="";
		Double q=0.0;
		if(con!=null){
			for (Product product : nuevo.get(user.toString())) {
				q+=product.getAmount();
			}
			
			Key k =	KeyFactory.createKey(Product.class.getSimpleName(), new
				Long(req.getParameter("id")).longValue());
				Product p = pm.getObjectById(Product.class, k);
				
		nuevo.get(user.toString()).add(p);
		q+=p.getAmount();
		Mensaje ="Gracias por comprar";
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/prueba.jsp");
		req.setAttribute("lista", nuevo.get(user.toString()));
		req.setAttribute("Precio", q);
		req.setAttribute("mensaje", Mensaje);
		dispatcher.forward(req, resp);	
		}
		else{
			if(!a.isEmpty()){
				for (Product product : a) {
					q+=product.getAmount();
				}
			}
			req.setAttribute("lista", a);
			req.setAttribute("Precio", q);
			req.setAttribute("mensaje", Mensaje);
			req.setAttribute("antiguo",false);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/prueba.jsp");
			dispatcher.forward(req, resp);
			}
		}else{
			req.setAttribute("lista", new ArrayList<Product>());
			req.setAttribute("Precio", 0.0);
			req.setAttribute("mensaje", "No hay User");
			req.setAttribute("antiguo",false);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/prueba.jsp");
			dispatcher.forward(req, resp);
		}
	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		com.google.appengine.api.users.User user = UserServiceFactory.getUserService().getCurrentUser();
		
		if(user!=null){
			String opcion =req.getParameter("Action");
			String Mensaje ="";
			if(opcion.equals("Comprar")){
				Mensaje="Gracias por comprar \n su pedido pronto sera atendido";
				ArrayList<Product> a = nuevo.get(user.toString());
				String productos="";
				for (Product b:a) {
					if(b!=null)
						productos=productos+b.getName()+"<br>";
				}
			Car.sendEmail("latorredeloscomicscorp@gmail.com", user.getEmail(), "Usted compro los siguientes productos:<br>"+productos+"<br>"+Mensaje, resp);
				Car.sendEmail("latorredeloscomicscorp@gmail.com", "latorredeloscomicscorp@gmail.com", "El usuario con el correo: " +user.getEmail()+" pidio los siguientes productos:<br>" +productos+"<br>"+Mensaje, resp);
			}
			else{
				Mensaje="Los productos que usted Eligio han sido eliminados";
			}
			nuevo.remove(user.toString());
			new ArrayList<Product>();
			req.setAttribute("lista", new ArrayList<Product>());
			req.setAttribute("mensaje", Mensaje);
			req.setAttribute("antiguo",true);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/prueba.jsp");
			//dispatcher.forward(req, resp);	
		}
		else{
			req.setAttribute("lista", new ArrayList<Product>());
			req.setAttribute("mensaje", "No hay Usuario");
			req.setAttribute("antiguo",false);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/prueba.jsp");
			dispatcher.forward(req, resp);	
		}
	}
	public static void sendEmail(String from, String to, String message, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String messageBody = message;

		try {
			Message emailMessage = new MimeMessage(session);
			emailMessage.setFrom(new InternetAddress(from, "The Author"));
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to, "The Reader"));
			emailMessage.setSubject("How's the book?");
			emailMessage.setText(messageBody);
			Transport.send(emailMessage);
			resp.getOutputStream().println("Message sent!");
		} catch (AddressException e) {
			resp.getOutputStream().println("Adress Exception!");
		} catch (MessagingException e) {
			resp.getOutputStream().println("Message exception");
		}
	}
}