package com.ipartek.jonBarnes.DAL;

import com.ipartek.jonBarnes.DAO.CarritoDAOMySQL;
import com.ipartek.jonBarnes.DAO.ProductoDAOMySQL;

/**
 * Created by Jon on 11/06/2017.
 */
public class CarritoDALFactory {
    public static CarritoDAOMySQL getProductosDAL() {
        // return new UsuariosDALUsuarioUnico();
        return new CarritoDAOMySQL();
    }
}
