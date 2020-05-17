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
try{
	String email = request.getParameter("inputEmail");
	String username = request.getParameter("inputUsername");
	String password = request.getParameter("inputPassword");
	String firstName = request.getParameter("inputFirstName");
	String lastName = request.getParameter("inputLastName");
	String address = request.getParameter("inputAddress");
	String country = request.getParameter("inputCountry");
	String postalCode = request.getParameter("inputPostalCode");
	
	out.println(email + username + password + firstName + lastName + address + country + postalCode);
	    
	} catch (Exception e) {         
		out.println("Error :" + e);      
	} 
%>
</body>
</html>