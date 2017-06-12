//ProductoDAOMySQL.java

package com.ipartek.jonBarnes.DAO;

//import para sql. Mirar que sean las estandar.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * DAO para la tabla de usuarios.
 * 
 * 
 * @author jonBarnes
 * @version 09/06/2017
 *
 */
public class ProductoDAOMySQL extends IpartekDAOMySQL implements ProductoDAO {

	// atributos.

	// Sentencias sql constanetes.

	private final static String FIND_ALL = "SELECT*FROM productos";
	private final static String FIND_BY_ID = "SELECT*FROM productos WHERE id=?";
	private final static String FIND_BY_NOMBRE = "SELECT*FROM productos WHERE nombre=?";
	private final static String INSERT = "INSERT INTO productos(nombre,descripcion,precio,stock,imagen) VALUES (?,?,?,?,?)";
	private final static String UPDATE = "UPDATE productos SET nombre=?,descripcion=?, precio=?, stock=?, imagen=? WHERE id=?";
	private final static String DELETE = "DELETE FROM productos WHERE id = ?";

	// Preparamos los atributos de sentencias.

	private PreparedStatement psFindAll;
	private PreparedStatement psFindByID;
	private PreparedStatement psFindByUsername;
	private PreparedStatement psInsert;
	private PreparedStatement psUpdate;
	private PreparedStatement psDelete;

	// Constructor.

	public ProductoDAOMySQL() {

		super();

	}

	public ProductoDAOMySQL(String url, String mysqlUser, String mysqlPass) {

		super(url, mysqlUser, mysqlPass);

	}

	// Metodos de la Interfaz.

	/**
	 * Buscar todos los usuarios de una tabla.
	 */
	public ProductoStockImagen[] findAll() {

		// Creamos la ArrayList.
		ArrayList<ProductoStockImagen> productos = new ArrayList<ProductoStockImagen>();
		ResultSet rs = null;

		try {

			psFindAll = con.prepareStatement(FIND_ALL);
			// Lee la tabla.
			// ""rs es el valor de la tabla??""
			rs = psFindAll.executeQuery();

			// Creamos la clase de usuario.
			ProductoStockImagen producto;

			// Con el resultado, pasamos a recorrer la tabla.
			while (rs.next()) {

				// System.out.println(rs.getString("username"));
				// Creamos el objeto de usuario.
				producto = new ProductoStockImagen();
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setRutaImagen("Imagen");

				// Añadimos al array el usuario creado.
				productos.add(producto);

			}
		} catch (SQLException e) {

			throw new DAOException("Error en findAll", e);
		} finally {

			cerrar(psFindAll, rs);
		}

		return productos.toArray(new ProductoStockImagen[productos.size()]);
	}

	/**
	 * Buscar un usuario por id en una tabla.
	 */
	public ProductoStockImagen findById(int id) {

		// Creamos el usuario.
		ProductoStockImagen producto = null;

		ResultSet rs = null;

		try {

			psFindByID = con.prepareStatement(FIND_BY_ID);

			// Metemos el id en la sentencia-
			psFindByID.setInt(1, id);

			rs = psFindByID.executeQuery();

			// Cogemos el dato.
			if (rs.next()) {

				// Metemos los datos recogidos.
				producto = new ProductoStockImagen();
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setRutaImagen("Imagen");
			}
		} catch (SQLException e) {

			throw new DAOException("Error en FindByID", e);

		} finally {

			cerrar(psFindByID, rs);
		}

		return producto;
	}

