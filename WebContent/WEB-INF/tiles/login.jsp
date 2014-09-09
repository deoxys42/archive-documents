<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
	$(document).ready(function() {
		document.form.j_username.focus();
	});
</script>

<h3>Here must be a login page!</h3>
Чек check


<form name="form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
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