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
				window.location.href='${pageContext.request.contextPath}/index';
				alert("You do not have access rights.");
				</script>
				<%
			}
		}else{
			%>
			<script type="text/javascript">
			window.location.href='${pageContext.request.contextPath}/index';
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
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="${pageContext.request.contextPath}/EditProductServlet" method="post" enctype="multipart/form-data">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - L i s t i n g</p>
        <hr>
		<%
		try {			
			if(request.getParameter("productId")!=null){
				int getProductId = Integer.parseInt(request.getParameter("productId"));
				
				Product product = (Product) request.getAttribute("product");
				
				String productName = product.getName();               
				String productDescription = product.getDescription();
				Double productCost = product.getCostPrice();
				Double productPrice = product.getRetailPrice();
				int productQuantity = product.getQuantity();
				int productCategoryId = product.getCategoryId();
				String productImage = product.getImage();
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
				    <label for="inputCategoryId">Category</label>
				    <select class="form-control" id="inputCategoryId" name="inputCategoryId">
					    <%
					    try{			    	
					    	for(int x=0; x<categoriesArrayList.size(); x++) {
					    		if(productCategoryId==categoriesArrayList.get(x).getCategoryId()){
					    			out.println("<option selected value="+categoriesArrayList.get(x).getCategoryId()+">"+categoriesArrayList.get(x).getName()+"</option>");
								}else{
									out.println("<option value="+categoriesArrayList.get(x).getCategoryId()+">"+categoriesArrayList.get(x).getName()+"</option>");
								}
							} 
								
					      } catch(Exception e){
					    	  System.out.println("(edit_product.jsp) Category Error: " + e + "\n");
					      }
					      %>
				    </select>
				  </div>
		        </div>
				<div class="form-row">
				<div class="form-group col-md-12">
			        <label for="inputImageUrl">Image Upload</label>
			        <input type="file" multiple="false" class="form-control" style="min-height: 45px" id="inputImageUrl" name="inputImageUrl">
			      </div>
			      <div class="form-group col-md-12">
			        <label class="col-md-12 p-0" style="display: block; max-height: 200px" id="previewImageUrlLabel">Image Preview</label>
			        <img id="previewImageUrl" src="<%= productImage %>" style="display: block; max-height: 200px" alt="Category Image">
			        <input id="previewInputImageUrl" type="hidden" name="previewInputImageUrl" value="<%= productImage %>">
			      </div>
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
    <script>
    
    function readFile(input, previewImage, previewLabel) {
    	if (input.files && input.files[0]) {
    		var reader = new FileReader();
    		
    		reader.onload = function(e) {
    			$(previewLabel).attr('style', "display: block")
    			$(previewImage).attr('src', e.target.result);
    			$(previewImage).attr('style', "display: block; max-height: 200px");
    		}
    		
    		reader.readAsDataURL(input.files[0]);
    	}	
    }
    
    $("#inputImageUrl").change(function() {
    	readFile(this, "#previewImageUrl", "previewImageUrlLabel")
    })
    
    </script>
    <!--===============================================================================================-->
</body>
</html>