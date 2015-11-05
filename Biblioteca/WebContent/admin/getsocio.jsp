<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.ArrayList, entidades.Socio"%>
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
		<h3 align="center">Buscar socio</h3>
		<form name="getsocio" method="post" action="/Biblioteca/controller">
			Introduzca el nombre o parte:<input type="text" name="cadena"
				id="cadena"> <input type="hidden" name="operacion"
				value="getsocio"> <input type="submit" value="Enviar">
		</form>
		<c:choose>
			<c:when test="${not empty buscaSocio}">
				<table width="100%" border="1">
					<caption>Listado de Socios</caption>
					<tr align="center">
						<th>IDSOCIO</th>
						<th>EMAIL</th>
						<th>NOMBRE</th>
						<th>DIRECCION</th>
						<th>EDITAR</th>
					</tr>
					<c:forEach items="${buscaSocio}" var="socio">
						<tr align="center">
							<td>${socio.idsocio}</td>
							<td>${socio.email}</td>
							<td>${socio.nombre}</td>
							<td>${socio.direccion}</td>
							<td><form name="modificarsocio" method="post"
									action="/Biblioteca/controller">
									<input type="hidden" name="operacion" value="modificarsocio">
									<input type="hidden" name="idsocio" value="${socio.idsocio}">
									<input type="submit" value="Editar">
								</form></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</div>
</body>
</html>