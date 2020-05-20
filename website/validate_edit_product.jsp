<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Validate Edits</title>
	<%
	try{ 
		// validate if user executing request is admin // ***********************************************
		if(!session.getAttribute("accountType").equals("admin")){
			%>
			  <script type="text/javascript">
			  	window.location.href='index.jsp';
				alert("You do not have access rights.");
			  </script>
			<%
		}
	} catch (Exception e){
		System.out.println("Error: " + e + "\n");
	}
	%>
</head>
<body>
	<%		
	try {           
		int inputProductId = Integer.parseInt(request.getParameter("inputProductId"));
		String inputProductName = request.getParameter("inputProductName");
		String inputProductDescription = request.getParameter("inputProductDescription");
		Double inputCostPrice = Double.parseDouble(request.getParameter("inputCostPrice"));
		Double inputRetailPrice = Double.parseDouble(request.getParameter("inputRetailPrice"));
		int inputQuantity = Integer.parseInt(request.getParameter("inputQuantity"));
		int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
		String inputImageUrl = request.getParameter("inputImageUrl");
		
		// connect to mysql database
		Class.forName("com.mysql.jdbc.Driver"); 
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);
		
		// edit and update products with inputs by product id
		String updateProductQuery = "UPDATE products SET name=?, description=?, cost_price=?, retail_price=?, quantity=?, categoryId=?, image=? WHERE productId=?;"; 
		PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
	    pstmt.setString(1, inputProductName);
	    pstmt.setString(2, inputProductDescription);
	    pstmt.setDouble(3, inputCostPrice);
	    pstmt.setDouble(4, inputRetailPrice);
	    pstmt.setInt(5, inputQuantity);
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
		System.out.println("Error :" + e + "\n");      
	} 
	%>
</body>
</html>