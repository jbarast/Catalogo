//Listener.java

package com.ipartek.jonBarnes.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ipartek.jonBarnes.DAL.ProductoDALFactory;
import com.ipartek.jonBarnes.DAL.ProductoDALInterface;
import com.ipartek.jonBarnes.DAL.UsuarioDALFactory;
import com.ipartek.jonBarnes.DAL.UsuariosDAL;
import com.ipartek.jonBarnes.tipos.ProductoStockImagen;
import com.ipartek.jonBarnes.tipos.Usuario;

/**
 * 
 * Creacion de un listener para el ejercicio. Carga la lista de usuarios.
 * 
 * @author Jon Barnes
 * @version 26/05/2017
 *
 */
public class Listener implements ServletContextListener {

	// Para hacer el log4j.
	private static Logger log = Logger.getLogger(Listener.class);

	/**
	 * 
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * Creamos la lista de usuarios, de la dal.
	 */
	public void contextInitialized(ServletContextEvent sce) {

		log.info("Aplicacion inicializada:");

		ServletContext application = sce.getServletContext();

		// Para usuarios.
		UsuariosDAL dalRename = (UsuariosDAL) application.getAttribute("dal");

		if (dalRename == null) {
			dalRename = UsuarioDALFactory.getUsuariosDAL();

			dalRename.alta(new Usuario("usuario1", "pass1"));
			dalRename.alta(new Usuario("usuario2", "pass2"));

			// Creamos el usuario adm.

			dalRename.alta(new Usuario("admin", "admin"));

			application.setAttribute("dal", dalRename);

			// Para indicar que los usuarios han sido creados.
			log.info("Lista de usuarios inicializada.");

		}

		// Para poroductos.

		ProductoDALInterface dalProductos = (ProductoDALInterface) application.getAttribute("dalProductos");

		if (dalProductos == null) {

			dalProductos = ProductoDALFactory.getProductos();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

			// El arca.
			dalProductos.altaProducto(new ProductoStockImagen("1", "Arca Perdida",
					"El arca que contiene los diez mandamientos. Recomendamos no abrirla.", "1.0000.0000", "1",
					"https://i.ytimg.com/vi/LaXq8DetoNI/hqdefault.jpg"));

			// El necromonicon.
			dalProductos.altaProducto(new ProductoStockImagen("2", "Necromonicum",
					"Libro para traer al mundo a los primogenitos o no.", "1.5000.0000", "1",
					"https://i.ytimg.com/vi/9hgoOPgq9OY/maxresdefault.jpg"));

			// Espada excalibur.
			dalProductos.altaProducto(new ProductoStockImagen("3", "Espada Excalibur",
					"Espada para reinar Inglaterra. No nos hacemos cargo de entregar al cliente Inglaterra",
					"15.5000.0000", "1",
					"http://4.bp.blogspot.com/-FG_uA1t58h4/UMj1vAUNmhI/AAAAAAAAAJs/DuCh4tcptlk/s1600/excalibur.jpg"));

			// Videojuego e.t.
			dalProductos.altaProducto(new ProductoStockImagen("4", "Videojuego E.T.",
					"Consigue una copia de uno de los videojuegos mas galardonados jamas creado.", "1.000", "100.000",
					"http://img3.meristation.com/files/imagenes/general/1398540488-2.jpg"));

			// Maquina del tiempo delorian.
			dalProductos
					.altaProducto(new ProductoStockImagen(
							"5",
							"Maquina del tiempo delorean",
							"Maquina del tiempo modelo delorian. No nos hacemos cargo del mal uso que den los clientes.Combustible no incluido por temas legales.",
							"1", "50.000.000.000",
							"https://static.motor.es/fotos-noticias/2015/10/min652x435/curiosidades-delorean-regreso-al-futuro-201523728_4.jpg"));

			//
			application.setAttribute("dalProductos", dalProductos);

			// Para indicar que los usuarios han sido inicializados.
			log.info("Lista de productos inicializada.");

		}

	}

}
