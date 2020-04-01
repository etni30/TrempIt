package Model;

public class Driver extends User{
	
	private boolean isInARide;

	public Driver(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email);
		this.isInARide = isInARide;
	}

	// get and set functions:
	public boolean getIsInARide() {
		return isInARide;
	}
	public void setIsInARide(boolean isInARide) {
		this.isInARide = isInARide;
	}

	// add ride to DB
	public void addRide(String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		Model md = new Model();
		md.addRide(this.getIdUser(), time, srcStation, srcCity, dstStation, dstCity);
	}

}
