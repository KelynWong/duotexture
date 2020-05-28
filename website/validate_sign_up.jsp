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
	try {
		if(request.getParameter("inputEmail")!=null){
			String inputEmail = request.getParameter("inputEmail");
			String inputUsername = request.getParameter("inputUsername");
			String inputPassword = request.getParameter("inputPassword");
			String inputFirstName = request.getParameter("inputFirstName");
			String inputLastName = request.getParameter("inputLastName");
			String inputAddress = request.getParameter("inputAddress");
			String inputCountry = request.getParameter("inputCountry");
			String inputPostalCode = request.getParameter("inputPostalCode");

			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);    
			Statement stmt = conn.createStatement(); 
			
			// checks if email is equal to user's email
			String getAllUsersEmailQuery = "SELECT email FROM duotexture.users;";
			ResultSet getAllUsersEmailResult = stmt.executeQuery(getAllUsersEmailQuery); 
			
			while(getAllUsersEmailResult.next()){
				String userEmail = getAllUsersEmailResult.getString("email");
				if(userEmail.equals(inputEmail)){
					throw new java.sql.SQLIntegrityConstraintViolationException("Duplicate Entry");
				};
			}		
			
			// insert inputs into users table
			String insertUserQuery = "INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES(?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insertUserQuery);
		    pstmt.setString(1, inputEmail);
		    pstmt.setString(2, inputUsername);
		    pstmt.setString(3, inputPassword);
		    pstmt.setString(4, "Member");
			int count = pstmt.executeUpdate(); 
			
			if (count > 0){
				// get last row of users
				String getLastMemberQuery = "SELECT * FROM users ORDER BY userId DESC LIMIT 1;";
				ResultSet getLastMemberResult = stmt.executeQuery(getLastMemberQuery);
				
				getLastMemberResult.next();
				int userId = getLastMemberResult.getInt("userId");
				String memberUsername = getLastMemberResult.getString("username");
				
				session.setAttribute("userId", userId);
				session.setAttribute("username", memberUsername);
				session.setAttribute("accountType", "member");
				
				// insert inputs into members table
				String insertMemberQuery = "INSERT INTO members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES(?, ?, ?, ?, ?, ?);";
				PreparedStatement pstmt2 = conn.prepareStatement(insertMemberQuery);
			    pstmt2.setString(1, inputFirstName);
			    pstmt2.setString(2, inputLastName);
			    pstmt2.setString(3, inputCountry);
			    pstmt2.setString(4, inputAddress);
			    pstmt2.setString(5, inputPostalCode);
			    pstmt2.setObject(6, userId);
				int count2 = pstmt2.executeUpdate(); 
				
				if(count2 > 0){
					response.sendRedirect("index.jsp");
				}else{
					response.sendRedirect("sign_up.jsp?registration=fail"); 
				}
			}else{
				response.sendRedirect("sign_up.jsp?registration=fail"); 
			}                  
		           
			conn.close();
		}else{
			response.sendRedirect("sign_up.jsp");
			System.out.println("(validate_sign_up.jsp) Error: Wrong Flow\n");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("(validate_sign_up.jsp) Error: Duplicate Entry\n");
		response.sendRedirect("sign_up.jsp?registration=fail"); 
	} catch(Exception e){
		System.out.println("(validate_sign_up.jsp) Error: " + e + "\n");
		response.sendRedirect("sign_up.jsp?registration=fail"); 
	}
	%>
</body>
</html>