package Model;

public class Group {
	
	
	private String srcCity;
	private String srcStation;
	private String destCity;
	private String destStation;
	private int amount;
	private Time time;
	private int idDriver;
	private int idUser1;
	private int idUser2;
	private int idUser3;
	private int idUser4;
	
	
	public Group(String srcCity, String srcStation, String destCity, String destStation, int amount, String time,
			int idDriver, int idUser1, int idUser2, int idUser3, int idUser4) throws Exception {
		super();
		this.srcCity = srcCity;
		this.srcStation = srcStation;
		this.destCity = destCity;
		this.destStation = destStation;
		this.amount = amount;
		this.time = new Time(time);
		this.idDriver = idDriver;
		//TODO IF it's possible change to array it's more easy to handle
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		this.idUser3 = idUser3;
		this.idUser4 = idUser4;
	}
	
	// get and set functions:
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
	public int getIdUser1() {
		return idUser1;
	}
	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}
	public int getIdUser2() {
		return idUser2;
	}
	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}
	public int getIdUser3() {
		return idUser3;
	}
	public void setIdUser3(int idUser3) {
		this.idUser3 = idUser3;
	}
	public int getIdUser4() {
		return idUser4;
	}
	public void setIdUser4(int idUser4) {
		this.idUser4 = idUser4;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public String getDestStation() {
		return destStation;
	}
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getIdDriver() {
		return idDriver;
	}
	public void setIdDriver(int idDriver) {
		this.idDriver = idDriver;
	}
	
}
