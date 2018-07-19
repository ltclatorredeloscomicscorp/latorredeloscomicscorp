package controllers.products;
import java.io.IOException;
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
public class ProductIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (AccessController.isPermitedIndex(req.getServletPath(), req, resp, this)){
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			User log = LogController.getUser();
			req.setAttribute("log", log);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from " + Product.class.getName();
			List<Product> products = (List<Product>)pm.newQuery(query).execute();
			req.setAttribute("products",products);
			// forward the request to the jsp
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/WEB-INF/Views/Product/index.jsp");
			dispatcher.forward(req, resp);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (AccessController.isPermitedIndex(req.getServletPath(), req, resp, this)){
			User log = LogController.getUser();
			req.setAttribute("log", log);
			boolean isLogged = LogController.isLogged();
			req.setAttribute("isLogged", isLogged);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from " + Product.class.getName();
			List<Product> products = (List<Product>)pm.newQuery(query).execute();
			for (Product p: products){
				String res = "<div class=\"product\">\r\n" + 
						"						<img src=\""+p.getImage()+"\">\r\n" + 
						"						<p class=\"title\"><b>"+p.getName()+"</b></p>\r\n" + 
						"						<p class=\"price\">"+p.getAmount()+"</p>\r\n";
				if (!log.isAdministrador()) {
				  res=res+"					<button class=\"buttoncar\"  id=\""+p.getId()+"\">AÑADIR AL CARRO</button>\r\n";
					
				}
				res=res+					"				</div>";
				resp.getWriter().print(res);
			}
		}
	}
}