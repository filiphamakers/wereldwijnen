<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html>
<head>
<title></title>
</head>
<body>
	<v:menu/>
	<h1>Soorten uit ${land.naam}</h1>
	<ul>
		<c:forEach items='${land.soorten}' var='soort'>
			<li>${soort.naam}</li>
		</c:forEach>
	</ul>
</body>
</html>