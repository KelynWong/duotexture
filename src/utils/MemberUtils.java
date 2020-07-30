package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javabeans.Member;

public class MemberUtils {
	
	// get all members
	public ArrayList<Member> getMembers () throws Exception {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all members query and result
		Statement stmt = conn.createStatement();
		String getMembersQuery = "SELECT * FROM duotexture.members;";
		ResultSet getMembersResult = stmt.executeQuery(getMembersQuery);
		
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
			MemberBean.setPostalCode(getMembersResult.getInt("postal_code"));
			
			// add MembersBean to categoriesArrayList
			membersArrayList.add(MemberBean);
		}
		
		// close connection
		conn.close();
		return membersArrayList;
	}
	
	// get member by id
	public Member getMemberById (int userId) throws ClassNotFoundException, SQLException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a member by user id query and result
		String getMemberByIdQuery = "SELECT * FROM duotexture.members WHERE userId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getMemberByIdQuery);
		pstmt.setInt(1,  userId);
		ResultSet getMemberByIdResult = pstmt.executeQuery();
		
		// create an instance of member
		Member memberBean = new Member();
		
		// if there is a new row
		if(getMemberByIdResult.next()) {
			memberBean.setFirstName(getMemberByIdResult.getString("first_name"));
			memberBean.setLastName(getMemberByIdResult.getString("last_name"));
			memberBean.setCountry(getMemberByIdResult.getString("country"));
			memberBean.setAddress(getMemberByIdResult.getString("address"));
			memberBean.setPostalCode(getMemberByIdResult.getInt("postal_code"));
			memberBean.setUserId(getMemberByIdResult.getInt("userId"));
		}
	
		// close connection
		conn.close();
		return memberBean;
	}
	
	// add members
	public int insertMember (String firstName, String lastName, String country, String address, int postalCode, int userId) throws SQLException, ClassNotFoundException {

		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, add members query and result
		String insertMemberQuery = "INSERT INTO members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(insertMemberQuery);
	    pstmt.setString(1, firstName);
	    pstmt.setString(2, lastName);
	    pstmt.setString(3, country);
	    pstmt.setString(4, address);
	    pstmt.setInt(5, postalCode);
	    pstmt.setInt(6, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit member
	public int editMember (String firstName, String lastName, String country, String address, int postalCode, int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, edit members query and result
		String updateMembersQuery = "UPDATE members SET first_name=?, last_name=?, country=?, address=?, postal_code=? WHERE userId=?;"; 
		PreparedStatement pstmt = conn.prepareStatement(updateMembersQuery);
		pstmt.setString(1, firstName);
	    pstmt.setString(2, lastName);
	    pstmt.setString(3, country);
	    pstmt.setString(4, address);
	    pstmt.setInt(5, postalCode);
	    pstmt.setInt(6, userId);
	    int count = pstmt.executeUpdate();
		
	    // close connection
		conn.close();
		return count;
	}
}
