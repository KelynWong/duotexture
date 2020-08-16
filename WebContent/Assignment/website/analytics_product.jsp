<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.* , javabeans.*"%>
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
		System.out.println("(analytics.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file="components/navigation.jsp" %>

    <section class="col-12 p-5 row">
    
      <!-- analytics -->
      <section class="mx-auto col-10 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - A n a l y t i c s</p>
        <hr>
        
        <section class="row">
        	<!-- tablist -->
	        <div class="nav flex-column nav-pills col-3" id="v-pills-tab" role="tablist">
			  <a class="nav-link" id="v-pills-customer-tab" href="${pageContext.request.contextPath}/analyticscustomer?page=1">Customer</a>
			  <a class="nav-link active" id="v-pills-product-tab" href="#" role="tab">Product</a>
			  <a class="nav-link" id="v-pills-order-tab" href="${pageContext.request.contextPath}/analyticsorder?orderPageNumber=1&top10PageNumber=1&purchaseLogPageNumber=1">Order</a>
			</div>
			
			<!-- product content -->
			<div class="tab-content col-8" id="v-pills-tabContent" style="margin:auto">
			  <div class="tab-pane fade show active" id="v-pills-product" role="tabpanel">
			  	<h5>Product Analysis</h5>
			  	
			  	<!-- All Product Analysis -->
			  	<h6 class="text-info mt-3">All Products Analysis</h6>
			  	<!-- search and add function -->
		        <form class="form-inline col-12 justify-content-left my-4 p-0" action="${pageContext.request.contextPath}/analyticsproduct" method="get">
		        	<input class="form-control" name="page" type="hidden" value="1">
		        	<input class="form-control" name="bestLeastPage" type="hidden" value="1">
		            <input class="form-control col-5" name="keywordInput" type="search" value="<%=request.getAttribute("keywordInput")%>" placeholder="Search">
		            <select class="form-control mx-2" id="orderInput" name="orderInput">
		    			<%
		    			String order = (String) request.getAttribute("orderInput");
		    			ArrayList<Dropdown> orderArrayList = new ArrayList<Dropdown>();
		    			orderArrayList.add(new Dropdown("ASCProdId", "Ascending Product Id"));
		    			orderArrayList.add(new Dropdown("DESCProdId", "Descending Product Id"));
		    			orderArrayList.add(new Dropdown("ASCLowStock", "Products with Low Stock"));
		    			orderArrayList.add(new Dropdown("ASCModerateStock", "Products with Moderate Stock"));
		    			orderArrayList.add(new Dropdown("ASCHighStock", "Products with High Stock"));
		    			
		    			for(int x=0; x<orderArrayList.size(); x++){
		    				String orderType = orderArrayList.get(x).getOrderType();
		    				String orderDisplay = orderArrayList.get(x).getOrderDisplay();
		    				
		    				if(order.equals(orderType)){
		    					%><option selected value="<%=orderType%>"><%=orderDisplay%></option><%
		    				} else {
		    					%><option value="<%=orderType%>"><%=orderDisplay%></option><%
		    				}
		    			}
		    			%>
	    			</select>
		            <button class="btn btn-outline-danger mx-2 search-btn" type="submit">Render</button>
		        </form>
			  	  <!-- List of Products -->
				  <table class="table table-bordered">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">Product Id</th>
				      <th scope="col">Image</th>
				      <th scope="col">Name</th>
				      <th scope="col">Cost Price</th>
				      <th scope="col">Retail Price</th>
				      <th scope="col">Quantity</th>
				      <th scope="col">Stock Status</th>
				      <th scope="col">CategoryId</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<Product> productsArrayList = (ArrayList<Product>) request.getAttribute("productsArrayList");
				  
				  for(int x=0; x<productsArrayList.size(); x++) {
					  int productQuantity = productsArrayList.get(x).getQuantity();
				  %>
				    <tr>
				      <th scope="row"><%=productsArrayList.get(x).getProductId()%></th>
				      <td><img src="<%=productsArrayList.get(x).getImage()%>" style="height:50px" alt="Product Image"></td>
				      <td><%=productsArrayList.get(x).getName()%></td>
				      <td>$<%=productsArrayList.get(x).getCostPrice()%></td>
				      <td>$<%=productsArrayList.get(x).getRetailPrice()%></td>
				      <td><%=productQuantity%></td>
				      <td><% if(productQuantity<=20){
				    	  %><span class="text-danger">Low</span><%
				    	  }else if(productQuantity<=50){%>
				    	  <span class="text-warning">Moderate</span>
				    	  <%}else{%>
				    	  <span class="text-success">High</span>
				    	  <%}%>
			    	  </td>
				      <td><%=productsArrayList.get(x).getCategoryId()%></td>
				    </tr>
					<%}%>
				  </tbody>
				</table>
				
				<!-- Pagination -->
				<nav>
					<ul class="pagination justify-content-end">
						
				  <% 
				  try {
					  if(request.getParameter("page")!=null) {
						  if(productsArrayList.size()==0){
							  %>
							  </ul>
							  <p class="mb-5"'>There are no records found.</p>
							  <%
						  } else {
							  int pageNumber = Integer.parseInt(request.getParameter("page"));
							  String keywordInput = (String) request.getAttribute("keywordInput");
							  String orderInput = (String) request.getAttribute("orderInput");
							  Boolean maxRecord = false;
							  
							  if(request.getParameter("maxRecord")!=null) {
							    maxRecord = true;
							  }
	
							  if(pageNumber!=1){
							  %>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?page=<%=pageNumber-1%>&keywordInput=<%=keywordInput%>&orderInput=<%=orderInput%>&bestLeastPage=1">Previous</a></li>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?page=<%=pageNumber-1%>&keywordInput=<%=keywordInput%>&orderInput=<%=orderInput%>&bestLeastPage=1"><%=pageNumber-1%></a></li>
							  <%
							  }
				  			%>
						    <li class="page-item active"><a class="page-link" href="#"><%=pageNumber%></a></li>
						    <%
						    if(maxRecord==false){
						    %>
						    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?page=<%=pageNumber+1%>&keywordInput=<%=keywordInput%>&orderInput=<%=orderInput%>&bestLeastPage=1"><%=pageNumber+1%></a></li>
					  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?page=<%=pageNumber+1%>&keywordInput=<%=keywordInput%>&orderInput=<%=orderInput%>&bestLeastPage=1">Next</a></li>
					  		<%}%>
				  			<%
						  }
					  } else {
						  System.out.println("(analytics-product.jsp) Error: Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics-product.jsp) Error: " + e + "\n");      
				  }
				  %>
					</ul>
				</nav>
				
				<!-- Best and Least Selling Product Analysis by Purchases -->
			  	<h6 class="text-info mt-3">Best and Least Selling Product Analysis by Purchases</h6>
			  	<!-- search and add function -->
		        <form class="form-inline col-12 justify-content-left my-4 p-0" action="${pageContext.request.contextPath}/analyticsproduct" method="get">
		        	<input class="form-control" name="page" type="hidden" value="1">
		        	<input class="form-control" name="bestLeastPage" type="hidden" value="1">
		            <input class="form-control col-6" name="bestLeastKeywordInput" type="search" value="<%=request.getAttribute("bestLeastKeywordInput")%>" placeholder="Search">
		            <select class="form-control mx-2" id="bestLeastOrderInput" name="bestLeastOrderInput">
		    			<%
		    			String bestLeastOrder = (String) request.getAttribute("bestLeastOrderInput");
		    			ArrayList<Dropdown> bestLeastOrderArrayList = new ArrayList<Dropdown>();
		    			bestLeastOrderArrayList.add(new Dropdown("DESCBestLeast", "Best to Least Selling Product"));
		    			bestLeastOrderArrayList.add(new Dropdown("ASCBestLeast", "Least to Best Selling Product"));
		    			bestLeastOrderArrayList.add(new Dropdown("DESCProfit", "Highest to Lowest Profit"));
		    			bestLeastOrderArrayList.add(new Dropdown("ASCProfit", "Lowest to Highest Profit"));

		    			for(int x=0; x<bestLeastOrderArrayList.size(); x++){
		    				String bestLeastOrderType = bestLeastOrderArrayList.get(x).getOrderType();
		    				String bestLeastOrderDisplay = bestLeastOrderArrayList.get(x).getOrderDisplay();
		    				
		    				if(bestLeastOrder.equals(bestLeastOrderType)){
		    					%><option selected value="<%=bestLeastOrderType%>"><%=bestLeastOrderDisplay%></option><%
		    				} else {
		    					%><option value="<%=bestLeastOrderType%>"><%=bestLeastOrderDisplay%></option><%
		    				}
		    			}
		    			%>
	    			</select>
		            <button class="btn btn-outline-danger mx-2 search-btn" type="submit">Render</button>
		        </form>
			  	  <!-- List of Purchases -->
				  <table class="table table-bordered">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">Product Id</th>
				      <th scope="col">Image</th>
				      <th scope="col">Name</th>
				      <th scope="col">Cost Price</th>
				      <th scope="col">Retail Price</th>
				      <th scope="col">Quantity Sold</th>
				      <th scope="col">Profit</th>
				      <th scope="col">CategoryId</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<Purchase> bestLeastProductsArrayList = (ArrayList<Purchase>) request.getAttribute("bestLeastProductsArrayList");
				  
				  for(int x=0; x<bestLeastProductsArrayList.size(); x++) {
				  %>
				    <tr>
				      <th scope="row"><%=bestLeastProductsArrayList.get(x).getProductId()%></th>
				      <td><img src="<%=bestLeastProductsArrayList.get(x).getProductImage()%>" style="height:50px" alt="Product Image"></td>
				      <td><%=bestLeastProductsArrayList.get(x).getProductName()%></td>
				      <td>$<%=bestLeastProductsArrayList.get(x).getProductCostPrice()%></td>
				      <td>$<%=bestLeastProductsArrayList.get(x).getProductRetailPrice()%></td>
				      <td><%=bestLeastProductsArrayList.get(x).getQuantity()%></td>
				      <td>$<%=bestLeastProductsArrayList.get(x).getProductProfit()%></td>
				      <td><%=bestLeastProductsArrayList.get(x).getProductCategoryId()%></td>
				    </tr>
					<%}%>
				  </tbody>
				</table>
				
				<!-- Pagination -->
				<nav>
					<ul class="pagination justify-content-end">
						
				  <% 
				  try {
					  if(request.getParameter("page")!=null) {
						  if(bestLeastProductsArrayList.size()==0){
							  %>
							  </ul>
							  <p class="mb-5">There are no records found.</p>
							  <%
						  } else {
							  int bestLeastPageNumber = Integer.parseInt(request.getParameter("bestLeastPage"));
							  String bestLeastKeywordInput = (String) request.getAttribute("bestLeastKeywordInput");
							  String bestLeastOrderInput = (String) request.getAttribute("bestLeastOrderInput");
							  Boolean bestLeastMaxRecord = false;
							  
							  if(request.getParameter("bestLeastMaxRecord")!=null) {
								  bestLeastMaxRecord = true;
							  }
	
							  if(bestLeastPageNumber!=1){
							  %>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?bestLeastPage=<%=bestLeastPageNumber-1%>&bestLeastKeywordInput=<%=bestLeastKeywordInput%>&bestLeastOrderInput=<%=bestLeastOrderInput%>&page=1">Previous</a></li>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?bestLeastPage=<%=bestLeastPageNumber-1%>&bestLeastKeywordInput=<%=bestLeastKeywordInput%>&bestLeastOrderInput=<%=bestLeastOrderInput%>&page=1"><%=bestLeastPageNumber-1%></a></li>
							  <%
							  }
				  			%>
						    <li class="page-item active"><a class="page-link" href="#"><%=bestLeastPageNumber%></a></li>
						    <%
						    if(bestLeastMaxRecord==false){
						    %>
						    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?bestLeastPage=<%=bestLeastPageNumber-1%>&bestLeastKeywordInput=<%=bestLeastKeywordInput%>&bestLeastOrderInput=<%=bestLeastOrderInput%>&page=1"><%=bestLeastPageNumber+1%></a></li>
					  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?bestLeastPage=<%=bestLeastPageNumber-1%>&bestLeastKeywordInput=<%=bestLeastKeywordInput%>&bestLeastOrderInput=<%=bestLeastOrderInput%>&page=1">Next</a></li>
					  		<%}%>
				  			<%
						  }
					  } else {
						  System.out.println("(analytics-product.jsp) Error: Best Least Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics-product.jsp) Error: " + e + "\n");      
				  }
				  %>
					</ul>
				</nav>
				
			  </div>
			</div>
        </section>
        
   	  </section>
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
    
    $("#inputCategoryImageUrl").change(function() {
    	readFile(this, "#previewCategoryImageUrl", "previewCategoryImageUrlLabel")
    })
    
    function validateFileType(){
        var fileName = document.getElementById("inputCategoryImageUrl").value;
        var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="jpg" || extFile=="jpeg" || extFile=="png"){

        }else{
        	document.getElementById("inputCategoryImageUrl").value = "";
            alert("Only jpg/jpeg and png files are allowed!");
        }   
    }
    
    </script>
    <!--===============================================================================================-->
</body>
</html>