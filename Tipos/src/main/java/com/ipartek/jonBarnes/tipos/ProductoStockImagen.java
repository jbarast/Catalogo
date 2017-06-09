//ProductoStockImagen.java

package com.ipartek.jonBarnes.tipos;

/**
 * 
 * Clase hija de Producto, para implementar el stock y la ruta de imagen.
 * 
 * @author jonBarnes
 * @version 11/05/2017
 *
 */
public class ProductoStockImagen extends Producto {

	// Atributos nuevos.
	private int stock;
	private String rutaImagen;

	// Constructor.
	public ProductoStockImagen(int id, String nombre, String descripcion, double precio, int stock, String rutaImagen) {
		super(id, nombre, descripcion, precio);
		this.stock = stock;

		if (rutaImagen == null || rutaImagen == "") {
			this.rutaImagen = "http://localhost:8080/img/sinfoto.jpg";
			// this.rutaImagen =
			// "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJNbqspyYdsDdipH8XWIm5QzxA58g6TBaALNZt3i0pEB4ZexDbnVvYEPY";
		} else {
			this.rutaImagen = rutaImagen;
		}
	}

	public ProductoStockImagen() {
		super();
		this.setPrecio(0.00);
		this.stock = 0;
		this.rutaImagen = "img/sinfoto.jpg";
	}

	public ProductoStockImagen(int id, String nombre, String descripcion, double precio) {
		super(id, nombre, descripcion, precio);

		this.stock = 0;
		this.rutaImagen = "img/sinfoto.jpg";
	}

	// Getters y setters.

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	// Otras funciones.

	@Override
	public String toString() {
		return "ProductoStockImagen [stock=" + stock + ", rutaImagen=" + rutaImagen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rutaImagen == null) ? 0 : rutaImagen.hashCode());
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoStockImagen other = (ProductoStockImagen) obj;
		if (rutaImagen == null) {
			if (other.rutaImagen != null)
				return false;
		} else if (!rutaImagen.equals(other.rutaImagen))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

}
