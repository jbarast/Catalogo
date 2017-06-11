package com.ipartek.jonBarnes.DAO;

//import para sql. Mirar que sean las estandar.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.jonBarnes.DAO.interfaces.UsuarioDAO;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * DAO para la tabla de usuarios.
 * 
 * 
 * @author jbarast
 * @version 07/06/2017
 *
 */
public class UsuarioDAOMySQL extends IpartekDAOMySQL implements UsuarioDAO {

	// atributos.

	// Sentencias sql constanetes.

	private final static String FIND_ALL = "SELECT*FROM usuarios";
	private final static String FIND_BY_ID = "SELECT*FROM usuarios WHERE id=?";
	private final static String FIND_BY_USERNAME = "SELECT*FROM usuarios WHERE username=?";
	private final static String INSERT = "INSERT INTO usuarios(username,password,nombre_completo,id_roles) VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE usuarios SET username=?,password=?, nombre_completo=?, id_roles=? WHERE id=?";
	private final static String DELETE = "DELETE FROM usuarios WHERE id = ?";

	// Preparamos los atributos de sentencias.

	private PreparedStatement psFindAll;
	private PreparedStatement psFindByID;
	private PreparedStatement psFindByUsername;
	private PreparedStatement psInsert;
	private PreparedStatement psUpdate;
	private PreparedStatement psDelete;

	// Constructor.

	public UsuarioDAOMySQL() {

		super();

	}

	public UsuarioDAOMySQL(String url, String mysqlUser, String mysqlPass) {

		super(url, mysqlUser, mysqlPass);

	}

	// Metodos de la Interfaz.

	/**
	 * Buscar todos los usuarios de una tabla.
	 */
	public Usuario[] findAll() {

		// Creamos la ArrayList.
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = null;

		try {

			psFindAll = con.prepareStatement(FIND_ALL);
			// Lee la tabla.
			// ""rs es el valor de la tabla??""
			rs = psFindAll.executeQuery();

			// Creamos la clase de usuario.
			Usuario usuario;

			// Con el resultado, pasamos a recorrer la tabla.
			while (rs.next()) {

				// System.out.println(rs.getString("username"));
				// Creamos el objeto de usuario.
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));

				// Añadimos al array el usuario creado.
				usuarios.add(usuario);

			}
		} catch (SQLException e) {

			throw new DAOException("Error en findAll", e);
		} finally {

			cerrar(psFindAll, rs);
		}

		return usuarios.toArray(new Usuario[usuarios.size()]);
	}

	/**
	 * Buscar un usuario por id en una tabla.
	 */
	public Usuario findById(int id) {

		// Creamos el usuario.
		Usuario usuario = null;

		ResultSet rs = null;

		try {

			psFindByID = con.prepareStatement(FIND_BY_ID);

			// Metemos el id en la sentencia-
			psFindByID.setInt(1, id);

			rs = psFindByID.executeQuery();

			// Cogemos el dato.
			if (rs.next()) {

				// Creamos el objeto de usuario.
				usuario = new Usuario();

				// Metemos los datos recogidos.
				usuario.setId(rs.getInt("id"));
				usuario.setId_roles(rs.getInt("id_roles"));
				usuario.setNombre_completo(rs.getString("nombre_completo"));
				usuario.setPassword(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {

			throw new DAOException("Error en FindByID", e);

		} finally {

			cerrar(psFindByID, rs);
		}

		return usuario;
	}


	/**
	 * Para buscar un usuario apartir del username.
	 * @param username
	 * @return El usuario.
	 */
	public Usuario findbyUsername(String username){

		// Creamos el usuario.
		Usuario usuarioBD = null;

		ResultSet rs = null;



		try {

			System.out.println(username);

			System.out.println(psFindByUsername);
			System.out.println(FIND_BY_USERNAME);
			System.out.println(con);

			abrirConexion();

			System.out.println(con);




			psFindByUsername = con.prepareStatement(FIND_BY_USERNAME);

			System.out.println("Hola");

			// Metemos el id en la sentencia-
			psFindByUsername.setString(1, username);

			System.out.println(psFindByUsername);

			rs = psFindByUsername.executeQuery();

			// Cogemos el dato.
			if (rs.next()) {

				// Creamos el objeto de usuario.
				usuarioBD = new Usuario();

				// Metemos los datos recogidos.
				usuarioBD.setId(rs.getInt("id"));
				usuarioBD.setId_roles(rs.getInt("id_roles"));
				usuarioBD.setNombre_completo(rs.getString("nombre_completo"));
				usuarioBD.setPassword(rs.getString("password"));
				usuarioBD.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {

			throw new DAOException("Error en FindByUsername", e);

		} finally {

			cerrar(psFindByID, rs);
		}

		return usuarioBD;

	}

	/**
	 * Metodo para insertar un usuario.
	 */
	public int insert(Usuario usuario) {

		ResultSet generatedKeys = null;

		try {

			psInsert = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, usuario.getUsername());
			psInsert.setString(2, usuario.getPassword());
			psInsert.setString(3, usuario.getNombre_completo());
			psInsert.setInt(4, usuario.getId_roles());

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
	public void update(Usuario usuario) {

		// Son 4 parametros

		try {
			// Primero metemos los datos en el commando.
			psUpdate = con.prepareStatement(UPDATE);

			// Datos que queremos cambiar.
			psUpdate.setString(1, usuario.getUsername());
			psUpdate.setString(2, usuario.getPassword());
			psUpdate.setString(3, usuario.getNombre_completo());
			psUpdate.setInt(4, usuario.getId_roles());

			// Id del que quiero cambiar.
			psUpdate.setInt(5, usuario.getId());

			// Hacemos el comando.
			int res = psUpdate.executeUpdate();

			if (res != 1) {
				throw new DAOException(String.format(
						"Cuando solo deberia haber cambiado un Registro se han cambiado %d", res));

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
	public void delete(Usuario usuario) {

		try {

			psDelete = con.prepareStatement(DELETE);

			// Metemos los datos del usuario en la sentencia.
			psDelete.setInt(1, usuario.getId());

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

	public boolean validar(Usuario usuario) {

		// variables necesarios.
		boolean usuarioValido = false;
		Usuario usuarioBD = null;

		System.out.println(usuario);

		// Sacamos el usuario de la base de datos si lo saca.

     if(usuario.getUsername() != null) {



		 if(this.findbyUsername((usuario.getUsername()))!=null){

			 usuarioBD = this.findbyUsername(usuario.getUsername());

		 if (usuarioBD.getUsername() != null) {

			 //Sacamos el username y la contraseña.
			 String contraseñaBD = usuarioBD.getPassword();
			 String usernameBD = usuarioBD.getUsername();



			 // Miramos si son iguales.
			 if (contraseñaBD.equals(usuario.getPassword()) && usernameBD.equals(usuario.getUsername())) {
				 usuarioValido = true;
			 }
		 }
	 }}
		// True si existe false si no.

		return usuarioValido;

	}

}