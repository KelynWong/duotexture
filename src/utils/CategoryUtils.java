package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Category;

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

public class CategoryUtils {
	
	// get all categories
	public static ArrayList<Category> getCategories () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all categories query and result
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
	public static Category getCategoryById (int categoryId) throws SQLException, ClassNotFoundException {
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
	public static int editCategory (String name, String description, String image, int categoryId) throws SQLException, ClassNotFoundException {
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
	
	// get image url
	public static String getCategoryImageUrl (int categoryId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get category image url query and result
		String getCategoryImageUrlQuery = "SELECT image FROM duotexture.categories WHERE categoryId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(getCategoryImageUrlQuery);
	    pstmt.setInt(1, categoryId);
	    ResultSet getCategoryImageUrlResult = pstmt.executeQuery(); 
	    
	    String categoryImageUrl = "";
	    if (getCategoryImageUrlResult.next()) {
	    	categoryImageUrl = getCategoryImageUrlResult.getString("image");
	    }
		
		// close connection
		conn.close();
		return categoryImageUrl;
	}
}
