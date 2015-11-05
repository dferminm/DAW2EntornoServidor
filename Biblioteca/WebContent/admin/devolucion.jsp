<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<p align="center">Devolución de prestamo de libro</p>
		<form name="devolucion" method="post" action="/Biblioteca/controller">
			Número de ejemplar: <input type="text" name="numeroejemplar"
				id="numeroejemplar"> <input type="hidden" name="operacion"
				value="devolucion"> <input type="submit"
				value="Eliminar prestamo">
		</form>
	</div>
</body>
</html>