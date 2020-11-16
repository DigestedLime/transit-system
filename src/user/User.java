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
		return this.pass.equals(password);
		
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.pass;
	}
}
