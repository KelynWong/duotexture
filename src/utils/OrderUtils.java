package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javabeans.Order;

public class OrderUtils {
	
	// get all orders
	public static ArrayList<Order> getOrders () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all orders query and result
		Statement stmt = conn.createStatement();
		String getOrdersQuery = "SELECT * FROM duotexture.order;";
		ResultSet getOrdersResult = stmt.executeQuery(getOrdersQuery);
		
		// create new ArrayList of order
		ArrayList<Order> OrdersArrayList = new ArrayList<Order>();
		
		// loop if there are new row
		while(getOrdersResult.next()) {
			// create an instance of Order
			Order OrderBean = new Order();
			
			OrderBean.setUserId(getOrdersResult.getInt("userId"));
			OrderBean.setProductId(getOrdersResult.getInt("productId"));
			OrderBean.setQuantity(getOrdersResult.getInt("quantity"));
			
			// add orderBean to OrdersArrayList
			OrdersArrayList.add(OrderBean);
		}
		
		// close connection
		conn.close();
		return OrdersArrayList;
	}
	
	// get order by user id
	public static Order getOrderByUserId (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a order by user id query and result
		String getOrderByUserIdQuery = "SELECT * FROM duotexture.order WHERE userId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getOrderByUserIdQuery);
		pstmt.setInt(1,  userId);
		ResultSet getOrderByUserIdResult = pstmt.executeQuery();
		
		// create an instance of order
		Order OrderBean = new Order();
		
		// if there is a new row
		if(getOrderByUserIdResult.next()) {
			OrderBean.setUserId(getOrderByUserIdResult.getInt("userId"));
			OrderBean.setProductId(getOrderByUserIdResult.getInt("productId"));
			OrderBean.setQuantity(getOrderByUserIdResult.getInt("quantity"));
		}
		
		// close connection
		conn.close();
		return OrderBean;
	}
	
	// add order
	public static int insertOrder (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add order query and result
		String addOrderQuery = "INSERT INTO duotexture.order(`userId`, `productId`, `quantity`) VALUES(?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addOrderQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.setInt(3, quantity);
	    
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit order
	public static int editOrder (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit order query and result
		String updateOrderQuery = "UPDATE duotexture.order SET productId=?, quantity=? WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(updateOrderQuery);
		pstmt.setInt(1, productId);
		pstmt.setInt(2, quantity);
		pstmt.setInt(3, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete order
	public static int deleteOrder (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete order query and result
		String deleteOrderQuery = "DELETE FROM duotexture.order WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteOrderQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}