//CarritoDAOMySQL.java

package com.ipartek.jonBarnes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.jonBarnes.DAO.interfaces.CarritoDAO;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * DAO para el carrito.
 * 
 * @author JAVA
 * @version 13/06/2017
 *
 */
public class CarritoDAOMySQL extends ProductoDAOMySQL implements CarritoDAO {

	// atributos.

	// Sentencias SQL.
	private final static String MOSTRAR_CARRITO = "SELECT productos.id,productos.nombre,productos.descripcion,productos.precio,carrito_productos.cantidad,productos.Imagen FROM productos,carrito,carrito_productos WHERE carrito_productos.id_productos = productos.id AND carrito.id_usuario = carrito_productos.id_carrito AND carrito.id_usuario=?";
	private final static String NUEVO_CARRITO = "INSERT IGNORE INTO carrito set id_usuario = ?,numero_carrito=?, fecha=?";
	private final static String AUMENTAR_UN_PRODUCTO = "UPDATE IGNORE carrito_productos SET cantidad=cantidad+1 WHERE id_productos=? AND id_carrito=(SELECT numero_carrito FROM carrito WHERE id_usuario=?)";
	private final static String DESCONTAR_PRODUCTO = "UPDATE IGNORE carrito_productos SET cantidad=cantidad+1 WHERE id_productos=? AND id_carrito=(SELECT numero_carrito FROM carrito WHERE id_usuario=?)";
	private final static String INSERTAR_PRODUCTO = "INSERT IGNORE INTO carrito_productos SET cantidad='1', id_productos=?, id_carrito=(SELECT numero_carrito FROM carrito WHERE id_usuario=?);";
	private final static String ELIMINAR_PRODUCTO = "DELETE FROM carrito_productos WHERRE id_carrito=? AND id_productos=?";
	private final static String BORRAR_CARRITO = "DELETE FROM carrito WHERE id_usuario=?";
	private final static String MANDAR_A_FACTURA = "NO SE COMO HACERLO"; // TODO
																			// Terminar

	// Preparamos los atributos de sentencias.
	private PreparedStatement psMostrarCarrito;
	private PreparedStatement psNuevoCarrito;
	private PreparedStatement psAumentarUnProducto;
	private PreparedStatement psDesconectarProducto;
	private PreparedStatement psMeterProducto;
	private PreparedStatement psBorrarCarrito;
	private PreparedStatement psMandarAFactura;

	// Constructor.
	public CarritoDAOMySQL() {

		super();
	}

	public CarritoDAOMySQL(String url, String mysqlUser, String mysqlPass) {

		super(url, mysqlUser, mysqlPass);

	}

	// Otros metodos.

	@Override
	public ProductoStockImagen[] mostrarCarrito(int id_usuario) {

		// Creamos la ArrayList.
		ArrayList<ProductoStockImagen> productosCarrito = new ArrayList<ProductoStockImagen>();
		ResultSet rs = null;

		System.out.println("estoy aqui");
		System.out.println(id_usuario);
		// La operacion que hacemos.
		try {

			// Preparamos la sentencia SQL.
			psMostrarCarrito = con.prepareStatement(MOSTRAR_CARRITO);

			psMostrarCarrito.setInt(1, id_usuario);

			// Ejecutamos la sentencia SQL.
			rs = psMostrarCarrito.executeQuery();

			// Creamos la clase producto.
			ProductoStockImagen producto;

			while (rs.next()) {

				producto = new ProductoStockImagen();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("cantidad"));
				producto.setRutaImagen(rs.getString("Imagen"));

				// Añadimos el producto al array.
				productosCarrito.add(producto);

			}
		} catch (SQLException e) {
			throw new DAOException("Error en mostrar productos del carrito", e);
		} finally {
			// Cerramos.
			cerrar(psMostrarCarrito, rs);
		}

		// Lo que devolvemos.
		return productosCarrito.toArray(new ProductoStockImagen[productosCarrito.size()]);

	}

	/**
	 * Para crear el carrito del usuario.
	 */
	@Override
	public void crearCarritoUsuario(int id_usuario) {

		// atributos necesarios.
		// La fecha.
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(utilDate.getTime());

		// Creamos el carrito de usuario.

		try {
			psNuevoCarrito = con.prepareStatement(NUEVO_CARRITO);

			// Metemos los datos al comando SQL.
			psNuevoCarrito.setInt(1, id_usuario);
			System.out.println(psNuevoCarrito);
			psNuevoCarrito.setInt(2, id_usuario);
			System.out.println(psNuevoCarrito);

			psNuevoCarrito.setDate(3, fecha);
			System.out.println(psNuevoCarrito);

			// Ejecutamos la sentencia SQL.
			psNuevoCarrito.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Para meter un producto al carrito.
	 */
	@Override
	public void meterProducto(int id_producto, int id_usuario) {

		try {
			psMeterProducto = con.prepareStatement(INSERTAR_PRODUCTO);

			// Metemos los parametros.
			psMeterProducto.setInt(1, id_producto);
			psMeterProducto.setInt(2, id_usuario);

			// Miramos lo de escribir.
			System.out.println(psMeterProducto);

			// Primero miramos/creamos el carrito.
			crearCarritoUsuario(id_usuario);

			// Hacemos la sentencia SQL.
			psMeterProducto.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Para decrementar en uno la cantidad de un producto del carrito.
	 */
	@Override
	public void descontarProducto(int id_producto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Elimina un producto del carrito
	 */
	@Override
	public void eliminarProducto(int id_producto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Borra el carrito del usuario.
	 */
	@Override
	public void borrarCarritoUsuario(int id_usuario) {
		// TODO Auto-generated method stub

	}

	/**
	 * Manda la informacion del carrito al navagedor.
	 */
	@Override
	public void mandarAFactura(int id_usuario) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo para cerrar codigo.
	 */
	private void cerrar(PreparedStatement ps) {
		cerrar(ps, null);
	}

	/**
	 * Metodo para cerrar recursos.
	 * 
	 * @param rs
	 */
	private void cerrar(PreparedStatement ps, ResultSet rs) {
		// Cerramos todos los recursos que hemos utilizado.
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}

		} catch (SQLException e) {
			throw new DAOException("Error al cerrar recursos", e);
		} catch (Exception e) {
			throw new DAOException("Error no esperado al cerrar recursos", e);

		}
	}

}
