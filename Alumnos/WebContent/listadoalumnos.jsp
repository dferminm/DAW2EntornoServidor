<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.ArrayList, entidades.Alumno"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${not empty listadoAlumnos}">
		<table width="100%" border="1">
			<caption>Listado de Alumnos</caption>
			<tr>
				<th scope="col">DNI</th>
				<th scope="col">NOMBRE</th>
				<th scope="col">CURSO</th>
			</tr>
			<c:forEach items="${listadoAlumnos}" var="alumno">
				<tr>
					<td>${alumno.dni}</td>
					<td>${alumno.nombre}</td>
					<td>${alumno.curso}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>