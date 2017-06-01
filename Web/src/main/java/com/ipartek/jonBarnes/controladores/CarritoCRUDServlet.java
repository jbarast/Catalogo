//CarritoCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;
//Las rutas.
//Mis imports.

/**
 * Servlet para carricrud.jsp
 * 
 * @author jonBarnes
 * @version 31/06/2017
 */
// @WebServlet("/carritocrud")
public class CarritoCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(CarritoCRUDServlet.class);

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

		// Cogemos los datos de la sesion.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession();
		ProductoDALInterface dalCarrito = (ProductoDALInterface) session.getAttribute("dalCarrito");

		// Miramos si cogemos daltos del dalCarrito.
		log.info(String.format("dalCarrito: %s", dalCarrito));

		// Las operaciones.
		// TODO hacerlo:
		// https://github.com/ipartek/JavaServidorTardes/blob/master/HolaMundo/src/com/ipartek/ejemplos/javierlete/controladores/UsuarioCRUDServlet.java

		// Miramos que la dalProductos no este vacia.
		if (dalCarrito == null) {

			dalCarrito = ProductoDALFactory.getProductos();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

		}

		// Creamos op.
		String op = request.getParameter("op");

		// Miramos que llega.
		log.info(String.format("Operacion a realizar: %s", op));
		log.info(String.format("dalCarrito %s", dalCarrito));

		if (op == null) {

			ProductoStockImagen[] carrito = dalCarrito.buscarTodosLosProductos();

			// log.info(String.format("Carrito: %s", carrito.toString()));
			// request.setAttribute("carrito", carrito);
			// Por si acaso.
			session.setAttribute("carrito", carrito);

			// Imprimimos la session.
			log.info(String.format("Session %s", session.getAttribute("carrito")));
			// Imprimimos el carrito.
			for (int i = 0; i < carrito.length; i++) {
				log.info(String.format("Que tiene el carrito?? %s", carrito[i]));
			}

			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_CARRITO).forward(request, response);
		} else {

			String id = request.getParameter("id");

			ProductoStockImagen producto;

			switch (op) {
			case "modificar":
			case "borrar":
				producto = dalCarrito.buscarProductoPorId(id);
				request.setAttribute("dalCarrito", producto);
			case "alta":
				request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO_CARRITO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_CARRITO).forward(request, response);
			}
		}
	}
}
