package Model;

public class Passenger extends User{
	
	private boolean isInARide;//TODO delete 'isInARide' and change the C'tor
	
	public Passenger(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email);
		this.isInARide = isInARide;
	}
	
	public boolean isInARide() {
		return isInARide;
	}
	public void setInARide(boolean isInARide) {
		this.isInARide = isInARide;
	}

	// join ride in DB
	public boolean joinGroup(int idGroup) throws Exception{
		Model md = new Model();
		return md.joinGroup(this.getIdUser(), idGroup);
	}

}
