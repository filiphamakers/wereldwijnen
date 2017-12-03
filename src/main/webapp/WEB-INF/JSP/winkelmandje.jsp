<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!doctype html>
<html lang='nl'>
<v:head/>
<body>
	<h1>Mandje</h1>
	<table id='mandje-tabel'>
		<thead>
			<tr>
				<td>Wijn</td>
				<td>Prijs</td>
				<td>Aantal</td>
				<td>Te betalen</td>
			</tr>
		</thead>
		<tbody>
			<c:if test='${not empty bestelbonlijnen}'>
				<c:forEach items='${bestelbonlijnen}' var='lijn'>
					<tr>
						<td>${lijn.wijn.soort.land.naam} ${lijn.wijn.soort.naam} ${lijn.wijn.jaar}</td>
						<td>${lijn.prijs}</td>
						<td>${lijn.aantal}</td>
						<td>${lijn.subtotaal}</td>
					</tr>
				</c:forEach>
				<td colspan='3'></td>
				<td>${totaal}</td>
			</c:if>
		</tbody>
	</table>
	<form method='post' id='bestelform'>
	<label>Naam<span>${fouten.naam}</span><input type='text' name='naam' value='${naam}'></label>
	<label>Straat<span>${fouten.straat}</span><input type='text' name='straat' value='${straat}'></label>
	<label>Huisnummer<span>${fouten.huisnummer}</span><input type='text' name='huisnummer' value='${huisnummer}'></label>
	<label>Postcode<span>${fouten.postcode}</span><input type='text' name='postcode' value='${postcode}'></label>
	<label>Gemeente<span>${fouten.gemeente}</span><input type='text' name='gemeente' value='${gemeente}'></label>
	<label><span>${fouten.bestelwijze}</span><input type='radio' name='bestelwijze' value='0' checked>Afhalen</label>
	<label><input type='radio' name='bestelwijze' value='1'>Opsturen</label>
	<input type='submit' value='Als bestelbon bevestigen' id='bestelknop'>
	</form>
	<script>
		document.getElementById('bestelform').onsubmit = function() {
			if (!navigator.cookieEnabled) {
				alert("Dit werkt enkel als cookies aanstaan");
				return false;
			}
			document.getElementById('bestelknop').disabled = true;
		};
	</script>
</body>
</html>
