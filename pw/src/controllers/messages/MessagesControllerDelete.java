package controllers.messages;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controllers.users.LogController;
import models.Message;

@SuppressWarnings("serial")
public class MessagesControllerDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		if(LogController.getUser().isAdministrador()){
			
			PersistenceManager persistenceManager = controllers.PMF.get().getPersistenceManager();
			Key k =	KeyFactory.createKey(models.Message.class.getSimpleName(), new Long(request.getParameter("id")).longValue());
			Message m = persistenceManager.getObjectById(Message.class, k);
			persistenceManager.deletePersistent(m);
			response.sendRedirect("/messages");
			
		}
	}

}
