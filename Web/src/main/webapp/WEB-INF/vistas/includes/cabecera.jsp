<%--cabecera.jsp --%>

<%--
     Cabeceras para los jsp.
     Autor: jonBarnes
     Version: 09/05/2017
  

 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%--HEAD--%>
<head>
<meta charset="UTF-8" />
<title>Ejercicio Tienda</title>
<%--<link rel="stylesheet" href="css/estilos.css" /> --%>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilos.css"></link>
<script src="js/funciones.js"></script>
</head>

<%--Empieza el Codigo --%>
<body>

<%--Titulo. --%>
	<header>
		<h1>CATALOGO</h1>
		<p>La mejor tienda online de productos unicos.</p>
	</header>
	<nav>
		<ul>
			
			<%--Botones en la parte superior. --%>
			<li><a href="productoform?op=alta">Alta Producto</a></li>
			<li><a href="productocrud">Mantenimiento productos</a></li>
			
			<li><a href="usuarioform?op=alta">Alta Usuarios</a></li>
			<li><a href="usuariocrud">Mantenimiento usuarios</a></li>
			
			<li><a href="listaproductos">Vista usuario</a></li>
			<li><a href="carritocrud">Carrito</a></li>
			
			<li><a href="login?opcion=logout">Logout</a></li>
		
		</ul>
	</nav>