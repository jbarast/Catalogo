//ProductoDALInterface.java

package com.ipartek.jonBarnes.DAL;

//Mis "clases"
//import com.ipartek.ejercicioTienda.jonBarnes.tipos.Producto;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * Interfaz de la DAL: Date Acces Layout.
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public interface ProductoDALInterface {

	// Metodos que tiene que tener todas las funciones ProductoDAl....

	// public void altaProducto(Producto producto);
	//
	// public void modificarProducto(Producto producto);
	//
	// public void borrarProducto(Producto producto);
	//
	// public Producto buscarProductoPorId(String id);
	//
	// public Producto[] buscarTodosLosProductos();
	//
	// public boolean validarProducto(Producto producto);

	public void altaProducto(ProductoStockImagen producto);

	public void modificarProducto(ProductoStockImagen producto);

	public void borrarProducto(ProductoStockImagen producto);

	public ProductoStockImagen buscarProductoPorId(String id);

	public ProductoStockImagen[] buscarTodosLosProductos();

	public boolean validarProducto(ProductoStockImagen producto);
}
