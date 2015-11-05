<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GlassFish JSP Page</title>
<link rel="Stylesheet" type="text/css"
	href="/Biblioteca/resources/css/estilo.css">
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:out value="Bienvenido:${pageContext.request.userPrincipal.name}"></c:out>
		<br>
		<c:if test="<%=request.isUserInRole("administrativos")%>">
			<c:out value="Perteneces al grupo administrativos"></c:out>
		</c:if>
	</div>
</body>
</html>
