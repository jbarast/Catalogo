//UsuarioDALFactory.java

package com.ipartek.jonBarnes.DAL;

/**
 * 
 * @author jonBarnes
 * @version 24/05/2017
 *
 */
public class UsuarioDALFactory {
	public static UsuariosDAL getUsuariosDAL() {
		// return new UsuariosDALUsuarioUnico();
		return new UsuariosDALColeccion();
	}
}
