package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.AnalyticsOrder;
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
		String getProductsQuery = "SELECT * FROM duotexture.products WHERE (products.name LIKE ?) ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASCProdId")) {
			getProductsQuery += "ORDER BY products.productId ASC LIMIT ?,5;";
		}else if(order.equals("DESCProdId")) {
			getProductsQuery += "ORDER BY products.productId DESC LIMIT ?,5;";
		}else if(order.equals("ASCLowStock")) {
			getProductsQuery += "AND products.quantity<=20 ORDER BY products.quantity DESC LIMIT ?,5;";
		}else if(order.equals("ASCModerateStock")) {
			getProductsQuery += "AND products.quantity>20 AND products.quantity<=50 ORDER BY products.quantity DESC LIMIT ?,5;";
		}else if(order.equals("ASCHighStock")) {
			getProductsQuery += "AND products.quantity>50 ORDER BY products.quantity DESC LIMIT ?,5;";
		}else {
			getProductsQuery += "ORDER BY products.productId ASC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getProductsQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, limit);
		ResultSet getProductsResult = pstmt.executeQuery();
		
		// create new ArrayList of Product
		ArrayList<Product> productArrayList = new ArrayList<Product>();
		
		// loop if there are new row
		while(getProductsResult.next()) {
			// create an instance of Product
			Product productBean = new Product();
			
			productBean.setProductId(getProductsResult.getInt("productId"));
			productBean.setName(getProductsResult.getString("name"));
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
		String getBestLeastProductsQuery = "SELECT pro.name, pro.cost_price, pro.retail_price, pro.categoryId, pro.image, sum(pur.quantity) as quantity, pro.cost_price*sum(pur.quantity) as profit, pur.productId FROM duotexture.products pro INNER JOIN duotexture.purchases pur ON pur.productId = pro.productId WHERE (pro.name LIKE ?) GROUP BY pro.name ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("DESCBestLeast")) {
			getBestLeastProductsQuery += "ORDER BY quantity ASC LIMIT ?,5;";
		}else if (order.equals("ASCBestLeast")) {
			getBestLeastProductsQuery += "ORDER BY quantity DESC LIMIT ?,5;";
		}else if (order.equals("DESCProfit")) {
			getBestLeastProductsQuery += "ORDER BY profit DESC LIMIT ?,5;";
		}else if (order.equals("ASCProfit")) {
			getBestLeastProductsQuery += "ORDER BY profit ASC LIMIT ?,5;";
		}else {
			getBestLeastProductsQuery += "ORDER BY quantity ASC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getBestLeastProductsQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, limit);
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
			purchaseBean.setProductCostPrice(getBestLeastProductsResult.getDouble("cost_price"));
			purchaseBean.setProductRetailPrice(getBestLeastProductsResult.getDouble("retail_price"));
			purchaseBean.setProductRetailPrice(getBestLeastProductsResult.getDouble("profit"));
			purchaseBean.setProductImage(getBestLeastProductsResult.getString("image"));
			purchaseBean.setProductCategoryId(getBestLeastProductsResult.getInt("categoryId"));
			
			// add purchaseBean to bestLeastProductArrayList
			bestLeastProductArrayList.add(purchaseBean);
		}
		
		// close connection
		conn.close();
		return bestLeastProductArrayList;
	}
	
	// get all orders
	public static ArrayList<AnalyticsOrder> getOrders (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// compute limit formula
		int limit = count*5;
		
		// prepared statement, get all orders query and result
		String getOrdersQuery = "SELECT users.userId, CONCAT(members.first_name,' ',members.last_name) AS fullName, orders.productId, products.image, products.name, products.cost_price, orders.quantity, orders.dateTime FROM duotexture.orders INNER JOIN duotexture.products ON orders.productId = products.productId INNER JOIN duotexture.users ON users.userId = orders.userId INNER JOIN duotexture.members ON users.userId = members.userId WHERE products.name LIKE ? ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("DESCUserId")) {
			getOrdersQuery += "ORDER BY users.userId DESC LIMIT ?,5;";
		}else { // "ASCUserId"
			getOrdersQuery += "ORDER BY users.userId ASC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getOrdersQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, limit);
		ResultSet getOrdersResult = pstmt.executeQuery();
		
		// create new ArrayList of AnalyticsOrder
		ArrayList<AnalyticsOrder> analyticsOrderArrayList = new ArrayList<AnalyticsOrder>();
		
		// loop if there are new row
		while(getOrdersResult.next()) {
			// create an instance of AnalyticsOrder
			AnalyticsOrder analyticsOrderBean = new AnalyticsOrder();
			
			// initialize variables
			int userId = getOrdersResult.getInt("userId");
			String fullName = getOrdersResult.getString("fullName");
			int productId = getOrdersResult.getInt("productId");
			String productImage = getOrdersResult.getString("image");
			String productName = getOrdersResult.getString("name");
			Double productCostPrice = getOrdersResult.getDouble("cost_price");
			int productQuantity = getOrdersResult.getInt("quantity");
			String dateTime = getOrdersResult.getString("dateTime");
			
			analyticsOrderBean.setUserId(userId);
			analyticsOrderBean.setFullName(fullName);
			analyticsOrderBean.setProductId(productId);
			analyticsOrderBean.setProductImage(productImage);
			analyticsOrderBean.setProductName(productName);
			analyticsOrderBean.setProductCostPrice(productCostPrice);
			analyticsOrderBean.setProductQuantity(productQuantity);
			analyticsOrderBean.setDateTime(dateTime);
			
			// add analyticsOrderBean to analyticsOrderArrayList
			analyticsOrderArrayList.add(analyticsOrderBean);
		}
		
		// close connection
		conn.close();
		return analyticsOrderArrayList;
	}
	
	// get top 10 customers
	public static ArrayList<AnalyticsOrder> getTop10Customers (String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get top 10 customers query and result
		String getTop10CustomersQuery = "SELECT users.userId, CONCAT(members.first_name,' ',members.last_name) AS fullName, SUM(products.cost_price*purchases.quantity) AS total_profit FROM duotexture.members INNER JOIN duotexture.purchases ON members.userId = purchases.userId INNER JOIN duotexture.products ON purchases.productId = products.productId INNER JOIN duotexture.users ON users.userId = members.userId WHERE CONCAT(members.first_name,' ',members.last_name) LIKE ? GROUP BY fullName ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASCProfit")) {
			getTop10CustomersQuery += "ORDER BY total_profit ASC LIMIT 10;";
		}else { // "DESCProfit"
			getTop10CustomersQuery += "ORDER BY total_profit DESC LIMIT 10;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(getTop10CustomersQuery);
		pstmt.setString(1, "%" + keyword + "%");
		ResultSet getTop10CustomersResult = pstmt.executeQuery();
		
		// create new ArrayList of AnalyticsOrder
		ArrayList<AnalyticsOrder> getTop10CustomersArrayList = new ArrayList<AnalyticsOrder>();
		
		// loop if there are new row
		while(getTop10CustomersResult.next()) {
			// create an instance of AnalyticsOrder
			AnalyticsOrder analyticsOrderBean = new AnalyticsOrder();
			
			// initialize variables
			int userId = getTop10CustomersResult.getInt("userId");
			String fullName = getTop10CustomersResult.getString("fullName");
			Double totalProfit = getTop10CustomersResult.getDouble("total_profit");
			
			analyticsOrderBean.setUserId(userId);
			analyticsOrderBean.setFullName(fullName);
			analyticsOrderBean.setTotalProfit(totalProfit);
			
			// add analyticsOrderBean to getTop10CustomersArrayList
			getTop10CustomersArrayList.add(analyticsOrderBean);
		}
		
		// close connection
		conn.close();
		return getTop10CustomersArrayList;
	}
	
	// get purchases log
	public static ArrayList<AnalyticsOrder> getPurchasesLog (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// compute limit formula
		int limit = count*5;
		
		// prepared statement, get purchases log query and result
		String purchasesLogQuery = "SELECT users.userId, CONCAT(members.first_name,' ',members.last_name) AS fullName, products.productId, products.image, products.name, products.cost_price, SUM(purchases.quantity) as total_quantity, purchases.dateTime FROM duotexture.purchases INNER JOIN duotexture.products ON purchases.productId = products.productId INNER JOIN duotexture.users ON users.userId = purchases.userId INNER JOIN duotexture.members ON users.userId = members.userId WHERE products.name LIKE ? GROUP BY products.name, users.userId ";
		
		// prepared statement inserts string, which is denied for ORDER BY, therefore if else validation required
		if(order.equals("ASCUserId")) {
			purchasesLogQuery += "ORDER BY users.userId ASC LIMIT ?,5;";
		}else { // "ASCUserId"
			purchasesLogQuery += "ORDER BY users.userId DESC LIMIT ?,5;";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(purchasesLogQuery);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, limit);
		ResultSet purchasesLogResult = pstmt.executeQuery();
		
		// create new ArrayList of AnalyticsOrder
		ArrayList<AnalyticsOrder> purchasesLogArrayList = new ArrayList<AnalyticsOrder>();
		
		// loop if there are new row
		while(purchasesLogResult.next()) {
			// create an instance of AnalyticsOrder
			AnalyticsOrder analyticsOrderBean = new AnalyticsOrder();
			
			// initialize variables
			int userId = purchasesLogResult.getInt("userId");
			String fullName = purchasesLogResult.getString("fullName");
			int productId = purchasesLogResult.getInt("productId");
			String productImage = purchasesLogResult.getString("image");
			String productName = purchasesLogResult.getString("name");
			Double productCostPrice = purchasesLogResult.getDouble("cost_price");
			int productTotalQuantity = purchasesLogResult.getInt("total_quantity");
			String dateTime = purchasesLogResult.getString("dateTime");
			
			analyticsOrderBean.setUserId(userId);
			analyticsOrderBean.setFullName(fullName);
			analyticsOrderBean.setProductId(productId);
			analyticsOrderBean.setProductImage(productImage);
			analyticsOrderBean.setProductName(productName);
			analyticsOrderBean.setProductCostPrice(productCostPrice);
			analyticsOrderBean.setTotalQuantity(productTotalQuantity);
			analyticsOrderBean.setDateTime(dateTime);
			
			// add analyticsOrderBean to purchasesLogArrayList
			purchasesLogArrayList.add(analyticsOrderBean);
		}
		
		// close connection
		conn.close();
		return purchasesLogArrayList;
	}
}
