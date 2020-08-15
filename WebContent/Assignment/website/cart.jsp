<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*, java.util.* , javabeans.*" %>
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
		// validate if user executing request is member
		if(session.getAttribute("accountType")!=null){
			if(!session.getAttribute("accountType").equals("member")){
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
		System.out.println("(cart.jsp) Member Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row">
      <div class="mx-auto col-10 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
      	<div class="d-flex flex-row">
			<div class="col-md-6 custom-font-playfair fs-15">D u o - T e x t u r e - C a r t</div>
			    <div class="col-md-6">
			    	<div class="float-right">
				    	<form action="${pageContext.request.contextPath}/cart">
				    	<div style="float: left">
							<select class="form-control" name="currency">
								<%
								ArrayList<Rate> ratesArrayList = (ArrayList<Rate>) request.getAttribute("ratesArrayList");
								String currency = (String) request.getAttribute("currency");
								
								for(int x=0; x<ratesArrayList.size(); x++){
									if(ratesArrayList.get(x).getRateType().equals(currency)){
										%>
										<option selected><%=ratesArrayList.get(x).getRateType()%></option>
										<%
									}else{
										%>
										<option><%=ratesArrayList.get(x).getRateType()%></option>
									<%}
								}%>
							</select>
							</div>
							<div class="ml-2" style="float: left">
								<button type="submit" class="btn btn-info">Convert</button>
							</div>
						</form>
					</div>
			    </div>
			</div>
        <hr>
        
        	<div class="mx-auto col-12 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        		<div class="table-responsive-sm">
		      		<%
		      		double totalAmount=0;
		      		
		        	try{
		        		// display cart
		        		if(request.getAttribute("cartArrayList") != null){
		        			ArrayList<Cart> cartArrayList = (ArrayList<Cart>) request.getAttribute("cartArrayList");
		        			if(cartArrayList.size() == 0){
								%>
								<p>You currently do not have any items in your cart.</p>
								<a class="btn btn-primary" href="${pageContext.request.contextPath}/categories" role="button">Explore</a>
								<%
							} else {
		        				%>
		        				<p>You are now viewing the price in <span class="text-info"><%=currency%></span></p>
		        				<table class="table table-bordered">
								  <thead class="table-dark">
								    <tr>
								      <th scope="col">Image</th>
								      <th scope="col">Product Name</th>
								      <th scope="col">Price</th>
								      <th scope="col">Quantity</th>
								      <th scope="col">Total</th>
								      <th scope="col" style="min-width: 10vw">Action</th>
								    </tr>
								  </thead>
								  <tbody>
								<%
								for(int x=0; x<cartArrayList.size(); x++) {
									Double rate = (Double) request.getAttribute("rate");
									String productImage = cartArrayList.get(x).getProductImage();
									String productName = cartArrayList.get(x).getProductName();
									String productCost = String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*rate);
									int productQuantity = cartArrayList.get(x).getQuantity();
									String productTotalCost = String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate);
									%>
									<tr>
								      <th><img src="<%=productImage%>" style="height:50px" alt="Product Image"></th>
								      <td><%=productName%></td>
								      <td>$<%=productCost%></td>
								      <td><%=productQuantity%></td>
								      <td>$<%=productTotalCost%></td>
								      <td class="row justify-content-center" style="border-width:0px">
								      	<form class="row col-3 p-0 m-0" action="${pageContext.request.contextPath}/EditCartDecreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
										  	<button type="submit" class="btn btn-warning">-</button>
										</form>
										<form class="row col-3 p-0 m-0" action="${pageContext.request.contextPath}/EditCartIncreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
									      	<button type="submit" class="btn btn-info">+</button>
									    </form>
									    <form class="row col-3 p-0 m-0" action="${pageContext.request.contextPath}/DeleteCartServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
									    	<button type="submit" class="btn btn-danger">Delete</button>
									 	</form>	
								      </td>
								    </tr>
							        <% 
							        	totalAmount += cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate;
								} 
								%>
			        	</tbody>
					</table>
				</div>
	      		<p>Total (without GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount)%></b></span></p>
	      		<p>GST (7%): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*0.07)%></b></span></p>
	      		<p>Total (with GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*1.07)%></b></span></p>
	      		
	      		<%
	        	try{
	        		// display different error message dependant on request success and failure
	        		if(request.getParameter("cartEdit")!=null){
		        		String errorMessage = "";
		            	String cartEdit = request.getParameter("cartEdit");
		            	
		            	if(cartEdit.equals("fail")){
		            		errorMessage = "Attempt to edit failed. Please try again.";
		            		%>
					        <small class="text-danger"><%= errorMessage %><br><br></small>
					        <%
		            	}else if(cartEdit.equals("success")){
		            		errorMessage = "Successfully Edited.";
		            		%>
					        <small class="text-success"><%= errorMessage %><br><br></small>
					        <%
		            	}
	        		}
	        	} catch(Exception e){
		        	System.out.println("(cart.jsp) Message Error: " + e + "\n");
		        }
		        %>
		        <form action="${pageContext.request.contextPath}/checkout">
	      			<button type="submit" class="btn btn-primary">Checkout</button>
	      		</form>
	      		<%
	       			}
	       		}
	       	} catch(Exception e){
	        	System.out.println("(cart.jsp) Message Error: " + e + "\n");
	        }
	        %>
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