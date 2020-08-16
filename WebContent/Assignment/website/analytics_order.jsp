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
		System.out.println("(analytics_order.jsp) Admin Validation Error: " + e + "\n");
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
			  <a class="nav-link" id="v-pills-product-tab" href="${pageContext.request.contextPath}/analyticsproduct?page=1&bestLeastPage=1" role="tab">Product</a>
			  <a class="nav-link active" id="v-pills-order-tab" href="#">Order</a>
			</div>
			
			<!-- order content -->
			<div class="tab-content col-8" id="v-pills-tabContent" style="margin:auto">
			  <div class="tab-pane fade show active" id="v-pills-product" role="tabpanel">
			  	<h5>Order Analysis</h5>
			  	
			  	<!-- All Orders Analysis -->
			  	<h6 class="text-info mt-3">All Orders Analysis</h6>
			  	<!-- search and add function -->
		        <form class="form-inline col-12 justify-content-left my-4 p-0" action="${pageContext.request.contextPath}/analyticsorder" method="get">
		        	<input class="form-control" name="orderPage" type="hidden" value="1">
		        	<input class="form-control" name="top10Page" type="hidden" value="1">
		        	<input class="form-control" name="purchaseLogPage" type="hidden" value="1">
		            <input class="form-control col-5" name="orderKeywordInput" type="search" value="<%=request.getAttribute("orderKeywordInput")%>" placeholder="Search">
		            <select class="form-control mx-2" id="orderOrderInput" name="orderOrderInput">
		    			<%
		    			String orderOrder = (String) request.getAttribute("orderOrderInput");
		    			ArrayList<Dropdown> orderOrderArrayList = new ArrayList<Dropdown>();
		    			orderOrderArrayList.add(new Dropdown("ASCUserId", "Ascending User Id"));
		    			orderOrderArrayList.add(new Dropdown("DESCUserId", "Descending User Id"));
		    			
		    			for(int x=0; x<orderOrderArrayList.size(); x++){
		    				String orderType = orderOrderArrayList.get(x).getOrderType();
		    				String orderDisplay = orderOrderArrayList.get(x).getOrderDisplay();
		    				
		    				if(orderOrder.equals(orderType)){
		    					%><option selected value="<%=orderType%>"><%=orderDisplay%></option><%
		    				} else {
		    					%><option value="<%=orderType%>"><%=orderDisplay%></option><%
		    				}
		    			}
		    			%>
	    			</select>
		            <button class="btn btn-outline-danger mx-2 search-btn" type="submit">Render</button>
		        </form>
			  	  <!-- List of Orders -->
				  <table class="table table-bordered">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">User Id</th>
				      <th scope="col">Full Name</th>
				      <th scope="col">Product Id</th>
				      <th scope="col">Image</th>
				      <th scope="col">Product Name</th>
				      <th scope="col">Cost Price</th>
				      <th scope="col">Total Quantity</th>
				      <th scope="col">Date of Order</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<AnalyticsOrder> ordersArrayList = (ArrayList<AnalyticsOrder>) request.getAttribute("ordersArrayList");
				  
				  for(int x=0; x<ordersArrayList.size(); x++) {
				  %>
				    <tr>
				      <th scope="row"><%=ordersArrayList.get(x).getUserId()%></th>
				      <td><%=ordersArrayList.get(x).getFullName()%></td>
				      <td><%=ordersArrayList.get(x).getProductId()%></td>
				      <td><img src="<%=ordersArrayList.get(x).getProductImage()%>" style="height:50px" alt="Product Image"></td>
				      <td><%=ordersArrayList.get(x).getProductName()%></td>
				      <td>$<%=ordersArrayList.get(x).getProductCostPrice()%></td>
				      <td>$<%=ordersArrayList.get(x).getProductQuantity()%></td>
				      <td><%=ordersArrayList.get(x).getDateTime()%></td>
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
						  if(ordersArrayList.size()==0){
							  %>
							  </ul>
							  <p class="mb-5">There are no records found.</p>
							  <%
						  } else {
							  int orderPageNumber = Integer.parseInt(request.getParameter("orderPage"));
							  String orderKeywordInput = (String) request.getAttribute("orderKeywordInput");
							  String orderOrderInput = (String) request.getAttribute("orderOrderInput");
							  Boolean orderMaxRecord = false;
							  
							  if(request.getParameter("orderMaxRecord")!=null) {
								  orderMaxRecord = true;
							  }
	
							  if(orderPageNumber!=1){
							  %>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?orderPage=<%=orderPageNumber-1%>&orderKeywordInput=<%=orderKeywordInput%>&orderOrderInput=<%=orderOrderInput%>&top10Page=1&purchaseLogPage=1">Previous</a></li>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?orderPage=<%=orderPageNumber-1%>&orderKeywordInput=<%=orderKeywordInput%>&orderOrderInput=<%=orderOrderInput%>&top10Page=1&purchaseLogPage=1"><%=orderPageNumber-1%></a></li>
							  <%
							  }
				  			%>
						    <li class="page-item active"><a class="page-link" href="#"><%=orderPageNumber%></a></li>
						    <%
						    if(orderMaxRecord==false){
						    %>
						    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?orderPage=<%=orderPageNumber-1%>&orderKeywordInput=<%=orderKeywordInput%>&orderOrderInput=<%=orderOrderInput%>&top10Page=1&purchaseLogPage=1"><%=orderPageNumber+1%></a></li>
					  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?orderPage=<%=orderPageNumber-1%>&orderKeywordInput=<%=orderKeywordInput%>&orderOrderInput=<%=orderOrderInput%>&top10Page=1&purchaseLogPage=1">Next</a></li>
					  		<%}%>
				  			<%
						  }
					  } else {
						  System.out.println("(analytics_order.jsp) Error: Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics_order.jsp) Error: " + e + "\n");      
				  }
				  %>
					</ul>
				</nav>
			  	
			  	<!-- Top 10 Analysis -->
			  	<h6 class="text-info mt-3">Top 10 Customers by Purchase Value Analysis</h6>
			  	<!-- search and add function -->
		        <form class="form-inline col-12 justify-content-left my-4 p-0" action="${pageContext.request.contextPath}/analyticsorder" method="get">
		        	<input class="form-control" name="orderPage" type="hidden" value="1">
		        	<input class="form-control" name="top10Page" type="hidden" value="1">
		        	<input class="form-control" name="purchaseLogPage" type="hidden" value="1">
		            <input class="form-control col-5" name="top10KeywordInput" type="search" value="<%=request.getAttribute("top10KeywordInput")%>" placeholder="Search">
		            <select class="form-control mx-2" id="top10OrderInput" name="top10OrderInput">
		    			<%
		    			String top10Order = (String) request.getAttribute("top10OrderInput");
		    			ArrayList<Dropdown> top10OrderArrayList = new ArrayList<Dropdown>();
		    			top10OrderArrayList.add(new Dropdown("DESCProfit", "Highest to Lowest Profit"));
		    			top10OrderArrayList.add(new Dropdown("ASCProfit", "Lowest to Highest Profit"));
		    			
		    			for(int x=0; x<top10OrderArrayList.size(); x++){
		    				String orderType = top10OrderArrayList.get(x).getOrderType();
		    				String orderDisplay = top10OrderArrayList.get(x).getOrderDisplay();
		    				
		    				if(top10Order.equals(orderType)){
		    					%><option selected value="<%=orderType%>"><%=orderDisplay%></option><%
		    				} else {
		    					%><option value="<%=orderType%>"><%=orderDisplay%></option><%
		    				}
		    			}
		    			%>
	    			</select>
		            <button class="btn btn-outline-danger mx-2 search-btn" type="submit">Render</button>
		        </form>
			  	  <!-- List of Orders -->
				  <table class="table table-bordered">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">User Id</th>
				      <th scope="col">Full Name</th>
				      <th scope="col">Total Purchased Value</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<AnalyticsOrder> top10ArrayList = (ArrayList<AnalyticsOrder>) request.getAttribute("top10ArrayList");
				  
				  for(int x=0; x<top10ArrayList.size(); x++) {
				  %>
				    <tr>
				      <th scope="row"><%=top10ArrayList.get(x).getUserId()%></th>
				      <td><%=top10ArrayList.get(x).getFullName()%></td>
				      <td>$<%=top10ArrayList.get(x).getTotalProfit()%></td>
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
						  if(top10ArrayList.size()==0){
							  %>
							  </ul>
							  <p class="mb-5">There are no records found.</p>
							  <%
						  } else {
							  int top10PageNumber = Integer.parseInt(request.getParameter("top10Page"));
							  String top10KeywordInput = (String) request.getAttribute("top10KeywordInput");
							  String top10OrderInput = (String) request.getAttribute("top10OrderInput");
							  Boolean top10MaxRecord = false;
								
							  if(request.getParameter("top10MaxRecord")!=null) {
								  top10MaxRecord = true;
							  }
	
							  if(top10PageNumber!=1){
							  %>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?top10Page=<%=top10PageNumber-1%>&top10KeywordInput=<%=top10KeywordInput%>&top10OrderInput=<%=top10OrderInput%>&orderPage=1&purchaseLogPage=1">Previous</a></li>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?top10Page=<%=top10PageNumber-1%>&top10KeywordInput=<%=top10KeywordInput%>&top10OrderInput=<%=top10OrderInput%>&orderPage=1&purchaseLogPage=1"><%=top10PageNumber-1%></a></li>
							  <%
							  }
				  			%>
						    <li class="page-item active"><a class="page-link" href="#"><%=top10PageNumber%></a></li>
						    <%
						    if(top10MaxRecord==false){
						    %>
						    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?top10Page=<%=top10PageNumber-1%>&top10KeywordInput=<%=top10KeywordInput%>&top10OrderInput=<%=top10OrderInput%>&orderPage=1&purchaseLogPage=1"><%=top10PageNumber+1%></a></li>
					  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?top10Page=<%=top10PageNumber-1%>&top10KeywordInput=<%=top10KeywordInput%>&top10OrderInput=<%=top10OrderInput%>&orderPage=1&purchaseLogPage=1">Next</a></li>
					  		<%}%>
				  			<%
						  }
					  } else {
						  System.out.println("(analytics_order.jsp) Error: Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics_order.jsp) Error: " + e + "\n");      
				  }
				  %>
					</ul>
				</nav>
				
				<!-- Purchases Log Analysis -->
			  	<h6 class="text-info mt-3">Purchases Log Analysis</h6>
			  	<!-- search and add function -->
		        <form class="form-inline col-12 justify-content-left my-4 p-0" action="${pageContext.request.contextPath}/analyticsorder" method="get">
		        	<input class="form-control" name="orderPage" type="hidden" value="1">
		        	<input class="form-control" name="top10Page" type="hidden" value="1">
		        	<input class="form-control" name="purchaseLogPage" type="hidden" value="1">
		            <input class="form-control col-5" name="purchaseLogKeywordInput" type="search" value="<%=request.getAttribute("purchaseLogKeywordInput")%>" placeholder="Search">
		            <select class="form-control mx-2" id="purchaseLogOrderInput" name="purchaseLogOrderInput">
		    			<%
		    			String purchaseLogOrder = (String) request.getAttribute("purchaseLogOrderInput");
		    			ArrayList<Dropdown> purchaseLogOrderArrayList = new ArrayList<Dropdown>();
		    			purchaseLogOrderArrayList.add(new Dropdown("ASCUserId", "Ascending User Id"));
		    			purchaseLogOrderArrayList.add(new Dropdown("DESCUserId", "Descending User Id"));
		    			
		    			for(int x=0; x<purchaseLogOrderArrayList.size(); x++){
		    				String orderType = purchaseLogOrderArrayList.get(x).getOrderType();
		    				String orderDisplay = purchaseLogOrderArrayList.get(x).getOrderDisplay();
		    				
		    				if(purchaseLogOrder.equals(orderType)){
		    					%><option selected value="<%=orderType%>"><%=orderDisplay%></option><%
		    				} else {
		    					%><option value="<%=orderType%>"><%=orderDisplay%></option><%
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
				      <th scope="col">User Id</th>
				      <th scope="col">Full Name</th>
				      <th scope="col">Product Id</th>
				      <th scope="col">Image</th>
				      <th scope="col">Product Name</th>
				      <th scope="col">Cost Price</th>
				      <th scope="col">Total Quantity</th>
				      <th scope="col">Date & Time of Purchase</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<AnalyticsOrder> purchaseLogArrayList = (ArrayList<AnalyticsOrder>) request.getAttribute("purchaseLogArrayList");
				  
				  for(int x=0; x<purchaseLogArrayList.size(); x++) {
				  %>
				    <tr>
				      <th scope="row"><%=purchaseLogArrayList.get(x).getUserId()%></th>
				      <td><%=purchaseLogArrayList.get(x).getFullName()%></td>
				      <td><%=purchaseLogArrayList.get(x).getProductId()%></td>
				      <td><img src="<%=purchaseLogArrayList.get(x).getProductImage()%>" style="height:50px" alt="Product Image"></td>
				      <td><%=purchaseLogArrayList.get(x).getProductName()%></td>
				      <td>$<%=purchaseLogArrayList.get(x).getProductCostPrice()%></td>
				      <td><%=purchaseLogArrayList.get(x).getTotalQuantity()%></td>
				      <td><%=purchaseLogArrayList.get(x).getDateTime()%></td>
				      
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
						  if(purchaseLogArrayList.size()==0){
							  %>
							  </ul>
							  <p class="mb-5">There are no records found.</p>
							  <%
						  } else {
							  int purchaseLogPageNumber = Integer.parseInt(request.getParameter("purchaseLogPage"));
							  String purchaseLogKeywordInput = (String) request.getAttribute("purchaseLogKeywordInput");
							  String purchaseLogOrderInput = (String) request.getAttribute("purchaseLogOrderInput");
							  Boolean purchaseLogMaxRecord = false;
							  
							  if(request.getParameter("purchaseLogMaxRecord")!=null) {
								  purchaseLogMaxRecord = true;
							  }
	
							  if(purchaseLogPageNumber!=1){
							  %>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?purchaseLogPage=<%=purchaseLogPageNumber-1%>&purchaseLogKeywordInput=<%=purchaseLogKeywordInput%>&purchaseLogOrderInput=<%=purchaseLogOrderInput%>&orderPage=1&top10Page=1">Previous</a></li>
							  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?purchaseLogPage=<%=purchaseLogPageNumber-1%>&purchaseLogKeywordInput=<%=purchaseLogKeywordInput%>&purchaseLogOrderInput=<%=purchaseLogOrderInput%>&orderPage=1&top10Page=1"><%=purchaseLogPageNumber-1%></a></li>
							  <%
							  }
				  			%>
						    <li class="page-item active"><a class="page-link" href="#"><%=purchaseLogPageNumber%></a></li>
						    <%
						    if(purchaseLogMaxRecord==false){
						    %>
						    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?purchaseLogPage=<%=purchaseLogPageNumber-1%>&purchaseLogKeywordInput=<%=purchaseLogKeywordInput%>&purchaseLogOrderInput=<%=purchaseLogOrderInput%>&orderPage=1&top10Page=1"><%=purchaseLogPageNumber+1%></a></li>
					  		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticsproduct?purchaseLogPage=<%=purchaseLogPageNumber-1%>&purchaseLogKeywordInput=<%=purchaseLogKeywordInput%>&purchaseLogOrderInput=<%=purchaseLogOrderInput%>&orderPage=1&top10Page=1">Next</a></li>
					  		<%}%>
				  			<%
						  }
					  } else {
						  System.out.println("(analytics_order.jsp) Error: Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics_order.jsp) Error: " + e + "\n");      
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