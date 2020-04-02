package View;

import API.ControllerInterface;
import Controller.Controller;

// this class will be responsible for the view and will access the Controller
public class View{
	ControllerInterface cont = null;
    
	public View(){
        cont = new Controller();
    }
	
	// add a new user, return false if failed
	public int addNewUser(String first, String last, String type, String username, String password, String email) {
		return  cont.addNewUser(first, last, type, username, password, email);
	}
	
}
