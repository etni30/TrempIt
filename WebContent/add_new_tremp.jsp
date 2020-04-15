<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Controller.Controller, Model.*"%>
<%
	try{
		//parameter and initialization
		Controller conn = new Controller();
		Driver user = (Driver)session.getAttribute("User");
		
		//save userName for next page
		session.setAttribute("User", user);
		
		//get data from form
		//get city and station for source and destination
		String[] src = request.getParameter("Origin").toString().split(",");  // source
		String srcStation = src[0];
		String srcCity = src[1];
		String[] dst = request.getParameter("destination").toString().split(",");  // destination
		String dstStation = dst[0];
	 	String dstCity = dst[1];
		//get departure time
	 	String  timeDep = request.getParameter("departureT");
		
		user.addRide(timeDep, srcStation, srcCity, dstStation, dstCity);
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
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