//FiltroUsuarioNoLogeado.java

package com.ipartek.jonBarnes.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * Filtro para cuando un usuario no este logeado que le manda para login, si no
 * esta en la pantalla de login o alta de usuario.
 */
public class FiltroUsuarioNoLogeado implements Filter {

	private FilterConfig config;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(FiltroUsuarioNoLogeado.class);

	/**
	 * Metodo destroy,vacio.
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		// Leemos datos de la session.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession();

		// Cogemos el usuario.
		// //String username = req.getParameter("admin");

		Usuario usuario = new Usuario();

		usuario = (Usuario) session.getAttribute("usuario");

		// Para evitar el null pointer exception

		String username;

		if (usuario == null) {
			username = "";
		} else {
			username = usuario.getNombre();
		}

		// Miramos que dato coge.

		System.out.println(username);

		// Para sacar la URI:
		String path = req.getRequestURI();

		// Miramos la ruta que cogemos.

		// Para mirar el path.
		if (username == "" && !(path.equals("/login"))) {

			// Ponemos mensaje de intruso.
			log.info("Intento de entrada de intruso");

			// Mandamos a la pantalla de login.
			res.sendRedirect("/login");

		}

		// Seguimos con los filtros.
		chain.doFilter(request, response);

	}

	/**
	 * Metodo de inicializacion del filtro.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;

	}

}
