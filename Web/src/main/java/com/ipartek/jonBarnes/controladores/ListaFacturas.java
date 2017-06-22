//ListaFacturas.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.jonBarnes.DAL.FacturaDALFactory;
import com.ipartek.jonBarnes.DAO.FacturaDAOMySQL;
import com.ipartek.jonBarnes.DAO.interfaces.FacturasDAO;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.Factura;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * Servet para el control de la vista listafacturas.
 * 
 * @author jonBarnes
 * @version 22/06/2017
 */
public class ListaFacturas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static FacturasDAO daoFacturas = null;

	/**
	 * 
	 * LLama al metodo doPost.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 
	 * Donde esta toda la txitxa de la vista ListaFacturas.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Inicializamos la dao.

		// DAO.
		daoFacturas = new FacturaDAOMySQL("jdbc:mysql://localhost/ipartek", "root", "");

		// Recogemos los datos de la aplicacion.
		ServletContext application = getServletContext();
		FacturasDAO dalFacturas = (FacturasDAO) application.getAttribute("dalFacturas");

		// Session.
		HttpSession session = ((HttpServletRequest) request).getSession();

		// Si el dal esta vacio.
		if (dalFacturas == null) {

			// Cargamos los productos de la base de datos.
			dalFacturas = FacturaDALFactory.getProductosDAL();

			application.setAttribute("dalFacturas", dalFacturas);
		}

		// Abrimos la conexion.
		dalFacturas.abrirConexion();

		// Miramos que usuario somos.
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.getAttribute("usuario");

		// Cogemos todos los uuarios.
		Factura[] facturas = dalFacturas.mostrarListaFacturas(usuario.getId());
		System.out.println("Que datos tengo: ");
		System.out.println(facturas);

		// Cerramos la conexion.
		dalFacturas.cerrarConexion();

		request.setAttribute("facturas", facturas);

		request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_FACTURAS).forward(request, response);

	}

}
