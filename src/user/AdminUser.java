package user;

/**
 * AdminUser that is used to identify if a user can have access to an admin
 * panel
 */
class AdminUser extends User {

	public AdminUser(String username, String password) {
		super(username, password);
	}

	/**
	 * To have more admins, only an admin can make more, as we want it so not
	 * everyone can make an admin
	 * 
	 * @param username The username for the new admin
	 * @param password The password for the new admin
	 * @return The new adminuser object
	 */
	public AdminUser makeAdmin(String username, String password) {
		return new AdminUser(username, password);
	}

}