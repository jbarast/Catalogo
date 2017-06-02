<%--listaproductosform.jsp --%>
<%--

  Controlador de añadir productos al carrito.
  autor: jonBarnes
  version: 01/06/2017

 --%>

<%--Cabecera --%>
<%@ include file="includes/cabecerausuario.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos las librerias. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>Borrado de productos.</h2>

<%--BORRARLO --%>

${producto}


	
	<%--Llamamos a clases. --%>
	<jsp:useBean id="carrito" scope="request"
		class="com.ipartek.jonBarnes.tipos.ProductoStockImagen" />


	<%--Redirige a la servlet de lista de productos --%>
 <%--"Creacion" de los formularios. --%>
	<form action="carritoform" method="post">
	 
	    <%--Nombre. --%>
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required"  value="${carrito.nombre}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if>   
		  	/>
		  	</fieldset>
		
		<%--El BOTON para validar el formulario. --%>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" />
			<p class="errores">${producto.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	
	<%--Para que se crea una ventana que hay que validar la operacion de borrado. --%>
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
		

   

<%--pie --%>
<%@ include file="includes/pie.jsp" %>
