package DataBase;

import java.sql.*;

import API.DBInterface;

// this class will be responsible for handling the database:
// inserting, updating, deleting, selecting
public class DataBase implements DBInterface {
	
	private Connection conn;
	
	// open a connection
	public void openConnection() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/trampit", "root", "");
		}
		catch(Exception e){
				throw new Exception("ERROR- COULD NOT OPEN DB CONNECTION");
		}
	}
	
	// close connection
	public void closeConnection() throws Exception
	{
		try {
			if(conn != null)
				conn.close();
		}
		catch (Exception e){
			System.out.println("Connection already closed");
		}
	}
	
	
	// user functions:------------------------------------------------------------------
	
	// add a new user
	public void addNewUser(String first, String last, String type, String username, String password,
			String email) throws Exception{
	
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `users` (`firstname`, `lastname`, `type`, `username`,"
				+ " `password`, `email`) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setString(1, first);
		ps.setString(2, last);
		ps.setString(3, type);
		ps.setString(4, username);
		ps.setString(5, password);
		ps.setString(6, email);
		ps.executeUpdate();
		this.closeConnection();
	}
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password, String email,
			boolean isInARide) throws Exception {
		
		this.openConnection();
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
		this.closeConnection();
		
	}
	
	// return ResultSet of user with specific username
	public ResultSet getUser(String username) throws Exception {
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` = ?;");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// return ResultSet of user with specific userId
	public ResultSet getUser(int userId) throws Exception {
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `iduser` = ?;");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// return ResultSet of all users
	public ResultSet getUsers() throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users`;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	
	// check valid password return true if correct
	public boolean checkPassword(String username, String password) throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `users` WHERE `username` = ? AND `password` = ?;");
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			this.closeConnection();
			return true;
		}
		this.closeConnection();
		return false;
	}

	// edge functions:--------------------------------------------------------------------
	
	// return ResultSet of all edges
	public ResultSet getEdges() throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `edge`;");
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// get distance between two stations in dsge table in DB, return float
	public float getDistance(String srcStation, String dstStation) throws Exception{
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT `distance` FROM `edge` WHERE `station1` = ? AND"
				+ " `station2` = ? ;");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		float dis = 0;
		ResultSet rs = ps.executeQuery();
		if(rs.next() != false) {
			dis = rs.getFloat("distance");
			rs.previous();
			this.closeConnection();
			return dis;
		}
		ps.setString(1, dstStation);
		ps.setString(2, srcStation);
		rs = ps.executeQuery();
		rs.next();
		dis = rs.getFloat("distance");
		this.closeConnection();
		return dis;
	}
	
	// update distance between two stations in edge Table in DB
	public void changeDistance(String station1, String station2, float dist) throws Exception{
		
		this.openConnection();
		
		// two stations can appear in data base in two ways station1,station2 and station2,station1
		PreparedStatement ps = conn.prepareStatement("UPDATE `edge` SET `distance` = ? WHERE `station1` = ? AND `station2` = ?");
		ps.setFloat(1, dist);
		ps.setString(2, station1);
		ps.setString(3, station2);
		ps.executeUpdate();
	
		ps = conn.prepareStatement("UPDATE `edge` SET `distance` = ? WHERE `station1` = ? AND `station2` = ?");
		ps.setFloat(1, dist);
		ps.setString(2, station2);
		ps.setString(3, station1);
		ps.executeUpdate();
		this.closeConnection();
	}
	
	// add station to edge table in DB with distance 1 to all stations in the same city
	public void addStationToEdge(String station1, String city) throws Exception{
		
		this.openConnection();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `edge` WHERE `city` = ? AND (`station1` = ? OR `station2` = ?) ");
		ps.setString(1, city);
		ps.setString(2, station1);
		ps.setString(3, station1);
		ResultSet rs = ps.executeQuery();
		// if the station is not inside the edge table in DB than add it 
		// with distance 1 to all other stations
		if(!rs.next()) {
			ps = conn.prepareStatement("SELECT `stationname` FROM `station` WHERE `city` = ?");
			ps.setString(1, city);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(!rs.getString("stationname").equals(station1)) {
					ps = conn.prepareStatement("INSERT INTO `edge` (`station1`, `station2`, `city`)"
							+ " VALUES (?, ?, ?)");
					ps.setString(1, station1);
					ps.setString(2, rs.getString("stationname"));
					ps.setString(3, city);
					ps.executeUpdate();
				}
			}
		}
		this.closeConnection();
	}
	
	// add city to edge table in DB with distance 1 to all cities
	public void addCityToEdge(String city) throws Exception{
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `edge` WHERE `city` = ? AND (`station1` = ? OR `station2` = ?) ");
		ps.setString(1, "cities");
		ps.setString(2, city);
		ps.setString(3, city);
		ResultSet rs = ps.executeQuery();
		
		// if the city is not inside the edge table in DB than add it 
		// with distance 1 to all other cities
		if(!rs.next()) {
			ps = conn.prepareStatement("SELECT DISTINCT `city` FROM `station`");
			rs = ps.executeQuery();
			while(rs.next()) {
				if(!rs.getString("city").equals(city)) {
					ps = conn.prepareStatement("INSERT INTO `edge` (`station1`, `station2`, `city`)"
							+ " VALUES (?, ?, ?)");
					ps.setString(1, city);
					ps.setString(2, rs.getString("city"));
					ps.setString(3, "cities");
					ps.executeUpdate();
				}
			}
		}
		this.closeConnection();
	}
	
	// station functions:------------------------------------------------------------------
	
	// return ResultSet of all stations
	public ResultSet getStations() throws Exception{	
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `station`");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	// return city of specific station
	public String getCity(String station) throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT `city` FROM `station` WHERE `stationname` = ? ");
		ps.setString(1, station);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String s =  rs.getString("city");
		this.closeConnection();
		return s;
	}
	
	// add new station to station table in DB
	public void addStation(String  stationName, String city) throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `station` (`stationname`, `city`) "
				+ " VALUES (?, ?);");
		ps.setString(1, stationName);
		ps.setString(2, city);
		ps.executeUpdate();
		this.closeConnection();
	}

	// group functions:------------------------------------------------------------------

	// add a ride from source to destination to group table in DB
	public void addRide(int idDriver, String departureTime, String srcStation, String srcCity, String dstStation,
			String dstCity) throws Exception{
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `group` (`srcCity`, `dstCity`, `srcstation`,"
				+ " `dststation`, `iddriver`, `departureTime`) VALUES (?, ?, ?, ?, ?, ?);");
		ps.setString(1, srcCity);
		ps.setString(2, dstCity);
		ps.setString(3, srcStation);
		ps.setString(4, dstStation);
		ps.setInt(5, idDriver);
		ps.setString(6, departureTime);
		ps.executeUpdate();
		this.closeConnection();
	}

	// return ResultSet of all rides between two stations
	public ResultSet getRide(String srcStation, String dstStation) throws Exception{
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `srcstation` = ? AND `dststation` = ?;");
		ps.setString(1, srcStation);
		ps.setString(2, dstStation);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	// return ResultSet of passenger's group
	public ResultSet getGroupForUser(int idUser) throws Exception{ 
		
		this.openConnection();
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
	
	// return ResultSet of all groups
	public ResultSet getGroups() throws Exception{	
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group`");
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	// add user to group, if group is full return false otherwise return true
	public boolean joinGroup(int idUser, int idGroup) throws Exception{

		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT `amount`, `iduser1`, `iduser2`, `iduser3`,"
				+ " `iduser4` FROM `group` WHERE `idgroup` = ?");
		ps.setInt(1, idGroup);
		ResultSet rs = ps.executeQuery();
		rs.next();

		// update iduser at group
		int amount = rs.getInt("amount");
		if(amount == 4){
			this.closeConnection();
			return false;
		}
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
		
		ps.setInt(1, idUser);
		ps.setInt(2, amount);
		ps.setInt(3, idGroup);
		ps.executeUpdate();
		this.closeConnection();
		return true;
	}
	
	// remove user from group
	public void leaveGroup(int idUser, int idGroup) throws Exception{

		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `idgroup` =?");
		ps.setInt(1, idGroup);
		ResultSet rs = ps.executeQuery();
		rs.next();

		// update iduser at group
		int amount = rs.getInt("amount");
		amount -= 1;
		if(rs.getInt("iduser1") == idUser) {
			ps = conn.prepareStatement("UPDATE `group` SET `iduser1` = ?, `amount` = ? WHERE `idgroup` = ? ");
		}
		else if(rs.getInt("iduser2") == idUser) {
			ps = conn.prepareStatement("UPDATE `group` SET `iduser2` = ?, `amount` = ? WHERE `idgroup` = ? ");
		}	
		else if(rs.getInt("iduser3") == idUser) {
			ps = conn.prepareStatement("UPDATE `group` SET `iduser3` = ?, `amount` = ? WHERE `idgroup` = ? ");
		}
		else if(rs.getInt("iduser4") == idUser) {
			ps = conn.prepareStatement("UPDATE `group` SET `iduser4` = ?, `amount` = ? WHERE `idgroup` = ? ");
		}
		
		ps.setInt(1, 0);
		ps.setInt(2, amount);
		ps.setInt(3, idGroup);
		ps.executeUpdate();
		this.closeConnection();
	}
		
	// return ResultSet of all groups from one city to another city
	public ResultSet getGroups(String srcCity, String dstCity) throws Exception {
		
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE `srcCity` = ? AND"
				+ " `dstCity` = ? AND `amount` < 4 ");
		ps.setString(1, srcCity);
		ps.setString(2, dstCity);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
		
	// delete group from DB
	public void deleteGroup(int idGroup) throws Exception {
		 
		this.openConnection();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM `group` WHERE `idgroup` = ? ");
		ps.setInt(1, idGroup);
		ps.executeUpdate();
		this.closeConnection();
	}
	
	
	// Create DB for new systems -------------------------------------------------------------------
		public void createDB() throws Exception{
			try {
				
				//-----------Open DB connection--------------------------//
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
				
				//--------------------------------------------------------//

				PreparedStatement ps = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS `trampit`");
				int exist = ps.executeUpdate(); // exist = 1 if created trampit Data Base 
												// and exist = 0 if already exists
				if(exist==1){
					
					this.createUsers();
					
					this.createGroup();
					
					this.createEdge();
					
					this.createStation();
					
				}
				
				this.closeConnection();
				
				
			} catch (Exception e) {
				dropDB();
				throw new Exception("DB creation failed");
			}	
		}
		
		public void dropDB() throws Exception
		{
			PreparedStatement ps= conn.prepareStatement("DROP DATABASE `trampit`");
			ps.executeUpdate();
		}
	
		// creating "users" table in DB
		public void createUsers() throws Exception{
			PreparedStatement ps= conn.prepareStatement("CREATE TABLE `trampit`.`users` (" + 
					"  `iduser` int(11) NOT NULL," + 
					"  `firstname` varchar(255) NOT NULL," + 
					"  `lastname` varchar(255) NOT NULL," + 
					"  `type` varchar(255) NOT NULL," + 
					"  `username` varchar(255) NOT NULL," + 
					"  `password` varchar(255) NOT NULL," + 
					"  `email` varchar(255) NOT NULL," + 
					"  `isinaride` tinyint(1) NOT NULL DEFAULT 0" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"); 

			ps.executeUpdate();

			ps = conn.prepareStatement("ALTER TABLE `trampit`.`users`" + 
					"  ADD PRIMARY KEY (`iduser`)," + 
					"  ADD UNIQUE KEY `username` (`username`);");
			ps.executeUpdate();
			
			ps = conn.prepareStatement("ALTER TABLE `trampit`.`users`" + 
					"  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;");
			
			ps.executeUpdate();
			
			ps= conn.prepareStatement("INSERT INTO `trampit`.`users` (`iduser`, `firstname`, `lastname`, `type`, `username`, `password`, `email`, `isinaride`) VALUES" + 
					"(33, 'Igor', 'Rochlin', 'admin', 'Igor123', '1234', 'Igor@gmail.com', 0)," + 
					"(34, 'Jerry', 'Seinfeld', 'driver', 'Jerom', '1234', 'JS@gmail.com', 1)," + 
					"(35, 'Eden', 'Fargon', 'driver', 'Eden123', '1234', 'ednenF@gmail.com', 1)," + 
					"(36, 'Guy', 'Amit', 'driver', 'guy123', 'password', 'guyAmit11@gmail.com', 1)," + 
					"(37, 'Mr', 'Zipan', 'driver', 'Marzipan', '1234', 'Marzipan@yalla.com', 1)," + 
					"(38, 'Itamar', 'Dayan', 'passenger', 'Itamar123', '1234', 'Itamard06@gmail.com', 1)," + 
					"(39, 'Etni', 'Hagashi', 'passenger', 'EtniH', 'Yemen', 'Hagashi@gmail.com', 1)," + 
					"(40, 'Eliyahu', 'Levi', 'driver', 'eli123', '1234abcd', 'Eliyahu@yahoo.com', 1)," + 
					"(41, 'Rick', 'Sanchez', 'driver', 'PickleRick', '1234', 'Rick@gmail.com', 0)," + 
					"(42, 'Assi', 'Cohen', 'driver', 'Assi', '2222', 'AC@hotmail.com', 1)," + 
					"(43, 'Walter', 'White', 'passenger', 'Heisenberg', '0000', 'WH@walla.com', 0);");
			
			ps.executeUpdate();

		}
	
		// creating "group" table in DB
		public void createGroup() throws Exception{
			PreparedStatement ps= conn.prepareStatement("CREATE TABLE `trampit`.`group` (" + 
					"  `idgroup` int(11) NOT NULL," + 
					"  `amount` int(11) NOT NULL DEFAULT 0," + 
					"  `departureTime` varchar(255) NOT NULL DEFAULT '00:00'," + 
					"  `srcCity` varchar(255) DEFAULT NULL," + 
					"  `dstCity` varchar(255) DEFAULT NULL," + 
					"  `srcstation` varchar(255) NOT NULL," + 
					"  `dststation` varchar(255) NOT NULL," + 
					"  `iddriver` int(11) NOT NULL," + 
					"  `iduser1` int(11) NOT NULL DEFAULT 0," + 
					"  `iduser2` int(11) NOT NULL DEFAULT 0," + 
					"  `iduser3` int(11) NOT NULL DEFAULT 0," + 
					"  `iduser4` int(11) NOT NULL DEFAULT 0" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
					
			ps.executeUpdate();
			
			ps = conn.prepareStatement("ALTER TABLE `trampit`.`group`" + 
					"  ADD PRIMARY KEY (`idgroup`)," + 
					"  ADD UNIQUE KEY `iddriver` (`iddriver`);");
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement("ALTER TABLE `trampit`.`group`" + 
					"  MODIFY `idgroup` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;");
			
			ps.executeUpdate();
			
			ps= conn.prepareStatement("INSERT INTO `trampit`.`group` (`idgroup`, `amount`, `departureTime`, `srcCity`, `dstCity`, `srcstation`, `dststation`, `iddriver`, `iduser1`, `iduser2`, `iduser3`, `iduser4`) VALUES" + 
					"(1, 0, '08:20', 'raanana', 'ramat gan', 'ahuza shwartz', 'ramat hen', 34, 0, 0, 0, 0)," + 
					"(2, 2, '10:20', 'raanana', 'ramat gan', 'amidar', 'tel binyamin', 35, 38, 39, 0, 0)," + 
					"(3, 0, '12:25', 'ramat gan', 'raanana', 'ramat hen', 'amidar', 36, 0, 0, 0, 0)," + 
					"(4, 0, '15:40', 'jerusalem', 'raanana', 'rehavia', 'amidar', 37, 0, 0, 0, 0)," + 
					"(5, 0, '13:30', 'jerusalem', 'ramat gan', 'rehavia', 'ramat hen', 40, 0, 0, 0, 0)," + 
					"(7, 0, '16:30', 'raanana', 'jerusalem', 'amidar', 'rehavia', 42, 0, 0, 0, 0);");
			
			ps.executeUpdate();

		}
	
		public void createEdge() throws Exception{
			PreparedStatement ps= conn.prepareStatement("CREATE TABLE `trampit`.`edge` (" + 
					"  `station1` varchar(255) NOT NULL," + 
					"  `station2` varchar(255) NOT NULL," + 
					"  `city` varchar(255) NOT NULL," + 
					"  `distance` double NOT NULL DEFAULT 1" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
					
			ps.executeUpdate();
			
			ps = conn.prepareStatement("ALTER TABLE `trampit`.`edge`" + 
					"  ADD PRIMARY KEY (`station1`,`station2`);");
			ps.executeUpdate();
			ps= conn.prepareStatement("INSERT INTO `trampit`.`edge` (`station1`, `station2`, `city`, `distance`) VALUES" + 
					"('ahuza shwartz', 'amidar', 'raanana', 2)," + 
					"('jerusalem', 'raanana', 'cities', 80)," + 
					"('jerusalem', 'ramat gan', 'cities', 60)," + 
					"('marom nave', 'ramat hen', 'ramat gan', 2.6)," + 
					"('neot sade', 'ahuza shwartz', 'raanana', 2)," + 
					"('neot sade', 'amidar', 'raanana', 2.5)," + 
					"('neot sade', 'ravutzki', 'raanana', 1.9)," + 
					"('ramat gan', 'raanana', 'cities', 20)," + 
					"('ravutzki', 'ahuza shwartz', 'raanana', 0.95)," + 
					"('ravutzki', 'amidar', 'raanana', 2.4)," + 
					"('rehavia', 'kiryat menachem', 'jerusalem', 4)," + 
					"('tel binyamin', 'marom nave', 'ramat gan', 3.5)," + 
					"('tel binyamin', 'ramat hen', 'ramat gan', 2.4);");
			
			ps.executeUpdate();
		}
	
		public void createStation() throws Exception{	
			PreparedStatement ps= conn.prepareStatement("CREATE TABLE `trampit`.`station` (" + 
					"  `stationname` varchar(255) NOT NULL," + 
					"  `city` varchar(255) NOT NULL" + 
					") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
			ps.executeUpdate();
			
			ps = conn.prepareStatement("ALTER TABLE `trampit`.`station` " +
					  "ADD UNIQUE KEY `stationname` (`stationname`);");
			
			ps.executeUpdate();
			
			ps= conn.prepareStatement("INSERT INTO `trampit`.`station` (`stationname`, `city`) VALUES" + 
					"('ahuza shwartz', 'raanana')," + 
					"('amidar', 'raanana')," + 
					"('kiryat menachem', 'jerusalem')," + 
					"('marom nave', 'ramat gan')," + 
					"('neot sade', 'raanana')," + 
					"('ramat hen', 'ramat gan')," + 
					"('ravutzki', 'raanana')," + 
					"('rehavia', 'jerusalem')," + 
					"('tel binyamin', 'ramat gan');");
			
			ps.executeUpdate();

		}
	
	
}
