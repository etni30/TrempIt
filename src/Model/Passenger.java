package Model;

import API.ModelInterface;

public class Passenger extends User{

	public Passenger(int idUser, String firstName, String lastName, String userName, String password, String email,
			boolean isInARide) {
		super(idUser, firstName, lastName, userName, password, email, isInARide);
	}

	// join ride in DB
	public boolean joinGroup(int idGroup) throws Exception{
		if(this.getIsInARide()) {
			throw new Exception("Already in a ride");
		}
		ModelInterface m = new Model();
		boolean result = m.joinGroup(this.getIdUser(), idGroup);
		if(result) {
			setIsInARide(true);
			updateDB();
			return true;
		}
		return false;
	}
	
	// leave ride in DB
	public void leaveGroup() throws Exception{
		if(!this.getIsInARide()) {
			throw new Exception("Not in a ride");
		}
		setIsInARide(false);
		updateDB();
		ModelInterface m = new Model();
		int idGroup = m.getGroupForUser(this.getIdUser()).getGroupId();
		m.leaveGroup(this.getIdUser(), idGroup);
	}
	

}
