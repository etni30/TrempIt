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
// TODO remove type
			String type = request.getParameterValues("type")[0];
			String userName = request.getParameter("userName");
			String psw = request.getParameter("psw");
			
			Model m = new Model();  // TODO change to view after finishig tests 
			boolean valid = m.checkPassword(userName, psw);

// If the password is valid: set session for the user
			if(valid == true){
			session.setAttribute("userName", userName);
			session.setAttribute("type", type);
	%>			
			
				<script> window.location.href = "mainpage.jsp";</script>`
	<%
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
		alert("invalid password/user name");
		window.location.href = "demo.jsp";
		</script>
<%
		
	} %>
</body>
</html>