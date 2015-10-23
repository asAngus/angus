<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Velocity</title>
</head>
<body>
	<c:if test="${not empty receiver}">
		<h3>Mail to ${receiver}</h3>
	</c:if>
	<form action="index.html" method="post">
		<table>
			<tr>
				<td>Receiver:</td>
				<td><input type="text" name="receiver" value="${receiver}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>