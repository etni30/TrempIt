package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group {
	
	private int id;
	private String srcCity;
	private String srcStation;
	private String dstCity;
	private String dstStation;
	private int amount;
	private Time depTime;
	private String idDriver;
	private String[] usersID;
	
	
	public Group(int id, String srcCity, String srcStation, String dstCity, String dstStation, int amount, String depTime,
			String idDriver, String idUser1, String idUser2, String idUser3, String idUser4) throws Exception {
		super();
		this.id =id;
		this.srcCity = srcCity;
		this.srcStation = srcStation;
		this.dstCity = dstCity;
		this.dstStation = dstStation;
		this.amount = amount;
		this.depTime = new Time(depTime);
		this.idDriver = idDriver;
		this.usersID = new String[4];
		usersID[0] = idUser1;
		usersID[1] = idUser2;
		usersID[2] = idUser3;
		usersID[3] = idUser4;
	}
	
	public Group(Group g) throws Exception {
		super();
		this.id = g.getGroupId();
		this.srcCity = g.getSourceCity();
		this.srcStation = g.getSourceStation();
		this.dstCity = g.getdstCity();
		this.dstStation = g.getdstStation();
		this.amount = g.getAmount();
		this.depTime = new Time(g.getDepTime().toString());
		this.idDriver = g.getIdDriver();
		this.usersID = g.getUsersID();
	}
	
	public Group(ResultSet rs) throws Exception
	{
		String srcCity = rs.getString("srcCity");
		String srcStation = rs.getString("srcstation");
		String dstCity = rs.getString("dstCity");
		String dstStation = rs.getString("dststation");
		int amount = Integer.parseInt(rs.getString("amount"));
		int id = Integer.parseInt(rs.getString("idGroup"));
		String depTime = rs.getString("departureTime");
		String idDriver = rs.getString("idDriver");
		String idUser1 = rs.getString("iduser1");
		String idUser2 = rs.getString("iduser2");
		String idUser3 = rs.getString("iduser3");
		String idUser4 = rs.getString("iduser4");
		
		
		this.srcCity = srcCity;
		this.srcStation = srcStation;
		this.dstCity = dstCity;
		this.dstStation = dstStation;
		this.amount = amount;
		this.id = id;
		this.depTime = new Time(depTime);
		this.idDriver = idDriver;
		this.usersID = new String[4];
		usersID[0] = idUser1;
		usersID[1] = idUser2;
		usersID[2] = idUser3;
		usersID[3] = idUser4;
		
		
	}
	
	// get and set functions:
	public int getGroupId() {
		return id;
	}
	public String getSourceCity() {
		return srcCity;
	}
	public void setSourceCity(String sourceCity) {
		this.srcCity = sourceCity;
	}
	public String getSourceStation() {
		return srcStation;
	}
	public void setSourceStation(String sourceStation) {
		this.srcStation = sourceStation;
	}
	public String[] getUsersID()
	{
		String[] usersIDCopy = usersID.clone();
		return usersIDCopy;
	}
	
	public String getdstCity() {
		return dstCity;
	}
	public void setdstCity(String dstCity) {
		this.dstCity = dstCity;
	}
	public String getdstStation() {
		return dstStation;
	}
	public void setdstStation(String dstStation) {
		this.dstStation = dstStation;
	}
	public Time getDepTime() {
		return depTime;
	}
	public void setTime(Time depTime) {
		this.depTime = depTime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getIdDriver() {
		return idDriver;
	}
	public void setIdDriver(String idDriver) {
		this.idDriver = idDriver;
	}
	
}
