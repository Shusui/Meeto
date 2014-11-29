<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Meeto</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="box">
				<!--
				<input type="text" placeholder="please">
				-->
				
				<table>
					<c:forEach var="meet" items="${session.meetings}">
						<tr>
							<td>
								<input size = "8" type="text" value=${meet.getTitle()}>
								<input size = "8" type="text" value=${meet.getLeader()}>
								<input size = "8" type="text" value=${meet.getOutcome()}>
								<input size = "8" type="text" value=${meet.getDate()}>
								<input size = "8" type="text" value=${meet.getLocal()}>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="meet-info">
			</div>
		</div>
		
		<div id="buttons">
			<s:form method="post">
				<br>
				<s:submit value="Schedule" onclick="form.action='schedule';"/>
				<s:submit value="Remove"/>
				<s:submit value="Reload"/>
				<s:submit value="Add"/>
				<s:submit value="Remove"/>
				<s:submit value="Decline"/>
				<s:submit value="New Item"/>
				<s:submit value="Remove Item"/>
				<s:submit value="New Key"/>
				<s:submit value="Assign Task"/>
				<s:submit value="Action Done"/>
				<s:submit value="All Action"/>
			</s:form>
		</div>
	</body>
</html>