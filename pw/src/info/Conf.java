package info;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import javax.servlet.http.*;
import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;
import models.User;


public class Conf extends HttpServlet  {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if (AccessController.isPermited(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			User log = LogController.getUser();
			req.setAttribute("log", log);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/info/conf.jsp");
			rd.forward(req, resp);
		}
	}
}
