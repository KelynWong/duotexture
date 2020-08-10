package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Cart;

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
		String getCartsByUserIdQuery = "SELECT * FROM duotexture.carts WHERE userId=?;";
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
			
			// add cartBean to cartsArrayList
			cartsArrayList.add(cartBean);
		}
		
		// close connection
		conn.close();
		return cartsArrayList;
	}
	
	// add cart
	public static int insertCart (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add cart query and result
		String addCartQuery = "INSERT INTO duotexture.carts(`userId`, `productId`, `quantity`) VALUES(?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addCartQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.setInt(3, quantity);
	    
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit cart
	public static int editCart (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit cart query and result
		String updateCartQuery = "UPDATE duotexture.carts SET productId=?, quantity=? WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(updateCartQuery);
		pstmt.setInt(1, productId);
		pstmt.setInt(2, quantity);
		pstmt.setInt(3, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete cart
	public static int deleteCarts (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete cart query and result
		String deleteCartQuery = "DELETE FROM duotexture.carts WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteCartQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
