//FacturaDAO.java

/**
 * Interfaz para el carrito.
 * 
 * @author Jon Barnes
 * @version 13/06/2017
 */
package com.ipartek.jonBarnes.DAO.interfaces;

import com.ipartek.jonBarnes.tipos.Factura;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * Interfaz para el DAO de facturas.
 * 
 * @author jonBarnes
 * @version 22/06/2017
 *
 */
public interface FacturasDAO extends IpartekDAO {

	// Metodos que tiene que implementar.

	// Para mostrar todos los productos del carrito de un usuario.
	public ProductoStockImagen[] mostrarFactura(int id_usuario, int id_factura);

	// Para mostrar todas las facturas.
	public Factura[] mostrarListaFacturas(int id_usuario);

}
