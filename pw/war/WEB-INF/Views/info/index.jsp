<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="models.User"%>
<%
	User log = (User) request.getAttribute("log");
	Boolean islog = (Boolean) request.getAttribute("isLogged");
	boolean isLogged = islog.booleanValue();
%>
<script type="text/javascript" src="script/s.js"></script>
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
			<a href="/conf" title="Configuracion"><img src="img/conf.png"></a>
			<%
				}
			%>
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
	<img src="img/chat.png" title="chat">
</div>
<div id="chat">
	<div id="head">
		<label>Hablemos</label><label id="closeChat">x</label>
	</div>
	<div id="conversacion"></div>
	<div class="newMessage">
		<form id="mes">
			<textarea id="message" placeholder="Ingrese mensaje"></textarea>
			<input type="button" value="Send" id="send">
			<script>
				
			</script>
		</form>
	</div>
</div>
<div id="car">
	<div id="en">
		Carro de pedidos <span id="closecar">X</span>
	</div>
	<div id="ped"></div>
</div>
<div id="banner">
	<img src="img/banner.png">
</div>
<div id="content">
	<script>
		$(document).ready(function() {
			$.post("/products", function(data) {
				$('#content').html(data);
			});
		});
	</script>
</div>
