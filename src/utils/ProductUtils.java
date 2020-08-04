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
	
	// get all products by category id
	public static ArrayList<Product> getProductsByCategoryId (int categoryId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all products by category id query and result		
		String getProductByCategoryIdQuery = "SELECT * FROM duotexture.products WHERE productId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getProductByCategoryIdQuery);
		pstmt.setInt(1, categoryId);
		ResultSet getProductByCategoryIdResult = pstmt.executeQuery();
		
		// create new ArrayList of product
		ArrayList<Product> productsArrayList = new ArrayList<Product>();
		
		// loop if there are new row
		while(getProductByCategoryIdResult.next()) {
			// create an instance of product
			Product productBean = new Product();
			
			productBean.setProductId(getProductByCategoryIdResult.getInt("productId"));
			productBean.setName(getProductByCategoryIdResult.getString("name"));
			productBean.setDescription(getProductByCategoryIdResult.getString("description"));
			productBean.setCostPrice(getProductByCategoryIdResult.getDouble("cost_price"));
			productBean.setRetailPrice(getProductByCategoryIdResult.getDouble("retail_price"));
			productBean.setQuantity(getProductByCategoryIdResult.getInt("quantity"));
			productBean.setCategoryId(getProductByCategoryIdResult.getInt("categoryId"));
			productBean.setImage(getProductByCategoryIdResult.getString("image"));
			
			// add productBean to productsArrayList
			productsArrayList.add(productBean);
		}
		
		// close connection
		conn.close();
		return productsArrayList;
	}
	
	// get all products by category id and keyword
	public static ArrayList<Product> getProductsByCategoryIdAndKeyword (int categoryId, String keyword) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all products by category id and keyword query and result		
		String getProductByCategoryIdAndKeywordQuery = "SELECT * FROM products WHERE products.categoryId = ? AND products.name LIKE '%?%';";
		PreparedStatement pstmt = conn.prepareStatement(getProductByCategoryIdAndKeywordQuery);
		pstmt.setInt(1, categoryId);
		pstmt.setString(2, keyword);
		ResultSet getProductByCategoryIdAndKeywordResult = pstmt.executeQuery();
		
		// create new ArrayList of product
		ArrayList<Product> searchProductsArrayList = new ArrayList<Product>();
		
		// loop if there are new row
		while(getProductByCategoryIdAndKeywordResult.next()) {
			// create an instance of product
			Product productBean = new Product();
			
			productBean.setProductId(getProductByCategoryIdAndKeywordResult.getInt("productId"));
			productBean.setName(getProductByCategoryIdAndKeywordResult.getString("name"));
			productBean.setDescription(getProductByCategoryIdAndKeywordResult.getString("description"));
			productBean.setCostPrice(getProductByCategoryIdAndKeywordResult.getDouble("cost_price"));
			productBean.setRetailPrice(getProductByCategoryIdAndKeywordResult.getDouble("retail_price"));
			productBean.setQuantity(getProductByCategoryIdAndKeywordResult.getInt("quantity"));
			productBean.setCategoryId(getProductByCategoryIdAndKeywordResult.getInt("categoryId"));
			productBean.setImage(getProductByCategoryIdAndKeywordResult.getString("image"));
			
			// add productBean to productsArrayList
			searchProductsArrayList.add(productBean);
		}
		
		// close connection
		conn.close();
		return searchProductsArrayList;
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
