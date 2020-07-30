package javabeans;

public class Members {
	private String first_name;
	private String last_name;
	private String country;
	private String address;
	private int postal_code;
	private int userId;
	
	// default constructor
	public Members() {
		
	}
	
	// constructor overload
	public Members(String first_name, String last_name, String country, String address, int postal_code, int userId) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.country = country;
		this.address = address;
		this.postal_code = postal_code;
		this.userId = userId;
	}
	
	// get member first name
	public String getFirstName() {
		return first_name;
	}
	
	// get member last name
	public String getLastName() {
		return last_name;
	}
	
	// get member country
	public String getCountry() {
		return country;
	}
	
	// get member address
	public String getAddress() {
		return address;
	}
	
	// get member postal code
	public int getPostalCode() {
		return postal_code;
	}
	
	// get member user id
	public int getUserId() {
		return userId;
	}
	
	// set member first name
	public void setFirstName(String first_name) {
		this.first_name= first_name;
	}
	
	// set member last name
	public void setLastName(String last_name) {
		this.last_name= last_name;
	}
	
	// set member country
	public void setCountry(String country) {
		this.country= country;
	}
	
	// set member address
	public void setAddress(String address) {
		this.address= address;
	}
	
	// set member postal code
	public void setPostalCode(int postal_code) {
		this.postal_code= postal_code;
	}
	
	// set member user id
	public void setUserId(int userId) {
		this.userId= userId;
	}
}
