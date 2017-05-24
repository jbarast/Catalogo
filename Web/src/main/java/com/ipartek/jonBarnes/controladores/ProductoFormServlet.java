//ProductoFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAL.DALException;
import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
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
		ProductoDALInterface dalProductos = (ProductoDALInterface) applicationProductos.getAttribute("dalProductos");
		// op.
		String op = request.getParameter("opform");

		// Cogiendo los datos
		String nombre = request.getParameter("nombre");
		// Para sacar la id.
		ProductoStockImagen[] productos = dalProductos.buscarTodosLosProductos(); // Solo
																					// sirve
																					// para
																					// el
																					// id.
		// String id = request.getParameter("id");
		String id = String.valueOf(productos.length + 1);
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		if (precio == null) {
			precio = "Sin precio";
		}

		String stock = request.getParameter("stock");
		String rutaImagen = request.getParameter("rutaImagen");

		// Miramos si op es null.
		if (op == null) {
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);

			return;
		}

		// Creamos el producto.

		ProductoStockImagen producto = new ProductoStockImagen(id, nombre, descripcion, precio, stock, rutaImagen);

		switch (op) {
		case "alta":
			try {
				dalProductos.altaProducto(producto);
			} catch (DALException de) {
				producto.setErrores("El producto ya existe");
				request.setAttribute("producto", producto);
				request.getRequestDispatcher("?op=alta").forward(request, response);
				return;

			}
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);

			break;
		case "modificar":
			try {
				dalProductos.modificarProducto(producto);
			} catch (DALException de) {
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
			dalProductos.borrarProducto(producto);
			request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request, response);
			break;
		}

	}
}
