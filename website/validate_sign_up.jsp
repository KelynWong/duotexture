<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.*"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Validate Sign Up</title>
</head>
<body>
	<%
	String email = request.getParameter("inputEmail");
	String username = request.getParameter("inputUsername");
	String password = request.getParameter("inputPassword");
	String firstName = request.getParameter("inputFirstName");
	String lastName = request.getParameter("inputLastName");
	String address = request.getParameter("inputAddress");
	String country = request.getParameter("inputCountry");
	String postalCode = request.getParameter("inputPostalCode");
	
	try {
		// connect to mysql database
		Class.forName("com.mysql.jdbc.Driver");  
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);    
		
		// insert inputs into members
		String insertMemberQuery = "INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(insertMemberQuery);
	    pstmt.setString(1, email);
	    pstmt.setString(2, username);
	    pstmt.setString(3, password);
	    pstmt.setString(4, firstName);
	    pstmt.setString(5, lastName);
	    pstmt.setString(6, country);
	    pstmt.setString(7, address);
	    pstmt.setString(8, postalCode);
		int count = pstmt.executeUpdate(); 
		
		if (count > 0){
			// get last row of members
			String getLastMembersQuery = "SELECT * FROM members ORDER BY memberId DESC LIMIT 1;";
			Statement stmt = conn.createStatement(); 
			ResultSet getLastMembersResult = stmt.executeQuery(getLastMembersQuery);
			
			getLastMembersResult.next();
			int memberId = getLastMembersResult.getInt("memberId");
			String memberUsername = getLastMembersResult.getString("username");
			
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberUsername", memberUsername);
			session.setAttribute("accountType", "member");
			
			response.sendRedirect("index.jsp");
		}else{
			response.sendRedirect("sign_up.jsp?registration=fail"); 
		}                  
	           
	conn.close();  
	} catch(Exception e){
		System.out.println("Error: " + e + "\n");
		response.sendRedirect("sign_up.jsp?registration=fail"); 
	}
	%>
</body>
</html>