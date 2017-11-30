<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!doctype html>
<html lang='nl'>
<head></head>
<body>
	<h1>Wijn toevoegen aan mandje</h1>
	<table>
		<tbody>
			<tr>
				<td>Land</td>
				<td>${wijn.soort.land.naam}</td>
			</tr>
			<tr>
				<td>Soort</td>
				<td>${wijn.soort.naam}</td>
			</tr>
			<tr>
				<td>Jaar</td>
				<td>${wijn.jaar}</td>
			</tr>
			<tr>
				<td>Beoordeling</td>
<%-- 				<td><c:forEach begin='0' end='${wijn.beoordeling-1}'> --%>
<!-- 						<span>&#9733;</span> -->
<%-- 					</c:forEach></td> --%>
			</tr>
			<tr>
				<td>Prijs</td>
				<td>${wijn.prijs}</td>
			</tr>
		</tbody>
	</table>
	<form method='post'>
		<label>Aantal flessen<span>${fouten.aantal}</span>
			<input type='number' name='aantal' value='${aantal}' required autofocus></label>
<%-- 		<input hidden='true' name='wijnid' value='${wijn.id}'>  --%>
		<input type='submit' value='Toevoegen'>
	</form>
</body>
</html>
