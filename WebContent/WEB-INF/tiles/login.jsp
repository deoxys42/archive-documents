<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
	$(document).ready(function() {
		document.form.j_username.focus();
	});
</script>

<div id="login">
	<h3>Увійдіть у систему, ввівши свої персональні дані:</h3>
	
	<form name="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		<input id="username" name="j_username" type="text" autocomplete="off" />
		<input id="password" name="j_password" type="password" />
		<input id="submit" type="submit" value="Увійти" />
	</form>

	<c:if test="${param.error != null }">
		<p class="errormessage">Неправильно введено дані. Спробуйте знову.</p>
	</c:if>
</div>