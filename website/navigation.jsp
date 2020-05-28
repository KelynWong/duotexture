<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Navigation Bar</title>
</head>
<body>
<!-- navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand custom-font-playfair fs-15" href="index.jsp">D u o - T e x t u r e</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <section class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
        <li class="nav-item">
			<a class="nav-link custom-font-mont fs-15" href="categories.jsp" role="button">All Categories</a>
		</li>
		<%
		try {
			// connect to mysql database
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);   
			Statement stmt = conn.createStatement(); 
			
			// get all categories
			String sqlStr = "SELECT * FROM categories";    
			ResultSet rs = stmt.executeQuery(sqlStr); 
			
			while(rs.next())   { 
				int categoryId = rs.getInt("categoryId"); 
				String categoryName = rs.getString("name");  
				%>
				<li class="nav-item">
            		<a class="nav-link custom-font-mont fs-15" href="product_listings.jsp?categoryId=<%= categoryId %>" role="button"><%= categoryName %></a>
          		</li>
          		<% 
			} 
			
			try{
            	if(session.getAttribute("accountType")!=null){
            		// if account is admin, allow access to add function
	            	if(session.getAttribute("accountType").equals("admin")){
	                   	%>
	                   	<a class="nav-link custom-font-mont fs-15 text-success" href="add_category.jsp" style="margin-left: 10px">Add</a>
	                   	<%
	                }
            	}
            } catch(Exception e){
            	System.out.println("(navigation.jsp) Admin Add Access Error: " + e + "\n");
            } 
		            
			conn.close();      
		} catch (Exception e) {         
			System.out.println("(navigation.jsp) Error :" + e + "\n");      
		} 
		%>
        </ul>
        
        <ul class="navbar-nav ml-auto">
		<%
		try{
			// check if user's account and retrieve their username
			if(session.getAttribute("accountType")!=null){ %>
					<a class="nav-link custom-font-mont fs-15 text-primary" href="edit_profile.jsp"><%=session.getAttribute("username")%></a>
					<a class="nav-link custom-font-mont fs-15 text-danger" href="log_out.jsp">Log Out</a>
					<%
			}else{
				%>
	        	<a class="nav-link custom-font-mont fs-15 text-primary" href="login.jsp">Login</a>
	        	<a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.jsp">Sign Up</a>
				<%
			}
			
		} catch(Exception e){ 
			System.out.println("(navigation.jsp) Error: " + e + "\n");
		}
		%>
		</ul>
      </section>
    </nav>
</body>
</html>