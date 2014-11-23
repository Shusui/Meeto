<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Meeto</title>
	</head>
	<body>
		<s:form action = "login" method = "post">
			<s: text name = "Username: "/>
			<s:textfield key = "user"/>
			<br>
			<s: text name = "Password: "/>
			<s:password key = "pw"/>
			<s:submit/>
		</s:form>
	</body>
</html>