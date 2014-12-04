<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ page import="rmiserver.application.Meeting"%>
<%@ page import="rmiserver.application.User"%>
<%@ page import="rmiserver.application.Action"%>
<%@ page import="rmiserver.application.Item"%>
<%@ page import="rmiserver.application.Key"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="login.model.LoadBean"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Meeto</title>
		<link rel="stylesheet" type="text/css" href="styles.css">
		
		<script type="text/javascript">
		
	        var websocket = null;
	
	        window.onload = function() {
	            connect('ws://' + window.location.host + '/Meetoj7/ws');
	            document.getElementById("chat").focus();
	        }
	
	        function connect(host) { // connect to the host websocket
	            if ('WebSocket' in window)
	                websocket = new WebSocket(host);
	            else if ('MozWebSocket' in window)
	                websocket = new MozWebSocket(host);
	            else {
	                writeToHistory('Get a real browser which supports WebSocket.');
	                return;
	            }
	
	            websocket.onopen    = onOpen; // set the event listeners below
	            websocket.onclose   = onClose;
	            websocket.onmessage = onMessage;
	            websocket.onerror   = onError;
	        }
	
	        function onOpen(event) {
	            //writeToHistory('Connected to ' + window.location.host + '.');
	            
	            document.getElementById('chat').onkeydown = function(key) {
	                if (key.keyCode == 13)
	                    doSend(); // call doSend() on enter key
	            };
	        }
	        
	        function debugi(){
	        	console.log(document.getElementById('usermeet').value);
	        	sendNotify(document.getElementById('usermeet').value, ' meeting junk');
	        }
	        
	        function debugu(){
	        	console.log(document.getElementById('useraction').value);
	        	sendNotify(document.getElementById('useraction').value, ' action junk');
	        }
	        
	        function sendNotify(text, topic) {
	            var message = "alert " + text + topic;
	            websocket.send(message);
	        }
	        
	        
	        function onClose(event) {
	            writeToHistory('WebSocket closed.');
	            document.getElementById('chat').onkeydown = null;
	        }
	        
	        function onMessage(message) { // print the received message
	        	var splited = (message.data).split(" ");
	        	if(splited[0] === "alert")
	        		if(splited[2] === "meeting")
	            		alert('You were invited to a meeting');
	        		else if(splited[2] === "action")
	        			alert('You have been assigned a task');
	        	else
	        		writeToHistory(message.data);
	        }
	        
	        function onError(event) {
	            writeToHistory('WebSocket error (' + event.data + ').');
	            document.getElementById('chat').onkeydown = null;
	        }
	        
	        function doSend() {
	            var message = document.getElementById('chat').value;
	            if (message != '')
	                websocket.send(message); // send the message
	            document.getElementById('chat').value = '';
	        }
	
	        function writeToHistory(text) {
	            var history = document.getElementById('history');
	            var line = document.createElement('p');
	            line.style.wordWrap = 'break-word';
	            line.innerHTML = text;
	            history.appendChild(line);
	            history.scrollTop = history.scrollHeight;
	        }
			
	    </script>
	</head>
	<body>	
		<div id="wrapper">
			<div id="meet-info">			
				<form id="menu" name="menu" action="menu.jsp" method="post">
					<%
						int pos = 0;
					%>	
					
					<select name="meeting" onchange="document.getElementById('menu').submit();">
						<option value="-1">
							Select a meeting
						</option>
						<c:forEach var="meet" items="${session.meetings}">
							<option value=${meet.getId()}>
								 <%
								 	pos = pos + 1;
								 %>
								 <%= pos %>
							</option>
						</c:forEach>
					</select>
				</form>
				<%
					String value = request.getParameter("meeting");
					if(value != null){	
				    	session.setAttribute("meeting_id", value);
				    	session.setAttribute("item_id", "-1");
				    	session.setAttribute("action_id", "-1");
				    }
				%>
				<%= "Meeting id=" + session.getAttribute("meeting_id")%>
				
				<%
					ArrayList<Action> ma = null;
					if(Integer.parseInt(session.getAttribute("meeting_id").toString()) != -1){
						LoadBean lb = new LoadBean();
						ma = lb.loadUserActions(session.getAttribute("meeting_id").toString(),
												session.getAttribute("username").toString());
						session.setAttribute("actions", ma);
					}
				%>
				<%
					ArrayList<Item> mi = null;
					if(Integer.parseInt(session.getAttribute("meeting_id").toString()) != -1){
						LoadBean lb = new LoadBean();
						mi = lb.loadItems(session.getAttribute("meeting_id").toString());
						session.setAttribute("items", mi);
					}
				%>
				
				<br>
				<form id="asd" name="asd" action="menu.jsp" method="post">
					<%
						int ipos = 0;
					%>
				
					<select name="item" onchange="document.getElementById('asd').submit();">
						<option value="-1">
							Select an item
						</option>
						<c:forEach var="item" items="${session.items}">
							<option value=${item.getId()}>
								 <%
									ipos = ipos + 1;
								 %>
								 <%= ipos %>
							</option>
						</c:forEach>
					</select>
				</form>
				
				<%
					String chat_messages = "";
					String value_ = request.getParameter("item");
					if(value_ != null){	
				    	session.setAttribute("item_id", value_);
				    	LoadBean tmpasd = (LoadBean) session.getAttribute("loadBean");
				    	chat_messages = tmpasd.loadChat(value_);
				    	session.setAttribute("chat_messages", chat_messages);
				    }
				%>
				
				<%= "Item id=" + session.getAttribute("item_id")%>
				<br>
				
				<% 
					ArrayList<Key> ik = null;
					if(Integer.parseInt(session.getAttribute("item_id").toString()) != -1){
						LoadBean lb = new LoadBean();
						ik = lb.loadKeys(session.getAttribute("item_id").toString());
						session.setAttribute("keys", ik);
					}
				%>
				
				<form id="kk" name="kk" action="menu.jsp" method="post">
					<%
						int apos = 0;
					%>
				
					<select name="action" onchange="document.getElementById('kk').submit();">
						<option value="-1">
							Select an action
						</option>
						<c:forEach var="action" items="${session.actions}">
							<option value=${action.getId()}>
								 <%
								 	apos = apos + 1;
								 %>
								 <%= apos %>
							</option>
						</c:forEach>
					</select>
				</form>
				
				<%
					String value__ = request.getParameter("action");
					if(value__ != null){	
				    	session.setAttribute("action_id", value__);
				    }
				%>
				<%= "Action id=" + session.getAttribute("action_id")%>
				<br><br>
				Meeting Agenda:
				<table>
					<c:forEach var="item" items="${session.items}">
						<tr>
							<td>
								<input size = "8" type="text" value=${item.getTitle()}>
								<input size = "8" type="text" value=${item.getDesc()}>
							</td>	
						</tr>
					</c:forEach>
				</table>
				
				<br>
				Item keydecisions:
				<table>
					<c:forEach var="key" items="${session.keys}">
						<tr>
							<td>
								<input size = "20" type="text" value=${key.getDesc()}>
							</td>
						</tr>
					</c:forEach>
				</table>
							
				<br>
				Personal TODO-List:
				<table>
					<c:forEach var="action" items="${session.actions}">
						<tr>
							<td>
								<input size = "23" type="text" value=${action.getDesc()}>
							</td>	
						</tr>
					</c:forEach>
				</table>
				
				<noscript></noscript>
				<div>
				    <div id="contentor">
				    	<div id="history">
				    	</div>
				    </div>
				    <p><input type="text" placeholder="type to chat" id="chat"></p>
				</div>
			</div>
		
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
				
				<br>
				<% 
					String meet_users = "";
					if(Integer.parseInt(session.getAttribute("meeting_id").toString()) != -1){
						LoadBean lb = new LoadBean();
						ArrayList<User> mu = lb.loadMeetUsers(session.getAttribute("meeting_id").toString());
						
						for(int i = 0; i < mu.size(); i++){
							meet_users += mu.get(i).getUser() + " - ";
						}
					}
				%>
				
				Meet Users: <br>
				<%= meet_users %>
			</div>
		</div>
		
		<div id="buttons">
			<s:form method="post">
				<br>
				<s:submit value="Schedule" onclick="form.action='schedule';"/>
				<s:submit value="Remove" onclick="form.action='remove';"/>
				<s:submit value="New Key"/>
				<s:submit value="Action Done" onclick="form.action='completed';"/>
				<s:submit value="All Action"/>
			</s:form>
			<br>
			<s:form method="post">
				<s:textfield id="usermeet" name="username" placeholder = "Type a username"/>
				<s:submit id="meetinvite" value = "Add User" onclick="form.action='add_user';debugi();"/>
				<s:submit value = "Remove User" onclick="form.action='del_user';"/>
				<s:submit value = "Decline Meeting" onclick="form.action='decline';"/>
			</s:form>
			<br>
			<s:form method="post">
				<s:textfield name="title" placeholder = "New Title"/>
				<s:textfield name="description" placeholder = "New Description"/>
				<s:textfield name="date" placeholder = "New Date"/>
				<s:textfield name="location" placeholder = "New Location"/>
				<s:submit value = "Update Meeting" onclick="form.action='update_meeting';"/>
			</s:form>
			<br>
			<s:form method="post">
				<s:textfield name="description" placeholder = "Change Description"/>
				<s:textfield name="title" placeholder = "Change Title"/>
				<s:submit value="New Item" onclick="form.action='add_item';"/>
				<s:submit value="Remove Item" onclick="form.action='del_item';"/>
				<s:submit value="Update Item" onclick="form.action='desc_item';"/>
			</s:form>
			<br>
			<s:form method="post">
				<s:textfield name="description" placeholder = "Type a Description"/>
				<s:submit value="New Key Decision" onclick="form.action='new_key';"/>
			</s:form>
			<br>
			<s:form method="post">
				<s:textfield name="description" placeholder = "Type a description"/>
				<s:textfield id="useraction" name="username" placeholder = "Type a username"/>
				<s:submit value="Assign Task" onclick="form.action='assign';debugu();"/>
			</s:form>
		</div>
	</body>
</html>