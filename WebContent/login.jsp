<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles.css">
		<title>Meeto</title>
	</head>
	<body id="login">
		<img id="logo" src="meeto2.png">
		<s:form id="login-form" method="post">
			<div id="acc">Meeto account:</div>
			<s:textfield name="username" placeholder="Username" size="25"/>
			<br>
			<s:password name="password" placeholder="Password" size="25"/>
			<br>
			
			<c:choose>
				<c:when test="${session.noCreds == true}">
					No credentials inserted.
				</c:when>
				
				<c:when test="${session.noUser == true}">
					No username inserted.
				</c:when>
	
				<c:when test="${session.noPw == true}">
					No password inserted.
				</c:when>
				
				<c:when test="${session.failLogin == true}">
					Wrong username and/or password.
				</c:when>
				
				<c:when test="${session.invalidUser == true}">
					User already in use.
				</c:when>
				
				<c:when test="${session.regError == true}">
					Register error. Please try again.
				</c:when>
				
				<c:when test="${session.regSuccess == true}">
					Successfully registered. Please login now.
				</c:when>
	
				<c:otherwise>
					<br>
				</c:otherwise>	
			</c:choose>
			
			<div id="lol">
				<s:submit value="Login" onclick="form.action='login';" cssClass="login-button"/>
				<s:submit value="Register" onclick="form.action='register';"/>
			</div>
			
			<style>
				.login-button{
					width: 80px;
				}
			</style>
			
		</s:form>
		
		<div id="bottom-pane">
		
		</div>
	</body>
</html>