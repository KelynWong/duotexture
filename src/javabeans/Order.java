package javabeans;

public class Order {
	private int orderId;
	private int userId;
	private int productId;
	private int quantity;
	
	// default constructor
	public Order() {
		
	}
	
	// constructor overload
	public Order(int orderId, int userId, int productId, int quantity) {
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	// get order id
	public int getOrderId() {
		return orderId;
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
	
	// set order id
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
}