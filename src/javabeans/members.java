package javabeans;

public class members {
	private String first_name;
	private String last_name;
	private String country;
	private String address;
	private int postal_code;
	private int userId;
	
	public String getFirstName() {
		return first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public String getCountry() {
		return country;
	}
	public String getAddress() {
		return address;
	}
	public int getPostalCode() {
		return postal_code;
	}
	public int getUserId() {
		return userId;
	}
	
	public void setFirstName(String first_name) {
		this.first_name= first_name;
	}
	public void setLastName(String last_name) {
		this.last_name= last_name;
	}
	public void setCountry(String country) {
		this.country= country;
	}
	public void setAddress(String address) {
		this.address= address;
	}
	public void setPostalCode(int postal_code) {
		this.postal_code= postal_code;
	}
	public void setUserId(int userId) {
		this.userId= userId;
	}
}
