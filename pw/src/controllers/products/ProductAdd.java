package controllers.products;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controllers.PMF;
import controllers.users.AccessController;
import controllers.users.LogController;
import models.Product;
import models.User;

@SuppressWarnings("serial")
public class ProductAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (AccessController.isPermited(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			User log = LogController.getUser();
			req.setAttribute("log", log);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/add.jsp");
			dispatcher.forward(req, resp);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Double amount=Double.parseDouble(req.getParameter("amount"));
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String description=req.getParameter("description");
		String dateOfSale = req.getParameter("dateOfSale");
		String direccion=req.getParameter("file");
		Product p =new Product(name, amount, description, author, dateOfSale,direccion);
		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
		resp.sendRedirect("/products");
	}
}
