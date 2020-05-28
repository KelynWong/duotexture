<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.*"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Delete Product</title>
	<%
	try{ 
		// validate if user executing request is admin
		if(session.getAttribute("accountType")!=null){
			if(!session.getAttribute("accountType").equals("admin")){
				%>
				<script type="text/javascript">
				window.location.href='index.jsp';
				alert("You do not have access rights.");
				</script>
				<%
			}
		}else{
			%>
			<script type="text/javascript">
			window.location.href='index.jsp';
			alert("You do not have access rights.");
			</script>
			<%
		}
	} catch (Exception e){
		System.out.println("(delete_product.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<body> 
	<%	
	try {
		if(session.getAttribute("categoryId")!=null){
			if(request.getParameter("productId")!=null){
				int categoryId = (int) session.getAttribute("categoryId");
				String productId = request.getParameter("productId");
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver");         
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
				Connection conn = DriverManager.getConnection(connURL);   
				
				// delete product by given id
				String deleteProductById = "DELETE FROM products WHERE productId=?"; 
				PreparedStatement pstmt = conn.prepareStatement(deleteProductById);
			    pstmt.setString(1, productId);
				int count = pstmt.executeUpdate(); 
				
				if(count > 0){
					%>
		  		    <script type="text/javascript">
		  		  		window.location.href='product_listings.jsp?categoryId=<%=categoryId%>';
		  				alert("Product has successfully been deleted.");
		  		    </script>
			   		<%
				}else{
					%>
		  		    <script type="text/javascript">
		  		  		window.location.href='product_listings.jsp?categoryId=<%=categoryId%>';
		  				alert("Failed to delete product.");
		  		    </script>
			   		<%
				}
				conn.close();     
			}else{
				System.out.println("(delete_product.jsp) Error: ProductId is null.\n");
				%>
	  		    <script type="text/javascript">
	  		  		window.location.href='product_listings.jsp?categoryId=<%=session.getAttribute("categoryId")%>';
	  				alert("Failed to delete product.");
	  		    </script>
		   		<%
			}
		}else{
			System.out.println("(delete_product.jsp) Error: CategoryId is null.\n");
			response.sendRedirect("categories.jsp");
		}
	} catch (Exception e) {         
		System.out.println("(delete_product.jsp) Error: " + e + "\n");
		response.sendRedirect("categories.jsp");
	} 
	%>
</body>
</html>