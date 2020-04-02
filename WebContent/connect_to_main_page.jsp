<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>validation</title>
</head>
<body>
	<%
		String type = request.getParameter("type");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String userName = request.getParameter("userName");
		String psw = request.getParameter("psw");
		View v = new View();
		int valid = v.addNewUser(first, last, type, userName, psw, "test@gmail.com");
		
		if(valid != 0){
	%>
			<script type="text/javascript">window.location.href = "mainpage.jsp";</script>`
	<%
			session.setAttribute("userName", userName);
			session.setAttribute("type", type);
		}else{
	%>
			<script type="text/javascript"> 
			alert("invalid password/user name");
			window.location.href = "demo.jsp";
			</script>
	<%} %>
</body>
</html>