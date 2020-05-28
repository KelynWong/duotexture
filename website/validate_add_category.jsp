<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Validate Addition</title>
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
		System.out.println("(validate_add_category.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<body>
	<%		
	try {       
		if(request.getParameter("inputCategoryName")!=null){
			String inputCategoryName = request.getParameter("inputCategoryName");
			String inputCategoryDescription = request.getParameter("inputCategoryDescription");
			String inputImageUrl = request.getParameter("inputImageUrl");
			
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=potato&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			// insert inputs into categories
			String addCategoryQuery = "INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES(?, ?, ?);"; 
			PreparedStatement pstmt = conn.prepareStatement(addCategoryQuery);
		    pstmt.setString(1, inputCategoryName);
		    pstmt.setString(2, inputCategoryDescription);
		    pstmt.setString(3, inputImageUrl);
			int count = pstmt.executeUpdate(); 
		
			if(count > 0){
				response.sendRedirect("add_category.jsp?categoryAddition=success"); 
			}else{
				response.sendRedirect("add_category.jsp?categoryAddition=fail");
			}
		          
			conn.close();    
		}else{
			System.out.println("(validate_add_category.jsp) Error: Wrong Flow\n");
			response.sendRedirect("add_category.jsp?categoryAddition=fail");
		}
	} catch(java.sql.SQLIntegrityConstraintViolationException e){
		System.out.println("(validate_add_category.jsp) Error: Duplicate Entry\n");
		response.sendRedirect("add_category.jsp?categoryAddition=fail");
	} catch (java.lang.NumberFormatException e) {         
		System.out.println(" (validate_add_category.jsp) Error: Invalid Inputs\n"); 
		response.sendRedirect("add_category.jsp?categoryAddition=fail");
	} catch (Exception e) {         
		System.out.println(" (validate_add_category.jsp) Error: " + e + "\n"); 
		response.sendRedirect("add_category.jsp?categoryAddition=fail");
	} 
	%>
</body>
</html>