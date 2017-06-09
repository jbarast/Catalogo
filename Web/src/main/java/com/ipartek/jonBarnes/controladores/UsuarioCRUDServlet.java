//UsuarioCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		UsuarioDAOMySQL dal = (UsuarioDAOMySQL) application.getAttribute("dal");

		// Miramos la operacion a realiczar.
		String op = request.getParameter("op");

		// Sin peracion. Mostramos todos los usuarios.
		if (op == null) {

			// Abrimos la conexion.
			daoUsuarios.abrirConexion();

			// Cogemos todos los usuarios.
			Usuario[] usuarios = dal.findAll();

			// Cerramos la conexion.
			daoUsuarios.cerrarConexion();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_USUARIO).forward(request, response);
		} else {
			String id = request.getParameter("id");

			Usuario usuario;

			// Operacion a realizar, modificar, borrar o dar de alta a un
			// ABro la conexion.
			daoUsuarios.abrirConexion();
			// usuario.
			switch (op) {
			case "modificar":
			case "borrar":
				usuario = dal.findById(Integer.parseInt(id));
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
