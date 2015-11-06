<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, entidades.Producto, carrocompra.CarroCompra"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/CarroCompra/resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de compra</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
	</div>

	<h1 align="center">Bienvenido a la zona de compra!</h1>

	<table align="center" border="5" width="50%">
		<tr align="center">
			<td>ID</td>
			<td>Descripcion</td>
			<td>Precio</td>
			<td>Add</td>
		</tr>
		<c:forEach items="${listadoProductos}" var="producto">
			<tr align="center">
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precioNormal}</td>
				<td><a
					href="/CarroCompra/controller?operacion=comprar&idProducto=${producto.id}">
						<img src="./resources/img/comprar.png" width="50px" height="50px" />
				</a></td>
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
				<th>TOTAL</th>
				<th>INCREMENTAR</th>
				<th>DISMINUIR</th>
				<th>ELIMINAR</th>
			</tr>
			<c:forEach items="${carro.getElementos()}" var="producto">
				<tr align="center">
					<td>${producto.id}</td>
					<td>${producto.nombre}</td>
					<td>${producto.precioNormal}</td>
					<td>${producto.cantidad}</td>
					<td>${producto.cantidad * producto.precioNormal}</td>

					<td><a
						href="/CarroCompra/controller?operacion=incrementar&idProducto=${producto.id}">
							<img src="./resources/img/incrementar.jpg" width="50px"
							height="50px" />
					</a></td>

					<td><a
						href="/CarroCompra/controller?operacion=disminuir&idProducto=${producto.id}">
							<img src="./resources/img/disminuir.jpg" width="50px"
							height="50px" />
					</a></td>

					<td><a
						href="/CarroCompra/controller?operacion=eliminar&idProducto=${producto.id}">
							<img src="./resources/img/eliminar.jpg" width="50px"
							height="50px" />
					</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><b>Total a pagar:</b></td>
				<td>${carro.precioTotal()}</td>
			</tr>
		</table>
		<h2 align="center">
			<a href="/CarroCompra/formalizarPedido.jsp">FORMALIZAR PEDIDO</a>
		</h2>
	</c:if>
</body>
</html>