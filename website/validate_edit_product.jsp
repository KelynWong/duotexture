<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validate Edits</title>
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
		int inputProductId = Integer.parseInt(request.getParameter("inputProductId"));
		String inputProductName = request.getParameter("inputProductName");
		String inputProductDescription = request.getParameter("inputProductDescription");
		String inputCostPrice = request.getParameter("inputCostPrice");
		String inputRetailPrice = request.getParameter("inputRetailPrice");
		String inputQuantity = request.getParameter("inputQuantity");
		int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
		String inputImageUrl = request.getParameter("inputImageUrl");
		
		try {           
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			 
			String updateProductQuery = "UPDATE products SET name=?, description=?, cost_price=?, retail_price=?, quantity=?, categoryId=?, image=? WHERE productId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
		    pstmt.setString(1, inputProductName);
		    pstmt.setString(2, inputProductDescription);
		    pstmt.setString(3, inputCostPrice);
		    pstmt.setString(4, inputRetailPrice);
		    pstmt.setString(5, inputQuantity);
		    pstmt.setInt(6, inputCategoryId);
		    pstmt.setString(7, inputImageUrl);
		    pstmt.setInt(8, inputProductId);
			int count = pstmt.executeUpdate(); 
		
			if(count > 0){
				response.sendRedirect("edit_product.jsp?productId=" + inputProductId + "&productEdit=success"); 
			}else{
				response.sendRedirect("edit_product.jsp?productId=" + inputProductId + "&productEdit=fail");
			}
		          
		conn.close();      
		} catch (Exception e) {         
			out.println("Error :" + e);      
		} 
	%>
</body>
</html>