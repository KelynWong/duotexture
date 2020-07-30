package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javabeans.categories;


public class categoriesDB {
	
	// get all categories
	public ArrayList<categories> getCategories () {
		// pre-define variables
		categories categoryBean = null;
		ArrayList<categories> categoriesArrayList = null;
		Connection conn = null;
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/jad?user=root&password=password&serverTimezone=UTC";
			conn = DriverManager.getConnection(connURL);
			
			// prepared statement and query string
			Statement stmt = conn.createStatement();
			String getCategoryByIdQuery = "SELECT * FROM duotexture.categories;";
			ResultSet getCategoryByIdResult = stmt.executeQuery(getCategoryByIdQuery);
			
			// create new ArrayList of categories object
			categoriesArrayList = new ArrayList<categories>();
			
			// loop if there are new row
			while(getCategoryByIdResult.next()) {
				// create an instance of categories
				categoryBean = new categories();
				
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
	public categories getCategoryById (int categoryId) {
		// pre-define variables
		categories categoryBean = null;
		Connection conn = null;
		
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/jad?user=root&password=password&serverTimezone=UTC";
			conn = DriverManager.getConnection(connURL);
			
			// prepared statement and query string
			String getCategoryByIdQuery = "SELECT * FROM duotexture.categories WHERE categoryId=?;";
			PreparedStatement pstmt = conn.prepareStatement(getCategoryByIdQuery);
			pstmt.setInt(1,  categoryId);
			ResultSet getCategoryByIdResult = pstmt.executeQuery();
			
			// create an instance of categories
			categoryBean = new categories();
			
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
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/jad?user=root&password=password&serverTimezone=UTC";
			conn = DriverManager.getConnection(connURL);

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
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/jad?user=root&password=password&serverTimezone=UTC";
			conn = DriverManager.getConnection(connURL);

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
			Class.forName("com.mysql.jdbc.Driver");         
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
			conn = DriverManager.getConnection(connURL);   
			
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
