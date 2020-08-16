package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Cart;

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

public class CartUtils {
	
	// get all carts
	public static ArrayList<Cart> getCarts () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all carts query and result
		Statement stmt = conn.createStatement();
		String getCartsQuery = "SELECT * FROM duotexture.carts;";
		ResultSet getCartsResult = stmt.executeQuery(getCartsQuery);
		
		// create new ArrayList of cart
		ArrayList<Cart> cartsArrayList = new ArrayList<Cart>();
		
		// loop if there are new row
		while(getCartsResult.next()) {
			// create an instance of cart
			Cart cartBean = new Cart();
			
			cartBean.setUserId(getCartsResult.getInt("userId"));
			cartBean.setProductId(getCartsResult.getInt("productId"));
			cartBean.setQuantity(getCartsResult.getInt("quantity"));
			cartBean.setDateTime(getCartsResult.getString("dateTime"));
			
			// add cartBean to cartsArrayList
			cartsArrayList.add(cartBean);
		}
		
		// close connection
		conn.close();
		return cartsArrayList;
	}
	
	// get carts by user id
	public static ArrayList<Cart> getCartsByUserId (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get carts by user id query and result
		String getCartsByUserIdQuery = "SELECT products.name, products.description, products.cost_price, products.image, carts.quantity, carts.userId, carts.productId, carts.dateTime FROM duotexture.products INNER JOIN duotexture.carts ON carts.productId = products.productId WHERE userId = ?;";
		PreparedStatement pstmt = conn.prepareStatement(getCartsByUserIdQuery);
		pstmt.setInt(1,  userId);
		ResultSet getCartsByUserIdResult = pstmt.executeQuery();
		
		// create new ArrayList of cart
		ArrayList<Cart> cartsArrayList = new ArrayList<Cart>();
		
		// loop if there are new row
		while(getCartsByUserIdResult.next()) {
			// create an instance of cart
			Cart cartBean = new Cart();
			
			cartBean.setUserId(getCartsByUserIdResult.getInt("userId"));
			cartBean.setProductId(getCartsByUserIdResult.getInt("productId"));
			cartBean.setQuantity(getCartsByUserIdResult.getInt("quantity"));
			cartBean.setDateTime(getCartsByUserIdResult.getString("dateTime"));
			
			cartBean.setProductName(getCartsByUserIdResult.getString("name"));
			cartBean.setProductDescription(getCartsByUserIdResult.getString("description"));
			cartBean.setProductCostPrice(getCartsByUserIdResult.getDouble("cost_price"));
			cartBean.setProductImage(getCartsByUserIdResult.getString("image"));
			
			// add cartBean to cartsArrayList
			cartsArrayList.add(cartBean);
		}
		
		// close connection
		conn.close();
		return cartsArrayList;
	}
	
	// add cart
	public static int insertCart (int userId, int productId, int quantity, String dateTime) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add cart query and result
		String addCartQuery = "INSERT INTO duotexture.carts(`userId`, `productId`, `quantity`, `dateTime`) VALUES(?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addCartQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.setInt(3, quantity);
		pstmt.setString(4, dateTime);
	    
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit cart increase
	public static int editCartIncrease (int userId, int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get cart quantity query and result
		String getCartQuantityQuery = "SELECT quantity FROM duotexture.carts WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(getCartQuantityQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		ResultSet getCartQuantityResult = pstmt.executeQuery();
		
		int quantity = 0;
		while(getCartQuantityResult.next()) {
			quantity = getCartQuantityResult.getInt("quantity");
		}
		quantity++;

		// prepared statement, increase cart by editing cart query and result
		String updateCartQuery = "UPDATE duotexture.carts SET quantity=? WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt2 = conn.prepareStatement(updateCartQuery);
		pstmt2.setInt(1, quantity);
		pstmt2.setInt(2, userId);
		pstmt2.setInt(3, productId);
		int count = pstmt2.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit cart decrease
	public static int editCartDecrease (int userId, int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
			
		// prepared statement, get cart quantity query and result
		String getCartQuantityQuery = "SELECT quantity FROM duotexture.carts WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(getCartQuantityQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		ResultSet getCartQuantityResult = pstmt.executeQuery();
			
		int quantity = 0;
		while(getCartQuantityResult.next()) {
			quantity = getCartQuantityResult.getInt("quantity");
		}
		quantity--;

		int count = 0;
		if(quantity > 0) {
			// prepared statement, increase cart quantity by editing cart query and result
			String updateCartQuery = "UPDATE duotexture.carts SET quantity=? WHERE userId=? AND productId=?"; 
			PreparedStatement pstmt2 = conn.prepareStatement(updateCartQuery);
			pstmt2.setInt(1, quantity);
			pstmt2.setInt(2, userId);
			pstmt2.setInt(3, productId);
			count = pstmt2.executeUpdate(); 
		}
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete a cart product
	public static int deleteCart (int userId, int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete cart query and result
		String deleteCartQuery = "DELETE FROM duotexture.carts WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteCartQuery);
	    pstmt.setInt(1, userId);
	    pstmt.setInt(2, productId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete all of cart
	public static int deleteAllCart (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
			
		// prepared statement, delete cart query and result
		String deleteAllCartQuery = "DELETE FROM duotexture.carts WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteAllCartQuery);
		pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
			
		// close connection
		conn.close();
		return count;
	}
}