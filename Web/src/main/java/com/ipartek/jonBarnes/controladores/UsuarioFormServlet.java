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
import com.ipartek.jonBarnes.tipos.Usuario;

//@WebServlet("/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String op = request.getParameter("opform");

		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		RequestDispatcher rutaListado = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_FORMULARIO);

		// response.setContentType("text/plain");
		// PrintWriter out = response.getWriter();
		// out.println(op);
		// out.println(nombre);
		// out.println(pass);
		// out.println(pass2);

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		Usuario usuario = new Usuario(nombre, pass);

		ServletContext application = getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		switch (op) {
		case "alta":
			if (pass.equals(pass2)) {

				// Indicamos que usuario se da de alta.
				log.info(String.format("Registrado el usuario %s.", nombre));

				dal.alta(usuario);
				rutaListado.forward(request, response);
			} else {
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

					dal.modificar(usuario);
				} catch (DALException de) {
					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
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
