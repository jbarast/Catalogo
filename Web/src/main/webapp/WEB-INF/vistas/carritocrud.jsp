<%--carritocrud.jsp --%>

<%--
     Vista para carritocrud.
     autor= jonBarnes
     version = 31/06/2017

 --%>

<%--cabecera --%>
<%@ include file="includes/cabecerausuario.jsp"%>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos la libreria core de jstl --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%--Titulo --%>
<h2>Carrito</h2>
<%--Tabla --%>
<table border="1">

<%--Titulos de las columnas. --%>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Id</th>
			<th>Nombre</th>
			<th>Descripcion</th>
			<th>Precio</th>
			<th>Stock</th>
			<th>Imagen Producto</th>
		</tr>
	</thead>
	<tbody>
	
	<%--Bucle de lectura de elementos de la tienda. --%>
		<c:forEach items="${sessionScope.carrito}" var="productoCarrito">
			<tr>
				<td>
					<a href="?op=modificar&id=${productoCarrito.nombre}">Modificar</a>
					<a href="?op=borrar&id=${productoCarrito.nombre}">Borrar</a>
				</td>
				<td>${productoCarrito.id}</td>
				<td>${productoCarrito.nombre}</td>
				<td>${productoCarrito.descripcion}</td>
				<td>${productoCarrito.precio} €</td>
				<td>${productoCarrito.stock}</td>
				<td><IMG src="${productoCarrito.rutaImagen}" width="100" height="100"/></td>
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:out value="${sessionScope.carrito}"/>				



<%--pie --%>
<%@ include file="includes/pie.jsp"%>