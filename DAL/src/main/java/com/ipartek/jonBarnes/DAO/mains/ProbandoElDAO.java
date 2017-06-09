//ProbandoElDAo

package com.ipartek.jonBarnes.DAO.mains;

import java.sql.Connection;

import com.ipartek.jonBarnes.DAO.ProductoDAOMySQL;
import com.ipartek.jonBarnes.DAO.UsuarioDAOMySQL;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import com.ipartek.jonBarnes.DAO.interfaces.UsuarioDAO;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * Para probar que el DAO esta bien.
 * 
 * @author jbarast
 * @version 09/06/2017
 *
 */
public class ProbandoElDAO {

	// Creamos la dao.
	public static UsuarioDAO daoUsuarios = null;
	public static ProductoDAO daoProductos = null;
	public static Connection con = null;

	public static void main(String[] args) {

		// Creo el dao.
		daoUsuarios = new UsuarioDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");
		daoProductos = new ProductoDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");

		// Abro la conexion.
		daoProductos.abrirConexion();

		// Hacemos el listado.
		listadoProductos();

		// Creamos un producto.

		ProductoStockImagen productoUpdate = new ProductoStockImagen(5, "aef", "mod", 0200);

		// Metemos el producto.
		// daoProductos.insert(productoInsert);

		daoProductos.delete(productoUpdate);

		listadoProductos();

		// Cerramos la conexion.
		daoProductos.cerrarConexion();
	}

	// Metodos de listado.
	private static void listado() {
		System.out.println("\nLISTADO\n=======");

		for (Usuario u : daoUsuarios.findAll())
			System.out.println(u);

		System.out.println();
	}

	private static void listado(UsuarioDAO dao) {

		// Titulo para cuando aparezca.
		System.out.println("=======");
		System.out.println("LISTADO");
		System.out.println("=======");
		// Muestro las datos de la tabla de usurios.
		for (Usuario u : dao.findAll()) {
			System.out.println(u);
		}
	}

	private static void listadoProductos() {
		System.out.println("\nLISTADO\n=======");

		for (ProductoStockImagen u : daoProductos.findAll())
			System.out.println(u);

		System.out.println();
	}

	private static void listadoProductos(ProductoDAO dao) {

		// Titulo para cuando aparezca.
		System.out.println("=======");
		System.out.println("LISTADO");
		System.out.println("=======");
		// Muestro las datos de la tabla de usurios.
		for (ProductoStockImagen u : dao.findAll()) {
			System.out.println(u);
		}
	}

}
