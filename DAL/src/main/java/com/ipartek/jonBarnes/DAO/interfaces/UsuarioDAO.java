//UsuarioDAO.java

package com.ipartek.jonBarnes.DAO.interfaces;

import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Interfaz basica para la interfaz DAO para la clase Usuario.
 * 
 * @author jbarast
 * @version 07/06/2017
 *
 */
public interface UsuarioDAO extends IpartekDAO {

	// Los metodos necesarios.

	public Usuario[] findAll(); // Sacar todos los usuarios.

	public Usuario findById(int id); // Buscar usuario por id.

	public Usuario findbyUsername(String username); // Buscar usuario por
													// Username.

	public int insert(Usuario usuario); // Insertar un usuario.

	public void update(Usuario usuario); // Para modificar los datos de un
											// usuario.

	public void delete(Usuario usuario); // Para borrar un usuario dando como
											// dato un usuario.

	public void delete(int id); // Para borrar un usuario dando como dato un id.

	public boolean validar(Usuario usuario); // Para validar los usuarios.

}