//ProductosDALColeccion.java

package com.ipartek.jonBarnes.DAL;

import java.util.HashMap;
import java.util.Map;

import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * Clase DAL(Date Access Layout), para acceder a un map de productos.
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public class ProductosDALColeccion implements ProductoDALInterface {

	// Map de los productos.
	private Map<String, ProductoStockImagen> productos = new HashMap<String, ProductoStockImagen>();

	/**
	 * Metodo para dar de alta a nuevos productos.
	 */

	public void altaProducto(ProductoStockImagen producto) {

		// A�adimos un producto pero primero miramos que no exista ya.
		if (productos.containsKey(producto.getNombre())) {
			// Lanzamos la exception.
			throw new ProductoYaExistenteDALException("Ya existe el producto " + producto.getNombre());
		} else {
			productos.put(producto.getNombre(), producto);
		}

	}

	/**
	 * Funcion para modificar los datos de un producto.
	 */

	public void modificarProducto(ProductoStockImagen producto) {

		// Para modificar un producto primero tenemos que mirar que este
		// producto exista.
		if (!productos.containsKey(producto.getNombre())) {
			// lanzamos un exception si no lo hay
			throw new DALException(String.format("Error el  producto %s no existe por lo que no se puede modificar.",
					producto.getNombre()));
		} else {
			// Si no lo modificamos. Por metodo de sobreescritura.
			productos.put(producto.getNombre(), producto);
		}

	}

	/**
	 * Funcion para borrar un producto.
	 */

	public void borrarProducto(ProductoStockImagen producto) {
		// Para borrar un producto primero miraremos que este producto existe.
		if (!productos.containsKey(producto.getNombre())) {
			// Si no lo hay, lanzamos una exception.
			throw new DALException(String.format("Error por que el producto %s no existe.", producto.getNombre()));
		} else {
			// Borramos el producto por que existe.
			productos.remove(producto.getNombre());
		}

	}

	/**
	 * Funcion para buscar producto por Id.
	 */

	public ProductoStockImagen buscarProductoPorId(String id) {

		// Utilizamos el metodo get.
		// TODO mirar este metodo bien.
		return productos.get(id);
	}

	/**
	 * Funcion para buscar todos los productos.
	 */

	public ProductoStockImagen[] buscarTodosLosProductos() {
		// Funcion extra�a. Devuelve todo en un Array.
		return productos.values().toArray(new ProductoStockImagen[productos.size()]);
	}

	public boolean validarProducto(ProductoStockImagen producto) {
		// Miramos si el producto existe.
		return productos.containsValue(producto);
	}

}
