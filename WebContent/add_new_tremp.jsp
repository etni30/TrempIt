<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Model.*"%>
<%
	try{
		Model m = new Model();
		String Origin = request.getParameter("Origin");
		String destination = request.getParameter("destination");
		String time = request.getParameter("time");
		String userName = session.getAttribute("userName").toString();
		User user = m.getUser(userName);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
<% m.addRide(user.getIdUser(), time, Origin, "Bar-ilan", destination, "Aco"); %>
				<script type="text/javascript"> 
					alert("success, for more data go to tremp zone");
					window.location.href = "mainpage.jsp";
				</script>
<%}catch(Exception e){%>
				<script type="text/javascript"> 
					alert("faild: you already have a ride"); window.location.href = "mainpage.jsp";
				</script>
				<% } %>
</body>


</html>