<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%/*

String operacion = request.getParameter("operacion");
String cantidad = request.getParameter("cantidad");
if(operacion.equals("kmMillas"))
	out.println("<h1>"+cantidad+"Millas="+(Double)request.getAttribute("resultado")+" millas </h1>");
else
	out.println("<h1>"+cantidad+"Millas="+(Double)request.getAttribute("resultado")+" km </h1>");
*/
%>

<c:set var="resultado" value="1000" scope="page"></c:set>
<c:choose>
	<c:when test="${param.operacion.equals('kmmillas')}">
	<h1>
		<c:out value="${param.cantidad} km son ${requestScope.resultado} millas"></c:out>
	</h1>
	</c:when>
	<c:otherwise>
	<h1>
		<c:out value="${param.cantidad} Millas son ${requestScope.resultado} Km"></c:out>
	</h1>
	</c:otherwise>
</c:choose>
</body>
</html>