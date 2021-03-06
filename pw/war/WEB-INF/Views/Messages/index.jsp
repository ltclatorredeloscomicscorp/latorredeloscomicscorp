<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.User"%>
<%@ page import="models.Message"%>
<%@ page import="java.util.List"%>
<%
	User log = (User) request.getAttribute("log");
	List<Message> messages = (List<Message>) request.getAttribute("messages");
	List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Messages</title>
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
<script type="text/javascript" src="/script/index.js"></script>
<script type="text/javascript" src="/script/s.js"></script>
</head>
<body>

	<div id="menu2">
		<div id="closeMenudiv" class="closeMenu"></div>
		<ul>
			<li class="closeMenu">x</li>
			<li><a href="/">Home</a></li>
			<li><a href="/products">comprar</a></li>
			<li><a href="/us">nosotros</a></li>
			<li><a href="/contact">contactanos</a></li>
		</ul>
	</div>
	<header>
	<div id="name">
		La torre de los comics
		<div id="info_page">
			<%
				//if(log.isAdministrador()){
			%>
			<a href="/conf" title="Configuracion"><img src="../img/conf.png"></a>
			<%
				//}
			%>
			<a
				href="https://www.youtube.com/playlist?list=PLSbWh2Bhn9eEvXkPKiDtsW1KGYfNbtRun"
				title="Tutoriales de uso"><img src="../img/youtube.png"></a> <a
				href="https://github.com/ltclatorredeloscomicscorp/latorredeloscomicscorp"
				title="Open Source"><img src="../img/github.png"></a>
		</div>
	</div>
	<div id="secondname">
		<!-- <img src="../img/logo1.png" id="log"> -->
		<div id="sdm">
			<%
				//if (isLogged){
			%>
			<a class="men" href="/users/logout">Cerrar Sesion</a>
			<%
				//} else{
			%>
			<a href="/users/register" class="men">Registrar</a><a class="men"
				href="/users/login">Iniciar Sesion</a class="men">
			<%
				//}
			%><label class="men" id="opencar">Carro</label><label class="men"
				id="menu">Men�</label>
		</div>
	</div>
	</header>

	<div class="usersIndex">

		<center>
			<a href="/messages/add">Add Message</a>
			<%
				if (messages.size() > 0) {
			%>
			<h1>Messages</h1>
			<table>
				<tr>
					<td class="title"><center>
							<h3>Id User</h3>
						</center></td>
					<td class="title"><center>
							<h3>Email user</h3>
						</center></td>
					<td class="title"><center>
							<h3>Message</h3>
						</center></td>
					<td class="title"><center>
							<h3>Actions</h3>
						</center></td>
				</tr>
				<%
					for (int i = messages.size() - 1; i >= 0; i--) {
							Message m = messages.get(i);
				%>
				<tr>
					<td class="content"><%=m.getIdUser()%></td>
					<td class="content"><%=users.get(i).getCorreo()%></td>
					<td class="content"><%=m.getMessage()%></td>
					<td class="content content2"><a
						href="/messages/view?id=<%=users.get(i).getId()%>">View</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<%
				} else {
			%>
			<h2>No messages found.</h2>
			<%
				}
			%>
		</center>
	</div>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="script/s.js"></script>
</body>
</html>