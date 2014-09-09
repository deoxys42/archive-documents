<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home page</title>
	</head>

	<body>
		<h3>Home HTML page content is not the desirable one!</h3>
    
		<a href="${pageContext.request.contextPath}/login">Go to the login page</a><br/>

		<c:forEach var="user" items="${users}">
			Passport: ${user.passport}<br/>
			Name: ${user.name}<br/>
			Surname: ${user.surname}<br/>
		</c:forEach>
	</body>
</html>
