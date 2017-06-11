//ListaProductosFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import org.apache.log4j.Logger;

// com.ipartek.jonBarnes.DAL.DALException;
import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
//import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * Servlet para la opcion de a�adir productos a los carritos.
 * 
 * @author jon Barnes
 * @version 01/06/2017
 * 
 */
public class ListaProductosFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(ListaProductosFormServlet.class);

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

			dalCarrito = ProductoDALFactory.getProductosDAL();

		}

		// Miramos que no saques dalCarrito.
		log.info(String.format("dalCarrito: %s", dalCarrito));

		// Cargamos la aplicacion de productos por si acaso.

		ServletContext applicationProductos = getServletContext();
		ProductoDAO dalProductos = (ProductoDAO) applicationProductos.getAttribute("dalProductos");

		// Miaramos que valor tiene dalProductos.
		log.info(String.format("dalProductos: %s", dalProductos));

		// Cargamos los datos a a�adir.

		// Miramos que producto es.
		int idProducto = Integer.parseInt(request.getParameter("id"));
		// Miramos que id coge de producto.
		log.info(String.format("Producot: %s", idProducto));
		// Producto que vamos a utilizar.
		ProductoStockImagen productoAnadirCarrito;// = new
													// ProductoStockImagen();
		// Buscamos el producto en el dalProductos.
		productoAnadirCarrito = dalProductos.findById(idProducto);

		// Producto que vamos a a�adir.
		log.info(String.format("productoAnadirCarrito: %s ", productoAnadirCarrito));

		//Abrimos la conexion con la base de datos.
		dalProductos.abrirConexion();

		//Las operaciones.
		try{


		// Miramos si op es null.
		// Si lo es, que vuelva a lista de productos.
		if (op == null) {
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO_PRODUCTOS_USUARIO).forward(request,
					response);

			return;
		}

		if (op.startsWith("anadir")) {
			try {

				// Damos de alta el producto.
				dalCarrito.insert(productoAnadirCarrito);
				// Miramos si nos mete bien el producto.
				log.info(String.format("dalCarrito desdpues de alta: %s", dalCarrito));
				// Para saber lo que hay dentro del Array.
				ProductoStockImagen[] carrito = dalCarrito.findAll();

				for (int i = 0; i < carrito.length; i++) {
					log.info(String.format("Que tiene el carrito?? %s", carrito[i]));
				}

				// Guardamos el dato.

				session.setAttribute("dalCarrito", dalCarrito);

			} catch (DAOException de) {
				productoAnadirCarrito.setErrores("El producto ya existe");
				request.setAttribute("producto", productoAnadirCarrito);
				request.getRequestDispatcher("?op=anadir").forward(request, response);
				return;

			}
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO_PRODUCTOS_USUARIO).forward(request,
					response);

		}

	}catch (Exception e){
			throw new DAOException("Error en las operaciones con la base de datos.",e);

		}finally {

		        //Cerramos la conexion.
			try{
				dalProductos.cerrarConexion();

			}catch (Exception e){
				throw new DAOException("Error en la dexconesion con la base de datos.",e);

			}
		}
		}
}
