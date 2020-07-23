package dbaccess;

public class categories {
	private int categoryId;
	private String name;
	private String description;
	private String image;
	
	public int getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getImage() {
		return image;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId= categoryId;
	}
	public void setName(String name) {
		this.name= name;
	}
	public void setDescription(String description) {
		this.description= description;
	}
	public void setImage(String image) {
		this.image= image;
	}
}
