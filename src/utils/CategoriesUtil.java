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
	public ArrayList<Categories> getCategories () {
		// pre-define variables
		Categories categoryBean = null;
		ArrayList<Categories> categoriesArrayList = null;
		Connection conn = null;
		
		try {
			// connect to database
			conn = Database.connectToDatabase();
			
			// prepared statement and query string
			Statement stmt = conn.createStatement();
			String getCategoryByIdQuery = "SELECT * FROM duotexture.categories;";
			ResultSet getCategoryByIdResult = stmt.executeQuery(getCategoryByIdQuery);
			
			// create new ArrayList of categories object
			categoriesArrayList = new ArrayList<Categories>();
			
			// loop if there are new row
			while(getCategoryByIdResult.next()) {
				// create an instance of categories
				categoryBean = new Categories();
				
				categoryBean.setCategoryId(getCategoryByIdResult.getInt("categoryId"));
				categoryBean.setName(getCategoryByIdResult.getString("name"));
				categoryBean.setDescription(getCategoryByIdResult.getString("description"));
				categoryBean.setImage(getCategoryByIdResult.getString("image"));
				
				categoriesArrayList.add(categoryBean);
			}
			
		}catch(Exception e) {
			System.out.println("(categoriesDB.java) Error: " + e + "\n");
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println("(categoriesDB.java) Error: " + e + "\n");
			}
		}
		
		return categoriesArrayList;
	}
	
	// get category by id
	public Categories getCategoryById (int categoryId) {
		// pre-define variables
		Categories categoryBean = null;
		Connection conn = null;
		
		try {
			// connect to database
			conn = Database.connectToDatabase();
			
			// prepared statement and query string
			String getCategoryByIdQuery = "SELECT * FROM duotexture.categories WHERE categoryId=?;";
			PreparedStatement pstmt = conn.prepareStatement(getCategoryByIdQuery);
			pstmt.setInt(1,  categoryId);
			ResultSet getCategoryByIdResult = pstmt.executeQuery();
			
			// create an instance of categories
			categoryBean = new Categories();
			
			// if there is a new row
			if(getCategoryByIdResult.next()) {
				categoryBean.setCategoryId(getCategoryByIdResult.getInt("categoryId"));
				categoryBean.setName(getCategoryByIdResult.getString("name"));
				categoryBean.setDescription(getCategoryByIdResult.getString("description"));
				categoryBean.setImage(getCategoryByIdResult.getString("image"));
			}
			
		}catch(Exception e) {
			System.out.println("(categoriesDB.java) Error: " + e + "\n");
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println("(categoriesDB.java) Error: " + e + "\n");
			}
		}
		
		return categoryBean;
	}
	
	public int insertCategory (String name, String description, String image) {
		// pre-define variables
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to database
			conn = Database.connectToDatabase();

			// insert inputs into categories
			String addCategoryQuery = "INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES(?, ?, ?);"; 
			PreparedStatement pstmt = conn.prepareStatement(addCategoryQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setString(3, image);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
			System.out.println("(categoriesDB.java) Error: " + e + "\n");
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println("(categoriesDB.java) Error: " + e + "\n");
			}
		}
		return count;
	}
	
	public int editCategory (String name, String description, String image, int categoryId) {
		// pre-define variables
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to database
			conn = Database.connectToDatabase();

			// edit and update category with inputs by category id
			String updateCategoryQuery = "UPDATE categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateCategoryQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setString(3, image);
		    pstmt.setInt(4, categoryId);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
			System.out.println("(categoriesDB.java) Error: " + e + "\n");
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println("(categoriesDB.java) Error: " + e + "\n");
			}
		}
		return count;
	}
	
	public int deleteCategory (int categoryId) {
		// pre-define variables
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to database
			conn = Database.connectToDatabase();
			
			// delete category by given id
			String deleteCategory = "DELETE FROM categories WHERE categoryId=?"; 
			PreparedStatement pstmt = conn.prepareStatement(deleteCategory);
		    pstmt.setInt(1, categoryId);
			count = pstmt.executeUpdate(); 

		} catch (Exception e) {
			System.out.println("(categoriesDB.java) Error: " + e + "\n");
		}
		
		return count;
	}
}
