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
<link rel="stylesheet" href="css/estilos.css" />
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
			<li><a href="productoform?op=alta">Alta producto</a></li>
			<li><a href="productocrud">Mantenimiento productos</a></li>
			<li><a href="usuarioform?op=alta">Alta usuario</a></li>
			<li><a href="usuariocrud">Mantenimiento usuarios</a></li>
		
		</ul>
	</nav>