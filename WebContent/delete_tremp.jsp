<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Model.Driver" %>
<%		
	try{
		//parameter and initialization
		Driver user = (Driver)session.getAttribute("User");
		//save userName for next page
		session.setAttribute("User", user);
		
		//delete the ride		
		user.deleteRide();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
		<script >alert("the tremp was deleted ");</script >
		<script >window.location.href = "mainpage.jsp";</script >	
<% }catch(Exception e){
  %>	<script >alert("Connection has lost \n log in again");</script >
		<script >window.location.href = "clear_page.jsp";</script >	
<%}%>
</body>
</html>