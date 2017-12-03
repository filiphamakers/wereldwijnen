<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id='menu'>
	<h1>Wereldwijnen</h1>
	<ul class='lijst-horizontaal'>
		<c:forEach items='${landen}' var='land'>
			<c:url value='/soorten.htm' var='soortenUrl'>
				<c:param name='landid' value='${land.id}' />
			</c:url>
			<li><a href='${soortenUrl}'><img alt='${land.naam}'
					src='img/landen/${land.id}.png'></a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty winkelmandje}'>
		<a href='<c:url value='/winkelmandje.htm'/>'>
		<img alt='winkelmandje' src='img/mandje.png'></a>
	</c:if>
</nav>