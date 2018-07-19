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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import controllers.products.Car;
import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;
import models.Role;
import models.User;
import models.Resource;
import models.Access;
public class AddController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (AccessController.isPermited(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			User log = LogController.getUser();
			req.setAttribute("log", log);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/add.jsp");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "SELECT FROM "+ Role.class.getName();
		List<Role> roles = (List<Role>)pm.newQuery(query).execute();
		query = "SELECT FROM "+ Resource.class.getName();
		List<Resource> resource = (List<Resource>)pm.newQuery(query).execute();
		pm.close();
		req.setAttribute("roles", roles);
		req.setAttribute("rsr", resource);
		rd.forward(req, resp);
		}

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Access.class.getName()	+ " where idRole == "+request.getParameter("rol")+ " && idResource == "+ request.getParameter("url");
		List<Access> resources = (List<Access>)pm.newQuery(query).execute();
		if (resources.isEmpty()){
			Access access = new Access(Long.parseLong(request.getParameter("rol")), Long.parseLong(request.getParameter("url")), Boolean.parseBoolean(request.getParameter("is")));
			pm.makePersistent(access);
		}
		
		response.sendRedirect("/access");

		// parte extra

		//obtienes los usuarios con el rol que se agregó
		/*String query2 = "select from " + User.class.getName() + " where idRol == "+request.getParameter("rol");
		List<User> users = (List<User>)pm.newQuery(query2).execute();
		Resource rsr = pm.getObjectById(Resource.class, Long.parseLong(request.getParameter("url")));
		//pasas los correos de esos usuarios a un arreglo de solo emails
		ArrayList<String> emails = new ArrayList<String>();
		for(int i = 0;i<users.size();i++){
			emails.add(users.get(i).getCorreo()+" ");
		}
		//al request le das los emails y un atributo access que contiene el acceso otorgado
		request.setAttribute("emails", emails);
		request.setAttribute("access", rsr.getUrl());

		//redirecciona a la clase SendEmail(yo te la envie como Lab10_20172129) 
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/correoP");

		pm.close();
		rd.forward(request, response);*/
		 


	}
}
