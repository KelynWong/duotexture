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
			
			// checks if email is equal to admin's email
			String getAllAdministratorsQuery = "SELECT email FROM duotexture.administrators;";
			ResultSet getAllAdministratorsResults = stmt.executeQuery(getAllAdministratorsQuery); 
			
			while(getAllAdministratorsResults.next()){
				String administratorsEmail = getAllAdministratorsResults.getString("email");
				if(administratorsEmail.equals(inputEmail)){
					throw new java.sql.SQLIntegrityConstraintViolationException("Duplicate Entry against Administrators' Email");
				};
			}		
			
			// insert inputs into members
			String insertMemberQuery = "INSERT INTO duotexture.members(`email`, `username`, `password`, `first_name`, `last_name`, `country`, `address`, `postal_code`) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insertMemberQuery);
		    pstmt.setString(1, inputEmail);
		    pstmt.setString(2, inputUsername);
		    pstmt.setString(3, inputPassword);
		    pstmt.setString(4, inputFirstName);
		    pstmt.setString(5, inputLastName);
		    pstmt.setString(6, inputCountry);
		    pstmt.setString(7, inputAddress);
		    pstmt.setString(8, inputPostalCode);
			int count = pstmt.executeUpdate(); 
			
			if (count > 0){
				// get last row of members
				String getLastMembersQuery = "SELECT * FROM members ORDER BY memberId DESC LIMIT 1;";
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
		}else{
			response.sendRedirect("sign_up.jsp");
			System.out.println("(validate_sign_up.jsp) Error: Wrong Flow\n");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("Error: Duplicate Entry\n");
		response.sendRedirect("sign_up.jsp?registration=fail"); 
	} catch(Exception e){
		System.out.println("Error: " + e + "\n");
		response.sendRedirect("sign_up.jsp?registration=fail"); 
	}
	%>
</body>
</html>