<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div id="login">
	<h3>Увійдіть у систему, ввівши свої персональні дані:</h3>
	
	<form name="form" 
			action="${pageContext.request.contextPath}/j_spring_security_check" 
			method="post">
			
		<input name="j_username" type="text" autocomplete="off" 
				placeholder="Паспорт" autofocus />
		<input name="j_password" placeholder="Пароль" type="password" />
		<input type="submit" value="Увійти" />
	</form>

	<c:if test="${param.error != null }">
		<p class="errormessage">Неправильно введено дані. Спробуйте знову.</p>
	</c:if>
	
	<div class="register-or-login">
		<a class="link" href="${pageContext.request.contextPath}/registration">
			Зарегіструйтесь</a><span>, якщо не маєте акаунта.</span>
	</div>
</div>