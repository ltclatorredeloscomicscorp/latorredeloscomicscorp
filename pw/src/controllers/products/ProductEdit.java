package controllers.products;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.Product;
import models.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;

@SuppressWarnings("serial")
public class ProductEdit extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (AccessController.isPermited(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			User log = LogController.getUser();
			req.setAttribute("log", log);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k =	KeyFactory.createKey(Product.class.getSimpleName(), new
					Long(req.getParameter("id")).longValue());
			Product p = pm.getObjectById(Product.class, k);
			req.setAttribute("product", p);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/edit.jsp");
			dispatcher.forward(req, resp);	
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k =	KeyFactory.createKey(Product.class.getSimpleName(), new
				Long(req.getParameter("id")).longValue());
		Product p = pm.getObjectById(Product.class, k);

		p.setName(req.getParameter("name"));
		p.setAmount(Double.parseDouble(req.getParameter("amount")));
		p.setAuthor(req.getParameter("author"));
		p.setDescription(req.getParameter("description"));
		p.setDateOfSale(req.getParameter("dateOfSale"));
		p.setImage(req.getParameter("file"));

		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		resp.sendRedirect("/products");
	}
}
