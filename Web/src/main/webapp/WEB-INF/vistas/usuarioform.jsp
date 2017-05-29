<%--usuarioform.jsp --%>

<%--
	Para dar de alta, modificar y borrar usuarios.
	
	autor: jon Barnes
	version: 24/05/2017
 --%>


<%--Cabecera de la pagina. --%>
<%@ include file="includes/cabecera.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos las librerias de jstl. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>Formulario de usuarios</h2>
	
	<%--Cargamos la clase usuario. --%>
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.jonBarnes.tipos.Usuario" />

<%--Formulario dependiente de la opcion elegida. --%>
	<form action="usuarioform" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.nombre}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if>   
		  	/>
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass" />
		</fieldset>
		<fieldset>
			<label for="pass2">Contraseña otra vez</label> <input type="password" id="pass2"
				name="pass2" />
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" />
			<p class="errores">${usuario.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>


<%--Pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>