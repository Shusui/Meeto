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
		
		<s:if test = "${session.failUser == true && session.failPw == true}">
			No credentials inserted.
		</s:if>
		<s:elseif test = "${session.failUser == true}">
			No username inserted.
		</s:elseif>
		<s:elseif test = "${session.failUser == true}">
			No password inserted.
		</s:elseif>
		<s:else>
			Wrong username and/or password.
		</s:else>
		
	</body>
</html>