<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
    	<%@ page import="models.User" %>
<%//User log = (User)request.getAttribute("log"); 
//Boolean islog = (Boolean)request.getAttribute("isLogged");/
//boolean isLogged = islog.booleanValue();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Configuracion</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Anton|Yanone+Kaffeesatz"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/plantilla.css">
<meta charset="utf-8">
<link rel="icon" type="image/png" href="img/logo1.ico" />
<script type="text/javascript" src="/script/s.js"></script>
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
			</ul>
		</div>
		<header>
			<div id="name">
				La torre de los comics
				<div id="info_page">
					<%//if(log.isAdministrador()){ %>
				<a href="/conf" title="Configuracion"><img src="../img/conf.png"></a>
				<%//} %>
					<a
						href="https://www.youtube.com/playlist?list=PLSbWh2Bhn9eEvXkPKiDtsW1KGYfNbtRun"
						title="Tutoriales de uso"><img src="img/youtube.png"></a> <a
						href="https://github.com/ltclatorredeloscomicscorp/latorredeloscomicscorp"
						title="Open Source"><img src="img/github.png"></a>
				</div>
			</div>
			<div id="secondname">
				<img src="img/logo1.png" id="log">
				<div id="sdm">
					<%// if (isLogged){%>
			<a class="men" href="/users/logout">Cerrar Sesion</a>
			<%//} else{%> 
				<a href="/users/register" class="men">Registrar</a><a class="men" href="/users/login">Iniciar Sesion</a class="men"><%//} %><label
					class="men" id="opencar">Carro</label><label class="men" id="menu">Menú</label>
				</div>
			</div>
		</header>
		<div id="chaticon">
			<img src="img/chat.png" title="chat">
		</div>
		<div id="chat">
			<div id="head">
				<label>Hablemos</label><label id="closeChat">x</label>
			</div>
			<div id="conversacion">
				
			</div>
			<form id="mes">
				<textarea placeholder="Ingrese mensaje"></textarea>
				<input type="image" src="img/send.png">
			</form>
		</div>
		<div id="car">
			<div id="en">
				Carro de pedidos <span id="closecar">X</span>
			</div>
			<div id="ped">
			</div>
			<button>Hacer Pedido ($00.00)</button>
		</div>
		<div id="contenido" style="background:white;">
			<ul id="menu_conf">
			<li><a href="/users">Ver usuarios</a></li>
			<li><a href="/users/add">Añadir usuario</a></li>
			<li><a href="/roles">Ver roles</a></li>
			<li><a href="/roles/add">Añadir rol</a></li>
			<li><a href="/resources">Ver resources</a></li>
			<li><a href="/resources/add">Añadir resource</a></li>
			<li><a href="/access">Ver accesos</a></li>
			<li><a href="/access/add">Añadir acceso</a></li>
			<li><a href="/products">Ver Productos</a></li>
			<li><a href="/products/add">Añadir Productos</a></li>
			</ul>
		</div>
	</div>
</body>
</html>