<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "DataBase.DataBase, Model.*, java.util.LinkedList" %>
<%
// messege for itamar and eliyahu: this is a test file for our database dony delete it!


try{
	DataBase db = new DataBase();
//	db.addNewUser("etni", "hagshi", "admin", "etni30", "1234", "etni30@gmail.com");
//	db.updateUser(19, "tom", "cohen", "beni", "1234", "@", true);
//	db.addRide(18, "15:00", "ahuza shwartz", "ra'anana", "ramat hen", "ramat gan");
//	db.getEdge("ahuza shwartz", "amidar");
//	db.getRide("ahuza shwartz", "ramat hen");
//	db.getStations();
//	db.getGroupForUser(13);
//	System.out.println(db.joinGroup(13, 1));
//	int x = db.getDistance("ahuza shwartz", "amidar");
//	System.out.println(db.getTime());

/*
	Time t1 = new Time("13:00");
	Time t2 = new Time("13:30");
	Time t3 = new Time("02:00");
	Time t4 = new Time("13:00");
	System.out.println(Time.cmpTime(t1, t2));
	System.out.println(Time.cmpTime(t1, t3));
	System.out.println(Time.cmpTime(t1, t4));

*/
	Model m = new Model();


//	m.addNewUser("etni", "hagshi", "admin", "etni30", "1234", "etni30@gmail.com");

//	System.out.println(m.checkPassword("a", "a"));
	

//	System.out.println(u1.getEmail());
//	u1.setEmail("eli19@gmail.com");
//	u1.updateDB();

//	Passenger p1 = (Passenger) m.getUser("eli1");
//	System.out.println(p1.joinGroup(1));

//	Driver d = (Driver) m.getUser(17);
//	d.deleteRide();

//	Driver d1 = (Driver) m.getUser("guy1");
//	d1.addRide("12:30", "marom nave", "ramat gan", "amidar", "ra'anana");	
	Admin a = (Admin) m.getUser(24);
//	a.updateFirstName(13, "Eden");
//	a.updateFirstName(16, "Igor");
//	a.updateLastName(13, "Fargon");
//	a.updateLastName(16, "Rochlin");
//	a.updateUserName(13, "eden123");
//	a.updateUserName(16, "igor123");
//	a.updatePassword(13, "1234");
//	a.updatePassword(16, "12345");
	
	System.out.println(a.getStations().size());

//	a.addStation("soroka", "beer sheva");

/*	
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