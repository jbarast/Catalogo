//DALException.java

package com.ipartek.jonBarnes.DAL;

/**
 * 
 * Clase para "gestionar" las excepciones realacionadas con DAl.
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public class DALException extends RuntimeException {

	// TODO mirar esto bien.
	private static final long serialVersionUID = -7536930890973019777L;

	public DALException() {
		super();
	}

	public DALException(String message) {
		super(message);
	}

	public DALException(Throwable cause) {
		super(cause);
	}

	public DALException(String message, Throwable cause) {
		super(message, cause);
	}

	public DALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
