<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="registration">
	<h3>Введіть необхідні дані для регістрації в системі:</h3>
	
	<sf:form action="${pageContext.request.contextPath}/createaccount.jsp" method="post">
		<input id="name" name="name" type="text" placeholder="Ім’я" autocomplete="off" autofocus />
		<input id="surname" name="surname" type="text" placeholder="Прізвище" autocomplete="off" />
		<input id="passport" name="passport" type="text" placeholder="Паспорт" autocomplete="off" />
		<input id="password" name="password" placeholder="Пароль" type="password" />
		<input id="submit" type="submit" value="Зарегіструватись" />
	</sf:form>
	
	<div class="register-or-login">
		<a class="link" href="${pageContext.request.contextPath}/login">
			Увійдіть</a><span>, якщо вже регіструвались.</span>
	</div>
</div>