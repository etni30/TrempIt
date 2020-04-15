<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Controller.*, Model.* "%>
<%
try{
	int idGroup = Integer.parseInt(request.getParameter("idGroup"));
	Passenger pass = (Passenger)session.getAttribute("User");
	boolean successJoin = pass.joinGroup(idGroup);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
	<%	if(successJoin){ %>
			<script >alert("success! \n found more data in the 'trempBox'");</script >
			<script >window.location.href = "LiveChat.jsp";</script >
	<%}else{ %>
			<script >alert("failed \n try again");</script >
			<script >window.location.href = "automaticSreach.jsp";</script >
<%
	}
	// if time expired or someone tried to get access without permission
	}catch(NullPointerException e){
		%><script >alert("Connection has lost \n log in again");</script >
			<script >window.location.href = "clear_page.jsp";</script >
<%	}catch(Exception e){
%>	<script >alert("somethin went wrong\n log in again");</script >
	<script >window.location.href = "clear_page.jsp";</script >	
<%}%>	
</body>
</html>