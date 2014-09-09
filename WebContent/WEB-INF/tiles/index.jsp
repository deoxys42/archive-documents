<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<h3>Home HTML page content is not the desirable one!</h3>

<a href="${pageContext.request.contextPath}/login">Go to the login page</a><br/>
		
<c:forEach var="user" items="${users}">
	Пароль: ${user.passport}<br/>
	Ім'я: ${user.name}<br/>
	Прізвище: ${user.surname}<br/>
</c:forEach>