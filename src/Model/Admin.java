package Model;

public class Admin extends User{

	public Admin(int idUser, String firstName, String lastName, String userName, String password, String email) {
		super(idUser, firstName, lastName, userName, password, email);
	}
	
	// update properties for specific user
	public void updateUser(int iduser, String first, String last, String username, String password, String email) throws Exception{
		Model md = new Model();
		md.updateUser(iduser, first, last, username, password, email);
	}
}
