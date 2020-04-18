package Model;

import API.ModelInterface;

public class Driver extends User {
	
	public Driver(int idUser, String firstName, String lastName, String userName, String password,
			String email, boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email, isInARide);
	}

	// add ride to DB
	public void addRide(String time, String srcStation, String srcCity, String dstStation, String dstCity)
			throws Exception{
		if(this.getIsInARide()) {
			throw new Exception("Already in a ride");
		}
		ModelInterface m = new Model();
		setIsInARide(true);
		updateDB();
		m.addRide(this.getIdUser(), time, srcStation, srcCity, dstStation, dstCity);
	}
	
	// delete ride
	public void deleteRide() throws Exception{
		if(!this.getIsInARide()) {
			throw new Exception("Not in a ride");
		}
		ModelInterface m = new Model();
		setIsInARide(false);
		updateDB();
		m.deleteRide(this.getIdUser());
	}
}
