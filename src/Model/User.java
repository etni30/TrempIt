package Model;

import API.ModelInterface;
import Model.*;

public abstract class User {
	
	private int idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	//TODO create a private boolean isInARide, and change the C'tor
	
	public User(int idUser, String firstName, String lastName, String userName, String password, String email) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	// get and set functios:
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public boolean isInARide() {
		//TODO make an abstract function for both driver and passenger
		return true;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// if we change values we need to update the data base separately 
	public void updateDB() throws Exception{
		Model m = new Model();
		m.updateUser(idUser, firstName, lastName, userName, password, email);
	}
}
