package com.ipartek.jonBarnes.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.jonBarnes.DAO.interfaces.FacturasDAO;
import com.ipartek.jonBarnes.tipos.Factura;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

public class FacturaDAOMySQL extends IpartekDAOMySQL implements FacturasDAO {

	// Constantes sentencias SQL.
	// TODO la primera sentencia de SQL esta sin terminar, solo esta la
	// "plantilla" que vamos a utilizar
	private final static String MOSTRAR_PRODUCTOS_FACTURA = "SELECT productos.id,	productos.nombre, productos.descripcion, productos.precio, carrito_productos.cantidad, productos.Imagen  FROM  	productos,carrito,carrito_productos WHERE carrito.id= (SELECT id FROM carrito WHERE id_usuario=?) AND carrito_productos.id_carrito=(SELECT id FROM carrito WHERE id_usuario=?) AND productos.id = carrito_productos.id_productos";
	private final static String MOSTRAR_LISTA_FACTURAS_USUARIO = "SELECT * FROM facturas WHERE id_usuarios=?";

	// Declaraion de las preparedStatement.
	private PreparedStatement psProductosFactura;
	private PreparedStatement psFacturasUsuario;

	// Constructor.
	public FacturaDAOMySQL() {

		super();
	}

	public FacturaDAOMySQL(String url, String mysqlUser, String mysqlPass) {

		super(url, mysqlUser, mysqlPass);

	}

	@Override
	public ProductoStockImagen[] mostrarFactura(int id_usuario, int id_factura) {
		// TODO falta meter este dato.
		return null;
	}

	@Override
	public Factura[] mostrarListaFacturas(int id_usuario) {

		// Syso
		System.out.println("Estoy en la funcion mostrarListaFacturas: ");

		// Creamos la ArrayList.
		ArrayList<Factura> facturasUsuario = new ArrayList<Factura>();
		ResultSet rs = null;

		try {

			// Preparamos la sentencia SQL.
			psFacturasUsuario = con.prepareStatement(MOSTRAR_LISTA_FACTURAS_USUARIO);

			// Metemos el dato en la sentencia.
			psFacturasUsuario.setInt(1, id_usuario);

			// Miramos que funcion SQL saca.
			System.out.println(psFacturasUsuario);

			// Ejecutamos la sentencia.
			rs = psFacturasUsuario.executeQuery();

			// Creamos la clase Factura.
			Factura factura;

			while (rs.next()) {

				factura = new Factura();

				factura.setNumeroFactura(rs.getInt("id"));

				factura.setFechaFactura(rs.getDate("fecha"));

				// Añadimos el producto al array.
				facturasUsuario.add(factura);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Lo que devolvemos.
		return facturasUsuario.toArray(new Factura[facturasUsuario.size()]);
	}

}
