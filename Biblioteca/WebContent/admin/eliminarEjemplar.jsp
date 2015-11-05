<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css" />
<script language="javascript">
	function marcar(obj) {
		elem = obj.elements;
		for (i = 0; i < elem.length; i++)
			if (elem[i].type == "checkbox")
				elem[i].checked = true;
	}
	
	function desmarcar(obj) {
		elem = obj.elements;
		for (i = 0; i < elem.length; i++)
			if (elem[i].type == "checkbox")
				elem[i].checked = false;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" /></div>
		<p align="center">Eliminar ejemplares</p>
		<form name="buscarLibro" method="post" action="/Biblioteca/controller">
			Búsqueda: <input type="text" name="valorcampobusqueda"
				id="valorcampobusqueda"> <select name="campobusqueda">
				<option value="autor">autor</option>
				<option value="titulo">titulo</option>
				<option value="isbn">isbn</option>
			</select> <input type="hidden" name="operacion" value="buscaLibroEliminar">
			<input type="submit" value="Buscar">
		</form>
		<c:if test="${not empty listadoLibrosBusqueda}">
			<table width="45%" border="1" align="center">
				<tr>
					<th>Titulo</th>
					<th>Autor</th>
					<th>Eliminar</th>
				</tr>
				<c:forEach items="${listadoLibrosBusqueda}" var="libro">
					<tr>
						<td>${libro.titulo}</td>
						<td>${libro.nombreAutor}</td>
						<td><form name="verejemplares" method="post"
								action="/Biblioteca/controller">
								<input type="hidden" name="isbn" value="${libro.isbn}">
								<input type="hidden" name="isbn" value="${libro.titulo}">
								<input type="hidden" name="operacion" value="eliminarejemplar">
								<input type="submit" value="eliminar ejemplar">
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>


		<!-- ................ listado de ejemplares a eliminar ...........-->

		<c:if test="${not empty listadoEjemplaresEliminar}">
			<c:out
				value="Seleccione los ejemplares del libro ${titulo} a eliminar"></c:out>
			<form name="seleccionejemplares" method="post"
				action="/Biblioteca/controller">
				<p align="center">
					<input type="button" name="marcartodos" onclick="marcar(this.form)"
						value="Marcar todos" />
						<input type="button" name="desmarcartodos" onclick="desmarcar(this.form)"
						value="Desmarcar todos" />
				</p>
				<c:forEach items="${listadoEjemplaresEliminar}" var="ejemplar">
					<p align="center">${ejemplar.idejemplar}<input type="checkbox"
							name="ejemplaraeliminar" value="${ejemplar.idejemplar}">
					</p>
				</c:forEach>
				<input type="hidden" name="operacion"
					value="eliminarejemplaresseleccionados" />
				<p align="center">
					<input type="submit" value="Eliminar ejemplares" />
				</p>
			</form>
		</c:if>
	</div>
</body>
</html>