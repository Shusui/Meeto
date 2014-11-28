<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Meeto</title>
	</head>
	<body>
		<s:form method = "post">
			<s:text name = "Username: "/>
			<s:textfield name = "username"/>
			<br>
			<s:text name = "Password: "/>
			<s:password name = "password"/>
			<br><br>
			<s:submit value = "Login" onclick="form.action='login';"/>
			<s:submit value = "Register" onclick="form.action='register';"/>
			
		</s:form>
		
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
			
			<c:when test="${session.failLogin == true}">
				<p>Wrong username and/or password.</p>
			</c:when>
			
			<c:when test="${session.invalidUser == true}">
				<p>User already in use.</p>
			</c:when>
			
			<c:when test="${session.regError == true}">
				<p>Register error. Please try again.</p>
			</c:when>
			
			<c:when test="${session.regSuccess == true}">
				<p>Successfully registered. Please login now.</p>
			</c:when>

			<c:otherwise>
			</c:otherwise>	
		</c:choose>
		
	</body>
</html>