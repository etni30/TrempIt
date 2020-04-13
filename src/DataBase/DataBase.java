package DataBase;

import java.sql.*;

import API.DBInterface;
import Model.Admin;
import Model.Driver;
import Model.Passenger;

// this class will be responsible for handling the database 

public class DataBase implements DBInterface {
	
	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password, String email) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
	
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `users` (`firstname`, `lastname`, `type`, `username`, `password`, `email`) VALUES (?, ?, ?, ?, ?, ?)");
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
	public void updateUser(int iduser, String first, String last, String username, String password, String email) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("UPDATE `users` SET `firstname` = ?, `lastname` = ? , `username` = ?, `password` = ?, `email` = ?  WHERE `iduser` = ? ");
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, username);
		ps.setString(4, password);
		ps.setString(5, email);
		ps.setString(6, Integer.toString(iduser));
		ps.executeUpdate();
		conn.close();
	}
	
	// find user for specific user, return false if failed
	public ResultSet getUser(String username) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` = ?;");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// check valid password
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
	
	// find edge between two stations, return ResultSet
	public ResultSet getEdge(String srcStation, String dstStation) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `edge` WHERE `station1` = ? AND `station2` = ? ;");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		ResultSet rs = ps.executeQuery();
		if(rs.next() != false) {
			rs.previous();
			return rs;
		}
		ps.setString(1, dstStation);
		ps.setString(2, srcStation);
		rs = ps.executeQuery();
		return rs;
	}
	
	// find distance between two stations, return distance
	public float getDistance(String srcStation, String dstStation) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `distance` FROM `edge` WHERE `station1` = ? AND `station2` = ? ;");
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

	// add a ride from source to destination
	public void addRide(int idDriver, String departureTime, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `group` (`srcstation`, `dststation`, `iddriver`, `departureTime`) VALUES (?, ?, ?, ?);");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		ps.setInt(3, idDriver);
		ps.setString(4, departureTime);
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
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `iddriver` = ? OR `iduser1` = ? OR `iduser2` = ? OR `iduser3` = ? OR `iduser4` = ?");
		ps.setInt(1, idUser);
		ps.setInt(2, idUser);
		ps.setInt(3, idUser);
		ps.setInt(4, idUser);
		ps.setInt(5, idUser);
		ResultSet rs = ps.executeQuery();
		return rs;		
	}
	
	// return all stations
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
		
		PreparedStatement ps = conn.prepareStatement("SELECT `amount`, `iduser1`, `iduser2`, `iduser3`, `iduser4` FROM `group` WHERE `idgroup` = ?");
		ps.setInt(1, idGroup);
		ResultSet rs = ps.executeQuery();
		rs.next();
		// update status at user
		ps = conn.prepareStatement("UPDATE `users` SET `status` = 1 WHERE `iduser` = ? ");			
		ps.setInt(1, idUser);
		ps.executeUpdate();
		// update iduser at group
		int amount = rs.getInt("amount");
		if(amount < 4){
			amount += 1;
			switch(amount) {
			case 1:
				ps = conn.prepareStatement("UPDATE `group` SET `iduser1` = ?, `amount` = ? WHERE `idgroup` = ? ");			
				break;
			case 2:
				ps = conn.prepareStatement("UPDATE `group` SET `iduser2` = ?, `amount` = ? WHERE `idgroup` = ? ");			
				break;
			case 3:
				ps = conn.prepareStatement("UPDATE `group` SET `iduser3` = ?, `amount` = ? WHERE `idgroup` = ? ");			
				break;
			case 4:
				ps = conn.prepareStatement("UPDATE `group` SET `iduser4` = ?, `amount` = ? WHERE `idgroup` = ? ");			
				break;
			}	
			ps.setInt(1, idUser);
			ps.setInt(2, amount);
			ps.setInt(3, idGroup);
			ps.executeUpdate();
			conn.close();
			return true;
		}
		conn.close();
		return false;
	}
	
	// return all stations
	public ResultSet getStations() throws Exception{	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT `stationname` FROM `station`");
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

	// get groups from one city to another city
	public ResultSet getGroups(String srcCity, String dstCity) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `srcCity` = ? AND `dstCity` = ? AND `amount` < 4 ");
		ps.setString(1, srcCity);
		ps.setString(2, dstCity);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
		
	// delete group and update users
	public void deleteGroup(int idGroup) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `idgroup` = ? ");
		ps.setInt(1, idGroup);
		ResultSet rs = ps.executeQuery();
		// TODO update each user's status
		rs.next();
		int amount = rs.getInt("amount");
		for(int i = 1; i <= amount; i++) {
		}
		
		ps = conn.prepareStatement("DELETE FROM `group` WHERE `idgroup` = ? ");
		ps.setInt(1, idGroup);
		ps.executeUpdate();
		conn.close();

	}
}
