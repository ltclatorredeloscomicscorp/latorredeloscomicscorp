package controllers.messages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import controllers.PMF;
import controllers.users.LogController;
import models.Message;
import models.User;

@SuppressWarnings({ "serial" })
public class MessagesControllerIndex extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//Esto se imprime para el administrador, verificar que si alguien ingresa directamente a la url sea prohibido de ingresar.
		RequestDispatcher dispatcher;
		User currentUser = LogController.getUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(currentUser.isAdministrador()) {
			String queryM = "select from "+Message.class.getName();
			List<Message> messagesAll = (List<Message>) pm.newQuery(queryM).execute();
			List<Message> messages = new ArrayList<Message>();
			messages.addAll(messagesAll);
			Collections.sort(messages);
			HashMap<Long, Message> h = new HashMap<Long, Message>();
			Long aux;
			for(int i=0; i<messages.size(); i++) {
				aux = messages.get(i).getIdUser();
				if(messages.get(i).getIdUser().compareTo(currentUser.getId()) == 0){
					aux =  messages.get(i).getIdReceiver();
				}
				h.put(aux, messages.get(i));
			}
			messages.clear();
			Iterator<Entry<Long, Message>> it = h.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Message> e = it.next();
				messages.add(e.getValue());
			}
			List<User> users = new ArrayList<User>();
			
			for(int j=0; j<messages.size(); j++) {
				Long idUser = messages.get(j).getIdUser();
				if(currentUser.getId().compareTo(messages.get(j).getIdUser()) == 0){
					idUser = messages.get(j).getIdReceiver();
				}
				Key k =	KeyFactory.createKey(User.class.getSimpleName(), idUser);
				users.add(pm.getObjectById(User.class, k));
			}
			request.setAttribute("messages", messages);
			request.setAttribute("users", users);
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Messages/index.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		User currentUser = LogController.getUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryM1 = "select from "+Message.class.getName()+ " where idUser == " + currentUser.getId();
		List<Message> messagesAux1 = (List<Message>) pm.newQuery(queryM1).execute();
		String queryM2 = "select from "+Message.class.getName()+ " where idReceiver == " + currentUser.getId();
		List<Message> messagesAux2 = (List<Message>) pm.newQuery(queryM2).execute();
		List<Message> messages = new ArrayList<Message>();
		messages.addAll(messagesAux1);
		messages.addAll(messagesAux2);
		PrintWriter r = response.getWriter();
		if (messages.size() > 0) {
			Collections.sort(messages);
			String classMessage;
			for(int i=0; i<messages.size(); i++) {
				Message m = messages.get(i);
				classMessage = "other";
				if (m.getIdUser().compareTo(currentUser.getId()) == 0) {
					classMessage = "own";
				}
				r.println("<div class=\"" + classMessage + "\">");
				r.println("<div>" + m.getMessage() + "</div>");
				r.println("<div class=\"hidden\">");
				r.println("<p>Enviado:" + m.getCreated() + "</p>");
				r.println("</div>");
				r.println("</div>");
			}
		} else {
			r.println("<center><h3>Inicia un chat con el administrador. :D</h3></center>");
		}
	}

}
