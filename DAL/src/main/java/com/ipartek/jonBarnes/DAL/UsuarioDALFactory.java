package com.ipartek.jonBarnes.DAL;

import com.ipartek.jonBarnes.DAO.UsuarioDAOMySQL;

/**
 * 
 * @author jonBarnes
 * @version 24/05/2017
 *
 */
public class UsuarioDALFactory {
	public static UsuarioDAOMySQL getUsuariosDAL() {
		// return new UsuariosDALUsuarioUnico();
		return new UsuarioDAOMySQL();
	}
}