<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="models.Product"%>

<%
	List<Product> products = (List<Product>) (request.getAttribute("products"));
%>
<%@page import="java.util.Date"%>
<%@page import="models.Role"%>
<%@ page import="models.User"%>
<%
	Date date = (Date) request.getAttribute("date");
%>
<%
	List<Role> roles = (List<Role>) request.getAttribute("roles");
	User log = (User) request.getAttribute("log");
	Boolean islog = (Boolean) request.getAttribute("isLogged");
	boolean isLogged = islog.booleanValue();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Añadir Acceso</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Anton|Yanone+Kaffeesatz"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/plantilla.css">
<meta charset="utf-8">
<link rel="icon" type="image/png" href="/img/logo1.ico" />
<script type="text/javascript" src="/script/s.js"></script>
<script type="text/javascript" src="/script/form.js"></script>
</head>
<body>
	<div id="inicio">
		<div id="menu2">
			<div id="closeMenudiv" class="closeMenu"></div>
			<ul>
				<li class="closeMenu">x</li>
				<li><a href="/">Home</a></li>
				<li><a href="/products">comprar</a></li>
				<li><a href="/us">nosotros</a></li>
				<li><a href="/contact">contactanos</a></li>
				<li><a href="/messages">mensajes</a></li>
			</ul>
		</div>
		<header>
		<div id="name">
			La torre de los comics
			<div id="info_page">
				<%
					if (log.isAdministrador()) {
				%>
				<a href="/conf" title="Configuracion"><img src="../img/conf.png"></a>
				<%
					}
				%>
				<a
					href="https://www.youtube.com/playlist?list=PLSbWh2Bhn9eEvXkPKiDtsW1KGYfNbtRun"
					title="Tutoriales de uso"><img src="../img/youtube.png"></a>
				<a
					href="https://github.com/ltclatorredeloscomicscorp/latorredeloscomicscorp"
					title="Open Source"><img src="../img/github.png"></a>
			</div>
		</div>
		<div id="secondname">
			<img src="../img/logo1.png" id="log">
			<div id="sdm">
				<%
					if (isLogged) {
				%>
				<a class="men" href="/users/logout">Cerrar Sesion</a>
				<%
					} else {
				%>
				<a href="/users/register" class="men">Registrar</a><a class="men"
					href="/users/login">Iniciar Sesion</a class="men">
				<%
					}
				%><label class="men" id="opencar">Carro</label><label class="men"
					id="menu">Menú</label>
			</div>
		</div>
		</header>
		<div id="chaticon">
			<img src="/img/chat.png" title="chat">
		</div>
		<div id="chat">
			<div id="head">
				<label>Hablemos</label><label id="closeChat">x</label>
			</div>
			<div id="conversacion"></div>
			<form id="mes">
				<textarea id="message" placeholder="Ingrese mensaje"></textarea>
				<input type="button" value="Send" id="send">
			</form>
		</div>
		<div id="car">
			<div id="en">
				Carro de pedidos <span id="closecar">X</span>
			</div>
			<div id="ped"></div>
		</div>
		<div id="contenido" style="background: white;">
			<%
				if (products.size() > 0) {
			%>
			<%
				for (int i = 0; i < products.size(); i++) {
			%>
			<%
				Product o = (Product) products.get(i);
			%>
			<div class="product">
				<img src="<%=o.getImage()%>">
				<p class="title">
					<b><%=o.getName()%></b>
				</p>
				<p class="price"><%=o.getAmount()%></p>
				<%
					if (log.isAdministrador()) {
				%>
				<a href="/products/edit?id=<%=o.getId()%>"><button>Editar</button></a>
				<a href="/products/remove?id=<%=o.getId()%>"><button>Eliminar</button></a>
				<%
					} else {
				%>
				<button class="buttoncar" id="<%=o.getId()%>">AÑADIR AL
					CARRO</button>
				<%
					}
				%>
			</div>

			<%
				}
			%>
			<%
				} else {
			%>
			<h1>No hay productos</h1>
			<%
				}
			%>
		</div>
	</div>
</html>