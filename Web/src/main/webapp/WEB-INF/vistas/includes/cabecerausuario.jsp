<%--cabecerausuario.jsp --%>

<%--
     Cabeceras para las jsp de admin, no aparezca nada.
     Autor: jonBarnes
     Version: 31/05/2017
  

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
			<a href="listaproductos">Lista Productos</a>
			<a href="carritocrud">Carrito</a>
			<a href="login?opcion=logout">Logout</a>
		</ul>
	</nav>