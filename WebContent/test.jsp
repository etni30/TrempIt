<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "DataBase.DataBase, Model.*, java.util.LinkedList" %>
<%
// messege for itamar and eliyahu: this is a test file for our database dony delete it!


DataBase db = new DataBase();
try{
//	db.addNewUser("assi", "cohen", "driver", "thr", "1234", "@");
//	db.updateUser(19, "driver", "cohen", "beni", "1234", "@");
//	db.addRide(18, "15:00", "ahuza shwartz", "ra'anana", "ramat hen", "ramat gan");
//	db.getEdge("ahuza shwartz", "amidar");
//	db.getRide("ahuza shwartz", "ramat hen");
//	db.getStations();
//	db.getGroupForUser(13);
//	System.out.println(db.joinGroup(13, 1));
//	int x = db.getDistance("ahuza shwartz", "amidar");
//	System.out.println(db.getTime());

/*	Time t1 = new Time("13:00");
	Time t2 = new Time("13:30");
	Time t3 = new Time("02:00");
	Time t4 = new Time("13:00");
	System.out.println(Time.cmpTime(t1, t2));
	System.out.println(Time.cmpTime(t1, t3));
	System.out.println(Time.cmpTime(t1, t4));
*/


	Model m = new Model();
	User u = m.getUser("eli2");
	u.setUserName("igor123");
	u.updateDB();
//	m.addNewUser("eliyahu" , "levi", "passenger", "eli2", "1234", "eli@gmail.com");
	
/*
	User u1 = m.getUser("eli2");
	System.out.println(u1.getEmail());
	u1.setEmail("eli29@gmail.com");
	u1.updateDB();

	Passenger p1 = (Passenger) u1;
	System.out.println(p1.joinGroup(6));


	Driver d1 = (Driver) m.getUser("guy1");
	d1.addRide("12:30", "marom nave", "ramat gan", "amidar", "ra'anana");	
	
	System.out.println(m.getDistance("kiryat shret", "amidar"));
	System.out.println(m.getDistance("amidar", "kiryat shret"));
	

	Group g1 = m.getGroupForUser(18);
	System.out.println(g1.getDestCity());
	System.out.println(g1.getSourceCity());
	System.out.println(g1.getSourceStation());

	LinkedList<Group> l1 = m.getGroups();
	System.out.println(l1.size());	
	System.out.println(l1.get(0).getIdDriver());	

	System.out.println(m.getStations());
*/
	
}catch(Exception e){
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