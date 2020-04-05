package Model;

public class Driver extends User{
	
	private boolean isInARide;//TODO delete 'isInARide' and change the C'tor

	public Driver(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email);
		this.isInARide = isInARide;
	}

	// get and set functions: 
	//TODO change method name to isInARide()
	public boolean isInARide() {
		return isInARide;
	}
	public void setIsInARide(boolean isInARide) {
		this.isInARide = isInARide;// TODO change status in dB
	}

	// add ride to DB
	public void addRide(String time, String srcStation, String srcCity, String dstStation, String dstCity) throws Exception{
		Model md = new Model();
		setIsInARide(true);// TODO what happen when you want both to to get and create a tremp?
		md.addRide(this.getIdUser(), time, srcStation, srcCity, dstStation, dstCity);
	}

}
