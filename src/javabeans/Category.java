package javabeans;

public class Category {
	private int categoryId;
	private String name;
	private String description;
	private String image;
	
	// default constructor
	public Category() {
		
	}
	
	// constructor overload
	public Category(int categoryId, String name, String description, String image) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.image = image;
	}
	
	// get category id
	public int getCategoryId() {
		return categoryId;
	}
	
	// get category name
	public String getName() {
		return name;
	}
	
	// get category description
	public String getDescription() {
		return description;
	}
	
	// get category image
	public String getImage() {
		return image;
	}
	
	// set category id
	public void setCategoryId(int categoryId) {
		this.categoryId= categoryId;
	}
	
	// set category name
	public void setName(String name) {
		this.name= name;
	}
	
	// set category description
	public void setDescription(String description) {
		this.description= description;
	}
	
	// set category image
	public void setImage(String image) {
		this.image= image;
	}
}
