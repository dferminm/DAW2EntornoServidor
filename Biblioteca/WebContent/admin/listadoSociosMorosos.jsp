<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, entidades.Prestamo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>

		<h3 align="center">Listado de Socios Morosos</h3>
		<table width="45%" border="1" align="center">
			<tr>
				<th>IDSOCIO</th>
				<th>NOMBRE</th>
				<th>VER LIBROS</th>
			</tr>
			<c:forEach items="${listadoSociosMorosos}" var="prestamo">
				<tr align="center">
					<td>${prestamo.idsocio}</td>
					<td>${prestamo.nombre}</td>
					<td><form name="verlibros" method="post"
							action="/Biblioteca/controller">
							<input type="hidden" name="idsocio" value="${prestamo.idsocio}">
							<input type="hidden" name="nombreSocio"
								value="${prestamo.nombre}"> <input type="hidden"
								name="operacion" value="verlibros"> <input type="submit"
								value="VER">
						</form></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty listadoLibros}">
			<p align="center">
				<c:out value="Libros de : ${nombre}"></c:out>
			</p>
			<table width="45%" border="1" align="center">
				<tr>
					<th>TITULO</th>
					<th>DIAS DE DEMORA</th>
				</tr>
				<c:forEach items="${listadoLibros}" var="libro">
					<tr align="center">
						<td>${libro.titulo}</td>
						<td>${libro.diasdemora}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>