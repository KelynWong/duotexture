package dbaccess;

public class users {
	private int userId;
	private String email;
	private String username;
	private String password;
	private String userRole;
	
	public int getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserRole() {
		return userRole;
	}
	
	public void setUserId(int userId) {
		this.userId= userId;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	public void setUsername(String username) {
		this.username= username;
	}
	public void setPassword(String password) {
		this.password= password;
	}
	public void setUserRole(String userRole) {
		this.userRole= userRole;
	}
}
