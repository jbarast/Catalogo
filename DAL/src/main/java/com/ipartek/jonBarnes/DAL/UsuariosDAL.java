//UsuariosDAL.java

package com.ipartek.jonBarnes.DAL;

import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * La dal para los usuarios.
 * 
 * @author jonBarnes
 * @version 24/05/2017
 *
 */
public interface UsuariosDAL {
	public void alta(Usuario usuario);

	public void modificar(Usuario usuario);

	public void borrar(Usuario usuario);

	public Usuario buscarPorId(String id);

	public Usuario[] buscarTodosLosUsuarios();

	public boolean validar(Usuario usuario);
}
