package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javabeans.Product;

public class ProductUtils {
	
	// get all products
	public static ArrayList<Product> getProducts () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all products query and result
		Statement stmt = conn.createStatement();
		String getProductsQuery = "SELECT * FROM duotexture.products;";
		ResultSet getProductsResult = stmt.executeQuery(getProductsQuery);
		
		// create new ArrayList of product
		ArrayList<Product> productsArrayList = new ArrayList<Product>();
		
		// loop if there are new row
		while(getProductsResult.next()) {
			// create an instance of product
			Product productBean = new Product();
			
			productBean.setProductId(getProductsResult.getInt("productId"));
			productBean.setName(getProductsResult.getString("name"));
			productBean.setDescription(getProductsResult.getString("description"));
			productBean.setCostPrice(getProductsResult.getDouble("cost_price"));
			productBean.setRetailPrice(getProductsResult.getDouble("retail_price"));
			productBean.setQuantity(getProductsResult.getInt("quantity"));
			productBean.setCategoryId(getProductsResult.getInt("categoryId"));
			productBean.setImage(getProductsResult.getString("image"));
			
			// add productBean to productsArrayList
			productsArrayList.add(productBean);
		}
		
		// close connection
		conn.close();
		return productsArrayList;
	}
	
	// get product by id
	public static Product getProductById (int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a product by product id query and result
		String getProductByIdQuery = "SELECT * FROM duotexture.products WHERE productId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getProductByIdQuery);
		pstmt.setInt(1,  productId);
		ResultSet getProductByIdResult = pstmt.executeQuery();
		
		// create an instance of product
		Product productBean = new Product();
		
		// if there is a new row
		if(getProductByIdResult.next()) {
			productBean.setProductId(getProductByIdResult.getInt("productId"));
			productBean.setName(getProductByIdResult.getString("name"));
			productBean.setDescription(getProductByIdResult.getString("description"));
			productBean.setCostPrice(getProductByIdResult.getDouble("cost_price"));
			productBean.setRetailPrice(getProductByIdResult.getDouble("retail_price"));
			productBean.setQuantity(getProductByIdResult.getInt("quantity"));
			productBean.setCategoryId(getProductByIdResult.getInt("categoryId"));
			productBean.setImage(getProductByIdResult.getString("image"));
		}
		
		// close connection
		conn.close();
		return productBean;
	}
	
	// add product
	public static int insertProduct (String name, String description, double costPrice, double retailPrice, int quantity, int categoryId, String image) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add products query and result
		String addProductQuery = "INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addProductQuery);
		pstmt.setString(1, name);
	    pstmt.setString(2, description);
	    pstmt.setDouble(3, costPrice);
	    pstmt.setDouble(4, retailPrice);
	    pstmt.setInt(5, quantity);
	    pstmt.setInt(6, categoryId);
	    pstmt.setString(7, image);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit product
	public static int editProduct (int productId, String name, String description, double costPrice, double retailPrice, int quantity, int categoryId, String image) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit product query and result
		String updateProductQuery = "UPDATE duotexture.products SET name=?, description=?, cost_price=?, retail_price=?, quantity=?, categoryId=?, image=? WHERE productId=?;"; 
		PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
		pstmt.setString(1, name);
	    pstmt.setString(2, description);
	    pstmt.setDouble(3, costPrice);
	    pstmt.setDouble(4, retailPrice);
	    pstmt.setInt(5, quantity);
	    pstmt.setInt(6, categoryId);
	    pstmt.setString(7, image);
	    pstmt.setInt(8, productId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete product
	public static int deleteProduct (int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete product query and result
		String deleteProductQuery = "DELETE FROM duotexture.products WHERE productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteProductQuery);
	    pstmt.setInt(1, productId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
