<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.*"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Product</title>
<%
   try{
   	
   	if(!session.getAttribute("accountType").equals("admin")){
   		%>
   		  <script type="text/javascript">
   		  	window.location.href='index.jsp';
   			alert("You do not have access rights.");
   		  </script>
   		<%
  		}
   } catch (Exception e){
   	// out.println("Error: " + e);
   }
%>
</head>
<body> 
	<%	
	try {     
		String productId = request.getParameter("productId");
		String categoryId = request.getParameter("categoryId");
		
		Class.forName("com.mysql.jdbc.Driver");         
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
		Connection conn = DriverManager.getConnection(connURL);   
			
		String deleteProductById = "DELETE FROM products WHERE productId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteProductById);
	    pstmt.setString(1, productId);
		int count = pstmt.executeUpdate(); 
		
		if(count > 0){
			response.sendRedirect("product_listings.jsp?categoryId=" + categoryId + "&productDeletion=success"); 
		}else{
			response.sendRedirect("product_listings.jsp?categoryId=" + categoryId + "&productDeletion=fail");
		}

	conn.close();      
	} catch (Exception e) {         
		out.println("Error :" + e);      
	} 
	%>
</body>
</html>