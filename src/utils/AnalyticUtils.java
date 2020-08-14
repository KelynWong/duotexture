package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Member;

public class AnalyticUtils {
	
	// get all members
	public static ArrayList<Member> getMembers (int count, String keyword, String order) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		int limit = count*5;
		
		if(order.equals("null")) {
			order = "";
		}
		
		// prepared statement, get all member query and result
		String getMembersQuery = "SELECT * FROM duotexture.members WHERE members.country LIKE '%?%' OR members.address LIKE '%?%' ORDER BY members.userId ? LIMIT ?, 5;";
		PreparedStatement pstmt = conn.prepareStatement(getMembersQuery);
		pstmt.setString(1, keyword);
		pstmt.setString(2, keyword);
		pstmt.setString(3, order);
		pstmt.setInt(4, limit);
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
			
			// add MembersBean to categoriesArrayList
			membersArrayList.add(MemberBean);
		}
		
		// close connection
		conn.close();
		return membersArrayList;
	}
}
