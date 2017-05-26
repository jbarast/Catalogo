//Listener.java

package com.ipartek.jonBarnes.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Creacion de un listener para el ejercicio. Carga la lista de usuarios.
 * 
 * @author Jon Barnes
 * @version 26/05/2017
 *
 */
public class Listener implements ServletContextListener {

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

		ServletContext application = sce.getServletContext();
		UsuariosDAL dal = (UsuariosDAL) application.getAttribute("dal");

		if (dal == null) {
			dal = UsuarioDALFactory.getUsuariosDAL();

			dal.alta(new Usuario("usuario1", "pass1"));
			dal.alta(new Usuario("usuario2", "pass2"));

			// Creamos el usuario adm.

			dal.alta(new Usuario("admin", "admin"));

			application.setAttribute("dal", dal);
		}

	}

}
