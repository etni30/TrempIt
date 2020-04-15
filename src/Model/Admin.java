package Model;

import java.util.LinkedList;

<<<<<<< HEAD
=======
import API.ModelInterface;

>>>>>>> master
public class Admin extends User{

	public Admin(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email, isInARide);
	}
	
	// admin has extended update and search functions
		
	// update user's first name
	public void updateFirstName(int idUser, String first) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		User u = md.getUser(idUser);
		u.setFirstName(first);
		u.updateDB();
	}
	
	// update user's last name
	public void updateLastName(int idUser, String last) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		User u = md.getUser(idUser);
		u.setLastName(last);
		u.updateDB();	
	}
	
	// update user's user name
	public void updateUserName(int idUser, String userName) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		User u = md.getUser(idUser);
		u.setUserName(userName);
		u.updateDB();	
	}
	
	// update user's password
	public void updatePassword(int idUser, String password) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		User u = md.getUser(idUser);
		u.setPassword(password);
		u.updateDB();	
	}
	
	// update user's email
	public void updateEmail(int idUser, String email) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		User u = md.getUser(idUser);
		u.setEmail(email);
		u.updateDB();	
	}
	
	// add new station to DB with distance of 1 to all stations in same city
	public void addStation(String  stationName, String city) throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		md.addStation(stationName, city);
	}
	
	// update distance between two stations
<<<<<<< HEAD
	public void changeDistance(String station1, String station2) throws Exception{
		Model md = new Model();
		//md.changeDistance(station1, station2) ;
=======
	public void changeDistance(String station1, String station2, double dist) throws Exception{
		ModelInterface md = new Model();
		md.changeDistance(station1, station2, dist) ;
>>>>>>> master
	}
	
	// get all users
	public LinkedList<User> getUsers() throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		return md.getUsers();
	}
	
	// get all rides
	public LinkedList<Group> getGroups() throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		return md.getGroups();
	}
	
	// get all edges
	public LinkedList<Edge> getEdges() throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		return md.getEdges();
	}
	
	// get all stations
	public LinkedList<String> getStations() throws Exception{
<<<<<<< HEAD
		Model md = new Model();
=======
		ModelInterface md = new Model();
>>>>>>> master
		return md.getStations();
	}

}
