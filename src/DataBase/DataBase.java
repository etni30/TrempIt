package DataBase;

import java.sql.*;

import API.DBInterface;
import Model.Admin;
import Model.Driver;
import Model.Passenger;

// this class will be responsible for handling the database 

public class DataBase implements DBInterface {
	
	// user functions:------------------------------------------------------------------
	
	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password,
			String email) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
	
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `users` (`firstname`, `lastname`, `type`, `username`,"
				+ " `password`, `email`) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, type);
		ps.setString(4, username);
		ps.setString(5, password);
		ps.setString(6, email);
		ps.executeUpdate();
		conn.close();
	}
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password, String email
			, boolean isInARide) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("UPDATE `users` SET `firstname` = ?, `lastname` = ? , `username` = ?,"
				+ " `password` = ?, `email` = ?, `isinaride` = ?   WHERE `iduser` = ? ");
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, username);
		ps.setString(4, password);
		ps.setString(5, email);
		ps.setBoolean(6, isInARide);
		ps.setInt(7, iduser);
		ps.executeUpdate();
		conn.close();
	}
	
	// return user for specific username
	public ResultSet getUser(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` = ?;");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// return user for specific user, return ResultSet
	public ResultSet getUser(int userId) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `userid` = ?;");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// get all users
	public ResultSet getUsers() throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users`;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	
	// check valid password return true if correct
	public boolean checkPassword(String username, String password) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `password` FROM `users` WHERE `username` = ?;");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		rs.next();
		try {
			String psw = rs.getString("password");
			if(psw.equals(password)) {
				return true;
			}
			return false;	// wrong password
		}
		catch(Exception e) {
			return false;	// wrong username
		}
	}

	// edge functions:--------------------------------------------------------------------
	
	// get all edges, return ResultSet
	public ResultSet getEdges() throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `edge`;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// get distance between two stations, return float
	public float getDistance(String srcStation, String dstStation) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `distance` FROM `edge` WHERE `station1` = ? AND"
				+ " `station2` = ? ;");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		float dis = 0;
		ResultSet rs = ps.executeQuery();
		if(rs.next() != false) {
			dis = rs.getFloat("distance");
			rs.previous();
			conn.close();
			return dis;
		}
		ps.setString(1, dstStation);
		ps.setString(2, srcStation);
		rs = ps.executeQuery();
		rs.next();
		dis = rs.getFloat("distance");
		conn.close();
		return dis;
	}
	
	// update distance between two stations
	public void changeDistance(String station1, String station2) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("UPDATE `edge` SET `distacne` = ? WHERE `station1` = ? AND `station2` = ?");
		ps.setInt(1, 1);
		ps.setString(2, station1);
		ps.setString(3, station2);
		ps.executeUpdate();
	}
	
	// add station to city with all distance 1
	public void addStationToEdge(String station, String city) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `stationname` FROM `station` WHERE `station` = ?");
		ps.setString(1, city);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ps = conn.prepareStatement("INSERT INTO `edge` (`station1`, `station2`, `city`)"
					+ " VALUES (?, ?, ?)");
			ps.setString(1, station);
			ps.setString(2, rs.getString("stationname"));
			ps.setString(3, city);
		}
	}
	
	// station functions:------------------------------------------------------------------
	
	// return all stations
		public ResultSet getStations() throws Exception{	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM `station`");
			ResultSet rs = ps.executeQuery();
			return rs;
		}

		// return city of specific station
		public String getCity(String station) throws Exception{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
			
			PreparedStatement ps = conn.prepareStatement("SELECT `city` FROM `station` WHERE `stationname` = ? ");
			ps.setString(1, station);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString("city");
		}
		
		// add new station to DB with distance of 1 to all stations in same city
		public void addStation(String  stationName, String city) throws Exception{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO `station` (`station`, `city`) "
					+ " VALUES (?, ?);");
			ps.setString(1, stationName);
			ps.setString(2, city);

			ps.executeUpdate();
		}

	// group functions:------------------------------------------------------------------

	// add a ride from source to destination
	public void addRide(int idDriver, String departureTime, String srcStation, String srcCity, String dstStation,
			String dstCity) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `group` (`srcCity`, `dstCity`, `srcstation`,"
				+ " `dststation`, `iddriver`, `departureTime`) VALUES (?, ?, ?, ?, ?, ?);");
		ps.setString(1, srcCity);
		ps.setString(2, dstCity);
		ps.setString(3, srcStation);
		ps.setString(4, dstStation);
		ps.setInt(5, idDriver);
		ps.setString(6, departureTime);
		ps.executeUpdate();
		conn.close();
	}

	// return all rides between two stations
	public ResultSet getRide(String srcStation, String dstStation) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `srcstation` = ? AND `dststation` = ?;");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// return user's group
	public ResultSet getGroupForUser(int idUser) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `iddriver` = ? OR"
				+ " `iduser1` = ? OR `iduser2` = ? OR `iduser3` = ? OR `iduser4` = ?");
		ps.setInt(1, idUser);
		ps.setInt(2, idUser);
		ps.setInt(3, idUser);
		ps.setInt(4, idUser);
		ps.setInt(5, idUser);
		ResultSet rs = ps.executeQuery();
		return rs;		
	}
	
	// return all groups
	public ResultSet getGroups() throws Exception{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group`");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	// join group, if full return false
	public boolean joinGroup(int idUser, int idGroup) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `amount`, `iduser1`, `iduser2`, `iduser3`,"
				+ " `iduser4` FROM `group` WHERE `idgroup` = ?");
		ps.setInt(1, idGroup);
		ResultSet rs = ps.executeQuery();
		rs.next();
		// update status at user
		ps = conn.prepareStatement("UPDATE `users` SET `status` = ? WHERE `iduser` = ? ");			
		ps.setInt(1, 1);
		ps.setInt(2, idUser);

		ps.executeUpdate();
		// update iduser at group
		int amount = rs.getInt("amount");
		if(amount < 4){
			amount += 1;
			if(rs.getInt("iduser1") == 0) {
				ps = conn.prepareStatement("UPDATE `group` SET `iduser1` = ?, `amount` = ? WHERE `idgroup` = ? ");
			}
			else if(rs.getInt("iduser2") == 0) {
				ps = conn.prepareStatement("UPDATE `group` SET `iduser2` = ?, `amount` = ? WHERE `idgroup` = ? ");
			}	
			else if(rs.getInt("iduser3") == 0) {
				ps = conn.prepareStatement("UPDATE `group` SET `iduser3` = ?, `amount` = ? WHERE `idgroup` = ? ");
			}
			else if(rs.getInt("iduser4") == 0) {
				ps = conn.prepareStatement("UPDATE `group` SET `iduser4` = ?, `amount` = ? WHERE `idgroup` = ? ");
			}
		}
		else {
			conn.close();
			return false;
		}
		
		ps.setInt(1, idUser);
		ps.setInt(2, amount);
		ps.setInt(3, idGroup);
		ps.executeUpdate();
		conn.close();
		return true;
	}
	
	// get groups from one city to another city
	public ResultSet getGroups(String srcCity, String dstCity) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `srcCity` = ? AND"
				+ " `dstCity` = ? AND `amount` < 4 ");
		ps.setString(1, srcCity);
		ps.setString(2, dstCity);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
		
	// delete group and update users
	public void deleteGroup(int idGroup) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("DELETE FROM `group` WHERE `idgroup` = ? ");
		ps.setInt(1, idGroup);
		ps.executeUpdate();
		conn.close();
	}
	
	// get group by driver id
	public ResultSet getGroup(int idDriver) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `iddriver` = ? ");
		ps.setInt(1, idDriver);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

}
