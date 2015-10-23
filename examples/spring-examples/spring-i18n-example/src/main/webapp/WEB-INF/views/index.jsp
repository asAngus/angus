<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta charset="utf-8">
</head>
<body>
	<h2>${user}</h2>
	<ul>
		<li><spring:message code="label.birthday" />: <fmt:formatDate value="${user.birthDay.time}" type="date" dateStyle="short" /></li>
		<li><spring:message code="label.gender" />:
			<c:choose>
				<c:when test="${user.male}"><spring:message code="label.male" /></c:when>
				<c:otherwise><spring:message code="label.female" /></c:otherwise>
			</c:choose>
		</li>
	</ul>
	<ul>
		<li><a href="?lang=zh">Chinese</a></li>
		<li><a href="?lang=en">English</a></li>
		<li><a href="?lang=nl">Nederlands</a></li>
	</ul>
</body>
</html>
