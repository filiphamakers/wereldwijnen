<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<h1>Wereldwijnen</h1>
	<ul>
		<c:forEach items='${landen}' var='land'>
			<c:url value='/soorten.htm' var='soortenUrl'>
				<c:param name='landid' value='${land.id}' />
			</c:url>
			<li><a href='${soortenUrl}'><img alt='${land.naam}'
					src='img/landen/${land.id}.png'></a></li>
		</c:forEach>
	</ul>
</nav>