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
								<option>SGD</option>
								<option>CAD</option>
								<option>HKD</option>
								<option>ISK</option>
								<option>PHP</option>
								<option>DKK</option>
								<option>HUF</option>
								<option>CZK</option>
								<option>GBP</option>
								<option>RON</option>
								<option>SEK</option>
								<option>IDR</option>
								<option>INR</option>
								<option>BRL</option>
								<option>RUB</option>
								<option>HRK</option>
								<option>JPY</option>
								<option>THB</option>
								<option>CHF</option>
								<option>EUR</option>
								<option>MYR</option>
								<option>BGN</option>
								<option>TRY</option>
								<option>CNY</option>
								<option>NOK</option>
								<option>NZD</option>
								<option>ZAR</option>
								<option>USD</option>
								<option>MXN</option>
								<option>AUD</option>
								<option>ILS</option>
								<option>KRW</option>
								<option>PLN</option>
							</select>
							</div>
							<div style="float: left">
								<button type="submit" class="btn btn-success">Convert</button>
							</div>
						</form>
					</div>
			    </div>
			</div>
			<%if(request.getParameter("currency") != null){ 
				String currency = request.getParameter("currency");
			%>
			<span>You are now viewing the price in <%=currency%></span>
			<%}else{ %>
			<span>You are now viewing the price in SGD</span>
			<%} %>
        <hr>
        <% 
		double totalAmount=0;
		%>
        	<div class="mx-auto col-12 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        		<div class="table-responsive-sm">
		      		<%
		        	try{
		        		// display cart
		        		if(request.getAttribute("cartArrayList") != null){
		        			ArrayList<Cart> cartArrayList = (ArrayList<Cart>) request.getAttribute("cartArrayList");
		        			if(cartArrayList.size() != 0){
		        				%>
		        				<table class="table w-auto">
								  <thead class="thead-dark">
								    <tr>
								      <th scope="col">Image</th>
								      <th scope="col">Product Name</th>
								      <th scope="col">Price</th>
								      <th scope="col">Qty</th>
								      <th scope="col"></th>
								      <th scope="col"></th>
								      <th scope="col">Total</th>
								      <th scope="col"></th>
								    </tr>
								  </thead>
								  <tbody>
								<%
								for(int x=0; x<cartArrayList.size(); x++) {
									if(request.getAttribute("rate") != null){
										Double rate = (Double) request.getAttribute("rate");
									%>
									<tr>
								      <th><img src="<%=cartArrayList.get(x).getProductImage()%>" style="height:50px" alt="..."></th>
								      <td><%=cartArrayList.get(x).getProductName()%></td>
								      <td>$<%=String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*rate)%></td>
								      <td><%=cartArrayList.get(x).getQuantity()%></td>
								      <td>
								      	<form action="${pageContext.request.contextPath}/EditCartDecreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
										  	<button type="submit" class="btn btn-danger">-</button>
										</form>
								      </td>
								      <td>
								      	<form action="${pageContext.request.contextPath}/EditCartIncreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
									      	<button type="submit" class="btn btn-success">+</button>
									    </form>
								      </td>
								      <td>$<%=String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate)%></td>
								      <td>
								      	<form action="${pageContext.request.contextPath}/DeleteCartServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
									    	<button type="submit" class="btn btn-danger">Delete</button>
									 	</form>	
									  </td>
								    </tr>
							        <% 
							        	totalAmount += cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate;
									}else{ %>
										<tr>
									      <th scope="row"><img src="<%=cartArrayList.get(x).getProductImage()%>" style="height:50px" alt="..."></th>
									      <td><%=cartArrayList.get(x).getProductName()%></td>
									      <td>$<%=String.format("%.2f", cartArrayList.get(x).getProductCostPrice())%></td>
										  <td><%=cartArrayList.get(x).getQuantity()%></td>
									      <td>
									      	<form action="${pageContext.request.contextPath}/EditCartDecreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
											  	<button type="submit" class="btn btn-danger">-</button>
											</form>
									      </td>
									      <td>
									      	<form action="${pageContext.request.contextPath}/EditCartIncreaseServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
										      	<button type="submit" class="btn btn-success">+</button>
										    </form>
									      </td>
									      <td>$<%=String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity())%></td>
									      <td>
									      	<form action="${pageContext.request.contextPath}/DeleteCartServlet?productId=<%=cartArrayList.get(x).getProductId()%>" method="post">
										    	<button type="submit" class="btn btn-danger">Delete</button>
										 	</form>	
										  </td>
									    </tr>
								        <% 
								        	totalAmount += cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity();
									}
								} 	
		        			}else{ %>
		        				<p>You currently don't have any items in your cart.</p>
		        				<hr>
		        		<%	}
		        		}
		        	} catch(Exception e){
			        	System.out.println("(cart.jsp) Message Error: " + e + "\n");
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