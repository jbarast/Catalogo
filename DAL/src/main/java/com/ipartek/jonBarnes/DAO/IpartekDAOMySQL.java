package com.ipartek.jonBarnes.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ipartek.jonBarnes.DAO.interfaces.IpartekDAO;

/**
 * 
 * Clase para la "creacion" de los metodos de abrir y cerrar conexiones con la
 * base de datos.
 * 
 * @author jbarast
 * @version 08/06/2017
 *
 */
public class IpartekDAOMySQL implements IpartekDAO {

	// atributos.

	protected Connection con; // Compartirla por las hijas.
	private String url = "jdbc:mysql://localhost/ipartek";

	private String mysqlUser = "root";
	private String mysqlpassword = "";

	// Constructores.
	public IpartekDAOMySQL() {

		// Instanciamos el driver de MySQL.
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Constructores.
	public IpartekDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		this();
		this.url = url;
		this.mysqlUser = mysqlUser;
		this.mysqlpassword = mysqlPass;

	}

	// Getters y Setters.

	// Metodos que tenemos que implementar.
	/**
	 * Metodo para establecer conexion para la base de datos.
	 */
	public void abrirConexion() {

		try {

			// La conexion.
			con = DriverManager.getConnection(url, mysqlUser, mysqlpassword);

			// Vemos por pantalla que se a hecho la conexion.
			System.out.println("Conexion con la base de datos realizada.");

		} catch (SQLException e) {
			throw new DAOException("Error de conexion a la base de datos.", e);

		} catch (Exception e) {
			throw new DAOException("Error no esperado", e);
		}

	}

	/**
	 * Cerramos la conexion a la base de datos.
	 */
	public void cerrarConexion() {

		try {
			// Cerramos la conexion.
			if (con != null && !con.isClosed()) {
				con.close();

			}
			con = null; // Le decimos a la aplicacion que no lo necesitamos.

			// Mostramos por pantalla que hemos cerrado las conexiones.
			System.out.println("Conexion con la base de datos cerrada.");
		} catch (SQLException e) {
			throw new DAOException("Error de cierre de conexion a la base de datos.", e);

		} catch (Exception e) {
			throw new DAOException("Error no esperado cerrando la conexiones de la base de datos", e);
		}

	}

	/**
	 * Metodo para inicializar la transaccion.
	 */
	public void iniciarTransaccion() {

		try {
			con.setAutoCommit(false);
			System.out.println("Transaccion Iniciad");
		} catch (SQLException e) {

			throw new DAOException("Error al desactivar AutoCommit", e);
		}

	}

	/**
	 * Metodo para confirmarTransaccion.
	 */
	public void confirmarTransaccion() {

		try {

			// Confirmamos la transaccion.
			con.commit();

			System.out.println("Transaccion confirmada.");

			// Para volver la conexion al metodo normal.
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException("Error al confirmar transaccion", e);
		}

	}

	/**
	 * Metodo para desahacer la transaccion.
	 */
	public void deshacerTransaccion() {

		try {
			// La deshacemos.
			con.rollback();

			System.out.println("DeshacerTransaccion");

			// Para volver la conexion al metodo normal.
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException("Error al desahcer la transaccion.", e);
		}

	}

}