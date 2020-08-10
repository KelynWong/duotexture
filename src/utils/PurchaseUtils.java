package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Purchase;

public class PurchaseUtils {
	
	// get all purchases
	public static ArrayList<Purchase> getPurchases () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all purchases query and result
		Statement stmt = conn.createStatement();
		String getPurchasesQuery = "SELECT * FROM duotexture.purchases;";
		ResultSet getPurchasesResult = stmt.executeQuery(getPurchasesQuery);
		
		// create new ArrayList of purchase
		ArrayList<Purchase> PurchasesArrayList = new ArrayList<Purchase>();
		
		// loop if there are new row
		while(getPurchasesResult.next()) {
			// create an instance of purchase
			Purchase PurchaseBean = new Purchase();
			
			PurchaseBean.setUserId(getPurchasesResult.getInt("userId"));
			PurchaseBean.setProductId(getPurchasesResult.getInt("productId"));
			PurchaseBean.setQuantity(getPurchasesResult.getInt("quantity"));
			
			// add purchaseBean to PurchasesArrayList
			PurchasesArrayList.add(PurchaseBean);
		}
		
		// close connection
		conn.close();
		return PurchasesArrayList;
	}
	
	// get purchases by user id
	public static ArrayList<Purchase> getPurchasesByUserId (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get all purchases query and result
		String getPurchasesByUserIdQuery = "SELECT * FROM duotexture.purchases WHERE userId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getPurchasesByUserIdQuery);
		pstmt.setInt(1, userId);
		ResultSet getPurchasesByUserIdResult = pstmt.executeQuery();
				
		// create new ArrayList of purchase
		ArrayList<Purchase> PurchasesArrayList = new ArrayList<Purchase>();
		
		// loop if there are new row
		while(getPurchasesByUserIdResult.next()) {
			// create an instance of purchase
			Purchase PurchaseBean = new Purchase();
			
			PurchaseBean.setUserId(getPurchasesByUserIdResult.getInt("userId"));
			PurchaseBean.setProductId(getPurchasesByUserIdResult.getInt("productId"));
			PurchaseBean.setQuantity(getPurchasesByUserIdResult.getInt("quantity"));
			
			// add purchaseBean to PurchasesArrayList
			PurchasesArrayList.add(PurchaseBean);
		}
		
		// close connection
		conn.close();
		return PurchasesArrayList;
	}
	
	// add purchase
	public static int insertPurchase (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add purchase query and result
		String addPurchaseQuery = "INSERT INTO duotexture.purchases(`userId`, `productId`, `quantity`) VALUES(?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addPurchaseQuery);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, productId);
		pstmt.setInt(3, quantity);
	    
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit purchase
	public static int editPurchase (int userId, int productId, int quantity) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit purchase query and result
		String updatePurchaseQuery = "UPDATE duotexture.purchases SET productId=?, quantity=? WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(updatePurchaseQuery);
		pstmt.setInt(1, productId);
		pstmt.setInt(2, quantity);
		pstmt.setInt(3, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete purchase
	public static int deletePurchase (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete purchase query and result
		String deletePurchaseQuery = "DELETE FROM duotexture.purchases WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deletePurchaseQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
