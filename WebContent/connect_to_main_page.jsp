<%@page import="com.sun.xml.internal.bind.v2.TODO"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import=" Model.*, Controller.*"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>validation</title>
</head>
<body>
		
	<%
		try{
			//get name & password from user
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			
			//Password validation 
			Controller conn = new Controller();
			boolean valid = conn.checkPassword(userName, psw);
		
			// If the password is valid:
			if(valid == true){
				User user = conn.getUser(userName);  // set session for the user
				if(user instanceof Passenger || user instanceof Driver){
					
					session.setAttribute("User", user);
		%>						
					<script>
					window.location.href = "mainpage.jsp";</script>
		<%
	
				//for admin:
				}else{%>
					<script>
						alert("welcome ADMIN");//TODO
						window.location.href = "mainpage.jsp";// TODO CREATE ADMIN PAGE
					</script>
				<%}
// if the password is wrong, return to index.jsp
			}else{
	%>
				<script> alert("invalid password/user name");</script>
	<%		}
	}catch(Exception e){
		%>
		<script> alert("<%out.print(e);%>");</script>
<%	
	}finally{
		
		%><script >
			alert("Error in connection");
			window.location.href = "demo.jsp";
		</script>
		
	<% } %>
</body>
</html>