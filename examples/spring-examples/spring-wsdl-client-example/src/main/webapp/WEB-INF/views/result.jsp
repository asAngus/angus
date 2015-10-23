<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test="${not empty error}">
		<h1>${error}</h1>
	</c:if>

	<c:choose>
		<c:when test="${weather.status == 'Success'}">
			<h1>${weather.location}</h1>
			<dl>
				<dt>Temperature:</dt>
				<dd>${weather.temperature}</dd>
			</dl>
			<dl>
				<dt>Visibility:</dt>
				<dd>${weather.visibility}</dd>
			</dl>
			<dl>
				<dt>RelativeHumidity:</dt>
				<dd>${weather.relativeHumidity}</dd>
			</dl>
			<dl>
				<dt>Pressure:</dt>
				<dd>${weather.pressure}</dd>
			</dl>
		</c:when>
		<c:otherwise>
			<h1>Het weerbericht kon niet worden gevonden voor ${country},
				${city}</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>