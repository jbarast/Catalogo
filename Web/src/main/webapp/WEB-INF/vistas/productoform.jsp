<%--productoform.jsp --%>
<%--

  Controlador de los formularios de alta, modificacion y borrado.
  autor: jonBarnes
  version: 10/05/2017

 --%>

<%--Cabecera --%>
<%@ include file="includes/cabecera.jsp" %>

<%--Cargamos las librerias. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>Formulario de productos</h2>
	
	<%--Llamamos a clases. --%>
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.jonBarnes.tipos.ProductoStockImagen" />

		

   <%--"Creacion" de los formularios. --%>
	<form action="productoform" method="post">
	 
	    <%--Nombre. --%>
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required"  value="${producto.nombre}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if>   
		  	/>
		  	
		  	<%--Que solo se vea el dato de nombre a la hora de borrar. --%>
		  	
		  	<c:if test="${param.op != 'borrar' }">
		</fieldset>
		
		
		<%--id, en el alta no aparece por que se crea automaticamente. --%>
		<c:if test="{param.op != 'alta'">
		<fieldset>
			<label for="id">id</label> 
			<input  id="id" name="id" 
			<c:if test="${param.op=='modificar' }">
			readonly="readonly"
			value="${producto.id}"
			</c:if>
			/>
		</fieldset>
		</c:if>
		
		<%--Descripcion. --%>
		<fieldset>
			<label for="descripcion">Descripcion</label> 
			<input  id="descripcion" name="descripcion"  
			<c:if test="${param.op=='modificar' }">
			value="${producto.descripcion}"
			</c:if>
			/>
		</fieldset>
		
		<%--Precio --%>
		<fieldset>
			<label for="precio">Precio</label> 
			<input  id="precio" name="precio" 
			<c:if test="${param.op=='modificar' }">
			value="${producto.precio}"
			</c:if>
			/>
		</fieldset>		
		
		<%--Stock. --%>
		<fieldset>
			<label for="stock">Stock</label> 
			<input  id="stock" name="stock" 
			<c:if test="${param.op=='modificar' }">
			value="${producto.stock}"
			</c:if>
			/>
		</fieldset>
		
		<%--Ruta de la imagen. --%>
		<fieldset>
			<label for="rutaImagen">Imagen</label> 
			<input  id="rutaImagen" name="rutaImagen" 
			<c:if test="${param.op=='modificar' }">
			value="${producto.rutaImagen}"
			</c:if>
			/>
		</fieldset>
		
		 </c:if>  
		
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
