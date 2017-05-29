//UsuariosDALColeccion.java

package com.ipartek.jonBarnes.DAL;

import java.util.HashMap;
import java.util.Map;

import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * La DAL para la coleccion de usuarios.
 * 
 * @author jonBarnes
 * @version 24/05/2017
 *
 */
public class UsuariosDALColeccion implements UsuariosDAL {

	// Creamos la coleccion de usuarios.
	private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();

	// Los metodos para la DALColeccion

	/**
	 * Da de alta a un usuario.
	 */
	public void alta(Usuario usuario) {
		if (usuarios.containsKey(usuario.getNombre()))
			throw new UsuarioYaExistenteDALException("Ya existe el usuario " + usuario.getNombre());

		usuarios.put(usuario.getNombre(), usuario);
	}

	/**
	 * Valida el usuario.
	 */
	public boolean validar(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}

	/**
	 * Modifica al usuario.
	 */
	public void modificar(Usuario usuario) {
		if (!usuarios.containsKey(usuario.getNombre()))
			throw new DALException("Intento de modificar usuario no existente " + usuario);

		usuarios.put(usuario.getNombre(), usuario);
	}

	/**
	 * Borra el usuario.
	 */
	public void borrar(Usuario usuario) {
		usuarios.remove(usuario.getNombre());
	}

	/**
	 * Busca por ID al usuario.
	 */
	public Usuario buscarPorId(String id) {
		return usuarios.get(id);
	}

	/**
	 * Nos devuelve todos los usuarios.
	 */
	public Usuario[] buscarTodosLosUsuarios() {
		// Usuario[] arr = new Usuario[usuarios.size()];
		//
		// int i = 0;
		//
		// for(Usuario usuario : usuarios.values())
		// arr[i++] = usuario;
		//
		// return arr;

		return usuarios.values().toArray(new Usuario[usuarios.size()]);
	}

}
