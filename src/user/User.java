package user;

/***
 * A User class that has a username and password.
 *
 * It is a parent class with the basic functionality for logging in, with child
 * classes that have their own functionality.
 */
public class User {

	protected String username;
	protected String pass;

	/**
	 * Makes a new user object, and sets the username and password
	 * 
	 * @param name
	 * @param password
	 */
	public User(String name, String password) {
		this.username = name;
		this.pass = password;
	}

	/**
	 * @param password password log in attempt
	 * @return true if the password inputed is equal to the user's password
	 */
	public boolean logIn(String password) {
		return this.pass.equals(password);
	}

	/**
	 * @return This user's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return This user's password
	 */
	public String getPassword() {
		return this.pass;
	}
}
