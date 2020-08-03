<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<!-- 
	Class: DIT/FT/2B/21
	Group: 1
	
	Name: LEE ZONG XUN RENFRED
	Admin Number: P1935392 
	
	Name: WONG EN TING KELYN
	Admin Number: P1935800
-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>duo-texture</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assignment/website/assets/css/styles.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assignment/website/assets/css/util.css" />
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
		System.out.println("(edit_product.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row">
    	
      <!-- edit product form -->
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="../../EditProductServlet" method="post">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - L i s t i n g</p>
        <hr>
		<%
		try {			
			if(request.getParameter("productId")!=null){
				int getProductId = Integer.parseInt(request.getParameter("productId"));
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver"); 
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);   
				Statement stmt = conn.createStatement(); 
				
				// get and display product details by given id
				String getProductsByIdQuery = "SELECT * FROM products WHERE productId=? LIMIT 1";    
				PreparedStatement pstmt = conn.prepareStatement(getProductsByIdQuery);
				
			    pstmt.setInt(1, getProductId);
				ResultSet getProductsByIdResult = pstmt.executeQuery(); 
			
				getProductsByIdResult.next();
				String productName = getProductsByIdResult.getString("name");               
				String productDescription = getProductsByIdResult.getString("description");
				String productCost = getProductsByIdResult.getString("cost_price");
				String productPrice = getProductsByIdResult.getString("retail_price");
				String productQuantity = getProductsByIdResult.getString("quantity");
				int productCategoryId = getProductsByIdResult.getInt("categoryId");
				String productImage = getProductsByIdResult.getString("image"); 
				%>

				<div class="form-row">
		          <div class="form-group row col-md-12">
		            <label class="my-1 col-2" for="inputProductId">Product Id</label>
		            <input type="text" class="form-control col-10" id="inputProductId" name="inputProductId" placeholder="Product Id" value="<%= getProductId %>" readonly required>
		          </div>
		          <div class="form-group col-md-12">
		            <label for="inputProductName">Name</label>
		            <input type="text" class="form-control" id="inputProductName" name="inputProductName" value="<%= productName %>" required>
		          </div>
		          <div class="form-group col-md-12">
		            <label for="inputProductDescription">Description</label>
		            <textarea type="text" class="form-control" id="inputProductDescription" name="inputProductDescription" required><%= productDescription %></textarea>
		          </div>
		          <div class="form-group col-md-3">
		            <label for="inputCostPrice">Cost Price</label>
		            <input type="text" class="form-control" id="inputCostPrice" name="inputCostPrice" value="<%= productCost %>" required>
		          </div>
		          <div class="form-group col-md-3">
		            <label for="inputRetailPrice">Retail Price</label>
		            <input type="text" class="form-control" id="inputRetailPrice" name="inputRetailPrice" value="<%= productPrice %>" required>
		          </div>
		          <div class="form-group col-md-3">
		            <label for="inputQuantity">Quantity</label>
		            <input type="text" class="form-control" id="inputQuantity" name="inputQuantity" value="<%= productQuantity %>" required>
		          </div>
		          <div class="form-group col-md-3">
				    <label for="inputCategoryId">Category Id</label>
				    <select class="form-control" id="inputCategoryId" name="inputCategoryId">
					    <%
					    try{
							// get all categories
							String getCategoriesQuery = "SELECT * FROM categories";    
							ResultSet getCategoriesResults = stmt.executeQuery(getCategoriesQuery);
							
							while(getCategoriesResults.next())   { 
								int categoryId = getCategoriesResults.getInt("categoryId");  
								if(productCategoryId==categoryId){
									out.println("<option selected>"+categoryId+"</option>");
								}else{
									out.println("<option>"+categoryId+"</option>");
								}
							} 
								
					      } catch(Exception e){
					    	  System.out.println("(edit_product.jsp) Category Error: " + e + "\n");
					      }
					      %>
				    </select>
				  </div>
		        </div>

		        <div class="form-group">
		          <label for="inputImageUrl">Image Url</label>
		          <input type="text" class="form-control" id="inputImageUrl" name="inputImageUrl" value="<%= productImage %>" required>
		        </div>
		        
		        <%
	        	try{
	        		// display different error message dependant on request success and failure
	        		if(request.getParameter("productEdit")!=null){
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
		            	}
	        		}
	        	} catch(Exception e){
		        	System.out.println("(edit_product.jsp) Message Error: " + e + "\n");
		        }
		        %>
		        <button type="submit" class="btn btn-success">Edit</button>
			<%
			conn.close();      
			}else{
				%>
				<p>Product Id is not declared.</p>
				<%
			}
		} catch (Exception e) {         
			System.out.println("(edit_product.jsp) Error: " + e + "\n");      
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