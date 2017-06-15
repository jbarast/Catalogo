//LoginServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.UsuarioDAOMySQL;
import com.ipartek.jonBarnes.DAO.interfaces.UsuarioDAO;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Servlet para el login de los usuarios con DAO.
 * 
 * @author jbarast
 * @version 09/06/2017
 *
 */
// @WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(LoginServlet.class);

	// Creamos la dao.
	public static UsuarioDAO daoUsuarios = null;

	/**
	 * Metodo doGet, que lo que hace es llamar el metod doPost.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Metodo doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// DAO
		daoUsuarios = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");

		// Recoger datos de vistas
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");

		String opcion = request.getParameter("opcion");

		// Crear modelos en base a los datos
		Usuario usuario = new Usuario();
		usuario.setUsername(nombre);
		usuario.setPassword(pass);

		// abrimos la conexin.
		daoUsuarios.abrirConexion();

		// Llamada a l�gica de negocio
		ServletContext application = getServletContext();

		UsuarioDAOMySQL usuariosDAL = (UsuarioDAOMySQL) application.getAttribute(AltaServlet.USUARIOS_DAL);

		if (usuariosDAL == null) {
			usuariosDAL = UsuarioDALFactory.getUsuariosDAL();
		}

		// S�lo para crear una base de datos falsa con el
		// contenido de un usuario "javi", "lete"
		// usuarioDAL.alta(new Usuario("javi", "lete"));

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(ConstantesGlobales.TIEMPO_INACTIVIDAD);

		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(ConstantesGlobales.TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);

		// for (Cookie cookie : request.getCookies()) {
		// if ("JSESSIONID".equals(cookie.getName())) {
		// cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		// response.addCookie(cookie);
		// }
		// }

		// Operaciones.
		try {

			// abrimos la conexin.
			daoUsuarios.abrirConexion();

			// ESTADOS

			boolean esValido = usuariosDAL.validar(usuario);
			// boolean esValido=true; //TODO cambiarlo, es para una prueba
			// unicamente.

			boolean sinParametros = usuario.getUsername() == null;

			boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;

			boolean quiereSalir = "logout".equals(opcion);

			boolean nombreValido = usuario.getUsername() != null
					&& usuario.getUsername().length() >= ConstantesGlobales.MINIMO_CARACTERES;
			boolean passValido = !(usuario.getPassword() == null || usuario.getPassword().length() < ConstantesGlobales.MINIMO_CARACTERES);

			// Redirigir a una nueva vista
			if (quiereSalir) {

				// Indicamos quien sale de la session.
				log.info(String.format("Fin de sesion de %s.", usuario.getUsername()));

				// Finalizamos la sesion.
				session.invalidate();
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LOGIN).forward(request, response);
			} else if (esUsuarioYaRegistrado) {
				// request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
				// response);
				log.info("Usuario ya registrado.");
				response.sendRedirect("/productocrud");
			} else if (sinParametros) {
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LOGIN).forward(request, response);
			} else if (!nombreValido || !passValido) {
				usuario.setErrores("El nombre y la pass deben tener como m�nimo "
						+ ConstantesGlobales.MINIMO_CARACTERES + " caracteres y son ambos requeridos");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LOGIN).forward(request, response);
			} else if (esValido) {
				Usuario usuarioAmeter = new Usuario();

				usuarioAmeter = usuariosDAL.findbyUsername(nombre);

				session.setAttribute("usuario", usuarioAmeter);
				// response.sendRedirect("principal.jsp");
				// request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
				// response);

				log.info(String.format("Inicio de session de %s.", usuario.getUsername()));
				response.sendRedirect("/productocrud");

			} else {
				usuario.setErrores("El usuario y contrase�a introducidos no son v�lidos");
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LOGIN).forward(request, response);

			}
		} catch (Exception e) {
			throw new DAOException("Error en las operacion  con la base de  datos en login.", e);

		} finally {
			// cerramos la conexion con la base de datos.
			try {
				usuariosDAL.cerrarConexion();

			} catch (Exception e) {
				throw new DAOException("Error en la dexconesion con la base de datos.", e);

			}
		}
	}
}
