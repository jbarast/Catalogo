//Listener.java

package com.ipartek.jonBarnes.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * Creacion de un listener para el ejercicio. Carga la lista de usuarios.
 * 
 * @author Jon Barnes
 * @version 26/05/2017
 *
 */
public class Listener implements ServletContextListener {

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(Listener.class);

	/**
	 * 
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * Creamos la lista de usuarios, de la dal.
	 */
	public void contextInitialized(ServletContextEvent sce) {

		// Indicamos que hemos inicializado la aplicaion.
		log.info("Aplicacion inicializada:");

	}

}
