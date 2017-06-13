//UsuarioCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAO.UsuarioDAOMySQL;
import com.ipartek.jonBarnes.DAO.interfaces.UsuarioDAO;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
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

	// Creamos la dao.
	public static UsuarioDAO daoUsuarios = null;

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

		// DAO.
		daoUsuarios = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");

		// Recogemos los datos de la aplicacion.
		ServletContext application = getServletContext();
		UsuarioDAO dal = (UsuarioDAO) application.getAttribute("dalUsuarios"); // Si
																				// falla
																				// cambiar
																				// por
																				// dal

		// Si el dal esta vacio.
		if (dal == null) {

			// Cargamos los productos de la base de datos.
			dal = UsuarioDALFactory.getUsuariosDAL();

			application.setAttribute("dalUsuarios", dal);
		}

		// Abrimos la conexion.
		dal.abrirConexion();

		// Miramos la operacion a realiczar.
		String op = request.getParameter("op");

		// Sin peracion. Mostramos todos los usuarios.
		if (op == null) {

			// Cogemos todos los usuarios.
			Usuario[] usuarios = dal.findAll();

			// Cerramos la conexion.
			daoUsuarios.cerrarConexion();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_USUARIO).forward(request, response);
		} else {

			// ABro la conexion.
			// daoUsuarios.abrirConexion();
			String id = request.getParameter("id");

			Usuario usuario;

			// Operacion a realizar, modificar, borrar o dar de alta a un

			// usuario.
			switch (op) {
			case "modificar":
			case "borrar":

				usuario = dal.findById(Integer.parseInt(id));
				// usuario =
				// dal.findbyUsername(request.getParameter("username"));
				daoUsuarios.cerrarConexion();
				request.setAttribute("usuario", usuario);
			case "alta":
				request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO_USUARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_USUARIO).forward(request, response);
			}
		}
	}

}
