<!-- must be within a jsp because of using the jstl libs -->

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(validatePasswordsMatch);
		$("#password_confirm").keyup(validatePasswordsMatch);
		$("#form").submit(canSubmit);
	}
	
	function validatePasswordsMatch() {
		var password = $("#password").val();
		var password_confirm = $("#password_confirm").val();
		
		if (password.length > 7 || password_confirm.length > 7) {
			if (password == password_confirm) {
				$("#passwords_match_message").
					text("<fmt:message key='MatchedPasswords.user.password'/>");
				$("#passwords_match_message").removeClass("passwords_nomatch");
				$("#passwords_match_message").addClass("passwords_match");
			}  else {
				$("#passwords_match_message").
					text("<fmt:message key='UnmatchedPasswords.user.password'/>");
				$("#passwords_match_message").removeClass("passwords_match");
				$("#passwords_match_message").addClass("passwords_nomatch");
			}
		}
	}
	
	function canSubmit() {
		var password = $("#password_input").val();
		var password_confirm = $("#password_confirm").val();
		
		if (password != password_confirm) {
			$("#passwords_match_message").
				text("<fmt:message key='UnmatchedPasswords.user.password'/>");
			$("#passwords_match_message").removeClass("passwords_match");
			$("#passwords_match_message").addClass("passwords_nomatch");
			return false;
		} else {
			return true;
		}
	}
	
	$(document).ready(onLoad);
</script>