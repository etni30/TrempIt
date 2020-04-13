package Model;

public class Passenger extends User{

	public Passenger(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email, isInARide);
	}

	// join ride in DB
	public boolean joinGroup(int idGroup) throws Exception{
		Model m = new Model();
		setIsInARide(true);
		updateDB();
		return m.joinGroup(this.getIdUser(), idGroup);
	}
	

}
