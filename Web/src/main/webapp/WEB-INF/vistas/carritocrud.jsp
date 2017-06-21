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
					
					<a href="carritoform?op=borrar&id=${productoCarrito.id}">Borrar</a>
					<a href="carritoform?op=aumentar&id=${productoCarrito.id }">Aumentar una Unidad</a>
					<a href="carritoform?op=quitar&id=${productoCarrito.id}">Quitar una Unidad</a>
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

<p>				
<a class="boton_personalizado" href="/carritoform?op=comprar&id=${usuario.id}">Realizar Pedido</a>
</p>

<%--pie --%>
<%@ include file="includes/pie.jsp"%>