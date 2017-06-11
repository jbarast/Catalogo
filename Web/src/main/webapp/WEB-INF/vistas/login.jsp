<%--login.jsp --%>

<%--
	Pagina de login de la aplicacion.
	author: jon Barnes
	version: 24/05/2017


 --%>



<%--Cargamos la cabecera --%>
<%@ include file="includes/cabeceralogin.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Login</h2>
	
	<%--Cargamos la clase usuario. --%>
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.jonBarnes.tipos.Usuario" />
		
		
	<%--El formulario. --%>
	<form action="login" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.username}" />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass" />
		</fieldset>
		<fieldset>
			<input type="submit" value="Login" />
			<p class="errores">${usuario.errores}</p>
		</fieldset>		
	</form>
	
<%--El pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>