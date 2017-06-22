//Factura.java

package com.ipartek.jonBarnes.tipos;

import java.util.Date;

/**
 * 
 * Clase para manejar facturas, como objetos.
 * 
 * @author jonBarnes
 * @version 22/06/2017
 *
 */
public class Factura {

	// atributos.
	private int numeroFactura;
	private Date fechaFactura;

	// Constructores.
	public Factura(int numeroFactura, Date fechaFactura) {
		super();
		this.numeroFactura = numeroFactura;
		this.fechaFactura = fechaFactura;
	}

	public Factura() {

	}

	// Getters y setters.

	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	// otros metodos.
	/**
	 * Convierte en un string al objeto.
	 */
	@Override
	public String toString() {
		return "Factura [numeroFactura=" + numeroFactura + ", fechaFactura=" + fechaFactura + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaFactura == null) ? 0 : fechaFactura.hashCode());
		result = prime * result + numeroFactura;
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
		Factura other = (Factura) obj;
		if (fechaFactura == null) {
			if (other.fechaFactura != null)
				return false;
		} else if (!fechaFactura.equals(other.fechaFactura))
			return false;
		if (numeroFactura != other.numeroFactura)
			return false;
		return true;
	}

}
