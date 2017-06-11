//ListaProductosFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.jonBarnes.DAL.CarritoDALFactory;
import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import org.apache.log4j.Logger;

//import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
//import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * Servlet para la opcion de a�adir productos a los carritos.
 * 
 * @author jon Barnes
 * @version 02/06/2017
 * 
 */
public class CarritoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(CarritoFormServlet.class);

	/**
	 * Llamamos al metodo doPost().
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Llamamos al metodo doPost.
		doPost(request, response);
	}

	/**
	 * Metodo doPost del servlet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// op.
		String op = request.getParameter("op");
		log.info(String.format("Operacion %s", op));

		// Leemos datos de la session.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession();
		ProductoDAO dalCarrito = (ProductoDAO) session.getAttribute("dalCarrito");

		// Miramos que la dalProductos no este vacia.
		if (dalCarrito == null) {

			dalCarrito = CarritoDALFactory.getProductosDAL();

			// Creamos unos productos de prueba.
			// dalCarrito.altaProducto(new ProductoStockImagen()); // Uno de
			// muestra.

		}

		// Miramos que no saques dalCarrito.
		log.info(String.format("dalCarrito: %s", dalCarrito));

		// Cargamos la aplicacion de productos por si acaso.

		ServletContext applicationProductos = getServletContext();
		ProductoDAO dalProductos = (ProductoDAO) applicationProductos.getAttribute("dalProductos");

		//abrimos la conexion con la base de datos.
		dalCarrito.abrirConexion();

		// Miaramos que valor tiene dalProductos.
		log.info(String.format("dalProductos: %s", dalProductos));

		// Cargamos los datos a a�adir.

		// Miramos que producto es.
		String idProducto = request.getParameter("id");
		// Miramos que id coge de producto.
		log.info(String.format("Producot: %s", idProducto));
		// Producto que vamos a utilizar.
		ProductoStockImagen productoBorrarCarrito;// = new
		// ProductoStockImagen();
		// Buscamos el producto en el dalProductos.
		productoBorrarCarrito = dalProductos.findById(Integer.parseInt(idProducto));

		// Producto que vamos a a�adir.
		log.info(String.format("productoAnadirCarrito: %s ", productoBorrarCarrito));

		// Miramos si op es null.
		// Si lo es, que vuelva a lista de productos.

		//Las operaciones.
		try {


			if (op == null) {
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request, response);

				return;
			}

			if (op.equals("borrar")) {
				// Indicamos que producto se a dado de alta.
				log.info(String.format("Objeto %s borrado del carrito.", productoBorrarCarrito.getNombre()));

				// Borramos el producto.
				dalCarrito.delete(productoBorrarCarrito);
				request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO_CARRITO).forward(request, response);

			}

		} catch (Exception e) {

			throw new DAOException("Error en la dexconesion con la base de datos.", e);

		} finally {

			//Cerramos la conexion.
			try {
				dalCarrito.cerrarConexion();
			} catch (Exception e) {
				throw new DAOException("Error en la dexconesion con la base de datos.", e);

			}
		}
	}}


