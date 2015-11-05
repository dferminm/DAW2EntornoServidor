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
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<form name="altasocio" method="post"
			action="/Biblioteca/controllerRegistro">
			<table width="50%" border="1">
				<caption>Nuevo alta de socio</caption>
				<tr align="left">
					<th>Nombre<input type="text" name="nombre" id="nombre"></th>
				</tr>
				<tr align="left">
					<th>Email<input type="text" name="email" id="email"></th>
				</tr>
				<tr align="left">
					<th>Clave<input type="password" name="password" id="password"></th>
				</tr>
				<tr align="left">
					<th>Direccion<input type="text" name="direccion"
						id="direccion"></th>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			<input type="hidden" name="operacion" value="altasocio" /> <input
				type="submit" value="Registro" />
		</form>
	</div>
</body>
</html>