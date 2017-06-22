//ConstantesGlobales.java

package com.ipartek.jonBarnes.constantesGlobales;

/**
 * 
 * Clase en donde metemos todas las constantes.
 * 
 * @author jonBarnes
 * @version 09/05/2017
 *
 */
public class ConstantesGlobales {

	// RUTAS:

	public static final String RUTA = "/WEB-INF/vistas/";

	public static final String RUTA_LISTADO = RUTA + "productocrud.jsp";
	public static final String RUTA_SERVLET_LISTADO = "/productocrud";

	public static final String RUTA_FORMULARIO = RUTA + "productoform.jsp";
	public static final String RUTA_SERVLET_FORMULARIO = "/productoform";

	public static final String RUTA_LISTADO_PRODUCTOS_USUARIO = RUTA + "listaproductos.jsp";
	public static final String RUTA_SERVLET_LISTADO_PRODUCTOS_USUARIO = "/listaproductos";

	public static final String RUTA_FORMULARIO_PRODUCTOS_USUARIO = RUTA + "listaproductosform.jsp";
	public static final String RUTA_SERVLET_FORMULARIO_PRODUCTOS_USUARIO = "/listaproductosform";

	// Rutas par LoginServlet

	public static final String PRODUCTO = "ProductoStockImagen";

	public static final String RUTA_PRINCIPAL = RUTA + "productocrud.jsp";
	public static final String RUTA_LOGIN = RUTA + "login.jsp";

	public static final int TIEMPO_INACTIVIDAD = 30 * 60;

	public static final int MINIMO_CARACTERES = 4;

	// Rutas usuario crud

	public static final String RUTA_FORMULARIO_USUARIO = "/WEB-INF/vistas/usuarioform.jsp";
	public static final String RUTA_LISTADO_USUARIO = "/WEB-INF/vistas/usuariocrud.jsp";
	public static final String RUTA_SERVLET_LISTADO_USUARIO = "/usuariocrud";

	// Rutas para el carrito.

	public static final String RUTA_LISTADO_CARRITO = RUTA + "carritocrud.jsp";
	public static final String RUTA_SERVLET_LISTADO_CARRITO = "/carritocrud";

	public static final String RUTA_FORMULARIO_CARRITO = RUTA + "carritoform.jsp";
	public static final String RUTA_SERVLET_FORMULARIO_CARRITO = "/carritoform";

	// Rutas para las facturas.

	public static final String RUTA_LISTADO_FACTURAS = RUTA + "listafacturas.jsp";
	public static final String RUTA_SERVLET_LISTADO_FACTURAS = "/listafacturas";

}
