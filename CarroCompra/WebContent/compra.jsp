<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.ArrayList, entidades.Producto, carrocompra.CarroCompra"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/CarroCompra/resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
	</div>

	<h1 align="center">Bienvenido a la página de compra</h1>

	<table align="center" border="5" width="50%">
		<tr align="center">
			<td>ID</td>
			<td>Descripcion</td>
			<td>Precio</td>
			<td>Añadir al carrito</td>
		</tr>
		<c:forEach items="${listadoProductos}" var="producto">
			<tr align="center">
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precioNormal}</td>
				<td><a href="/CarroCompra/controller?operacion=comprar&idProducto=${producto.id}">Añadir</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${not empty carro}">
	<h3 align="center">Contenido actual del carro</h3>
			<table width="60%" border="5" align="center">
				<tr align="center">
					<th>CODIGO</th>
					<th>PRODUCTO</th>
					<th>PRECIO</th>
					<th>CANTIDAD</th>
				</tr>
				<c:forEach items="${carro.getElementos()}" var="producto">
					<tr align="center">
						<td>${producto.id}</td>
						<td>${producto.nombre}</td>
						<td>${producto.precioNormal}</td>
						<td>${producto.cantidad}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
</body>
</html>