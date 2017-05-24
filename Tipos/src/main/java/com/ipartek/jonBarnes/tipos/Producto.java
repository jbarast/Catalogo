/**
 * 
 */
package com.ipartek.jonBarnes.tipos;

/**
 * 
 * Clase Producto necesaria para manejar productos. Esta compuesta por los
 * siguientes atributos:
 * <ul>
 * <li>ID</li>
 * <li>Nombre</li>
 * <li>Descripcion</li>
 * <li>Precio</li>
 * </ul>
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public class Producto {

	// Atributos.

	private String id;
	private String nombre;
	private String descripcion;
	private String precio; // TODO mirar bien este tipo de dato.
	// Vamos a meter tambien los errores.
	private String errores;

	// Constructores.

	public Producto(String id, String nombre, String descripcion, String precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio + "�";
	}

	public Producto() {
		super();
		this.id = "No id";
		this.nombre = "Producto Desconocido";
		this.descripcion = "No hay descripcion";
		this.precio = "0�";
	}

	// Getters y setters.

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio + "�";
	}

	// getter y setter para los errores.
	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	// Otros metodos.
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (errores == null) {
			if (other.errores != null)
				return false;
		} else if (!errores.equals(other.errores))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}

}
