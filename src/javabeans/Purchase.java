package javabeans;

public class Purchase {
	private int purchaseId;
	private int userId;
	private int productId;
	private int quantity;
	
	// default constructor
	public Purchase() {
		
	}
	
	// constructor overload
	public Purchase(int purchaseId, int userId, int productId, int quantity) {
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	// get purchase id
	public int getPurchaseId() {
		return purchaseId;
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
	
	// set purchase id
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
