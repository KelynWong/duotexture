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
	
	public int getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public double getCostPrice() {
		return cost_price;
	}
	public double getRetailPrice() {
		return retail_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getImage() {
		return image;
	}
	
	public void setProductId(int productId) {
		this.productId= productId;
	}
	public void setName(String name) {
		this.name= name;
	}
	public void setDescription(String description) {
		this.description= description;
	}
	public void setCostPrice(double cost_price) {
		this.cost_price= cost_price;
	}
	public void setRetailPrice(double retail_price) {
		this.retail_price= retail_price;
	}
	public void setQuantity(int quantity) {
		this.quantity= quantity;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId= categoryId;
	}
	public void setImage(String image) {
		this.image= image;
	}
}
