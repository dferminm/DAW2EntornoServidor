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
		<%
			@SuppressWarnings("unchecked")
			ArrayList<Socio> listadoSocios = (ArrayList<Socio>) request.getAttribute("listadoSocios");
		%>
		<c:if test="${not empty listadoSocios}">
			<table width="100%" border="1">
				<caption>Listado de Socios</caption>
				<tr>
					<th scope="col">IDSOCIO</th>
					<th scope="col">EMAIL</th>
					<th scope="col">NOMBRE</th>
					<th scope="col">DIRECCION</th>
				</tr>
				<c:forEach items="${listadoSocios}" var="socio">
					<tr>
						<td>${socio.idsocio}</td>
						<td>${socio.email}</td>
						<td>${socio.nombre}</td>
						<td>${socio.direccion}</td>
					</tr>
				</c:forEach>

			</table>
			<c:set var="REGISTROSPORPAGINA" scope="session"
				value="${sessionScope.nrp}" />
			<c:remove var="nrp" scope="session" />
			<c:set var="PAGINAACTUAL" scope="session"
				value="${sessionScope.paginaactual}" />
			<c:remove var="pag" scope="session" />
			<c:set var="TOTALREGISTROS" scope="session"
				value="${sessionScope.totalregistros}" />
			<c:remove var="totalregistros" scope="session" />
			<c:set var="PAGINAMASALTA" scope="session"
				value="${sessionScope.paginamasalta}" />
			<c:remove var="paginamasalta" scope="session" />

			<div>
				Total de registros:
				<c:out value="${TOTALREGISTROS}" />
				. Mostrando registros desde ${(REGISTROSPORPAGINA*PAGINAACTUAL)+1} a
				${(REGISTROSPORPAGINA*PAGINAACTUAL)+REGISTROSPORPAGINA<TOTALREGISTROS?(REGISTROSPORPAGINA*PAGINAACTUAL)+REGISTROSPORPAGINA:TOTALREGISTROS}
				<a
					href="/Biblioteca/controller?operacion=listarSocios&pag=${PAGINAACTUAL-1>=0?PAGINAACTUAL-1:PAGINAMASALTA}&nrp=${REGISTROSPORPAGINA}">Anterior</a>
				<a
					href="/Biblioteca/controller?operacion=listarSocios&pag=${PAGINAACTUAL+1>PAGINAMASALTA?0:PAGINAACTUAL+1}&nrp=${REGISTROSPORPAGINA}">Siguiente</a>
			</div>
		</c:if>
	</div>
</body>
</html>