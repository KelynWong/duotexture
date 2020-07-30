package javabeans;

public class Users {
	private int userId;
	private String email;
	private String username;
	private String password;
	private String userRole;
	
	// default constructor
	public Users() {
		
	}
	
	// constructor overload
	public Users(int userId, String email, String username, String password, String userRole) {
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	// get user id
	public int getUserId() {
		return userId;
	}
	
	// get user email
	public String getEmail() {
		return email;
	}
	
	// get user name
	public String getUsername() {
		return username;
	}
	
	// get user password
	public String getPassword() {
		return password;
	}
	
	// get user role
	public String getUserRole() {
		return userRole;
	}
	
	// set user id
	public void setUserId(int userId) {
		this.userId= userId;
	}
	
	// set user email
	public void setEmail(String email) {
		this.email= email;
	}
	
	// set user name
	public void setUsername(String username) {
		this.username= username;
	}
	
	// set user password
	public void setPassword(String password) {
		this.password= password;
	}
	
	// set user role
	public void setUserRole(String userRole) {
		this.userRole= userRole;
	}
}
