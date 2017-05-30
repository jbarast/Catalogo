//AltaServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAL.UsuarioYaExistenteDALException;
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Servlet para dar de alta a usuarios.
 * 
 * @author jon Barnes
 * @version 24/05/2017
 *
 */
// @WebServlet("/alta")
public class AltaServlet extends HttpServlet {
	/* package */static final String USUARIOS_DAL = "dal";

	private static final long serialVersionUID = 1L;

	/* package */static final String RUTA_ALTA = ConstantesGlobales.RUTA + "alta.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		// Inicio sin datos: mostrar formulario
		// Datos incorrectos: sin rellenar, límite de caracteres, no coinciden
		// contraseñas
		// Las contraseñas deben ser iguales
		// Datos correctos: guardar

		Usuario usuario = new Usuario(nombre, pass);

		boolean hayDatos = nombre != null && pass != null && pass2 != null;
		boolean datosCorrectos = validarCampo(nombre) && validarCampo(pass) && validarCampo(pass2);
		boolean passIguales = pass != null && pass.equals(pass2);

		if (hayDatos) {
			if (!datosCorrectos) {
				usuario.setErrores("Todos los campos son requeridos y con un mínimo de "
						+ ConstantesGlobales.MINIMO_CARACTERES + " caracteres");
				request.setAttribute("usuario", usuario);
			} else if (!passIguales) {
				usuario.setErrores("Las contraseñas deben ser iguales");
				request.setAttribute("usuario", usuario);
			} else {
				ServletContext application = getServletContext();

				UsuariosDAL usuariosDAL = (UsuariosDAL) application.getAttribute(USUARIOS_DAL);

				if (usuariosDAL == null) {
					usuariosDAL = UsuarioDALFactory.getUsuariosDAL();
				}

				try {
					usuariosDAL.alta(usuario);
				} catch (UsuarioYaExistenteDALException de) {
					usuario.setNombre("");
					usuario.setErrores("El usuario ya existe. Elige otro");
					request.setAttribute("usuario", usuario);
				}

				application.setAttribute(USUARIOS_DAL, usuariosDAL);
			}
		}
		request.getRequestDispatcher(RUTA_ALTA).forward(request, response);
	}

	private boolean validarCampo(String campo) {
		return campo != null && campo.length() >= ConstantesGlobales.MINIMO_CARACTERES;
	}

}
