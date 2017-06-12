//ProductoFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
//import com.ipartek.jonBarnes.DAL.DALException;
//import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * 
 * Servlet para la vista del ProductoForm.
 * 
 * @author jonBarnes
 * @version 10/05/2017
 * 
 */
// @WebServlet("/productoform")
public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);

	/**
	 * 
	 * Metodo que llama al metodo doPost();
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Llamamos al metodo doPost.
		doPost(request, response);

	}

	/**
	 * 
	 * Metodo doPost().
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// La "application"
		ServletContext applicationProductos = getServletContext();
		ProductoDAO dalProductos = (ProductoDAO) applicationProductos.getAttribute("dalProductos");
		// op.
		String op = request.getParameter("opform");

		// Cogiendo los datos
		String nombre = request.getParameter("nombre");

		// Si la dal es null.
		if (dalProductos == null) {

			dalProductos = ProductoDALFactory.getProductosDAL();
			applicationProductos.setAttribute("dalProductos", dalProductos);

		}

		// abrimos la conexion.
		dalProductos.abrirConexion();

		// Para sacar la id.
		ProductoStockImagen[] productos = dalProductos.findAll(); // Solo
																	// sirve
																	// para
																	// el
																	// id.
		// String id = request.getParameter("id");
		// int id = String.valueOf(productos.length + 1); Mirarlo bien.
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		if (precio == null) {
			precio = "0";
		}

		// Cogemos el stock
		String stringStock = request.getParameter("stock");

		if (stringStock == null) {
			precio = "0";
		}

		System.out.println(stringStock);
		// int stock = Integer.parseInt(stringStock);
		int stock = 1; // TODO quitar esto, solo es para seguir adelante.

		String rutaImagen = request.getParameter("rutaImagen");

		// Miramos si op es null.
		if (op == null) {
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);

			return;
		}

		// Creamos el producto.

		ProductoStockImagen producto = new ProductoStockImagen();

		// producto.setId(id);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(Double.parseDouble(precio));
		producto.setStock(stock);
		producto.setRutaImagen(rutaImagen);

		System.out.println("El producto a modificar/borrar: " + producto);

		// Las operaciones.
		try {
			switch (op) {
			case "alta":
				try {

					producto.setNombre(nombre);
					producto.setDescripcion(descripcion);
					producto.setPrecio(Double.parseDouble(precio));
					producto.setStock(stock);
					producto.setRutaImagen(rutaImagen);

					// Miramos que producto mete.
					System.out.println("Producto para alta: " + producto);
					// Indicamos que producto se a dado de alta.
					log.info(String.format("Objeto %s a�adido a la tienda.", producto.getNombre()));

					// Damos de alta el producto.
					dalProductos.insert(producto);
				} catch (DAOException de) {
					producto.setErrores("El producto ya existe");
					request.setAttribute("producto", producto);
					request.getRequestDispatcher("?op=alta").forward(request, response);
					return;

				}
				request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);

				break;
			case "modificar":
				try {

					// Cogemos la id.
					producto = dalProductos.findbyUsername(producto.getNombre());
					producto.setDescripcion(descripcion);
					// Indicamos que producto se a modificado..
					log.info(String.format("Objeto %s modificado de la  tienda.", producto.getNombre()));

					// Hacemos la modificacion.
					dalProductos.update(producto);
				} catch (DAOException de) {
					producto.setErrores(de.getMessage());
					request.setAttribute("producto", producto);
					request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO).forward(request, response);
					return;
				}
				request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);

				// dalProductos.modificarProducto(producto);
				// request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request,
				// response);

				break;
			case "borrar":

				// Cogemos la id.
				producto = dalProductos.findbyUsername(producto.getNombre());

				// Indicamos que producto se a dado de alta.
				log.info(String.format("Objeto %s borrado de la tienda.", producto.getNombre()));

				// Borramos el producto.
				dalProductos.delete(producto.getId());
				request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);
				break;
			}

		} catch (Exception e) {
			throw new DAOException("Error en las operaciones con la base de datos.", e);

		} finally {

			// Cerramos la conexion.
			try {

				dalProductos.cerrarConexion();
			} catch (Exception e) {
				throw new DAOException("Error en la dexconesion con la base de datos.", e);

			}
		}
	}
}
