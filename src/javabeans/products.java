package javabeans;

public class products {
	private int productId;
	private String name;
	private String description;
	private double cost_price;
	private double retail_price;
	private int quantity;
	private int categoryId;
	private String image;
	
	// default constructor
	public products() {
		
	}
	
	// constructor overload
	public products(int productId, String name, String description, double cost_price, double retail_price, int quantity, int categoryId, String image) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.cost_price = cost_price;
		this.retail_price = retail_price;
		this.quantity = quantity;
		this.categoryId = categoryId;
		this.image = image;
	}
	
	// get product id
	public int getProductId() {
		return productId;
	}
	
	// get product name
	public String getName() {
		return name;
	}
	
	// get product description
	public String getDescription() {
		return description;
	}
	
	// get product cost price
	public double getCostPrice() {
		return cost_price;
	}
	
	// get product retail price
	public double getRetailPrice() {
		return retail_price;
	}
	
	// get product quantity
	public int getQuantity() {
		return quantity;
	}
	
	// get product category id
	public int getCategoryId() {
		return categoryId;
	}
	
	// get product image
	public String getImage() {
		return image;
	}
	
	// set product id
	public void setProductId(int productId) {
		this.productId= productId;
	}
	
	// set product name
	public void setName(String name) {
		this.name= name;
	}
	
	// set product description
	public void setDescription(String description) {
		this.description= description;
	}
	
	// set product cost price
	public void setCostPrice(double cost_price) {
		this.cost_price= cost_price;
	}
	
	// set product retail price
	public void setRetailPrice(double retail_price) {
		this.retail_price= retail_price;
	}
	
	// set product quantity
	public void setQuantity(int quantity) {
		this.quantity= quantity;
	}
	
	// set product category id
	public void setCategoryId(int categoryId) {
		this.categoryId= categoryId;
	}
	
	// set product image
	public void setImage(String image) {
		this.image= image;
	}
}
