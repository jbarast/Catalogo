package com.ipartek.jonBarnes.DAO;

/**
 * 
 * Excepciones para la instanciacion del driver de MySQL.
 * 
 * @author jbarast
 * @version 07/06/2017
 *
 */
public class DAOException extends RuntimeException {

	// Para generar esto. Cuando da Warning DAOException, boton derecho,
	// generate serial version ID.

	private static final long serialVersionUID = -5425320393583754106L;

	public DAOException() {
		super();

	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOException(String message) {
		super(message);

	}

	public DAOException(Throwable cause) {
		super(cause);

	}

}
