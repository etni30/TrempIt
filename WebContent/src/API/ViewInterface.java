package API;

public interface ViewInterface {
	// add a new user, return false if failed
	public boolean addNewUser(String first, String last, String kind, String userName, String password, String email);
		
}
