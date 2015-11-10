<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="/CarroCompra/resources/css/estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de formalizacion</title>
<script type="text/javascript">
	function validar() {
		if (document.getElementById("idcliente").value == "") {
			alert("El campo idcliente está vacío y es obligatorio.")
			return false;
		} else if (document.getElementById("direccion").value == "") {
			alert("El campo dirección está vacío y es obligatorio.")
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="container">
		<div id="header"></div>
	</div>
	<h2 align="center">Introduzca su identificador de cliente y la
		dirección de envio</h2>
	<form method="post" action="controller" onsubmit="return validar()">
		<p align="center">
			IDCLIENTE*<input type="text" name="idcliente" id="idcliente" />
			DIRECCION DE ENVIO*<input type="text" name="direccion" id="direccion" />
			<input type="hidden" name="operacion" value="formalizar" /><br>
			<br> <input type="submit" value="Finalizar pedido" />
	</form>
	<p align="center">
		<font color="red"><c:out value="${mensajeError}"></c:out></font>
	</p>
	<c:if test="${esNumero == false}">
		<p align="center">
			<font color="red">El id del cliente debe de ser numérico.</font>
		</p>
	</c:if>
</body>
</html>