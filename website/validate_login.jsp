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
		String emailInput = request.getParameter("emailInput");
		String passwordInput = request.getParameter("passwordInput");
		
		// connect to mysql database
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);
		Statement stmt = conn.createStatement();
		
		// get all administrators
		String getAllAdminsQuery = "SELECT * FROM administrators;";
		ResultSet getAllAdminsResult = stmt.executeQuery(getAllAdminsQuery);
		
		while (getAllAdminsResult.next()){
			String adminEmail = getAllAdminsResult.getString("email");
			String adminPassword = getAllAdminsResult.getString("password");
			
			// checks if inputs equals administrators database
			if(adminEmail.equals(emailInput) && adminPassword.equals(passwordInput)){
				int adminId = getAllAdminsResult.getInt("administratorId");
				String adminUsername = getAllAdminsResult.getString("username");
				
				session.setAttribute("adminId", adminId);
        		session.setAttribute("adminUsername", adminUsername);
        		session.setAttribute("accountType", "admin");
				response.sendRedirect("index.jsp");
			}
		}
		
		Boolean isAdmin = false;
				
		// get all members
		String getAllMembersQuery = "SELECT * FROM members";
		ResultSet getAllMembersResult = stmt.executeQuery(getAllMembersQuery);
		
		while(getAllMembersResult.next()){
			String memberEmail = getAllMembersResult.getString("email");
			String memberPassword = getAllMembersResult.getString("password");
			
			// checks if inputs equals members database
			if(memberEmail.equals(emailInput) && memberPassword.equals(passwordInput)){
				int memberId = getAllMembersResult.getInt("memberId");
				String memberUsername = getAllMembersResult.getString("username");
				
				session.setAttribute("memberId", memberId);
				session.setAttribute("memberUsername", memberUsername);
				session.setAttribute("accountType", "member");
				response.sendRedirect("index.jsp");
			}
		}
		
		Boolean isMember = false;
		
		// if account is neither in members or administrators database
		if(isAdmin == false && isMember == false){
			response.sendRedirect("login.jsp?accountType=none");
		}

		conn.close();
	}catch(Exception e){
		System.out.println("Error: " + e + "\n");
	}
	%>
</body>
</html>