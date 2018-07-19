<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="models.*"%>
<%
	List<User> users = (List<User>) request.getAttribute("users");
	User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Add User</title>
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
				id="menu">Menú</label>
		</div>
	</div>
	</header>
	<div class="form">
		<center>
			<img src="../user.png" />
			<h3>New Message</h3>
		</center>
		<form action="/messages/add" method="post">
			<p>Name:</p>
			<input type="hidden" name="idUser" value="<%=user.getId()%>">
			<input type="text" name="emailUser" value="<%=user.getCorreo()%>"
				disabled> <br> <br>
			<p>Email receiver:</p>
			<select name="idReceiver">
				<%
					for (int i = 0; i < users.size(); i++) {
						User u = users.get(i);
						if (!u.getCorreo().equals(user.getCorreo())) {
				%>
				<option value="<%=u.getId()%>"><%=u.getCorreo()%></option>
				<%
					}
					}
				%>
			</select><br>
			<p>Message:</p>
			<input type="text" name="message" id="text2" onkeyup="validar()2"
				required> <br> <br>
			<center>
				<input type="submit" id="submit" value="Send">
			</center>
		</form>
	</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../motor.js"></script>
</body>
</html>