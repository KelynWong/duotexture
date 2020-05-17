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
		
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);
		Statement stmt = conn.createStatement();
		
		String getAllAdmins = "SELECT * FROM administrators;";
		ResultSet getAllAdminsResult = stmt.executeQuery(getAllAdmins);
		
		while (getAllAdminsResult.next()){
			String adminEmail = getAllAdminsResult.getString("email");
			String adminPassword = getAllAdminsResult.getString("password");
			
			if(adminEmail.equals(emailInput) && adminPassword.equals(passwordInput)){
				int adminId = getAllAdminsResult.getInt("administratorId");
				String adminUsername = getAllAdminsResult.getString("username");
				
				session.setAttribute("adminId", adminId);
        		session.setAttribute("adminUsername", adminUsername);
        		session.setAttribute("accountType", "admin");
				response.sendRedirect("index.jsp");
				
			}else{
				
				String getAllMembers = "SELECT * FROM members";
				ResultSet getAllMembersResult = stmt.executeQuery(getAllMembers);
				
				while(getAllMembersResult.next()){
					String memberEmail = getAllMembersResult.getString("email");
					String memberPassword = getAllMembersResult.getString("password");
					
					if(memberEmail.equals(emailInput) && memberPassword.equals(passwordInput)){
						int memberId = getAllMembersResult.getInt("memberId");
						String memberUsername = getAllMembersResult.getString("username");
						
						session.setAttribute("memberId", memberId);
						session.setAttribute("memberUsername", memberUsername);
						session.setAttribute("accountType", "member");
						response.sendRedirect("index.jsp");
					}else{
						// session.setAttribute("accountType", "none");
						response.sendRedirect("login.jsp?accountType=none");
					}
				}
			}
		}
		
		conn.close();
	}catch(Exception e){
		out.println("Error: " + e);
	}
%>
</body>
</html>