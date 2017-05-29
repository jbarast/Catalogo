//EncodingFilter.java

package com.ipartek.jonBarnes.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filtro para asegurar que sienpre esta en UTF-8
 * 
 * Ademas ha que añadir a los jsp, la siguiente linea: <li><%@ page
 * contentType="text/html; charset=UTF-8" %></li>
 */
public class EncodingFilter implements Filter {

	private FilterConfig config;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * El filtro.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/**
	 * Configuracion basica del filtro init.
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		this.config = config;

	}

}
