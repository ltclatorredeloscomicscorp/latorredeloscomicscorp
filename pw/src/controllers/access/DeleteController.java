package controllers.access;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;
import models.Access;
import models.User;
public class DeleteController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	if (AccessController.isPermited(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			req.setAttribute("url", req.getRequestURI());
			User log = LogController.getUser();
			req.setAttribute("log", log);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Access access = pm.getObjectById(Access.class, new Long(req.getParameter("id")).longValue());
			if (access!=null){
				pm.deletePersistent(access);
			}
			pm.close();
			resp.sendRedirect("/access");
		}
	}
}
