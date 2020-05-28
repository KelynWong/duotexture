<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Account Existence Validation</title>
</head>
<body>
	<%
	try{
		if(request.getParameter("emailInput")!=null){
			String emailInput = request.getParameter("emailInput");
			String passwordInput = request.getParameter("passwordInput");
			
			Boolean isAdmin = false;
			Boolean isMember = false;
			
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=potato&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			Statement stmt = conn.createStatement();
			
			// get all users
			String getAllAdminsQuery = "SELECT * FROM users;";
			ResultSet getAllUsersResult = stmt.executeQuery(getAllAdminsQuery);
			
			while (getAllUsersResult.next()){
				String userEmail = getAllUsersResult.getString("email");
				String userPassword = getAllUsersResult.getString("password");
				String userRole = getAllUsersResult.getString("userRole");
				
				// checks if inputs equals details in users table
				if(userEmail.equals(emailInput) && userPassword.equals(passwordInput)){
					int userId = getAllUsersResult.getInt("userId");
					String username = getAllUsersResult.getString("username");
					if(userRole.equals("Admin")){
						isAdmin = true;
						session.setAttribute("accountType", "admin");
					}else if(userRole.equals("Member")){
						isMember = true;
						session.setAttribute("accountType", "member");
					}else if(isAdmin == false && isMember == false){
						response.sendRedirect("login.jsp?accountType=none");
					}
					session.setAttribute("userId", userId);
	        		session.setAttribute("username", username);
					response.sendRedirect("index.jsp");
				}
			}
			conn.close();
		}else{
			response.sendRedirect("login.jsp");
			System.out.println("(validate_login.jsp) Error: Wrong Flow\n");
		}
	}catch(Exception e){
		System.out.println("(validate_login.jsp) Error: " + e + "\n");
	}
	%>
</body>
</html>