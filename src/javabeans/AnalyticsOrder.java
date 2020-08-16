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

public class AnalyticsOrder {
	private int userId;
	private String fullName;
	private int productId;
	private String productImage;
	private String productName;
	private Double productCostPrice;
	private int productQuantity;
	private String dateTime;
	
	private Double totalProfit;
	private int totalQuantity;
	
	// default constructor
	public AnalyticsOrder() {
		
	}
	
	// get user id
	public int getUserId() {
		return userId;
	}

	// set user id
	public void setUserId(int userId) {
		this.userId = userId;
	}

	// get full name
	public String getFullName() {
		return fullName;
	}

	// set full name
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	// get product id
	public int getProductId() {
		return productId;
	}

	// set product id
	public void setProductId(int productId) {
		this.productId = productId;
	}

	// get product image
	public String getProductImage() {
		return productImage;
	}

	// set product image
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	// get product name
	public String getProductName() {
		return productName;
	}

	// set product name
	public void setProductName(String productName) {
		this.productName = productName;
	}

	// get product cost price
	public Double getProductCostPrice() {
		return productCostPrice;
	}

	// set product cost price
	public void setProductCostPrice(Double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}

	// get product quantity
	public int getProductQuantity() {
		return productQuantity;
	}

	// set product quantity
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	// get date time
	public String getDateTime() {
		return dateTime;
	}

	// set date time
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	// get total profit
	public Double getTotalProfit() {
		return totalProfit;
	}

	// set total profit
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	// get total quantity
	public int getTotalQuantity() {
		return totalQuantity;
	}

	// set total quantity
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
}
