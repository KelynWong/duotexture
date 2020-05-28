<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Validate Edits</title>
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
		System.out.println("(validate_edit_product.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<body>
	<%		
	try {       
		if(request.getParameter("inputProductId")!=null){
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
			
			// edit and update categories with inputs by category id
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
		}else{
			System.out.println("(validate_edit_product.jsp) Error: Wrong Flow\n");
			response.sendRedirect("edit_product.jsp?productEdit=fail");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("Edit Product Error: Duplicate Entry\n");
		response.sendRedirect("edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
	} catch (java.lang.NumberFormatException e) {         
		System.out.println("Edit Product Error: Invalid Inputs\n"); 
		response.sendRedirect("edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
	} catch (Exception e) {         
		System.out.println("Edit Product Error :" + e + "\n");    
		response.sendRedirect("edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
	} 
	%>
</body>
</html>