package Controller;

import java.util.LinkedList;
import API.*;
import DataBase.DataBase;
import Model.*;
import Model.Driver;

import java.sql.*;
import API.DBInterface;


//this class will contain all of the business logic and algorithms
public class Controller implements ControllerInterface{
    
	private ModelInterface model = null;
	private DataBase dataBase = null;
    
    public Controller() throws Exception {
    	this.model = new Model();
    	this.dataBase = new DataBase();
    }
    
    	

	// here we will put functions the business logic

    
    // here we will put the functions that access the database through the model
    
	// add a new user, return 0 if success, return 1 if there exist user with same name, return 2 if sql problem
    
    public User getUser(String username) throws Exception {
			
    	return model.getUser(username);
    }
    
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
    
	// return all station
    public LinkedList<String> getStations() throws Exception
    {
    	return model.getStations();
    }

    
    // get type of user return: 0 - Passenger | 1 - Driver | 2 -Admin 
    public int getType(User u) {
    	if(u instanceof Passenger)
    		return 0;
    	if(u instanceof Driver)
    		return 1;
    	return 2;
    }



	@Override
	public Group getGroupForUser(int idUser) throws Exception {
		return model.getGroupForUser(idUser);
	}



	@Override
	public void updateUser(int iduser, String first, String last, String username, String password, String email,
			boolean isInARide) throws Exception {
		model.updateUser(iduser, first, last, username, password, email, isInARide);
	}
	public String getUserNumberOrMail(int userId) throws Exception {
		User user = model.getUser(userId);
		return user.getEmail();
	}
    
}


