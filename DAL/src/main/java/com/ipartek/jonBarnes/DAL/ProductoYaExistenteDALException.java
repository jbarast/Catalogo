//ProductoYAExistenteDALException.java

package com.ipartek.jonBarnes.DAL;

/**
 * 
 * Excepction cuando ya existe un usuario.
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public class ProductoYaExistenteDALException extends DALException {

	private static final long serialVersionUID = 5371466154809794327L;

	public ProductoYaExistenteDALException() {
		super();
	}

	public ProductoYaExistenteDALException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductoYaExistenteDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductoYaExistenteDALException(String message) {
		super(message);
	}

	public ProductoYaExistenteDALException(Throwable cause) {
		super(cause);
	}

}
