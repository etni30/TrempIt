package API;

import java.sql.ResultSet;
import java.util.LinkedList;

import Model.*;

public interface ModelInterface {
		
		// add a new user
		public void addNewUser(String first, String last, String type, String username, String password, String email) throws Exception;
		
		// update properties for specific user
		public void updateUser(int iduser, String first, String last, String username, String password, String email) throws Exception;
		
		// find user for specific user, return ResultSet
		public User getUser(String username) throws Exception;
		
		// check valid password
		public boolean checkPassword(String username, String password) throws Exception;
		
		// find edge between two stations, return ResultSet
		public void getEdge(String station1, String station2) throws Exception;
		
		// find distance between two stations, return distance
		public float getDistance(String srcStation, String dstStation) throws Exception;
			
		// add a ride from source to destination
		public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception;
		
		// return all edges between two stations
		public LinkedList<Group> getRide(String srcStation, String dstStation) throws Exception;

		// return all stations
		public LinkedList<String> getStations() throws Exception;
		
		// return user's group
		public Group getGroupForUser(int idUser) throws Exception;

		// return all groups
		public LinkedList<Group> getGroups() throws Exception;

		// join group, if full return false
		public boolean joinGroup(int idUser, int idGroup) throws Exception;

		// get groups from one city to another city
		public LinkedList<Group> getGroups(String srcCity, String dstCity) throws Exception;
}
