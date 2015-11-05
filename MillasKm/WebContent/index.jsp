<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<form style="height: 88px; width: 316px" method="post" action="/MillasKm/controlador">
		<table id="tabla" style="width: 300px;">
			<tr>
				<td>Operación</td>
				<td><select name="operacion" id="operacion" style="width: 115px; ">
				<option value="millasKm">Millas a Km</option>
				<option value="kmMillas">Km a Millas</option>
				</select></td>
			</tr>
			<tr>
				<td>Número</td>
				<td style="width: 122px; "><input type="text" name="cantidad" id="cantidad" style="width: 115px; height: 21px"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Calcular"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>