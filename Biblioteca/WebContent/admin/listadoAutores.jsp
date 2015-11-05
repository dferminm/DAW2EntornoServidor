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
		<c:if test="${not empty listadoAutores}">
			<table width="100%" border="1">
				<caption>Listado de Autores</caption>
				<tr>
					<th>IDAUTOR</th>
					<th>NOMBRE</th>
					<th>FECHA</th>
				</tr>
				<c:forEach items="${listadoAutores}" var="autor">
					<tr>
						<td>${autor.idautor}</td>
						<td>${autor.nombre}</td>
						<td>${autor.fecha}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>