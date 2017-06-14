//AppDAOCarrito.java

package com.ipartek.jonBarnes.DAO.mains;

import com.ipartek.jonBarnes.DAO.CarritoDAOMySQL;
import com.ipartek.jonBarnes.DAO.interfaces.CarritoDAO;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * Main para probar el funcionamiento de la DAO del carrito.
 * 
 * @author jonBarnes
 * @version 13/06/2017
 *
 */
public class AppDAOCarrito {

	// Creamos la dao.
	// public static UsuarioDAO daoUsuarios = null;
	// public static ProductoDAO daoProductos = null;
	public static CarritoDAO daoCarrito = null;

	public static void main(String[] args) {

		// Creo el dao.
		// daoUsuarios = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek",
		// "root", "");
		// daoProductos = new ProductoDAOMySQL("jdbc:mysql://localhost/ipartek",
		// "root", "");
		daoCarrito = new CarritoDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");

		daoCarrito.abrirConexion();

		daoCarrito.meterProducto(8, 5);

		listadoCarrito(daoCarrito, 5);
		daoCarrito.cerrarConexion();

	}

	private static void listadoCarrito(int id_usuario) {
		System.out.println("\nLISTADO\n=======");
		System.out.println(id_usuario);

		for (ProductoStockImagen u : daoCarrito.mostrarCarrito(id_usuario))
			System.out.println(u);

		System.out.println();
	}

	private static void listadoCarrito(CarritoDAO dao, int id_usuario) {

		// Titulo para cuando aparezca.
		System.out.println("=======");
		System.out.println("LISTADO");
		System.out.println("=======");
		// Muestro las datos de la tabla de usurios.
		for (ProductoStockImagen u : dao.mostrarCarrito(id_usuario)) {
			System.out.println(u);
		}
	}

}
