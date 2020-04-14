package Model;

import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;
import API.DBInterface;
import API.ModelInterface;
import DataBase.DataBase;

// this class will be responsible for all of the classes
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
	public void updateUser(int iduser, String first, String last, String username, String password, String email
			, boolean isInARide) throws Exception{
		db.updateUser(iduser, first, last, username, password, email, isInARide);
	}
	
	// return user for specific username
	public User getUser(String username) throws Exception{
		ResultSet rs = db.getUser(username);
		User u = null;
		rs.next();
		switch(rs.getString("type")) {
			case "passenger":
				u = new Passenger(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride"));
				break;
			case "driver":
				u = new Driver(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride"));
				break;
			case "admin":
				u = new Admin(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"),
						rs.getBoolean("isinaride"));
				break;
			default:
				throw new Exception("not a valid input in DB");
		}	
		return u;
	}
	
	// get all users
	public LinkedList<User> getUsers() throws Exception{
		ResultSet rs = db.getUsers();
		LinkedList<User> users= new LinkedList<User>();
		while(rs.next()) {
			switch(rs.getString("type")) {
			case "passenger":
				users.add(new Passenger(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride")));
				break;
			case "driver":
				users.add(new Driver(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), 
						rs.getBoolean("isinaride")));
				break;
			case "admin":
				users.add(new Admin(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"),
						rs.getBoolean("isinaride")));
				break;
			default:
				throw new Exception("not a valid input in DB");
			}	
		}
		return users;
	}
	
	// return user for specific userid
	public User getUser(int userId) throws Exception{
		ResultSet rs = db.getUser(userId);
		User u = null;
		rs.next();
		switch(rs.getString("type")) {
			case "passenger":
				u = new Passenger(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), false);
				break;
			case "driver":
				u = new Driver(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"), false);
				break;
			case "admin":
				u = new Admin(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getString("email"),
						rs.getBoolean("isinaride"));
				break;
			default:
				throw new Exception("not a valid input in DB");
		}	
		return u;
	}
	
	// check valid password
	public boolean checkPassword(String username, String password) throws Exception{
		return db.checkPassword(username, password);
	}
			
	// edge functions:------------------------------------------------------------------
			
	// return all edges
	public LinkedList<Edge> getEdges() throws Exception{
		ResultSet rs = db.getEdges();
		LinkedList<Edge> edges = new LinkedList<Edge>();
		while(rs.next()) {
			edges.add(new Edge(rs.getString("station1"), rs.getString("station2"),
					rs.getString("city"), rs.getInt("distance")));
		}
		return edges;
	}
	
	// find distance between two stations, return distance
	public float getDistance(String srcStation, String dstStation) throws Exception{
		return db.getDistance(srcStation, dstStation);
	}
	
	// update distance between two stations
	public void changeDistance(String station1, String station2) throws Exception{
		db.changeDistance(station1, station2);
	}
		
	// station functions:------------------------------------------------------------------

	// return all stations in a linked list where the first is the station name and the second is the city
	public LinkedList<String> getStations() throws Exception{
		ResultSet rs = db.getStations();
		LinkedList<String> stations= new LinkedList<String>();
		while(rs.next()) {
			stations.add(rs.getString("stationname"));
			stations.add(rs.getString("city"));
		}
		return stations;
	}
	
	// add new station to DB with default distance of 1 to all stations in same city
	public void addStation(String  stationName, String city) throws Exception{
		db.addStationToEdge(stationName, city);
	}
		
		
	// group functions:------------------------------------------------------------------

	// add a ride from source to destination TODO change parameters ()
	public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		db.addRide(idDriver, time, srcStation, srcCity, dstStation, dstCity);
	}
	
	// return all edges between two stations
	public LinkedList<Group> getRide(String srcStation, String dstStation) throws Exception{
		
		String srcCity = db.getCity(srcStation);
		String dstCity = db.getCity(dstStation);
		ResultSet rs = db.getRide(srcStation, dstStation);
		LinkedList<Group> groups= new LinkedList<Group>();		
		while(rs.next()) {
			groups.add(new Group(rs.getInt("idgroup"), srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
					rs.getString("time"), rs.getString("iddriver"), rs.getString("iduser1"),
					rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
			}
		return groups;
	}
	
	// return user's group
	public Group getGroupForUser(int idUser) throws Exception{
		ResultSet rs = db.getGroupForUser(idUser);
		rs.next();
		String srcStation = rs.getString("srcstation");
		String dstStation = rs.getString("dststation");
		String srcCity = db.getCity(srcStation);
		String dstCity = db.getCity(dstStation);
		
		Group g = new Group(rs.getInt("idgroup"), srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
				rs.getString("time"), rs.getString("iddriver"), rs.getString("iduser1"),
				rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4"));
		return g;
	}

	// return all groups
	public LinkedList<Group> getGroups() throws Exception{
		ResultSet rs = db.getGroups();
		String srcStation, dstStation, srcCity, dstCity;
		LinkedList<Group> groups= new LinkedList<Group>();
		while(rs.next()) {
			srcStation = rs.getString("srcstation");
			dstStation = rs.getString("dststation");
			srcCity = db.getCity(srcStation);
			dstCity = db.getCity(dstStation);
			groups.add(new Group(rs.getInt("idgroup"), srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
      rs.getString("time"), rs.getString("iddriver"), rs.getString("iduser1"),
      rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
		}
		return groups;
	}
	

	// join group, if full return false
	public boolean joinGroup(int idUser, int idGroup) throws Exception{
		return db.joinGroup(idUser, idGroup);
	}

	// get groups from one city to another city
	public LinkedList<Group> getGroups(String srcCity, String dstCity) throws Exception {
		ResultSet rs = db.getGroups(srcCity, dstCity);
		String srcStation, dstStation;
		LinkedList<Group> groups= new LinkedList<Group>();
		while(rs.next()) {
			srcStation = rs.getString("srcstation");
			dstStation = rs.getString("dststation");
			
			groups.add(new Group(rs.getInt("idgroup"), srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
					rs.getString("departureTime"), rs.getString("iddriver"), rs.getString("iduser1"),
					rs.getString("iduser2"), rs.getString("iduser3"), rs.getString("iduser4")));
		}
		return groups;
		
	}
	
	// delete ride in DB and update each user
	public void deleteRide(int idDriver) throws Exception{
		ResultSet rs = db.getGroup(idDriver);
		
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
		
		db.deleteGroup(rs.getInt("idgroup"));
	}

}
