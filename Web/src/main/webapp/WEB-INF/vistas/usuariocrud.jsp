<%@ include file="includes/cabecera.jsp"%>


<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de usuarios</h2>

<table border="1">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Usuario</th>
			<th>Contraseña</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>
					<a href="?op=modificar&id=${usuario.nombre}">Modificar</a>
					<a href="?op=borrar&id=${usuario.nombre}">Borrar</a>
				</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.pass}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="?op=alta">Alta</a>

<%@ include file="includes/pie.jsp"%>
