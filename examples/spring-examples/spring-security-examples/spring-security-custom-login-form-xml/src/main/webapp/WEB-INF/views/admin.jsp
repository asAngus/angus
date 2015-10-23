<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<body>
	<ul>
		<li><a href="./">Home</a></li>

		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<li><a href="./admin">Admin</a></li>
		</sec:authorize>

		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<sec:authorize ifAllGranted="ROLE_USER">
			<li><a href="${logoutUrl}">Logout</a></li>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<li><a href="./user">Login</a></li>
		</sec:authorize>
	</ul>
	welcome
</body>
</html>