	/**
	 * Buscar producto por nombre de usuario.
	 */
	public ProductoStockImagen findbyUsername(String nombre) {

		// Creamos el usuario.
		ProductoStockImagen productoBD = null;

		ResultSet rs = null;

		try {

			System.out.println(nombre);

			abrirConexion();

			System.out.println(con);

			psFindByUsername = con.prepareStatement(FIND_BY_NOMBRE);

			System.out.println("Hola");

			// Metemos el id en la sentencia-
			psFindByUsername.setString(1, nombre);

			System.out.println(psFindByUsername);

			rs = psFindByUsername.executeQuery();

			// Cogemos el dato.
			if (rs.next()) {

				// Creamos el objeto de usuario.
				productoBD = new ProductoStockImagen();

				// Metemos los datos recogidos.
				productoBD.setId(rs.getInt("id"));
				productoBD.setNombre(rs.getString("nombre"));
				productoBD.setDescripcion(rs.getString("descripcion"));
				productoBD.setPrecio(rs.getDouble("precio"));
				productoBD.setStock(rs.getInt("precio"));
				productoBD.setRutaImagen(rs.getString("Imagen"));

				System.out.println("producto encontrado: " + productoBD); // TODO
																			// borrarlo
																			// despues.
			}
		} catch (SQLException e) {

			throw new DAOException("Error en FindByUsername", e);

		} finally {

			cerrar(psFindByID, rs);
		}

		return productoBD;

	}

	/**
	 * Metodo para insertar un usuario.
	 */
	public int insert(ProductoStockImagen producto) {

		ResultSet generatedKeys = null;

		try {

			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, producto.getNombre());
			psInsert.setString(2, producto.getDescripcion());
			psInsert.setDouble(3, producto.getPrecio());
			psInsert.setInt(4, producto.getStock());
			psInsert.setString(5, producto.getRutaImagen());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor " + res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (SQLException e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}
	}

	/**
	 * Cambiamos las parametros de una fila de base.
	 */
	public void update(ProductoStockImagen producto) {

		// Son 4 parametros

		try {
			// Primero metemos los datos en el commando.
			psUpdate = con.prepareStatement(UPDATE);

			// Datos que queremos cambiar.
			psUpdate.setString(1, producto.getNombre());
			psUpdate.setString(2, producto.getDescripcion());
			psUpdate.setDouble(3, producto.getPrecio());
			psUpdate.setInt(4, producto.getStock());
			psUpdate.setString(5, producto.getRutaImagen());

			// Id del que quiero cambiar.
			psUpdate.setInt(6, producto.getId());

			// Miramos que hace el comando.
			System.out.println(psUpdate);

			// Hacemos el comando.
			int res = psUpdate.executeUpdate();

			if (res != 1) {
				throw new DAOException(String.format(
						"Cuando solo deberia haber cambiado un Registro se han cambiado %d, con id: %d", res,
						producto.getId()));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			cerrar(psUpdate);
		}

	}

	/**
	 * Para borrar usuarios a partir de usuario.
	 */
	public void delete(ProductoStockImagen producto) {

		try {

			psDelete = con.prepareStatement(DELETE);

			// Metemos los datos del usuario en la sentencia.
			psDelete.setInt(1, producto.getId());

			// Hacemos el comando SQL.
			int res = psDelete.executeUpdate();

			// Si el resultado es diferente a uno. Hay un problema.
			if (res != 1) {
				throw new DAOException(String.format(
						"Cuando solo deberia haber cambiado un Registro se han cambiado %d", res));
			}

		} catch (SQLException e) {

			throw new DAOException("Error en DELETE.", e);
		} finally {
			cerrar(psDelete);
		}

	}

	/**
	 * Para borrar usuarios a partid de id.
	 */
	public void delete(int id) {
		try {

			psDelete = con.prepareStatement(DELETE);

			// Metemos los datos del usuario en la sentencia.
			psDelete.setInt(1, id);

			// Miramos que hace el comando.
			System.out.println(psDelete);

			// Hacemos el comando SQL.
			int res = psDelete.executeUpdate();

			// Si el resultado es diferente a uno. Hay un problema.
			if (res != 1) {
				throw new DAOException(String.format(
						"Cuando solo deberia haber cambiado un Registro se han cambiado %d", res));
			}

		} catch (SQLException e) {

			throw new DAOException("Error en DELETE.", e);
		} finally {
			cerrar(psDelete);
		}

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