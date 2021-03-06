<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html>
<v:head/>
<body>
	<v:menu />
	<h1>Soorten uit ${land.naam}</h1>
	<ul class='lijst-horizontaal'>
		<c:forEach items='${soorten}' var='soort'>
			<c:url value='/soorten.htm' var='wijnenUrl'>
				<c:param value='${soort.id}' name='soortid' />
				<c:param value='${soort.land.id}' name='landid' />
			</c:url>
			<li><a href='${wijnenUrl}'>${soort.naam}</a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty wijnen}'>
		<h1>Wijnen uit ${soort.naam}</h1>
		<ul>
			<c:forEach items='${wijnen}' var='wijn'>
				<c:url value='/bestellen.htm' var='bestellenUrl'>
					<c:param name='wijnid' value='${wijn.id}' />
				</c:url>
				<li><a href='${bestellenUrl}'> ${wijn.jaar} <c:forEach
							begin='0' end='${wijn.beoordeling-1}'>
							<span>&#9733</span>
						</c:forEach>
				</a></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>