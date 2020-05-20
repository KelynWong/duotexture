<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validate Addition</title>
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
			String inputProductName = request.getParameter("inputProductName");
			String inputProductDescription = request.getParameter("inputProductDescription");
			Double inputCostPrice = Double.parseDouble(request.getParameter("inputCostPrice"));
			Double inputRetailPrice = Double.parseDouble(request.getParameter("inputRetailPrice"));
			int inputQuantity = Integer.parseInt(request.getParameter("inputQuantity"));
			int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
			String inputImageUrl = request.getParameter("inputImageUrl");
			
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			 
			String updateProductQuery = "INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?);"; 
			PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
		    pstmt.setString(1, inputProductName);
		    pstmt.setString(2, inputProductDescription);
		    pstmt.setDouble(3, inputCostPrice);
		    pstmt.setDouble(4, inputRetailPrice);
		    pstmt.setInt(5, inputQuantity);
		    pstmt.setInt(6, inputCategoryId);
		    pstmt.setString(7, inputImageUrl);
			int count = pstmt.executeUpdate(); 
		
			if(count > 0){
				response.sendRedirect("add_product.jsp?productAddition=success"); 
			}else{
				response.sendRedirect("add_product.jsp?productAddition=fail");
			}
		          
		conn.close();      
		} catch (Exception e) {         
			out.println("Error :" + e);      
		} 
	%>
</body>
</html>