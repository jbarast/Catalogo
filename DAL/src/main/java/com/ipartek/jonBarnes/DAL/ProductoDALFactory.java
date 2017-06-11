package com.ipartek.jonBarnes.DAL;

import com.ipartek.jonBarnes.DAO.ProductoDAOMySQL;

/**
 * Created by Jon on 11/06/2017.
 */
public class ProductoDALFactory {
    public static ProductoDAOMySQL getProductosDAL() {
        // return new UsuariosDALUsuarioUnico();
        return new ProductoDAOMySQL();
    }
}
