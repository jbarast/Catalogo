<%@ include file="includes/cabecera.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Principal</h2>
	<jsp:useBean id="usuario" scope="session" 
		class="com.ipartek.jonBarnes.tipos.Usuario" />
	<h2>Bienvenido ${usuario.nombre} <a href="login?opcion=logout">Logout</a></h2>
	<h3><%= new java.util.Date() %></h3>

<%@ include file="includes/pie.jsp" %>