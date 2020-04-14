<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "DataBase.*, Model.*, Controller.*,  java.util.*" %>
<%


try
{
	Algorithm al = new Algorithm();
	
	Time desiredAT = new Time("20:00");
	Time departureT = new Time("11:00");
	ArrayList<Path> paths = al.findTramps("ahuza shwartz", "raanana", "ramat hen", 
											"ramat gan",desiredAT, departureT, 2 );
	
	for(Path path: paths)
	{
		System.out.println(path.toString());
	}
	
	System.out.println("done");
}
catch(Exception e)
{
	System.out.println(e);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>

</body>
</html>