<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css">
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<h2 align="center">
			<c:out
				value="Se ha producido el siguiente error: ${error}"></c:out>
		</h2>
	</div>
</body>
</html>