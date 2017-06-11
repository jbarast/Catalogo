//ProductoCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import org.apache.log4j.Logger;

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
	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(ProductoCRUDServlet.class);

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
		ProductoDAO dalProductos = (ProductoDAO) applicationProductos.getAttribute("dalProductos");

		// Las operaciones.
		// TODO hacerlo:
		// https://github.com/ipartek/JavaServidorTardes/blob/master/HolaMundo/src/com/ipartek/ejemplos/javierlete/controladores/UsuarioCRUDServlet.java

		// Miramos que la dalProductos no este vacia.
		if (dalProductos == null) {

			dalProductos = ProductoDALFactory.getProductosDAL();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

			applicationProductos.setAttribute("dalProductos", dalProductos);
		}

		// Creamos op.
		String op = request.getParameter("op");

		//abrimos la conexion con la base de datos.
		dalProductos.abrirConexion();

		try{
		if (op == null) {

			ProductoStockImagen[] productos = dalProductos.findAll();
			// Miramos como da el dato.
			// Por si queremos ver los productos que tenemos en el log.
			for (int i = 0; i < productos.length; i++) {
				log.info(String.format("Que tiene el carrito?? %s", productos[i]));
			}
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO).forward(request, response);
		} else {

			int id = Integer.parseInt(request.getParameter("id"));

			ProductoStockImagen producto;

			switch (op) {
			case "modificar":
			case "borrar":
				producto = dalProductos.findById(id);
				request.setAttribute("producto", producto);
			case "alta":
				request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO).forward(request, response);
				break;
			default:
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO).forward(request, response);
			}
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
