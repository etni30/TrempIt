package Model;

import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;
import API.DBInterface;
import API.ModelInterface;
import DataBase.DataBase;

// this class will be responsible for:
// 1) creating objects from information in DB
// 2) returning data and objects from DB to controller
public class Model implements ModelInterface {
    
	private DBInterface db=null;
	
	public Model() {
        this.db = new DataBase();
    }
	
	// user functions:------------------------------------------------------------------
	
	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password, String email) throws Exception{
		
		db.addNewUser(first, last, type, username, password, email);
	}
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password, String email,
			boolean isInARide) throws Exception{
		
		db.updateUser(iduser, first, last, username, password, email, isInARide);
	}
	
	// create specific kind of User object according to type
	public User creatUser(String type, ResultSet rs) throws Exception{
		
		switch(type) {
			case "passenger":
				return new Passenger(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride"));
			case "driver":
				return new Driver(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride"));
			case "admin":
				return new Admin(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"),
						rs.getBoolean("isinaride"));
			default:
				throw new Exception("not a valid input in DB");
		}	
	}
	
	// return User object for specific username
	public User getUser(String username) throws Exception{
		
		ResultSet rs = db.getUser(username);
		rs.next();
		User u = creatUser(rs.getString("type"), rs);
		db.closeConnection();
		return u;
	}
	
	// return LinkedList of all User object in DB
	public LinkedList<User> getUsers() throws Exception{
		
		ResultSet rs = db.getUsers();
		LinkedList<User> users= new LinkedList<User>();
		while(rs.next()) {
			users.add(creatUser(rs.getString("type"), rs));
		}
		db.closeConnection();
		return users;
	}
	
	// return User object for specific userId
	public User getUser(int userId) throws Exception{
		
		ResultSet rs = db.getUser(userId);
		rs.next();
		User u = creatUser(rs.getString("type"), rs);
		db.closeConnection();
		return u;
	}
	
	// check valid password return true if correct
	public boolean checkPassword(String username, String password) throws Exception{
		
		return db.checkPassword(username, password);
	}
			
	// edge functions:------------------------------------------------------------------
			
	// return LInkedList of all Edge object in DB
	public LinkedList<Edge> getEdges() throws Exception{
		
		ResultSet rs = db.getEdges();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		while(rs.next()) {
			edges.add(new Edge(rs.getString("station1"), rs.getString("station2"),
					rs.getString("city"), rs.getFloat("distance")));
		}
		db.closeConnection();
		return edges;
	}
	
	// get distance between two stations in dsge table in DB, return float
	public float getDistance(String srcStation, String dstStation) throws Exception{
		
		return db.getDistance(srcStation, dstStation);
	}
	
	// update distance between two stations in edge Table in DB
	public void changeDistance(String station1, String station2, float dist) throws Exception{
		
		db.changeDistance(station1, station2, dist);
	}
		
	// station functions:------------------------------------------------------------------

	// return all stations in a linked list in the next formation
	// "station1,city1" -> "station2,city2" -> "station3,city3" ->...
	public LinkedList<String> getStations() throws Exception{
		
		ResultSet rs = db.getStations();
		LinkedList<String> stations= new LinkedList<String>();
		while(rs.next()) {
			stations.add(rs.getString("stationname") + "," + rs.getString("city"));
		}
		db.closeConnection();
		return stations;
	}
	
	// add station to edge table in DB with distance 1 to all stations in the same city
	public void addStation(String  stationName, String city) throws Exception{
		
		db.addStation(stationName, city);
		db.addStationToEdge(stationName, city);
		db.addCityToEdge(city);
	}
		
		
	// group functions:------------------------------------------------------------------

	// add a ride from source to destination to group table in DB
	public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		
		db.addRide(idDriver, time, srcStation, srcCity, dstStation, dstCity);
	}
	
	// return LinkedList of all groups between two stations
	public LinkedList<Group> getRide(String srcStation, String dstStation) throws Exception{
		
		ResultSet rs = db.getRide(srcStation, dstStation);
		LinkedList<Group> groups= new LinkedList<Group>();		
		while(rs.next()) {
			groups.add(new Group(rs.getInt("idgroup"), rs.getString("srcCity"), srcStation,
					rs.getString("dstCity"), dstStation, rs.getInt("amount"),
					rs.getString("departureTime"), rs.getString("iddriver"), rs.getString("iduser1"),
					rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
		}
		db.closeConnection();
		return groups;
	}
	
	// return user's group
	public Group getGroupForUser(int idUser) throws Exception{
		
		ResultSet rs = db.getGroupForUser(idUser);
		rs.next();
		Group g = new Group(rs.getInt("idgroup"), rs.getString("srcCity"), rs.getString("srcstation"),
				rs.getString("dstCity"), rs.getString("dststation"), rs.getInt("amount"),
				rs.getString("departureTime"), rs.getString("iddriver"), rs.getString("iduser1"),
				rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4"));
		db.closeConnection();
		return g;
	}

	// return LinkedList with all group object
	public LinkedList<Group> getGroups() throws Exception{
		
		ResultSet rs = db.getGroups();
		LinkedList<Group> groups= new LinkedList<Group>();
		while(rs.next()) {
			groups.add(new Group(rs.getInt("idgroup"), rs.getString("srcCity"), rs.getString("srcstation"),
					rs.getString("dstCity"), rs.getString("dststation"), rs.getInt("amount"),
					rs.getString("departureTime"), rs.getString("iddriver"), rs.getString("iduser1"),
					rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
		}
		db.closeConnection();
		return groups;
	}
	

	// add user to group, if group is full return false otherwise return true
	public boolean joinGroup(int idUser, int idGroup) throws Exception{
		
		return db.joinGroup(idUser, idGroup);
	}
	
	// remove user from group
	public void leaveGroup(int idUser, int idGroup) throws Exception{
		db.leaveGroup(idUser, idGroup);
	}


	// get groups from one city to another city
	public LinkedList<Group> getGroups(String srcCity, String dstCity) throws Exception {
		ResultSet rs = db.getGroups(srcCity, dstCity);
		String srcStation, dstStation;
		LinkedList<Group> groups= new LinkedList<Group>();
		while(rs.next()) {
			groups.add(new Group(rs.getInt("idgroup"), srcCity, rs.getString("srcstation"),
					dstCity, rs.getString("dststation"), rs.getInt("amount"),
					rs.getString("departureTime"), rs.getString("iddriver"), rs.getString("iduser1"),
					rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
		}
		db.closeConnection();
		return groups;
		
	}
	
	// return ResultSet of all groups from one city to another city
	public void deleteRide(int idDriver) throws Exception{
		ResultSet rs = db.getGroupForUser(idDriver);
		rs.next();
		int id = rs.getInt("iduser1");
		if(id != 0) {
			User u = getUser(id);
			u.setIsInARide(false);
			u.updateDB();
		}
		
		id = rs.getInt("iduser2");
		if(id != 0) {
			User u = getUser(id);
			u.setIsInARide(false);
			u.updateDB();
		}
		
		id = rs.getInt("iduser3");
		if(id != 0) {
			User u = getUser(id);
			u.setIsInARide(false);
			u.updateDB();
		}
		
		id = rs.getInt("iduser4");
		if(id != 0) {
			User u = getUser(id);
			u.setIsInARide(false);
			u.updateDB();
		}
		int idgroup = rs.getInt("idgroup");
		db.closeConnection();
		db.deleteGroup(idgroup);
	}

}
