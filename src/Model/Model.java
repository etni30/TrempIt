package Model;

import java.util.LinkedList;
import java.sql.ResultSet;

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
	
	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password, String email) throws Exception{
		db.addNewUser(first, last, type, username, password, email);
	}

	// find distance between two stations, return distance
	public float getDistance(String srcStation, String dstStation) throws Exception{
		return db.getDistance(srcStation, dstStation);
	}
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password, String email) throws Exception{
		db.updateUser(iduser, first, last, username, password, email);
	}
	
	// find user for specific user, return ResultSet
	public User getUser(String username) throws Exception{
		ResultSet rs = db.getUser(username);
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
						rs.getString("username"), rs.getString("password"), rs.getString("email"));
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
			
			
	// find edge between two stations, return ResultSet
	public void getEdge(String station1, String station2) throws Exception{
	}
			
	// add a ride from source to destination
	public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		db.addRide(idDriver, time, srcStation, srcCity, dstStation, dstCity);
	}
	
	// return all edges between two stations
	public LinkedList<Group> getRide(String srcStation, String dstStation) throws Exception{
		String srcCity = db.getCity(srcStation);
		String dstCity = db.getCity(dstStation);
		ResultSet rs = db.getRide(srcStation, dstStation);
		LinkedList<Group> groups= new LinkedList<Group>();
		Group g = null;
		while(rs.next()) {
			g = new Group(srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
					rs.getString("time"), rs.getInt("iddriver"), rs.getInt("iduser1"),
					rs.getInt("iduser2"), rs.getInt("iduser3"), rs.getInt("iduser4"));
			groups.add(g);
		}
		return groups;
	}

	// return all stations in a linked list where the first is the station name and the second is the city
	public LinkedList<String> getStations() throws Exception{
		ResultSet rs = db.getStations();
		LinkedList<String> stations= new LinkedList<String>();
		while(rs.next()) {
			stations.add(rs.getString("stationname"));
		}
		return stations;
	}
	
	// return user's group
	public Group getGroupForUser(int idUser) throws Exception{
		ResultSet rs = db.getGroupForUser(idUser);
		rs.next();
		String srcStation = rs.getString("srcstation");
		String dstStation = rs.getString("dststation");
		String srcCity = db.getCity(srcStation);
		String dstCity = db.getCity(dstStation);
		return new Group(srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
				rs.getString("time"), rs.getInt("iddriver"), rs.getInt("iduser1"),
				rs.getInt("iduser2"), rs.getInt("iduser3"), rs.getInt("iduser4"));
	}

	// return all groups
	public LinkedList<Group> getGroups() throws Exception{
		ResultSet rs = db.getGroups();
		String srcStation, dstStation, srcCity, dstCity;
		LinkedList<Group> groups= new LinkedList<Group>();
		Group g =null;
		while(rs.next()) {
			srcStation = rs.getString("srcstation");
			dstStation = rs.getString("dststation");
			srcCity = db.getCity(srcStation);
			dstCity = db.getCity(dstStation);
			g = new Group(srcCity, srcStation, dstCity, dstStation, rs.getInt("amount"),
					rs.getString("time"), rs.getInt("iddriver"), rs.getInt("iduser1"),
					rs.getInt("iduser2"), rs.getInt("iduser3"), rs.getInt("iduser4"));
			groups.add(g);
		}
		return groups;
	}

	// join group, if full return false
	public boolean joinGroup(int idUser, int idGroup) throws Exception{
		return db.joinGroup(idUser, idGroup);
	}
	
 

}
