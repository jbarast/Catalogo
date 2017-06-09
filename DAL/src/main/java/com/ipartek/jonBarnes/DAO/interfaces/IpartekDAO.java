//IpertekDAO.java

package com.ipartek.jonBarnes.DAO.interfaces;

/**
 * 
 * Interfaz para lo conexion y desconexion.
 * 
 * @author jbarast
 * @version 08/06/2017
 *
 */
public interface IpartekDAO {

	/**
	 * Funcion para conectarse con la base de datos SQL.
	 */
	public void abrirConexion();

	/**
	 * Funcion para cerrar la conexion con la base de datos SQL.
	 */
	public void cerrarConexion();

	/**
	 * Para inicializar la transaccion.
	 */
	public void iniciarTransaccion();

	/**
	 * Para la confirmacion de la transaccion.
	 */
	public void confirmarTransaccion();

	/**
	 * Para deshacerTransaccion.
	 */
	public void deshacerTransaccion();

}