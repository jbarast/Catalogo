//FiltroUsuarioNoLogeado.java

package com.ipartek.jonBarnes.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;

/**
 * Si no hay un usuario Logeado manda a Login.
 */
public class FiltroUsuarioNoLogeado implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// acción de inicio de sesión previa
		// obtener nombre de usuario
		String username = req.getParameter("usuario");

		// Miramos que no este en login.

		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println(path);

		if (path.startsWith("/login")) {
			chain.doFilter(request, response);

		} else {
			if (username == null) {
				//
				request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LOGIN).forward(request, response);

			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
