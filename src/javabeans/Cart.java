package javabeans;

public class Cart {
	private int cardId;
	private int userId;
	private int productId;
	private int quantity;
	
	// default constructor
	public Cart() {
		
	}
	
	// constructor overload
	public Cart(int cardId, int userId, int productId, int quantity) {
		this.cardId = cardId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	// get card id
	public int getCardId() {
		return cardId;
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
	
	// set card id
	public void setCardId(int cardId) {
		this.cardId = cardId;
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
