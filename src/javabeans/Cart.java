package javabeans;

/**
 * 
 * Class: DIT/FT/2B/21
 * Group: 1
 * 
 * Name: LEE ZONG XUN RENFRED
 * Admin Number: P1935392 
 * 
 * Name: WONG EN TING KELYN
 * Admin Number: P1935800
 * 
 */

public class Cart {
	private int userId;
	private int productId;
	private int quantity;
	private String dateTime;
	
	private String productName;
	private String productDescription;
	private double productCostPrice;
	private String productImage;
	
	// default constructor
	public Cart() {
		
	}
	
	// constructor overload
	public Cart(int userId, int productId, int quantity, String dateTime, String productName, String productDescription, double productCostPrice, String productImage) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.dateTime = dateTime;
		
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCostPrice = productCostPrice;
		this.productImage = productImage;
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
	
	// get product name
	public String getProductName() {
		return productName;
	}
		
	// get product description
	public String getProductDescription() {
		return productDescription;
	}
		
	// get product cost price
	public double getProductCostPrice() {
		return productCostPrice;
	}
	
	// get dateTime
	public String getDateTime() {
		return dateTime;
	}
	
	// get product image
	public String getProductImage() {
		return productImage;
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
	
	// set product name
	public void setProductName(String productName) {
		this.productName = productName;
	}
		
	// set product description
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
		
	// set product cost price
	public void setProductCostPrice(double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}
		
	// set product image
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
