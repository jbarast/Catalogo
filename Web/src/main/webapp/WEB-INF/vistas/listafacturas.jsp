<%--listafacturas.jsp --%>

<%--
	Pagina para ver la lista de facturas del usuario.
	author: jon Barnes
	version: 22/06/2017


 --%>



<%--Cargamos la cabecera --%>
<%@ include file="includes/cabeceralogin.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Librerias de jstl que necesitamos. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Titulo que ponemos en la pagina --%>
<h2>Lista de facturas</h2>

<%--Tabla que muestra los usuarios --%>
<table border="1">

<%--Cabecera de la tabla. --%>
	<thead>
		<tr>
			<th>Factura</th>
			<th>Fecha de facturacion</th>			
		</tr>
	</thead>
	
	<%--Fila de las tablas. --%>
	<tbody>
		<c:forEach items="${requestScope.facturas}" var="factura">
			<tr>				
				<td><a href="/factura?id_factura=${factura.numeroFactura}">${factura.numeroFactura}</a></td>
				<td>${factura.fechaFactura}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>




<%--El pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>