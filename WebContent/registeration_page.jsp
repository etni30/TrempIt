<%@page import="Controller.Controller"%>
<%@page import="com.sun.xml.internal.bind.v2.TODO"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Model.*"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>validation</title>
</head>
<body>
	<%
	
		try{
			String type = request.getParameter("type");
			String first = request.getParameter("first");
			String last = request.getParameter("last");
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			Controller conn = new Controller();
			Model m = new Model();
			int valid = conn.addNewUser(first, last, type, userName, psw, "test@gmail.com");
			if(valid == 0){
				User user = m.getUser(userName);
				session.setAttribute("User", user);
				session.setAttribute("type", type);
	%>
				<script type="text/javascript">window.location.href = "mainpage.jsp";</script>`
	<%

			}else{
	%>
				<script type="text/javascript"> 
				alert("invalid user name, the name is already in use");
				window.location.href = "demo.jsp";
				</script>
	<%		}
	}catch(Exception e){
		%>
		<script type="text/javascript"> 
		alert("invalid password/user name");
		window.location.href = "demo.jsp";
		</script>
<%
		
	} %>
</body>
</html>