<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- 
	Class: DIT/FT/2B/21
	Group: 1
	
	Name: LEE ZONG XUN RENFRED
	Admin Number: P1935392 
	
	Name: WONG EN TING KELYN
	Admin Number: P1935800
-->
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Out</title>
</head>
<body>
	<%
	// mark session invalid and destroy
	session.invalidate();
	response.sendRedirect("index.jsp");
	%>
</body>
</html>