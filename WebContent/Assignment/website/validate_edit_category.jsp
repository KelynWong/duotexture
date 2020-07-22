<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
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
	<title>Validate Edit Category</title>
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
		System.out.println("(validate_edit_category.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<body>
	<%		
	try {       
		if(request.getParameter("inputCategoryId")!=null){
			int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
			String inputCategoryName = request.getParameter("inputCategoryName");
			String inputCategoryDescription = request.getParameter("inputCategoryDescription");
			String inputCategoryImageUrl = request.getParameter("inputCategoryImageUrl");
			
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			// edit and update products with inputs by product id
			String updateCategoryQuery = "UPDATE categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
			PreparedStatement pstmt = conn.prepareStatement(updateCategoryQuery);
		    pstmt.setString(1, inputCategoryName);
		    pstmt.setString(2, inputCategoryDescription);
		    pstmt.setString(3, inputCategoryImageUrl);
		    pstmt.setInt(4, inputCategoryId);
			int count = pstmt.executeUpdate(); 
		
			if(count > 0){
				response.sendRedirect("edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=success"); 
			}else{
				response.sendRedirect("edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=fail");
			}
			
			conn.close();   
		}else{
			System.out.println("(validate_edit_category.jsp) Error: Wrong Flow\n");
			response.sendRedirect("edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "categoryEdit=fail");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("(validate_edit_category.jsp) Error: Duplicate Entry\n");
		response.sendRedirect("edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
	} catch (java.lang.NumberFormatException e) {         
		System.out.println("(validate_edit_category.jsp) Error: Invalid Inputs\n"); 
		response.sendRedirect("edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
	} catch (Exception e) {         
		System.out.println("(validate_edit_category.jsp) Error :" + e + "\n");    
		response.sendRedirect("edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
	} 
	%>
</body>
</html>