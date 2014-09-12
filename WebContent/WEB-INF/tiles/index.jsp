<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="index">
	<h3>Вітаємо в Archive Documents!</h3>
	
	<sec:authorize access="!isAuthenticated()">
		<div id="images">
			<a href="<c:url value='/login' />"></a>
			<a href="<c:url value='/registration' />"></a>
		</div>
		<div id="buttons">
			<a class="link" href="<c:url value='/login' />">
				Увійти в кабінет
			</a>
			<a class="link" href="<c:url value='/registration' />">
				Регістрація
			</a>
		</div>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<div id="images">
			<a href="<c:url value='/usersroom' />"></a>
			<a href="<c:url value='/logout' />"></a>
		</div>
		<div id="buttons">
			<a class="link" href="<c:url value='/usersroom' />">
				До персональної сторінки
			</a>
			<a class="link" href="<c:url value='/j_spring_security_logout' />">
				Вийти з кабінету
			</a>		
		</div>
	</sec:authorize>
</div>