package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javabeans.Users;

public class UsersUtil {
	public Users getUsers () {
		Users uBean = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM duotexture.users");
			
			if(rs.next()) {
				uBean = new Users();
				uBean.setUserId(rs.getInt("userId"));
				uBean.setEmail(rs.getString("email"));
				uBean.setUsername(rs.getString("username"));
				uBean.setPassword(rs.getString("password"));
				uBean.setUserRole(rs.getString("userRole"));
			}
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return uBean;
	}
	
	public int insertUsers (String email, String username, String password, String userRole) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// insert inputs into users table
			String insertUserQuery = "INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES(?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insertUserQuery);
		    pstmt.setString(1, email);
		    pstmt.setString(2, username);
		    pstmt.setString(3, password);
		    pstmt.setString(4, "Member");
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return count;
	}
}
