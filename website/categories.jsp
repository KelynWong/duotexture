<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>duo-texture</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/styles.css"/>
    <link rel="stylesheet" href="assets/css/util.css" />
</head>

<body class="d-block w-100 vh-100 bg-img">
    
    <!-- import navigation bar -->
    <%@ include file = "navigation.jsp" %>

    <section class="col-12 p-5 row justify-content-center">
    	<%
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=potato&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL); 
			Statement stmt = conn.createStatement(); 
			
			// get and display all categories
			String getAllCategoriesQuery = "SELECT * FROM categories";    
			ResultSet getAllCategoriesResult = stmt.executeQuery(getAllCategoriesQuery); 
		
			while(getAllCategoriesResult.next())   { 
				int categoryId = getAllCategoriesResult.getInt("categoryId");   
				String categoryName = getAllCategoriesResult.getString("name");               
				String categoryDescription = getAllCategoriesResult.getString("description");
				String categoryImage = getAllCategoriesResult.getString("image"); 
				%>
				<div class="card col-3 mx-5 mb-5" style="width: 18rem;">
		          <img src="<%= categoryImage %>" class="card-img-top mt-3" alt="...">
		          <div class="card-body d-flex flex-column">
		              <h5 class="card-title"><%= categoryName %></h5>
		              <p class="card-text fs-13"><%= categoryDescription %></p>
		              <a class="mt-auto" href="product_listings.jsp?categoryId=<%= categoryId %>" class="btn btn-primary">Explore More</a>
		              <%
				            try{
				            	if(session.getAttribute("accountType")!=null){
				            		// if account is admin, allow access to edit and delete function
				            		if(session.getAttribute("accountType").equals("admin")){
					                   	%>
					                   	<div>
					                        <a href="edit_category.jsp?categoryId=<%= categoryId %>" class="btn btn-warning mr-1">Edit</a>
					                        <a onclick="return confirm('Are you sure you want to delete?')" href="delete_category.jsp?categoryId=<%= categoryId %>" class="btn btn-danger">Delete</a>
					                    </div>
					                   	<%
					                }
				            	}
				            } catch(Exception e){
				            	System.out.println("(categories.jsp) Admin Edit and Delete Access Error: " + e + "\n");
				            }
				      %>
		          </div>
		      	</div>
			<% 
			} 
		conn.close();      
		} catch (Exception e) {         
			System.out.println("(categories.jsp) Error :" + e + "\n");
		} 
		%>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>