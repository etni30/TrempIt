package API;

import java.util.LinkedList;

import Model.Group;
import Model.User;

public interface ControllerInterface {
	
	// add a new user, return 0 if success, return 1 if there exist user with same name, return 2 if sql problem
    public int addNewUser(String first, String last, String type, String username, String password, String email);
		
    // get type of user return 0 - Passenger | 1 - Driver | 2 -Admin 
    public int getType(User u);
    
    public boolean checkPassword(String userName, String psw) throws Exception;
    
    public LinkedList<String> getStations() throws Exception;
    
    public Group getGroupForUser(int idUser) throws Exception;
    
    // update properties for specific user
 	public void updateUser(int iduser, String first, String last, String username, String password, String email,
 			boolean isInARide) throws Exception;
}
