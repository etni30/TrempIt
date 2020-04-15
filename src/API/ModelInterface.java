package API;

import java.sql.ResultSet;
import java.util.LinkedList;

import Model.*;

public interface ModelInterface {
		
		// user functions:------------------------------------------------------------------
	
		// add a new user
		public void addNewUser(String first, String last, String type, String username, String password, String email) throws Exception;
		
		// update properties for specific user
		public void updateUser(int iduser, String first, String last, String username, String password, String email,
				boolean isInARide) throws Exception;
		
		// return user for specific username
		public User getUser(String username) throws Exception;
		
		// return user for specific userid
		public User getUser(int userId) throws Exception;

		// get all users
		public LinkedList<User> getUsers() throws Exception;

		
		// check valid password
		public boolean checkPassword(String username, String password) throws Exception;
		
		// edge functions:------------------------------------------------------------------

		// find edge between two stations, return ResultSet
		public LinkedList<Edge> getEdges() throws Exception;
		
		// find distance between two stations, return distance
		public float getDistance(String srcStation, String dstStation) throws Exception;
		
		// update distance between two stations
		public void changeDistance(String station1, String station2, float dist) throws Exception;

		// station functions:------------------------------------------------------------------

		// return all stations
		public LinkedList<String> getStations() throws Exception;
		
		// add new station to DB with distance of 1 to all stations in same city
		public void addStation(String  stationName, String city) throws Exception;
	
		// group functions:------------------------------------------------------------------

		// add a ride from source to destination
		public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception;
		
		// return all edges between two stations
		public LinkedList<Group> getRide(String srcStation, String dstStation) throws Exception;
		
		// return user's group
		public Group getGroupForUser(int idUser) throws Exception;

		// return all groups
		public LinkedList<Group> getGroups() throws Exception;

		// join group, if full return false
		public boolean joinGroup(int idUser, int idGroup) throws Exception;

		// get groups from one city to another city
		public LinkedList<Group> getGroups(String srcCity, String dstCity) throws Exception;
		
		// delete ride and update all users
		public void deleteRide(int idDriver) throws Exception;

//		//return city's station
//		public String getCity(String station);

}
