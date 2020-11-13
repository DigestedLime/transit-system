package user;


class AdminUser extends User {

	public AdminUser(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	
	//NOTE: We might need to make the log in method for Admin different.
	
	
	
	public AdminUser makeAdmin(String username, String password) {
		return new AdminUser(username, password);
	}
	
}