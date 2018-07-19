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

import controllers.Date.DateNow;
import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;
import models.Message;
import models.Role;
import models.User;

@SuppressWarnings("serial")
public class MessagesControllerAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//this.doPost(request, response);
		if(AccessController.isPermited(request.getServletPath(), request, response, this)){

			PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
			String queryUsers = "select from "+User.class.getName()+ " where name != ''";
			@SuppressWarnings("unchecked") List<User> users = (List<User>) persistenceManager.newQuery(queryUsers).execute();

			request.setAttribute("users", users);
			request.setAttribute("user", LogController.getUser());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Messages/add.jsp");
			dispatcher.forward(request, response);
		}

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(LogController.getUser().isAdministrador()){
			PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
			Long idUser = LogController.getUser().getId();
			String message = request.getParameter("message");
			Long idReceiver =  Long.parseLong(request.getParameter("idReceiver"));
			Message newMessage = new Message(idUser, idReceiver, "", message  , DateNow.getDateNow());
			persistenceManager.makePersistent(newMessage);
			persistenceManager.close();
			response.sendRedirect("/messages");
		} else {
			if (AccessController.isPermitedMessage(request.getServletPath(), request, response, this)){
				boolean isLogged = LogController.isLogged();
				request.setAttribute("isLogged", isLogged);
				request.setAttribute("url", request.getRequestURI());
				User log = LogController.getUser();
				request.setAttribute("log", log);
				PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
				Long idUser = LogController.getUser().getId();
				String message = request.getParameter("message");
				String queryRole = "select from "+Role.class.getName()+ " where name == 'Administrador'";
				List<Role> listRole = (List<Role>) persistenceManager.newQuery(queryRole).execute();
				String query = "select from "+User.class.getName()+ " where idRol == " + listRole.get(0).getId();
				List<User> adminList = (List<User>) persistenceManager.newQuery(query).execute();

				Message newMessage = new Message(idUser, adminList.get(0).getId(), "", message  , DateNow.getDateNow());
				persistenceManager.makePersistent(newMessage);
				persistenceManager.close();
				
				PersistenceManager persistenceManager2 = PMF.get().getPersistenceManager();
				String queryMessages1 = "select from "+Message.class.getName()+ " where idUser == " + idUser;
				List<Message> messagesAux1 = (List<Message>) persistenceManager2.newQuery(queryMessages1).execute();
				String queryMessages2 = "select from "+Message.class.getName()+ " where idReceiver == " + idUser;
				List<Message> messagesAux2 = (List<Message>) persistenceManager2.newQuery(queryMessages2).execute();
				List<Message> messages = new ArrayList<Message>();
				for(int i=0; i<messagesAux1.size(); i++){
					messages.add(messagesAux1.get(i));
				}
				for(int i=0; i<messagesAux2.size(); i++){
					messages.add(messagesAux2.get(i));
				}
				Collections.sort(messages);
				//SortListMessages.sortList(messages);
				String classMessage;
				Message m;
				response.setContentType("text/html");
				for(int i=0; i<messages.size(); i++) {
					m = messages.get(i);
					classMessage = "other";
					if(m.getIdUser().longValue()==idUser.longValue()) {
						classMessage = "own";
					}
					response.getWriter().print("<div class=\"" + classMessage + "\">"
					+ "<div>" + m.getMessage() + "</div><div class=\"hidden\">"
					+ "<p>Enviado:" + m.getCreated() + "</p>" + "</div>" + "</div>");
				}
			}
		}
	}

}
