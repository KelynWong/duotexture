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
</head>

<body class="d-block w-100 vh-100 bg-img">
	
	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row mx-auto my-5 justify-content-center">
	<%
	try {
		if(request.getParameter("productId")!=null){
			int getProductId = Integer.parseInt(request.getParameter("productId"));
			
			Product product = (Product) request.getAttribute("product");
			
			String productName = product.getName();               
			String productDescription = product.getDescription();
			Double productCostPrice = product.getCostPrice();
			Double productRetailPrice = product.getRetailPrice();
			int productQuantity = product.getQuantity();
			int productCategoryId = product.getCategoryId();
			String productImage = product.getImage();
				%>
				
				<section class="slide col-4">
		          <img src="<%= productImage %>" class="d-block w-100">
		        </section>
		
		        <section class="col-7 px-5 py-3" style="background-color: rgba(0, 0, 0, 0.5)">
		            <p class="text-white custom-font-playfair fs-50 text-center mb-5"><%= productName %></p>
		            <section class="text-white custom-font-mont">
		              <p class=""><%= productDescription %></p>
		              <p class="">Price: <span> $<%= productCostPrice %></span></p>
		              <p class="">Quantity: <%= productQuantity %></p>
		              <div class="row mt-3">
			              <a class="btn btn-success mx-3" href="${pageContext.request.contextPath}/productlistings?categoryId=<%=productCategoryId%>" role="button">Back</a>
			              <form action="${pageContext.request.contextPath}/AddCartServlet?productId=<%=getProductId%>" method="post">
			              	<button class="btn btn-danger">Add to cart</button>
			              </form>
		              </div>
		            </section>
		        </section>
			<% 
		}else{
			%>
			<p>There isn't any product selected.</p>
			<%
		}
	} catch (Exception e) {         
		System.out.println("(product_details.jsp) Error :" + e + "\n");      
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