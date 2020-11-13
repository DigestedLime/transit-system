package user;

/***
 * 
 * @author Rehmat
 * 
 * 
 * A User class that takes in the 
 */
public class User {
	
	protected String username;
	protected String pass;

	public User(String name, String password) {
		this.username = name;
		this.pass = password;
	}
	
	public boolean logIn(String password) {
		if (this.pass == password) {
			return true;
		}
		return false;
	}
}
