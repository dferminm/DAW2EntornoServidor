<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<p align="center">Consulta de libros</p>
		<form name="buscarLibro" method="post"
			action="/Biblioteca/controllerSocio">
			Búsqueda: <input type="text" name="valorcampobusqueda"
				id="valorcampobusqueda"> <select name="campobusqueda">
				<option value="autor">autor</option>
				<option value="titulo">titulo</option>
				<option value="isbn">isbn</option>
			</select> <input type="hidden" name="operacion" value="buscaLibro"> <input
				type="submit" value="Buscar">
		</form>
		<c:if test="${not empty listadoLibrosBusqueda}">
			<table width="45%" border="1" align="center">
				<tr>
					<th>Titulo</th>
					<th>Autor</th>
					<th>Totales</th>
					<th>Prestados</th>
					<th>Disponibles</th>
				</tr>
				<c:forEach items="${listadoLibrosBusqueda}" var="libro">
					<tr>
						<td>${libro.titulo}</td>
						<td>${libro.nombreAutor}</td>
						<td>${libro.ejemplaresTotales}</td>
						<td>${libro.ejemplaresEnPrestamo}</td>
						<td>${libro.ejemplaresDisponibles}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>