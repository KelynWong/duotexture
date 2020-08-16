package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Member;
import javabeans.Product;
import javabeans.Purchase;

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

public class AnalyticUtils {
	
	// get all members
	public static ArrayList<Member> getMembers (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// compute limit formula
		int limit = count*5;
		
		// prepared statement, get all members query and result
		String getMembersQuery = "SELECT * FROM duotexture.members WHERE members.country LIKE ? OR members.address LIKE ? ORDER BY members.userId ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASC")) {
			getMembersQuery += "ASC LIMIT ?,5;";
		}else {
			getMembersQuery += "DESC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getMembersQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		pstmt.setInt(3, limit);
		ResultSet getMembersResult = pstmt.executeQuery();
		
		// create new ArrayList of Members
		ArrayList<Member> membersArrayList = new ArrayList<Member>();
		
		// loop if there are new row
		while(getMembersResult.next()) {
			// create an instance of Member
			Member MemberBean = new Member();
			
			MemberBean.setUserId(getMembersResult.getInt("userId"));
			MemberBean.setFirstName(getMembersResult.getString("first_name"));
			MemberBean.setLastName(getMembersResult.getString("last_name"));
			MemberBean.setCountry(getMembersResult.getString("country"));
			MemberBean.setAddress(getMembersResult.getString("address"));
			MemberBean.setPostalCode(getMembersResult.getString("postal_code"));
			
			// add MemberBean to membersArrayList
			membersArrayList.add(MemberBean);
		}
		
		// close connection
		conn.close();
		return membersArrayList;
	}
	
	// get all products
	public static ArrayList<Product> getProducts (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// compute limit formula
		int limit = count*5;
		
		// prepared statement, get all products query and result
		String getProductsQuery = "SELECT * FROM duotexture.products WHERE products.name LIKE ? OR products.description LIKE ? ORDER BY products.productId ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASC")) {
			getProductsQuery += "ASC LIMIT ?,5;";
		}else {
			getProductsQuery += "DESC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getProductsQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		pstmt.setInt(3, limit);
		ResultSet getProductsResult = pstmt.executeQuery();
		
		// create new ArrayList of Product
		ArrayList<Product> productArrayList = new ArrayList<Product>();
		
		// loop if there are new row
		while(getProductsResult.next()) {
			// create an instance of Product
			Product productBean = new Product();
			
			productBean.setProductId(getProductsResult.getInt("productId"));
			productBean.setName(getProductsResult.getString("name"));
			productBean.setDescription(getProductsResult.getString("description"));
			productBean.setCostPrice(getProductsResult.getDouble("cost_price"));
			productBean.setRetailPrice(getProductsResult.getDouble("retail_price"));
			productBean.setQuantity(getProductsResult.getInt("quantity"));
			productBean.setCategoryId(getProductsResult.getInt("categoryId"));
			productBean.setImage(getProductsResult.getString("image"));
			
			// add productBean to productArrayList
			productArrayList.add(productBean);
		}
		
		// close connection
		conn.close();
		return productArrayList;
	}
	
	// get best to least products
	public static ArrayList<Purchase> getBestLeastProducts (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// compute limit formula
		int limit = count*5;
		
		// prepared statement, get best to least products query and result
		String getBestLeastProductsQuery = "SELECT pro.name, pro.description, pro.cost_price, pro.image,  sum(pur.quantity) as quantity, pur.productId FROM duotexture.products pro INNER JOIN duotexture.purchases pur ON pur.productId = pro.productId WHERE pro.name LIKE ? OR pro.description LIKE ? GROUP BY pro.name ORDER BY quantity";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASC")) {
			getBestLeastProductsQuery += "ASC LIMIT ?,5;";
		}else {
			getBestLeastProductsQuery += "DESC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getBestLeastProductsQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		pstmt.setInt(3, limit);
		ResultSet getBestLeastProductsResult = pstmt.executeQuery();
		
		// create new ArrayList of Purchase
		ArrayList<Purchase> bestLeastProductArrayList = new ArrayList<Purchase>();
		
		// loop if there are new row
		while(getBestLeastProductsResult.next()) {
			// create an instance of Purchase
			Purchase purchaseBean = new Purchase();
			
			purchaseBean.setProductId(getBestLeastProductsResult.getInt("productId"));
			purchaseBean.setQuantity(getBestLeastProductsResult.getInt("quantity"));
			
			purchaseBean.setProductName(getBestLeastProductsResult.getString("name"));
			purchaseBean.setProductDescription(getBestLeastProductsResult.getString("description"));
			purchaseBean.setProductCostPrice(getBestLeastProductsResult.getDouble("cost_price"));
			purchaseBean.setProductRetailPrice(getBestLeastProductsResult.getDouble("retail_price"));
			purchaseBean.setProductImage(getBestLeastProductsResult.getString("image"));
			purchaseBean.setProductCategoryId(getBestLeastProductsResult.getInt("categoryId"));
			
			// add purchaseBean to bestLeastProductArrayList
			bestLeastProductArrayList.add(purchaseBean);
		}
		
		// close connection
		conn.close();
		return bestLeastProductArrayList;
	}
}
