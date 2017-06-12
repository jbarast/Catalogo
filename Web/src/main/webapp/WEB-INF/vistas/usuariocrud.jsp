<%--usuariocrod.jsp --%>

<%--

	Pagina que nos muestra los usuarios con las opciones disponibles.
	
	autor: jon Barnes
	version: 24/05/2017

 --%>



<%--La cabecera. --%>
<%@ include file="includes/cabecera.jsp"%>


<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Librerias de jstl que necesitamos. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de usuarios</h2>


<%--Tabla que muestra los usuarios --%>
<table border="1">

<%--Cabecera de la tabla. --%>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Usuario</th>
			<th>Contraseña</th>
		</tr>
	</thead>
	
	<%--Fila de las tablas. --%>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>
					<a href="?op=modificar&id=${usuario.id}">Modificar</a>
					<a href="?op=borrar&id=${usuario.id}">Borrar</a>
				</td>
				<td>${usuario.username}</td>
				<td>${usuario.password}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%--Pie de la pagina. --%>
<%@ include file="includes/pie.jsp"%>
