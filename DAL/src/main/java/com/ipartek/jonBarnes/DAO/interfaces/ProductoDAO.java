//ProductoDAO.java

package com.ipartek.jonBarnes.DAO.interfaces;

import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * Interfaz de las funciones que tiene que tener producto.
 * 
 * @author jonBarnes
 * @version 09/06/2017
 *
 */
public interface ProductoDAO extends IpartekDAO {

	// Los metodos necesarios.

	public ProductoStockImagen[] findAll(); // Sacar todos los usuarios.

	public ProductoStockImagen findById(int id); // Buscar usuario por id.

	public ProductoStockImagen findbyUsername(String nombre); // Buscar producto
																// por nombre
																// producto.

	public int insert(ProductoStockImagen producto); // Insertar un usuario.

	public void update(ProductoStockImagen producto); // Para modificar los
														// datos de un
	// usuario.

	public void delete(ProductoStockImagen producto); // Para borrar un usuario
														// dando como
	// dato un usuario.

	public void delete(int id); // Para borrar un usuario dando como dato un id.

}
