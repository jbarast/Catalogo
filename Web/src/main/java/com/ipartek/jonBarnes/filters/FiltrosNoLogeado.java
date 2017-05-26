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

import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;

public class FiltrosNoLogeado implements Filter {

	private FilterConfig fConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.fConfig = fConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// acción de inicio de sesión previa
		// obtener nombre de usuario
		String username = req.getParameter("usuario");

		// sacar la url.
		String uri = req.getRequestURI(); // "/...."

		

		if (username == null && !(uri.equals("/login")||uri.equals("/WEB-INF/vistas/login.jsp"))) {
			res.sendRedirect("/login");

		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}