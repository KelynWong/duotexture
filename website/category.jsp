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
    
    <!-- navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand custom-font-playfair fs-15" href="index.jsp">D u o - T e x t u r e</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <section class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
		<%
		try {           
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);   
			Statement stmt = conn.createStatement(); 
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
		      
			conn.close();      
		} catch (Exception e) {         
			out.println("Error :" + e);      
		} 
		%>
        </ul>
        
        <ul class="navbar-nav ml-auto">
		<%
		try{
			if(session.getAttribute("accountType").equals("admin") && session.getAttribute("adminUsername") != null){ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="edit_profile.jsp"><%=session.getAttribute("adminUsername")%></a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="log_out.jsp">Log Out</a>
			<%
			}else if(session.getAttribute("accountType").equals("member") && session.getAttribute("memberUsername") != null){ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="edit_profile.jsp"><%=session.getAttribute("memberUsername")%></a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="log_out.jsp">Log Out</a>
			<%
			}else{ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="login.jsp">Login</a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.jsp">Sign Up</a>
			<%
			}
			
		}catch(Exception e){ %>
	          <a class="nav-link custom-font-mont fs-15 text-primary" href="login.jsp">Login</a>
	          <a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.jsp">Sign Up</a>
		<%
		}
		%>
		</ul>
      </section>
    </nav>

    <section class="col-12 p-5 row justify-content-center">
      <div class="card col-3 mx-5" style="width: 18rem;">
          <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
          <div class="card-body">
              <h5 class="card-title">Men</h5>
              <p class="card-text fs-13">IN STYLE - The Casual Classics.<br>Vintage styles with a modern twist to match everything you have.</p>
              <a href="product_listings.jsp" class="btn btn-primary">Explore More</a>
          </div>
      </div>

      <div class="card col-3 mx-5 d-flex flex-column" style="width: 18rem;">
          <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
          <div class="card-body">
              <h5 class="card-title">Women</h5>
              <p class="card-text fs-13">MOTHERS' DAY SPECIAL<br>Extra 30% off! Use code `30MOM` to get your mother a gift! Promotion ends on 30 May 2020.</p>
              <a href="product_listings.jsp" class="btn btn-danger mt-auto">Explore More</a>
          </div>
      </div>

      <div class="card col-3 mx-5" style="width: 18rem;">
          <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
          <div class="card-body">
              <h5 class="card-title">Kids</h5>
              <p class="card-text fs-13">Kids sportswear up to 40% off.<br>Cruise through the week with our fashionable picks at discounted price.</p>
              <a href="product_listings.jsp" class="btn btn-secondary">Explore More</a>
          </div>
      </div>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>