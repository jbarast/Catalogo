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
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.tipos.Usuario;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = RUTA + "productocrud.jsp";
	private static final String RUTA_LOGIN = RUTA + "login.jsp";

	public static final int TIEMPO_INACTIVIDAD = 30 * 60;

	/* package */static final int MINIMO_CARACTERES = 4;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(LoginServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Recoger datos de vistas
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");

		String opcion = request.getParameter("opcion");

		// Crear modelos en base a los datos
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPass(pass);

		// Llamada a lógica de negocio
		ServletContext application = getServletContext();

		UsuariosDAL usuariosDAL = (UsuariosDAL) application.getAttribute(AltaServlet.USUARIOS_DAL);

		if (usuariosDAL == null) {
			usuariosDAL = UsuarioDALFactory.getUsuariosDAL();
		}

		// Sólo para crear una base de datos falsa con el
		// contenido de un usuario "javi", "lete"
		// usuarioDAL.alta(new Usuario("javi", "lete"));

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);

		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);

		// for (Cookie cookie : request.getCookies()) {
		// if ("JSESSIONID".equals(cookie.getName())) {
		// cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		// response.addCookie(cookie);
		// }
		// }

		// ESTADOS
		boolean esValido = usuariosDAL.validar(usuario);

		boolean sinParametros = usuario.getNombre() == null;

		boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;

		boolean quiereSalir = "logout".equals(opcion);

		boolean nombreValido = usuario.getNombre() != null && usuario.getNombre().length() >= MINIMO_CARACTERES;
		boolean passValido = !(usuario.getPass() == null || usuario.getPass().length() < MINIMO_CARACTERES);

		// Redirigir a una nueva vista
		if (quiereSalir) {

			// Indicamos quien sale de la session.
			log.info(String.format("Fin de sesion de %s.", usuario.getNombre()));

			// Finalizamos la sesion.
			session.invalidate();
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esUsuarioYaRegistrado) {
			// request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
			// response);
			log.info("Usuario ya registrado.");
			response.sendRedirect("/productocrud");
		} else if (sinParametros) {
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (!nombreValido || !passValido) {
			usuario.setErrores("El nombre y la pass deben tener como mínimo " + MINIMO_CARACTERES
					+ " caracteres y son ambos requeridos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esValido) {
			session.setAttribute("usuario", usuario);
			// response.sendRedirect("principal.jsp");
			// request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
			// response);

			log.info(String.format("Inicio de session de %s.", usuario.getNombre()));
			response.sendRedirect("/productocrud");

		} else {
			usuario.setErrores("El usuario y contraseña introducidos no son válidos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		}
	}
}
