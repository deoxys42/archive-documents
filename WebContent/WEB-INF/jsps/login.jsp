<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Вхід в систему</title>
	</head>

	<body onload="document.form.j_username.focus();">
		<form name="form"
				action="${pageContext.request.contextPath}/j_spring_security_check"
				method="post">
			
			<table>
				<tr>
					<td>Дані паспорта:</td>
					<td><input name="j_username" type="text" value="" /></td>
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input name="j_password" type="password" /></td>
				</tr>
				<tr>
					<td><input value="Увійти" type="submit" /></td>
				</tr>
			</table>
		</form>
		<c:if test="${param.error != null }">
			<p>Неправильно введено дані. Спробуйте знову.</p>
		</c:if>
	</body>
</html>
