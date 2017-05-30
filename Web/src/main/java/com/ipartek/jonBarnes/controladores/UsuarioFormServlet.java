//UsuarioFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.DAL.DALException;
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Servlet para la gestion de modificacion, borrado y alta de usuarios.
 * 
 * @author jon Barnes
 * @version 24/05/2017
 *
 */
// @WebServlet("/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	/**
	 * El metodo doGet llama a un metodo doPost.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Metodo doPost.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Operacion a realizar.
		String op = request.getParameter("opform");

		// Datos del objeto que queremos modificar, borrar o dar de alta.
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		// rutas. //TODO Ponerlo que dependa de constantes globales.
		RequestDispatcher rutaListado = request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO_USUARIO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO_USUARIO);

		// Operacion nula, mostrar listado de usuarios.
		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		// Cogemos el objeto de la aplicacion, usuario.
		Usuario usuario = new Usuario(nombre, pass);

		ServletContext application = getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		// Operaciones.
		switch (op) {
		case "alta":
			if (pass.equals(pass2)) {

				// Indicamos que usuario se da de alta.
				log.info(String.format("Registrado el usuario %s.", nombre));

				// Damos de alta al usuario-
				dal.alta(usuario);
				rutaListado.forward(request, response);
			} else {
				// Que hacer si las contraseñas no coinciden.
				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "modificar":
			if (pass.equals(pass2)) {
				try {

					// Indicamos que usuario a sido modificado.
					log.info(String.format("Modificado el usuario %s.", nombre));

					// Modificamos el usuario.
					dal.modificar(usuario);
				} catch (DALException de) { // Error.
					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
				// Contraseñas no coinciden.
				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "borrar":
			// Indicamos que usuario se borra.
			log.info(String.format("Borrado el usuario %s.", nombre));

			// Borramos el usuario.
			dal.borrar(usuario);
			rutaListado.forward(request, response);

			break;
		}
	}
}
