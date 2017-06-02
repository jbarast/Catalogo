<%--listaproductosform.jsp --%>
<%--

  Controlador de añadir productos al carrito.
  autor: jonBarnes
  version: 02/06/2017

 --%>

<%--Cabecera --%>
<%@ include file="includes/cabecera.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos las librerias. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<%--Redirige a la servlet de lista de productos --%>
	
	<h2>Formulario de productos</h2>
	
	<%--Llamamos a clases. --%>
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.jonBarnes.tipos.ProductoStockImagen" />
	

		

   

<%--pie --%>
<%@ include file="includes/pie.jsp" %>
