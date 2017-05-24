<%@ include file="includes/cabecera.jsp" %>

	<h2>Principal</h2>
	<jsp:useBean id="usuario" scope="session" 
		class="com.ipartek.jonBarnes.tipos.Usuario" />
	<h2>Bienvenido ${usuario.nombre} <a href="login?opcion=logout">Logout</a></h2>
	<h3><%= new java.util.Date() %></h3>

<%@ include file="includes/pie.jsp" %>