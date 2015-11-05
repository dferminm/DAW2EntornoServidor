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
			<jsp:directive.include file="./WEB-INF/menu.jspf" />
			<jsp:scriptlet>response.sendRedirect("/Biblioteca/socios/home.jsp");</jsp:scriptlet>
		</div>
	</div>
</body>
</html>
