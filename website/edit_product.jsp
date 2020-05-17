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

    <section class="col-12 p-5 row">
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="validate_edit_product.jsp" method="post">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t L i s t i n g</p>
        <hr>

		<%
		try {                      
			Class.forName("com.mysql.jdbc.Driver"); 
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);   
			Statement stmt = conn.createStatement(); 
			
			String getProductsByIdQuery = "SELECT * FROM products WHERE productId=? LIMIT 1";    
			PreparedStatement pstmt = conn.prepareStatement(getProductsByIdQuery);
			
			int getProductId = Integer.parseInt(request.getParameter("productId"));
		    pstmt.setInt(1, getProductId);
			ResultSet getProductsByIdResult = pstmt.executeQuery(); 
		
			getProductsByIdResult.next();
			String productName = getProductsByIdResult.getString("name");               
			String productDescription = getProductsByIdResult.getString("description");
			String productCost = getProductsByIdResult.getString("cost_price");
			String productPrice = getProductsByIdResult.getString("retail_price");
			String productQuantity = getProductsByIdResult.getString("quantity");
			String productCategoryId = getProductsByIdResult.getString("categoryId");
			String productImage = getProductsByIdResult.getString("image"); 
			%>

			<div class="form-row">
	          <div class="form-group row col-md-12">
	            <label class="my-1 col-2" for="inputProductId">Product Id</label>
	            <input type="text" class="form-control col-10" name="inputProductId" placeholder="Product Id" value="<%= getProductId %>" readonly required>
	          </div>
	          <div class="form-group col-md-12">
	            <label for="inputProductName">Name</label>
	            <input type="text" class="form-control" name="inputProductName" value="<%= productName %>" required>
	          </div>
	          <div class="form-group col-md-12">
	            <label for="inputProductDescription">Description</label>
	            <input type="text" class="form-control" name="inputProductDescription" value="<%= productDescription %>" required></input>
	          </div>
	          <div class="form-group col-md-3">
	            <label for="inputCostPrice">Cost Price</label>
	            <input type="text" class="form-control" name="inputCostPrice" value="<%= productCost %>" required>
	          </div>
	          <div class="form-group col-md-3">
	            <label for="inputRetailPrice">Retail Price</label>
	            <input type="text" class="form-control" name="inputRetailPrice" value="<%= productPrice %>" required>
	          </div>
	          <div class="form-group col-md-3">
	            <label for="inputQuantity">Quantity</label>
	            <input type="text" class="form-control" name="inputQuantity" value="<%= productQuantity %>" required>
	          </div>
	          <div class="form-group col-md-3">
	            <label for="inputQuantity">Category Id</label>
	            <input type="text" class="form-control" name="inputCategoryId" value="<%= productCategoryId %>" required>
	          </div>
	        </div>

	        <div class="form-group">
	          <label for="inputImageUrl">Image Url</label>
	          <input type="text" class="form-control" name="inputImageUrl" value="<%= productImage %>" required>
	        </div>
	        
	        <%
	        	try{
	        		String errorMessage = "";
	            	String productEdit = request.getParameter("productEdit");
	            	
	            	if(productEdit.equals("fail")){
	            		errorMessage = "Attempt to edit failed. Please try again.";
	            		%>
				        <small class="text-danger"><%= errorMessage %><br><br></small>
				        <%
	            	}else if(productEdit.equals("success")){
	            		errorMessage = "Successfully Edited.";
	            		%>
				        <small class="text-success"><%= errorMessage %><br><br></small>
				        <%
	            	}else{	
	            	}
		        }catch(Exception e){
		        	// out.println("Error: " + e);
		        }
	        %>
	        
	        <button type="submit" class="btn btn-success">Edit</button>
		<%
		
		conn.close();      
		} catch (Exception e) {         
			out.println("Error :" + e);      
		} 
		%>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>