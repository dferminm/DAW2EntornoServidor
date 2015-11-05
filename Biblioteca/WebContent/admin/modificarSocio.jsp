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

		<br>
		<h2>Modificar socio</h2>
		<form name="editarSocio" method="post" action="/Biblioteca/controller">
			Nombre: <input type="text" name="nombre" id="nombre"> Email:
			<input type="text" name="email" id="email"> Dirección: <input
				type="text" name="direccion" id="direccion"> <br>
			<br> <input type="hidden" name="operacion" value="editarSocio">
			<input type="hidden" name="idsocio" value="${idsocio}"> <input
				type="submit" value="Modificar">
		</form>
	</div>
</body>
</html>