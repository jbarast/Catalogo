//UsuarioYAExistenteDALException.java

package com.ipartek.jonBarnes.DAL;

/**
 * 
 * Para la creacion de excepciones de cuando un usuario ya existe.
 * 
 * @author jonBarnes
 * @version 24/05/2017
 *
 */
public class UsuarioYaExistenteDALException extends DALException {

	private static final long serialVersionUID = 5371466154809794327L;

	public UsuarioYaExistenteDALException() {
		super();
	}

	public UsuarioYaExistenteDALException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioYaExistenteDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioYaExistenteDALException(String message) {
		super(message);
	}

	public UsuarioYaExistenteDALException(Throwable cause) {
		super(cause);
	}

}
