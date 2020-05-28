<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.*"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validate Edit Profile</title>
</head>
<body> 
	<%	
	try {          
		if(request.getParameter("inputUsername")!=null){
			String inputUsername = request.getParameter("inputUsername");
			String inputEmail = request.getParameter("inputEmail");
			String inputPassword = request.getParameter("inputPassword");
			
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");         
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
			Connection conn = DriverManager.getConnection(connURL);   
			
			// edit and update user with inputs by user id
			String updateUserQuery = "UPDATE duotexture.users SET email=?, username=?, password=? WHERE userId=?"; 
			PreparedStatement pstmt = conn.prepareStatement(updateUserQuery);
		    pstmt.setString(1, inputEmail);
		    pstmt.setString(2, inputUsername);
		    pstmt.setString(3, inputPassword);
		    pstmt.setObject(4, session.getAttribute("userId"));
			int count = pstmt.executeUpdate(); 
			
			if(session.getAttribute("accountType").equals("admin")){
				if(count > 0){
					session.setAttribute("username", inputUsername);
					response.sendRedirect("edit_profile.jsp?profileEdit=success"); 
				}else{
					response.sendRedirect("edit_profile.jsp?profileEdit=fail");
				}
			}
			// check if user is an member
			else if(session.getAttribute("accountType").equals("member")){
				String inputFirstName = request.getParameter("inputFirstName");
				String inputLastName = request.getParameter("inputLastName");
				String inputAddress = request.getParameter("inputAddress");
				String inputCountry = request.getParameter("inputCountry");
				String inputPostalCode = request.getParameter("inputPostalCode");
				// edit and update members with iputs by user id
				String updateMembersQuery = "UPDATE members SET first_name=?, last_name=?, country=?, address=?, postal_code=? WHERE userId=?"; 
				PreparedStatement pstmt2 = conn.prepareStatement(updateMembersQuery);
				pstmt.setString(1, inputFirstName);
			    pstmt.setString(2, inputLastName);
			    pstmt.setString(3, inputCountry);
			    pstmt.setString(4, inputAddress);
			    pstmt.setString(5, inputPostalCode);
			    pstmt.setObject(6, session.getAttribute("userId"));
			    int count2 = pstmt2.executeUpdate(); 
			    
			    if(count2 > 0){
			    	session.setAttribute("username", inputUsername);
					response.sendRedirect("edit_profile.jsp?profileEdit=success"); 
				}else{
					response.sendRedirect("edit_profile.jsp?profileEdit=fail");
				}
			}        	
			
			conn.close(); 			
		}else{
			System.out.println("(validate_edit_profile.jsp) Error: Wrong Flow\n");
			response.sendRedirect("edit_profile.jsp?profileEdit=fail");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("(validate_edit_profile.jsp) Error: Duplicate Entry\n");
		response.sendRedirect("edit_profile.jsp?profileEdit=fail");	
	} catch (Exception e) {         
		System.out.println("(validate_edit_profile.jsp) Error: " + e + "\n");
		response.sendRedirect("edit_profile.jsp?profileEdit=fail");	
	} 
	%>
</body>
</html>