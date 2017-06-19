//CarritoDAO.java

/**
 * Interfaz para el carrito.
 * 
 * @author Jon Barnes
 * @version 13/06/2017
 */
package com.ipartek.jonBarnes.DAO.interfaces;

import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

public interface CarritoDAO extends IpartekDAO {

	// Metodos que tiene que implementar.

	// Para mostrar todos los productos del carrito de un usuario.
	public ProductoStockImagen[] mostrarCarrito(int id_usuario);

	// Para crear el carrito del usuario.
	public void crearCarritoUsuario(int id_usuario);

	// Para meter un producto en el carrito.
	public void meterProducto(int id_producto, int id_usuario);

	// Para descontar un producto.
	void descontarProducto(int id_producto, int id_usuario);

	// Par eliminar un producto del carrito.
	public void eliminarProducto(int id_usuario, int id_producto);

	// Para eliminar el carrito de un usuario.
	public void borrarCarritoUsuario(int id_usuario);

	// Para mandar el carrito a facturacion.
	public void mandarAFactura(int id_usuario);

}
