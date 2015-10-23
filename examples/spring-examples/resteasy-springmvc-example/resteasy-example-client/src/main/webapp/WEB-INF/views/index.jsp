<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<h2>${user}</h2>
<ul>
    <li>Birthday: <fmt:formatDate value="${user.birthDay.time}" type="date" dateStyle="short"/></li>
    <li>Gender:
        <c:choose>
            <c:when test="${user.male}">Male</c:when>
            <c:otherwise>Female</c:otherwise>
        </c:choose>
    </li>
</ul>
</body>
</html>
