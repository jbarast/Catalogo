//UsuarioCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Servlet que muestra una lista de usuarios.
 * 
 * @author jon Barnes
 * @version 24/05/2017
 *
 */
// @WebServlet("/usuariocrud")
public class UsuarioCRUDServlet extends HttpServlet {

	// Rutas.
	// TODO meterla en constantes globales.
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/usuariocrud";

	private static final long serialVersionUID = 1L;

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

		// Recogemos los datos de la aplicacion.
		ServletContext application = getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		// Si no hay usuarios los crea. Por si el listener no a funcionado.
		if (dal == null) {
			dal = UsuarioDALFactory.getUsuariosDAL();

			dal.alta(new Usuario("usuario1", "pass1"));
			dal.alta(new Usuario("usuario2", "pass2"));

			application.setAttribute("dal", dal);
		}

		// Miramos la operacion a realiczar.
		String op = request.getParameter("op");

		// Sin peracion. Mostramos todos los usuarios.
		if (op == null) {

			Usuario[] usuarios = dal.buscarTodosLosUsuarios();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
		} else {
			String id = request.getParameter("id");

			Usuario usuario;

			// Operacion a realizar, modificar, borrar o dar de alta a un
			// usuario.
			switch (op) {
			case "modificar":
			case "borrar":
				usuario = dal.buscarPorId(id);
				request.setAttribute("usuario", usuario);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request, response);
			}
		}
	}

}
