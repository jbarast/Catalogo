<%--principal.jsp --%>

<%--
	Pagina sencilla que nos muestra el usuario y la fecha actual.
	No incluida en la aplicacion de forma directa.
	
	autor: jon Barnes
	version: 24/05/2017
 --%>

<%--Incluimos la cabecera. --%>
<%@ include file="includes/cabecera.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Principal</h2>
	
	<%--Cargamos la clase usuario. --%>
	<jsp:useBean id="usuario" scope="session" 
		class="com.ipartek.jonBarnes.tipos.Usuario" />
	
	<%--Mostramos el usuario con la opcion de logout. --%>	
	<h2>Bienvenido ${usuario.nombre} <a href="login?opcion=logout">Logout</a></h2>
	
	<%--Mostramos la fecha. --%>
	<h3><%= new java.util.Date() %></h3>

<%--Pie de la pagina. --%>
<%@ include file="includes/pie.jsp" %>