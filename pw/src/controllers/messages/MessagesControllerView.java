package controllers.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controllers.PMF;
import controllers.users.LogController;
import models.Message;
import models.User;

@SuppressWarnings("serial")
public class MessagesControllerView extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher;
		if(LogController.getUser().isAdministrador()){
			PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
			String queryMessages1 = "select from "+Message.class.getName()+ " where idUser == " + Long.parseLong(request.getParameter("id"));
			List<Message> messagesAux1 = (List<Message>) persistenceManager.newQuery(queryMessages1).execute();
			String queryMessages2 = "select from "+Message.class.getName()+ " where idReceiver == " + Long.parseLong(request.getParameter("id"));
			List<Message> messagesAux2 = (List<Message>) persistenceManager.newQuery(queryMessages2).execute();
			List<Message> messages = new ArrayList<Message>();
			messages.addAll(messagesAux1);
			messages.addAll(messagesAux2);
			Collections.sort(messages);
			request.setAttribute("messages", messages);
			
			Key kUser =	KeyFactory.createKey(User.class.getSimpleName(), new Long(request.getParameter("id")).longValue());
			User u = persistenceManager.getObjectById(User.class, kUser);
			request.setAttribute("user", u);

			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Messages/view.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
