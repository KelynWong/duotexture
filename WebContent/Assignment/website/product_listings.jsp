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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assignment/website/assets/css/styles.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assignment/website/assets/css/util.css" />
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row justify-content-center">
    	<% 
    	if(request.getParameter("categoryId")!=null){
    		// store categoryId
        	int getCategoryId = Integer.parseInt(request.getParameter("categoryId")); 
        	session.setAttribute("categoryId", getCategoryId);
	    	%>
	    	<!-- search and add function -->
	        <form class="form-inline col-11 justify-content-center" action="${pageContext.request.contextPath}/productlistings?categoryId=<%= getCategoryId %>" method="post">
	            <input class="form-control col-10" name="keywordInput" type="search" placeholder="Search">
	            <button class="btn btn-outline-danger my-2 my-sm-0 search-btn" type="submit">Search</button>
	            <%
	            try{
	            	if(session.getAttribute("accountType")!=null){
	            		// if account is admin, allow access to add function
		            	if(session.getAttribute("accountType").equals("admin")){
		                   	%>
		                   	<a href="${pageContext.request.contextPath}/addproduct" class="btn btn-success" style="margin-left: 10px">Add</a>
		                   	<%
		                }
	            	}
	            } catch(Exception e){
	            	System.out.println("(product_listings.jsp) Admin Add Access Error: " + e + "\n");
	            }
    	}else{
    		%>
			<p>There isn't any category selected.</p>
			<%
    	}
		%>
		</form>
		<%
		try {
			String keywordInput = request.getParameter("keywordInput");
			String getAllProductsByCategoryIdQuery;
			ArrayList<Product> productsArrayList = (ArrayList<Product>) request.getAttribute("searchProductsArrayList");
		
			for(int x=0; x<productsArrayList.size(); x++){
				int id = productsArrayList.get(x).getProductId();
				String name = productsArrayList.get(x).getName();         
				String description = productsArrayList.get(x).getDescription();
				String image = productsArrayList.get(x).getImage();
				
				%>
				<div class="card col-2 mx-2 mt-3" style="width: 18rem;">
		            <img src="<%= image %>" class="card-img-top mt-3" alt="..."> <!-- note!!! -->
		            <div class="card-body d-flex flex-column">
		                <p class="card-title fs-14" style="font-weight: bold"><%= name %></p>
		                <p class="card-text fs-13"><%= description %></p>
		                <div class="mx-2 pl-1 mt-auto">
		                    <a href="${pageContext.request.contextPath}/productdetails?productId=<%= id %>" class="btn btn-primary px-4 my-2">View Details</a>
				         	<%
				            try{
				            	if(session.getAttribute("accountType")!=null){
				            		// if account is admin, allow access to edit and delete function
				            		if(session.getAttribute("accountType").equals("admin")){
					                   	%>
					                   	<div class="row mt-2">
					                   		<form action="${pageContext.request.contextPath}/editproduct?productId=<%= id %>" method="post">
					                        	<button type="submit" class="btn btn-warning ml-3 mr-2">Edit</button>
					                        </form>
					                        <form action="${pageContext.request.contextPath}/DeleteProductServlet?productId=<%= id %>&categoryId=<%= request.getParameter("categoryId")%>" method="post">
					                        	<button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete?')">Delete</button>
					                        </form>
					                    </div>
					                   	<%
					                }
				            	}
				            } catch(Exception e){
				            	System.out.println("(product_listings.jsp) Admin Edit and Delete Access Error: " + e + "\n");
				            }
				            %>
		                </div>
		            </div>
		        </div>
				<% 
				}         
		} catch (Exception e) {         
			System.out.println("(product_listings.jsp) Error: " + e + "\n");     
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