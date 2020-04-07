package Controller;

import java.util.LinkedList;
import API.*;
import DataBase.DataBase;
import Model.*;


//this class will contain all of the business logic and algorithms
public class Controller implements ControllerInterface{
    
	private ModelInterface model = null;
	private DataBase dataBase = null;
    
    public Controller() {
    	this.model = new Model();
    	this.dataBase = new DataBase();
    }
    
	// here we will put functions the business logic
    
    // here we will put the functions that access the database through the model
    
	// add a new user, return 0 if success, return 1 if there exist user with same name, return 2 if sql problem
    
    public int addNewUser(String first, String last, String type, String username, String password, String email) {
		try {
			model.addNewUser(first, last, type, username, password, email);
			return 0;
		}catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {	// if users with same name
			System.out.println(e);
			return 1;
		}
		catch (Exception e) {	// if problem with SQL
			System.out.println(e);
			return 2;
		}
	}
    
    public boolean checkPassword(String username, String password) throws Exception
    {
    	try {
    		return dataBase.checkPassword(username, password);
    	}
    	catch(Exception e)
    	{
    		throw e;
    	}
    }
    // get type of user return: 0 - Passenger | 1 - Driver | 2 -Admin 
    public int getType(User u) {
    	if(u instanceof Passenger)
    		return 0;
    	if(u instanceof Driver)
    		return 1;
    	return 2;
    }
    
    //______________________________________________________________________________________________________________
    // Method name: Find_Tremp
    // Description: The function returns a list of tramps between the two stations that deploy after departure_time.
    // 				prefer = 0 for minimum walk, prefer = 1 for fastest arrive
    	
//TODO delete des and source station, we don't need them
    
//    public LinkedList <Tramp> Find_Tramp(String source_station, String source_city, String dest_station, String dest_city, Time departure_time, int prefer )
//    {
//    	
//    	LinkedList <Tramp> trampList  = model.getTramps(source_city, dest_city, departure_time);
//    	if(prefer == 0)
//    	{
//    		int dist;
//    		for(Tramp tr:trampList)
//    		{
//    			//dist = model.get_distance(source_city, source_station, tr.);
//    		}
//    	}
//    	return trampList;
//    }
    //_______________________________________________________________________________________________________________

}



