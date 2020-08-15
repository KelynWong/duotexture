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
		System.out.println("(checkout.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<% //double totalAmount = 0; %>
<body class="d-block w-100 vh-100 bg-img">
	
	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

	<div class="row" style="max-width: 99vw; min-height: 100vh">
		<section class="col-7 ml-5 mt-5">
       
	    <!-- checkout form -->
		<div class="mx-auto col-9 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
			<div class="row">
			    <div class="col-md-9 custom-font-playfair fs-15">D u o - T e x t u r e - C h e c k o u t</div>
			    <div class="col-md-3 text-right">
			    <a class="btn btn-primary" href="${pageContext.request.contextPath}/cart" role="button">Back</a>
			    </div>
			</div>
	     	<hr>
	     	<%if(request.getParameter("cards").equals("noCard")){ %>
	     	<form action="${pageContext.request.contextPath}/AddPaymentServlet" method="post">
		     	<div class="form-row">
			     	<div class="form-group col-md-12">
						<h3>Payment</h3>
						<div class="row" style="margin-left: 3px">
							<label for="fname">Accepted Cards</label>
							<div class="icon-container mx-3">
								<i class="fa fa-cc-visa" style="color:navy;"><!-- Visa --></i>
								<i class="fa fa-cc-amex" style="color:blue;"></i>
								<i class="fa fa-cc-mastercard" style="color:red;"></i>
								<i class="fa fa-cc-discover" style="color:orange;"></i>
							</div>
						</div>
					</div>
							        
					<div class="form-group col-md-12">
						<label for="cname">Name on Card</label>
						<input type="text" class="form-control" id="cardName" name="cardName" placeholder="John More Doe" required>
					</div>
							        
					<div class="form-group col-md-12">
						<label for="ccnum">Credit card number</label>
						<input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="1111222233334444" required>
					</div>
							        
					<div class="form-group col-md-6">
						<label for="expmonth">Expiry Month</label>
						<input type="text" class="form-control" id="expmonth" name="expMonth" placeholder="04" minlength="2" maxlength="2" required>
					</div>
							
					<div class="form-group col-md-6">
						<label for="expyear">Expiry Year</label>
						<input type="text" class="form-control" id="expyear" name="expYear" placeholder="2018" minlength="4" maxlength="4" required>
					</div>
							            
					<div class="form-group col-md-12">
						<label for="cvv">CVV</label>
						<input type="text" class="form-control" id="cvv" name="cvv" placeholder="352" minlength="3" maxlength="4" required>
					</div>           
				</div>
				<button type="submit" class="btn btn-outline-success">Pay</button>
			</form>
	      </div>
	      <%}else{ 
	    	  	Card card = (Card) request.getAttribute("card");
	    	  
	    	  	String cardName = card.getCardOwner();               
			  	String cardNumber = card.getCardNumber();
				int cardExpiryMonth = card.getExpiryMonth();
				int cardExpiryYear = card.getExpiryYear();
				int cardCvv = card.getCvv();
	      %>
	     	<form action="${pageContext.request.contextPath}/AddPaymentServlet" method="post">
		     	<div class="form-row">
			     	<div class="form-group col-md-12">
						<h3>Payment</h3>
						<label for="fname">Accepted Cards</label>
						<div class="icon-container">
							<i class="fa fa-cc-visa" style="color:navy;"></i>
							<i class="fa fa-cc-amex" style="color:blue;"></i>
							<i class="fa fa-cc-mastercard" style="color:red;"></i>
							<i class="fa fa-cc-discover" style="color:orange;"></i>
						</div>
					</div>
							        
					<div class="form-group col-md-12">
						<label for="cname">Name on Card</label>
						<input type="text" class="form-control" id="cardName" name="cardName" placeholder="John More Doe" value="<%=cardName%>" required>
					</div>
							        
					<div class="form-group col-md-12">
						<label for="ccnum">Credit card number</label>
						<input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="1111222233334444" value="<%=cardNumber%>" required>
					</div>
							        
					<div class="form-group col-md-6">
						<label for="expmonth">Expiry Month</label>
						<input type="text" class="form-control" id="expmonth" name="expMonth" placeholder="04" minlength="2" maxlength="2" value="<%=cardExpiryMonth%>" required>
					</div>
							
					<div class="form-group col-md-6">
						<label for="expyear">Expiry Year</label>
						<input type="text" class="form-control" id="expyear" name="expYear" placeholder="2018" minlength="4" maxlength="4" value="<%=cardExpiryYear%>" required>
					</div>
							            
					<div class="form-group col-md-12">
						<label for="cvv">CVV</label>
						<input type="text" class="form-control" id="cvv" name="cvv" placeholder="352" minlength="3" maxlength="3" value="<%=cardCvv%>" required>
					</div>           
				</div>
				<button type="submit" class="btn btn-outline-success">Pay</button>
			</form>
	      </div>
	      <%} %>
	    </section>
	    
	    <!-- cart content -->
	    <% 
		double totalAmount=0;
		%>
	    <section class="col-4 mt-5 mr-5">
		    <div class="col-12">
		    	<div class="mx-auto col-12 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
		    		<div class="row">
			    		<h4 class="col-4 custom-font-playfair p-0"><span><i class="fa fa-shopping-cart"></i></span>   Your cart</h4>
			    		<div class="ml-auto">
			    		<div class="float-right">
				    		<form action="${pageContext.request.contextPath}/checkout">
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
		    		<p class="mt-3">You are now viewing the price in <span class="text-info"><%=currency%></span></p>
		    		<hr>
		      		<div style="max-height: 51vh; overflow-y: auto;">
		      		<%
		        	try{
		        		// display cart
		        		if(request.getAttribute("cartArrayList") != null){
		        			ArrayList<Cart> cartArrayList = (ArrayList<Cart>) request.getAttribute("cartArrayList");
		        			Double rate = (Double) request.getAttribute("rate");
							
							for(int x=0; x<cartArrayList.size(); x++) {
							String productCostPrice = String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*rate);
							int productQuantity = cartArrayList.get(x).getQuantity();
							String productTotalPrice = String.format("%.2f", cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate);
							%>
							      	<p>
							      		<span><%=cartArrayList.get(x).getProductName()%></span>
							      		<br>
							      		<span class="price">$<%=productCostPrice%> x <%=productQuantity%> = $<%=productTotalPrice%></span>
							      	</p> 
							      	<hr>
					        <% 
					        	totalAmount += cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()*rate;
							}
							%>
							</div>
				      		<p>Total (without GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*rate)%></b></span></p>
				      		<p>GST (7%): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*0.07*rate)%></b></span></p>
				      		<p>Total (with GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*1.07*rate)%></b></span></p>
							<%
		        		}
		        	} catch(Exception e){
			        	System.out.println("(checkout.jsp) Message Error: " + e + "\n");
			        }
			        %>
		    	</div>
		  	</div>
	  	</section>
	</div>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>