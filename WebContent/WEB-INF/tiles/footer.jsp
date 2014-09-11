<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a id="home" class="link" href="${pageContext.request.contextPath}/">
	Вернутись на домашню сторінкy
</a>

<sec:authorize access="!isAuthenticated()">
	<a class="link" id="login" href="<c:url value='/login' />">
		Увійти в кабінет
	</a>
	<span>|</span>
	<a class="link" id="registration" href="<c:url value='/registration' />">
		Регістрація
	</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a class="link" id="logout" href="<c:url value='/j_spring_security_logout' />">
		Вийти з кабінету
	</a>
</sec:authorize>