<%@tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<ul>
		<c:forEach items='${landen}' var='land'>
			<c:url value='' var='soortenPerLandUrl'>
				<c:param name='land' value='${land.naam}' />
			</c:url>
			<li><a href='${soortenPerLandUrl}'><img alt='${land.naam}'
					src='img/landen/${land.id}.png'></a></li>
		</c:forEach>
	</ul>
</nav>