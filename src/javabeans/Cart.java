package javabeans;

public class Cart {
	private int userId;
	private int productId;
	private int quantity;
	private String dateTime;
	
	// default constructor
	public Cart() {
		
	}
	
	// constructor overload
	public Cart(int userId, int productId, int quantity, String dateTime) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.dateTime = dateTime;
	}
	
	// get user id
	public int getUserId() {
		return userId;
	}
	
	// get product id
	public int getProductId() {
		return productId;
	}
	
	// get quantity
	public int getQuantity() {
		return quantity;
	}
	
	// get date time
	public String getDateTime() {
		return dateTime;
	}
	
	// set user id
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	// set product id
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	// set quantity
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// set date time
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
