<%@page import="com.sun.xml.internal.bind.v2.TODO"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Model.*, Controller.*"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>validation</title>
</head>
<body>
	<%
		try{
// TODO remove type

			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			
			Controller con = new Controller();
			//boolean valid = con.checkPassword(userName, psw);
			Model m = new Model();  // TODO change to view after finishig tests 
			
			//User user = con.getUser(username); // TODO CREATE USER METHOD
			
			boolean valid = m.checkPassword(userName, psw);

// If the password is valid: set session for the user
			if(valid == true){
			User user = m.getUser(userName);
				if(user instanceof Passenger || user instanceof Driver){
					session.setAttribute("User", user);
		%>			
					<jsp:forward page="mainpage.jsp"/>
								
					<!-- <script> window.location.href = "mainpage.jsp";</script>` -->
		<%
//for admin:
				}else{%>
					<jsp:forward page="demo.jsp"/>
				<%}
// if we get a wrong password  get back to index.jsp
			}else{
	%>
				<script type="text/javascript"> 
				alert("invalid password/user name");
				window.location.href = "demo.jsp";
				</script>
	<%		}
	}catch(Exception e){
		%>
		<script type="text/javascript"> 
		alert("<% out.print(e);%>");
		window.location.href = "demo.jsp";
		</script>
<%
		
	} %>
</body>
</html>