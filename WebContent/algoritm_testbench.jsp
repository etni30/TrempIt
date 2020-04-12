<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "DataBase.*, Model.*, Controller.*,  java.util.*" %>
<%


try
{
	Algorithm al = new Algorithm();
	ArrayList<Path> paths = al.findTramps("ahuza shwartz", "raanana", "ramat hen", "ramat gan", new Time("10:00"));
	
	for(Path path: paths)
	{
		System.out.print(path.getG().getSourceStation());
		System.out.print(path.getG().getdstStation());
		System.out.println(path.getWalkDistance());
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