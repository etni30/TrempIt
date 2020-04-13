package API;

import java.sql.ResultSet;
import java.util.LinkedList;

import Model.User;

public interface DBInterface {
	
	// user functions:------------------------------------------------------------------

	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password,
			String email) throws Exception;
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password,
			String email, boolean isInARide) throws Exception;
	
	// find user for specific user, return ResultSet
	public ResultSet getUser(String username) throws Exception;
	
	// return user for specific user, return ResultSet
	public ResultSet getUser(int userId) throws Exception;
	
	// get all users
	public ResultSet getUsers() throws Exception;

	// check valid password
	public boolean checkPassword(String username, String password) throws Exception;
	
	// edge functions:--------------------------------------------------------------------

	// find edge between two stations, return ResultSet
	public ResultSet getEdges() throws Exception;
	
	// find distance between two stations, return distance
	public float getDistance(String srcStation, String dstStation) throws Exception;
	
	// update distance between two stations
	public void changeDistance(String station1, String station2) throws Exception;
	
	// add station to city with all distance 1
	public void addStationToEdge(String station, String city) throws Exception;
	
	// station functions:------------------------------------------------------------------

	// return all stations
	public ResultSet getStations() throws Exception;

	// return city of specific station
	public String getCity(String station) throws Exception;

	// add new station to DB with distance of 1 to all stations in same city
	public void addStation(String  stationName, String city) throws Exception;

	// group functions:------------------------------------------------------------------

	// add a ride from source to destination
	public void addRide(int idDriver, String time, String srcStation, String srcCity, String dstStation,
			String dstCity) throws Exception;
	
	// return all edges between two stations
	public ResultSet getRide(String srcStation, String dstStation) throws Exception;

	// return user's group
	public ResultSet getGroupForUser(int idUser) throws Exception;

	// return all groups
	public ResultSet getGroups() throws Exception;

	// join group, if full return false
	public boolean joinGroup(int idUser, int idGroup) throws Exception;

	// get groups from one city to another city
	public ResultSet getGroups(String srcCity, String dstCity) throws Exception;
	
	// delete group and update users
	public void deleteGroup(int idDriver) throws Exception;

	// get group by driver id
	public ResultSet getGroup(int idDriver) throws Exception;
}
