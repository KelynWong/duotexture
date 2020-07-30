package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javabeans.Products;

public class ProductsUtil {
	public Products getProducts (int categoryId) {
		Products uBean = null;
		Connection conn = null;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM duotexture.products WHERE categoryId =" + categoryId);
			
			if(rs.next()) {
				uBean = new Products();
				uBean.setProductId(rs.getInt("userId"));
				uBean.setName(rs.getString("email"));
				uBean.setDescription(rs.getString("username"));
				uBean.setCostPrice(rs.getDouble("password"));
				uBean.setRetailPrice(rs.getDouble("userRole"));
				uBean.setQuantity(rs.getInt("username"));
				uBean.setCategoryId(rs.getInt("password"));
				uBean.setImage(rs.getString("userRole"));
			}
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return uBean;
	}
	
	public int insertProducts (String name, String description, double costPrice, double retailPrice, int quantity, int categoryId, String image) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// insert inputs into products
			String updateProductQuery = "INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?);"; 
			PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setDouble(3, costPrice);
		    pstmt.setDouble(4, retailPrice);
		    pstmt.setInt(5, quantity);
		    pstmt.setInt(6, categoryId);
		    pstmt.setString(7, image);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return count;
	}
	
	public int editProducts (String name, String description, double costPrice, double retailPrice, int quantity, int categoryId, String image, int productId) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// edit and update categories with inputs by category id
			String updateProductQuery = "UPDATE products SET name=?, description=?, cost_price=?, retail_price=?, quantity=?, categoryId=?, image=? WHERE productId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
		    pstmt.setString(1, name);
		    pstmt.setString(2, description);
		    pstmt.setDouble(3, costPrice);
		    pstmt.setDouble(4, retailPrice);
		    pstmt.setInt(5, quantity);
		    pstmt.setInt(6, categoryId);
		    pstmt.setString(7, image);
		    pstmt.setInt(8, productId);
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
