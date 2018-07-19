<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="models.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Product> products = (List<Product>) (request.getAttribute("lista"));
	Double precio = (Double) (request.getAttribute("Precio"));
	String mensaje = (String) (request.getAttribute("mensaje"));
	Boolean prueba = (Boolean) (request.getAttribute("antiguo"));
%>
<%
	if (products.size() > 0) {
%>
<%
	for (int i = 0; i < products.size(); i++) {
%>
<%
	Product o = (Product) products.get(i);
%>
<div class="car_product">
	<a href="#"> <img src="<%=o.getImage()%>">
		<div class="info_car">
			<p class="title">
				<b><%=o.getName()%></b>
			</p>
			<p class="price"><%=o.getAmount()%></p>
		</div>
	</a>
</div>
<%
	}
%>
<label>El gasto estimado es : <%=precio%></label>
<br>
<form action="/car" method="post">
	<input type="submit" value="Comprar" name="Action"> <input
		type="submit" value="Borrar" name="Action">
</form>
<%
	} else {
		if (prueba) {
%>
<label><%=mensaje%></label>

<%
	} else {
%>
<label>No hay Productos Agregados</label>
<%
	}
%>
<%
	}
%>
</body>

</html>