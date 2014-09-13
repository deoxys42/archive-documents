<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="registration">
	<h3>Введіть необхідні дані для регістрації в системі:</h3>
	
	<sf:form action="${pageContext.request.contextPath}/register" 
			method="post" commandName="user">
		<div>
			<sf:input name="name" path="name" type="text" 
					placeholder="Ім’я" autocomplete="off" />
			<span class="error"><sf:errors path="name"/></span>
		</div>
		<div>
			<sf:input name="surname" path="surname" type="text" 
					placeholder="Прізвище" autocomplete="off" />
			<span class="error"><sf:errors path="surname"/></span>
		</div>
		<div>
			<sf:input name="passport" path="passport" type="text" 
					placeholder="Паспорт" autocomplete="off" />
			<span class="error"><sf:errors path="passport" /></span>
		</div>
		<div>
			<sf:input name="password" path="password" type="password" 
					placeholder="Пароль" id="password" />
			<span class="error"><sf:errors path="password"/></span>
		</div>
		<div>
			<input name="password_confirm" type="password" 
					placeholder="Підтвердіть пароль" id="password_confirm" />
			<span id="passwords_match_message"></span>
		</div>
		<div>
			<input type="submit" value="Зарегіструватись" />
			<div class="register-or-login" >
				<a class="link"
					href="${pageContext.request.contextPath}/login">Увійдіть
				</a><span>, якщо вже регіструвались.</span>
			</div>
		</div>
	</sf:form>
</div>