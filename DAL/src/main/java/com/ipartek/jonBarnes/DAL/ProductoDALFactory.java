//DALFactory.java

package com.ipartek.jonBarnes.DAL;

/**
 * 
 * 
 * 
 * @author jonBarnes
 * @version 10/05/2017
 *
 */
public class ProductoDALFactory {

	public static ProductoDALInterface getProductos() {
		return new ProductosDALColeccion();
	}

}
