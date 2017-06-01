//ListaProductosFormServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.jonBarnes.DAL.DALException;
import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;

/**
 * Servlet para la opcion de añadir productos a los carritos.
 * 
 * @author jon Barnes
 * @version 01/06/2017
 * 
 */
public class ListaProductosFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		// Leemos datos de la session.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession();
		ProductoDALInterface dalCarrito = (ProductoDALInterface) session.getAttribute("dalCarrito");

		// Cargamos la aplicacion de productos por si acaso.

		ServletContext applicationProductos = getServletContext();
		ProductoDALInterface dalProductos = (ProductoDALInterface) applicationProductos.getAttribute("dalProductos");

		// Cargamos los datos a añadir.

		// Miramos que producto es.
		String idProducto = request.getParameter("id");
		// Producto que vamos a utilizar.
		ProductoStockImagen productoAnadirCarrito;// = new
													// ProductoStockImagen();
		// Buscamos el producto en el dalProductos.
		productoAnadirCarrito = dalProductos.buscarProductoPorId(idProducto);

		// Miramos si op es null.
		// Si lo es, que vuelva a lista de productos.
		if (op == null) {
			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request, response);

			return;
		}

		if (op.startsWith("anadir")) {
			try {

				// Damos de alta el producto.
				dalCarrito.altaProducto(productoAnadirCarrito);
			} catch (DALException de) {
				productoAnadirCarrito.setErrores("El producto ya existe");
				request.setAttribute("producto", productoAnadirCarrito);
				request.getRequestDispatcher("?op=anadir").forward(request, response);
				return;

			}
			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request, response);

		}

	}
}
