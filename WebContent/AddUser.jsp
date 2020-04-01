<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "View.View" %>

<!DOCTYPE html>
<%

String first = request.getParameter("first");
String last = request.getParameter("last");
String type = "passenger";
String username = request.getParameter("userName");
String password = request.getParameter("psw");
String email = request.getParameter("email");

View v = new View();
v.addNewUser(first, last, type, username, password, email);

%>
<HTML><BODY>
<A HREF="NextPage.jsp">Continue</A>
</BODY></HTML>