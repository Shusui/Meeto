<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Meeto</title>
	</head>
	<body>
		
		<s:form action = "login" method = "post">
			<s:text name = "Username: "/>
			<s:textfield name = "username"/>
			<br>
			<s:text name = "Password: "/>
			<s:password name = "password"/>
			<s:submit/>
		</s:form>
		<br><br>
		
		<c:choose>
			<c:when test="${session.noCreds == true}">
				<p>No credentials inserted.</p>
			</c:when>
			
			<c:when test="${session.noUser == true}">
				<p>No username inserted.</p>
			</c:when>
			
			<c:when test="${session.noPw == true}">
				<p>No password inserted.</p>
			</c:when>
			
			<c:otherwise>
				<p>Wrong username and/or password.</p>
			</c:otherwise>
		</c:choose>
		
	</body>
</html>