package user;

/***
 * A User class that has a username and password
 *
 * It is a parent class with the basic functionality for logging in, with child classes
 * that have their own functionality
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
	
	public String getUsername() {
		return this.username;
	}
}
