package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javabeans.Category;


public class CategoryUtils {
	
	// get all categories
	public ArrayList<Category> getCategories () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all categories query and result
		Statement stmt = conn.createStatement();
		String getCategoriesQuery = "SELECT * FROM duotexture.categories;";
		ResultSet getCategoriesResult = stmt.executeQuery(getCategoriesQuery);
		
		// create new ArrayList of category
		ArrayList<Category> categoriesArrayList = new ArrayList<Category>();
		
		// loop if there are new row
		while(getCategoriesResult.next()) {
			// create an instance of category
			Category categoryBean = new Category();
			
			categoryBean.setCategoryId(getCategoriesResult.getInt("categoryId"));
			categoryBean.setName(getCategoriesResult.getString("name"));
			categoryBean.setDescription(getCategoriesResult.getString("description"));
			categoryBean.setImage(getCategoriesResult.getString("image"));
			
			// add categoryBean to categoriesArrayList
			categoriesArrayList.add(categoryBean);
		}
		
		// close connection
		conn.close();
		return categoriesArrayList;
	}
	
	// get category by id
	public Category getCategoryById (int categoryId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a category by category id query and result
		String getCategoryByIdQuery = "SELECT * FROM duotexture.categories WHERE categoryId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getCategoryByIdQuery);
		pstmt.setInt(1,  categoryId);
		ResultSet getCategoryByIdResult = pstmt.executeQuery();
		
		// create an instance of category
		Category categoryBean = new Category();
		
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
	
	// add category
	public static int insertCategory (String name, String description, String image) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add categories query and result
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
	
	// edit category
	public int editCategory (String name, String description, String image, int categoryId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit category query and result
		String updateCategoryQuery = "UPDATE duotexture.categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
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
	
	// delete category
	public static int deleteCategory (int categoryId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete category query and result
		String deleteCategoryQuery = "DELETE FROM duotexture.categories WHERE categoryId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteCategoryQuery);
	    pstmt.setInt(1, categoryId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
