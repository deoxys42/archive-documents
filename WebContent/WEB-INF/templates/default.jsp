<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
		<link href="${pageContext.request.contextPath}/static/css/
			<tiles:insertAttribute name="css"></tiles:insertAttribute>"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- inject jsp files with help of apache-tiles definition attributes -->
		<div>
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		<div>
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</div>
		<div>
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		</div>
	</body>
</html>