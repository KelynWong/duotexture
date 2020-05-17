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
		String inputUsername = request.getParameter("inputUsername");
		String inputEmail = request.getParameter("inputEmail");
		String inputPassword = request.getParameter("inputPassword");
		
		Class.forName("com.mysql.jdbc.Driver");         
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
		Connection conn = DriverManager.getConnection(connURL);   
		
		if(session.getAttribute("accountType").equals("member")){
			
			String sqlStr = "UPDATE duotexture.members SET email=?, username=?, password=?, first_name=?, last_name=?, country=?, address=?, postal_code=? WHERE memberId=?"; 
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			String inputFirstName = request.getParameter("inputFirstName");
			String inputLastName = request.getParameter("inputLastName");
			String inputAddress = request.getParameter("inputAddress");
			String inputCountry = request.getParameter("inputCountry");
			String inputPostalCode = request.getParameter("inputPostalCode");
		    pstmt.setString(1, inputEmail);
		    pstmt.setString(2, inputUsername);
		    pstmt.setString(3, inputPassword);
		    pstmt.setString(4, inputFirstName);
		    pstmt.setString(5, inputLastName);
		    pstmt.setString(6, inputCountry);
		    pstmt.setString(7, inputAddress);
		    pstmt.setString(8, inputPostalCode);
		    pstmt.setObject(9, session.getAttribute("memberId"));
			int count = pstmt.executeUpdate(); 
			
			if(count > 0){
				response.sendRedirect("edit_profile.jsp?profileEdit=success"); 
			}else{
				response.sendRedirect("edit_profile.jsp?profileEdit=fail");
			}
			
		}else{
			
			String sqlStr = "UPDATE administrators SET email=?, username=?, password=? WHERE administratorId=?"; 
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, inputEmail);
		    pstmt.setString(2, inputUsername);
		    pstmt.setString(3, inputPassword);
		    pstmt.setObject(4, session.getAttribute("adminId"));
		    int count = pstmt.executeUpdate(); 
		    
		    if(count > 0){
				response.sendRedirect("edit_profile.jsp?profileEdit=success"); 
			}else{
				response.sendRedirect("edit_profile.jsp?profileEdit=fail");
			}
		}        
		
	conn.close();      
	} catch (Exception e) {         
		out.println("Error :" + e);      
	} 
	%>
</body>
</html>