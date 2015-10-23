<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<ul>
    <li><a href="./">Home</a></li>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="./admin">Admin</a></li>
    </sec:authorize>

    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <sec:authorize access="hasRole('ROLE_USER')">
        <li><a href="${logoutUrl}">Logout</a></li>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_USER">
        <li><a href="./user">Login</a></li>
    </sec:authorize>
</ul>

<sec:authorize access="isRememberMe()">
    <h2># This user is login by "Remember Me Cookies".</h2>
</sec:authorize>

<sec:authorize access="isFullyAuthenticated()">
    <h2># This user is login by username / password.</h2>
</sec:authorize>

</body>
</html>