//FacturaDALFactory.java

package com.ipartek.jonBarnes.DAL;

import com.ipartek.jonBarnes.DAO.FacturaDAOMySQL;

/**
 * Created by Jon on 11/06/2017.
 */
public class FacturaDALFactory {
	public static FacturaDAOMySQL getProductosDAL() {
		// return new UsuariosDALUsuarioUnico();
		return new FacturaDAOMySQL();
	}
}
