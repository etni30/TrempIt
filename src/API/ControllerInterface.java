package API;

public interface ControllerInterface {
	
	// add a new user, return 0 if success, return 1 if there exist user with same name, return 2 if sql problem
    public int addNewUser(String first, String last, String type, String username, String password, String email);
		
}
