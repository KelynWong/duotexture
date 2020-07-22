<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.*"%> 
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
	<title>Delete Category</title>
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
		System.out.println("(delete_category.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<body> 
	<%	
	try {
		if(request.getParameter("categoryId")!=null){
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver");         
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
				Connection conn = DriverManager.getConnection(connURL);   
				
				// delete category by given id
				String deleteCategoryById = "DELETE FROM categories WHERE categoryId=?"; 
				PreparedStatement pstmt = conn.prepareStatement(deleteCategoryById);
			    pstmt.setInt(1, categoryId);
				int count = pstmt.executeUpdate(); 
				
				if(count > 0){
					%>
		  		    <script type="text/javascript">
		  		  		window.location.href='categories.jsp';
		  				alert("Category has successfully been deleted.");
		  		    </script>
			   		<%
				}else{
					%>
		  		    <script type="text/javascript">
		  		  		window.location.href='categories.jsp';
		  				alert("Failed to delete category.");
		  		    </script>
			   		<%
				}
				conn.close();     
			}else{
				System.out.println("(delete_category.jsp) Error: CategoryId is null.\n");
				%>
	  		    <script type="text/javascript">
	  		  		window.location.href='categories.jsp';
	  				alert("Failed to delete category.");
	  		    </script>
		   		<%
			}
	} catch (Exception e) {         
		System.out.println("(delete_categories.jsp) Error: " + e + "\n");
		response.sendRedirect("categories.jsp");
	} 
	%>
</body>
</html>