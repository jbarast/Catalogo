//Usuario.java

package com.ipartek.jonBarnes.tipos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Clase usuario. Para el ejemplo de JDBC.
 * 
 * @author jbarast
 * @version 07/06/2017
 *
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

	// atributos.
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "id_roles")
	private int id_roles;

	@Column(name = "nombre_completo")
	private String nombre_completo;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	private String errores;

	// Constructores.

	// //Constructor completo.
	public Usuario(int id, int id_roles, String nombre_completo, String password, String username) {
		super();
		this.id = id;
		this.id_roles = id_roles;
		this.nombre_completo = nombre_completo;
		this.password = password;
		this.username = username;
	}

	// //Constructo vacio.
	public Usuario() {
		super();

	}

	// getters y setters.

	// //Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// // Id_roles
	public int getId_roles() {
		return id_roles;
	}

	public void setId_roles(int id_roles) {
		this.id_roles = id_roles;
	}

	// // Nombre_completo
	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	// // password
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// // username
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// // Errores
	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	// otros metodos.

	// // toString
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", id_roles=" + id_roles + ", nombre_completo=" + nombre_completo + ", password="
				+ password + ", username=" + username + "]";
	}

	// //hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + id_roles;
		result = prime * result + ((nombre_completo == null) ? 0 : nombre_completo.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	// //equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (id_roles != other.id_roles)
			return false;
		if (nombre_completo == null) {
			if (other.nombre_completo != null)
				return false;
		} else if (!nombre_completo.equals(other.nombre_completo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
