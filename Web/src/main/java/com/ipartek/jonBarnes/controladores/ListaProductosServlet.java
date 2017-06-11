//ProductoCRUDServlet.java

package com.ipartek.jonBarnes.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
//import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
//Las rutas.
//Mis imports.
import com.ipartek.jonBarnes.DAO.DAOException;
import com.ipartek.jonBarnes.DAO.interfaces.ProductoDAO;
import com.ipartek.jonBarnes.constantesGlobales.ConstantesGlobales;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * Servlet para listaproductos.jsp
 * 
 * @author jonBarnes
 * @version 10/05/2017
 */
// @WebServlet("/listaproductos")
public class ListaProductosServlet extends HttpServlet {
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
		ProductoDAO dalProductos = (ProductoDAO) applicationProductos.getAttribute("dalProductos");

		// Leemos datos de la session.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession();
		ProductoDAO dalCarrito = (ProductoDAO) session.getAttribute("dalCarrito");

		// Miramos la operacion a realiczar.
		String op = request.getParameter("op");

		// Miramos que la dalProductos no este vacia.
		if (dalProductos == null) {

			dalProductos = ProductoDALFactory.getProductosDAL();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

			applicationProductos.setAttribute("dalProductos", dalProductos);
		}

		// Miramos que dalCarrito no este vacio.
		if (dalCarrito == null) {

			// Creamos el carrito.
			dalCarrito = ProductoDALFactory.getProductosDAL();
		}
		// ////////**************///////////////

		// Creamos un producto.

		ProductoStockImagen producto;

		//inicializamos la base de datos.
		dalProductos.abrirConexion();

		//Las operaciones con la base de datos.
		try{
		// Sin peracion. Mostramos todos los usuarios.
		if (op == null) {

			ProductoStockImagen[] productos = dalProductos.findAll();

			request.setAttribute("productos", productos);

			request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));

			Usuario usuario;

			// Operacion a realizar, modificar, borrar o dar de alta a un
			// usuario.
			switch (op) {
			case "modificar":
			case "borrar":
				producto = dalProductos.findById(id);
				request.setAttribute("producto", producto);
			case "alta":
				request.getRequestDispatcher(ConstantesGlobales.RUTA_FORMULARIO_PRODUCTOS_USUARIO).forward(request,
						response);
				break;
			default:
				request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request,
						response);
			}

			// /////////**************//////////
			// Lo siguiente esta bien, codigo del medio cambiado.
			// ProductoStockImagen[] productos =
			// dalProductos.buscarTodosLosProductos();
			// request.setAttribute("productos", productos);
			// request.getRequestDispatcher(ConstantesGlobales.RUTA_LISTADO_PRODUCTOS_USUARIO).forward(request,
			// response);

		}
	}catch (Exception e){
			throw new DAOException("Error en las operaicones con la base de datos.",e);

		}finally {

		    //cerramos la base de datos.
			try{
				dalProductos.cerrarConexion();
			}catch (Exception e){
				throw new DAOException("Error en la dexconesion con la base de datos.",e);
			}
		}
		}
}
