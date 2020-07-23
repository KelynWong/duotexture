package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class categoriesDB {
	public categories getCategories (int categoryId) {
		categories uBean = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM duotexture.categories WHERE categoryId =" + categoryId);
			
			if(rs.next()) {
				uBean = new categories();
				uBean.setCategoryId(rs.getInt("categoryId"));
				uBean.setName(rs.getString("name"));
				uBean.setDescription(rs.getString("description"));
				uBean.setImage(rs.getString("image"));
			}
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return uBean;
	}
	
	public int insertCategories (String name, String description, String image) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// insert inputs into categories
			String addCategoryQuery = "INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES(?, ?, ?);"; 
			PreparedStatement pstmt = conn.prepareStatement(addCategoryQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setString(3, image);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return count;
	}
	
	public int editCategories (String name, String description, String image, int categoryId) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// edit and update category with inputs by category id
			String updateCategoryQuery = "UPDATE categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateCategoryQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setString(3, image);
		    pstmt.setInt(4, categoryId);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return count;
	}
}
