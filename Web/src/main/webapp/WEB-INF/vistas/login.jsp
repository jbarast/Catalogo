<%@ include file="includes/cabecera.jsp" %>

	<h2>Login</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.jonBarnes.tipos.Usuario" />

	<form action="login" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.nombre}" />
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
	
	<!-- Para agregar el alta usuario usuarioform?alta -->
		<a href="usuarioform?op=alta">Alta</a>
<%@ include file="includes/pie.jsp" %>