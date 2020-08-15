package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Order;

public class OrderUtils {
	
	// get all orders
	public static ArrayList<Order> getOrders () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all orders query and result
		Statement stmt = conn.createStatement();
		String getOrdersQuery = "SELECT * FROM duotexture.orders;";
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
			OrderBean.setDateTime(getOrdersResult.getString("dateTime"));
			
			// add orderBean to OrdersArrayList
			OrdersArrayList.add(OrderBean);
		}
		
		// close connection
		conn.close();
		return OrdersArrayList;
	}
	
	// get orders by user id
	public static ArrayList<Order> getOrdersByUserId (int userId) throws SQLException, ClassNotFoundException {
			
		// connect to database
		Connection conn = Database.connectToDatabase();
			
		// prepared statement, get all orders query and result
		String getOrdersByUserIdQuery = "SELECT products.name, products.description, products.cost_price, products.image, orders.quantity, orders.userId, orders.productId, orders.dateTime FROM duotexture.products INNER JOIN duotexture.orders ON orders.productId = products.productId WHERE userId = ?;";
		PreparedStatement pstmt = conn.prepareStatement(getOrdersByUserIdQuery);
		pstmt.setInt(1, userId);
		ResultSet getOrdersByUserIdResult = pstmt.executeQuery(); 
			
		// create new ArrayList of order
		ArrayList<Order> OrdersArrayList = new ArrayList<Order>();
			
		// loop if there are new row
		while(getOrdersByUserIdResult.next()) {
			// create an instance of Order
			Order OrderBean = new Order();
				
			OrderBean.setUserId(getOrdersByUserIdResult.getInt("userId"));
			OrderBean.setProductId(getOrdersByUserIdResult.getInt("productId"));
			OrderBean.setQuantity(getOrdersByUserIdResult.getInt("quantity"));
			OrderBean.setDateTime(getOrdersByUserIdResult.getString("dateTime"));
				
			OrderBean.setProductName(getOrdersByUserIdResult.getString("name"));
			OrderBean.setProductDescription(getOrdersByUserIdResult.getString("description"));
			OrderBean.setProductCostPrice(getOrdersByUserIdResult.getDouble("cost_price"));
			OrderBean.setProductImage(getOrdersByUserIdResult.getString("image"));
				
			// add orderBean to OrdersArrayList
			OrdersArrayList.add(OrderBean);
		}
			
		// close connection
		conn.close();
		return OrdersArrayList;
	}
	
	// get one order by user id
	public static ArrayList<Order> getOrderByUserId (int userId, int productId) throws SQLException, ClassNotFoundException {
				
		// connect to database
		Connection conn = Database.connectToDatabase();
				
		// prepared statement, get one order by user id query and result
		String getOrdersByUserIdQuery = "SELECT products.name, products.description, products.cost_price, products.image, orders.quantity, orders.userId, orders.productId, orders.dateTime FROM products INNER JOIN orders ON orders.productId = products.productId WHERE userId = ? AND orders.productId = ?;";
		PreparedStatement pstmt = conn.prepareStatement(getOrdersByUserIdQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		ResultSet getOrdersByUserIdResult = pstmt.executeQuery(); 
				
		// create new ArrayList of order
		ArrayList<Order> OrdersArrayList = new ArrayList<Order>();
				
		// loop if there are new row
		while(getOrdersByUserIdResult.next()) {
			// create an instance of Order
			Order OrderBean = new Order();
					
			OrderBean.setUserId(getOrdersByUserIdResult.getInt("userId"));
			OrderBean.setProductId(getOrdersByUserIdResult.getInt("productId"));
			OrderBean.setQuantity(getOrdersByUserIdResult.getInt("quantity"));
			OrderBean.setDateTime(getOrdersByUserIdResult.getString("dateTime"));
					
			OrderBean.setProductName(getOrdersByUserIdResult.getString("name"));
			OrderBean.setProductDescription(getOrdersByUserIdResult.getString("description"));
			OrderBean.setProductCostPrice(getOrdersByUserIdResult.getDouble("cost_price"));
			OrderBean.setProductImage(getOrdersByUserIdResult.getString("image"));
					
			// add orderBean to OrdersArrayList
			OrdersArrayList.add(OrderBean);
		}
				
		// close connection
		conn.close();
		return OrdersArrayList;
	}
	
	// add order
	public static int insertOrder (int userId, int productId, int quantity, String dateTime) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add order query and result
		String addOrderQuery = "INSERT INTO duotexture.orders(`userId`, `productId`, `quantity`, `dateTime`) VALUES(?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addOrderQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.setInt(3, quantity);
		pstmt.setString(4, dateTime);
	    
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit order
	public static int editOrder (int userId, int productId, int quantity, String dateTime) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit order query and result
		String updateOrderQuery = "UPDATE duotexture.orders SET productId=?, quantity=? WHERE userId=?"; 
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
		String deleteOrderQuery = "DELETE FROM duotexture.orders WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteOrderQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete one order
	public static int deleteOneOrder (int userId , int productId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
			
		// prepared statement, delete order query and result
		String deleteOrderQuery = "DELETE FROM duotexture.orders WHERE userId=? AND productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteOrderQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		int count = pstmt.executeUpdate(); 
			
		// close connection
		conn.close();
		return count;
	}
}
