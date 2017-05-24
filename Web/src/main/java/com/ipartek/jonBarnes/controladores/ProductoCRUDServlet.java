//ProductoCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;
//Las rutas.
//Mis imports.

/**
 * Servlet para productocrud.jsp
 * 
 * @author jonBarnes
 * @version 10/05/2017
 */
// @WebServlet("/productocrud")
public class ProductoCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * LLamamos a una funcion doPost.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * 
	 * Metodo doPost....
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// Primero recogemos los datos...??
		ServletContext applicationProductos = getServletContext();
		ProductoDALInterface dalProductos = (ProductoDALInterface) applicationProductos.getAttribute("dalProductos");

		// Las operaciones.
		// TODO hacerlo:
		// https://github.com/ipartek/JavaServidorTardes/blob/master/HolaMundo/src/com/ipartek/ejemplos/javierlete/controladores/UsuarioCRUDServlet.java

		// Miramos que la dalProductos no este vacia.
		if (dalProductos == null) {

			dalProductos = ProductoDALFactory.getProductos();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

			applicationProductos.setAttribute("dalProductos", dalProductos);
		}

		// Creamos op.
		String op = request.getParameter("op");

		if (op == null) {

			ProductoStockImagen[] productos = dalProductos.buscarTodosLosProductos();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO).forward(request, response);
		} else {

			String id = request.getParameter("id");

			ProductoStockImagen producto;

			switch (op) {
			case "modificar":
			case "borrar":
				producto = dalProductos.buscarProductoPorId(id);
				request.setAttribute("producto", producto);
			case "alta":
				request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO).forward(request, response);
			}
		}
	}
}
