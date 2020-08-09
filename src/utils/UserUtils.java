package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.User;

public class UserUtils {
	
	// get all users
	public static ArrayList<User> getUsers () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all users query and result
		Statement stmt = conn.createStatement();
		String getUsersQuery = "SELECT * FROM duotexture.users;";
		ResultSet getUsersResult = stmt.executeQuery(getUsersQuery);
		
		// create new ArrayList of user
		ArrayList<User> usersArrayList = new ArrayList<User>();
		
		// loop if there are new row
		while(getUsersResult.next()) {
			// create an instance of user
			User userBean = new User();
			
			userBean.setUserId(getUsersResult.getInt("userId"));
			userBean.setEmail(getUsersResult.getString("email"));
			userBean.setUsername(getUsersResult.getString("username"));
			userBean.setPassword(getUsersResult.getString("password"));
			userBean.setUserRole(getUsersResult.getString("userRole"));
			
			// add userBean to usersArrayList
			usersArrayList.add(userBean);
		}
		
		// close connection
		conn.close();
		return usersArrayList;
	}
	
	// get user by id
	public static User getUserById (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a user by user id query and result
		String getUserByIdQuery = "SELECT * FROM duotexture.users WHERE userId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getUserByIdQuery);
		pstmt.setInt(1,  userId);
		ResultSet getUserByIdResult = pstmt.executeQuery();
		
		// create an instance of user
		User userBean = new User();
		
		// if there is a new row
		if(getUserByIdResult.next()) {
			userBean.setUserId(getUserByIdResult.getInt("userId"));
			userBean.setEmail(getUserByIdResult.getString("email"));
			userBean.setUsername(getUserByIdResult.getString("username"));
			userBean.setPassword(getUserByIdResult.getString("password"));
			userBean.setUserRole(getUserByIdResult.getString("userRole"));
		}
		
		// close connection
		conn.close();
		return userBean;
	}
	
	// add user
	public static int insertUser (String email, String username, String password, String userRole) throws SQLException, ClassNotFoundException {
		// pre-define variables
		int userId = 0;
		
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add users query and result
		String addUserQuery = "INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES(?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addUserQuery, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, email);
	    pstmt.setString(2, username);
	    pstmt.setString(3, password);
	    pstmt.setObject(4, userRole);
		int count = pstmt.executeUpdate(); 
		
		if(count > 0) {
			// get the last inserted id
			ResultSet addUserResult = pstmt.getGeneratedKeys();
			addUserResult.next();
			userId = addUserResult.getInt(1);
		}
		
		// close connection
		conn.close();
		return userId;
	}
	
	// edit user
	public static int editUser (int userId, String email, String username, String password) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit user query and result
		String updateUserQuery = "UPDATE duotexture.users SET email=?, username=?, password=? WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(updateUserQuery);
		pstmt.setString(1, email);
	    pstmt.setString(2, username);
	    pstmt.setString(3, password);
	    pstmt.setObject(4, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete user
	public static int deleteUser (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete user query and result
		String deleteUserQuery = "DELETE FROM duotexture.users WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteUserQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
