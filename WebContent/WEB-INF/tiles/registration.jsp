<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="registration">
	<h3>Введіть необхідні дані для регістрації в системі:</h3>
	
	<sf:form action="${pageContext.request.contextPath}/register" method="post" commandName="user">
		<sf:input name="name" path="name" type="text" placeholder="Ім’я" autocomplete="off"  />
		<span class="error"><sf:errors path="name"/></span>
		<sf:input name="surname" path="surname" type="text" placeholder="Прізвище" autocomplete="off" />
		<span class="error"><sf:errors path="surname"/></span>
		<sf:input name="passport" path="passport" type="text" placeholder="Паспорт" autocomplete="off" />
		<span class="error"><sf:errors path="passport" /></span>
		<sf:input name="password" path="password" type="password" placeholder="Пароль" />
		<span class="error"><sf:errors path="password" /></span>
		<input name="confirm_password" type="password" placeholder="Підтвердіть пароль" />
		<input type="submit" value="Зарегіструватись" />
	</sf:form>
	
	<div class="register-or-login">
		<a class="link" href="${pageContext.request.contextPath}/login">
			Увійдіть</a><span>, якщо вже регіструвались.</span>
	</div>
</div>