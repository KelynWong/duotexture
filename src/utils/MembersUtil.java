package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javabeans.Members;

public class MembersUtil {
	public Members getMembers (int userId) {
		Members uBean = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM duotexture.members WHERE userId =" + userId);
			
			if(rs.next()) {
				uBean = new Members();
				uBean.setFirstName(rs.getString("first_name"));
				uBean.setLastName(rs.getString("last_name"));
				uBean.setCountry(rs.getString("country"));
				uBean.setAddress(rs.getString("address"));
				uBean.setPostalCode(rs.getInt("postal_code"));
				uBean.setUserId(rs.getInt("userId"));
			}
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return uBean;
	}
	
	public int insertMembers (String firstName, String lastName, String country, String address, int postalCode) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");
			Statement stmt = conn.createStatement(); 
			
			// get last row of users
			String getLastUserQuery = "SELECT * FROM users ORDER BY userId DESC LIMIT 1;";
			ResultSet getLastUserResult = stmt.executeQuery(getLastUserQuery);
			
			getLastUserResult.next();
			int userId = getLastUserResult.getInt("userId");
			String memberUsername = getLastUserResult.getString("username");
			
//			session.setAttribute("userId", userId);
//			session.setAttribute("username", memberUsername);
//			session.setAttribute("accountType", "member");
			
			// insert inputs into members table
			String insertMemberQuery = "INSERT INTO members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insertMemberQuery);
		    pstmt.setString(1, firstName);
		    pstmt.setString(2, lastName);
		    pstmt.setString(3, country);
		    pstmt.setString(4, address);
		    pstmt.setInt(5, postalCode);
		    pstmt.setInt(6, userId);
			count = pstmt.executeUpdate(); 
			
		}catch(Exception e) {
		}finally {
			try {
				conn.close();
			}catch(Exception e) {}
		}
		return count;
	}
	
	public int editMembers (String firstName, String lastName, String country, String address, int postalCode, int userId) {
		Connection conn = null;
		int count = 0;
		
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC");

			// edit and update members with inputs by user id
			String updateMembersQuery = "UPDATE members SET first_name=?, last_name=?, country=?, address=?, postal_code=? WHERE userId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateMembersQuery);
			pstmt.setString(1, firstName);
		    pstmt.setString(2, lastName);
		    pstmt.setString(3, country);
		    pstmt.setString(4, address);
		    pstmt.setInt(5, postalCode);
		    pstmt.setInt(6, userId);
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
