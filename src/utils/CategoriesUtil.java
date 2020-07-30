package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;
import javabeans.Categories;


public class CategoriesUtil {
	
	// get all categories
	public ArrayList<Categories> getCategories () throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement and query string
		Statement stmt = conn.createStatement();
		String getCategoryByIdQuery = "SELECT * FROM duotexture.categories;";
		ResultSet getCategoryByIdResult = stmt.executeQuery(getCategoryByIdQuery);
		
		// create new ArrayList of categories object
		ArrayList<Categories> categoriesArrayList = new ArrayList<Categories>();
		
		// loop if there are new row
		while(getCategoryByIdResult.next()) {
			// create an instance of categories
			Categories categoryBean = new Categories();
			
			categoryBean.setCategoryId(getCategoryByIdResult.getInt("categoryId"));
			categoryBean.setName(getCategoryByIdResult.getString("name"));
			categoryBean.setDescription(getCategoryByIdResult.getString("description"));
			categoryBean.setImage(getCategoryByIdResult.getString("image"));
			
			// add categoryBean to categoriesArrayList
			categoriesArrayList.add(categoryBean);
		}
		
		// close connection
		conn.close();
		return categoriesArrayList;
	}
	
	// get category by id
	public Categories getCategoryById (int categoryId) throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement and query string
		String getCategoryByIdQuery = "SELECT * FROM duotexture.categories WHERE categoryId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getCategoryByIdQuery);
		pstmt.setInt(1,  categoryId);
		ResultSet getCategoryByIdResult = pstmt.executeQuery();
		
		// create an instance of categories
		Categories categoryBean = new Categories();
		
		// if there is a new row
		if(getCategoryByIdResult.next()) {
			categoryBean.setCategoryId(getCategoryByIdResult.getInt("categoryId"));
			categoryBean.setName(getCategoryByIdResult.getString("name"));
			categoryBean.setDescription(getCategoryByIdResult.getString("description"));
			categoryBean.setImage(getCategoryByIdResult.getString("image"));
		}
		
		// close connection
		conn.close();
		return categoryBean;
	}
	
	public static int insertCategory (String name, String description, String image) throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// insert inputs into categories
		String addCategoryQuery = "INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES(?, ?, ?);"; 
		PreparedStatement pstmt = conn.prepareStatement(addCategoryQuery);
	    pstmt.setString(1, name);
	    pstmt.setString(2, description);
	    pstmt.setString(3, image);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	public int editCategory (String name, String description, String image, int categoryId) throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// edit and update category with inputs by category id
		String updateCategoryQuery = "UPDATE categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
		PreparedStatement pstmt = conn.prepareStatement(updateCategoryQuery);
	    pstmt.setString(1, name);
	    pstmt.setString(2, description);
	    pstmt.setString(3, image);
	    pstmt.setInt(4, categoryId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	public int deleteCategory (int categoryId) throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// delete category by given id
		String deleteCategory = "DELETE FROM categories WHERE categoryId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteCategory);
	    pstmt.setInt(1, categoryId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
