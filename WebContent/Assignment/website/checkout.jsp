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

	<div class="row">
		<section class="col-8 p-5">
       
       	<%if(request.getParameter("cards").equals("noCard")){ %>
	    <!-- checkout form -->
	    <div class="mx-auto col-9 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
			<div class="row">
			    <div class="col-md-9 custom-font-playfair fs-15">D u o - T e x t u r e - C h e c k o u t</div>
			    <div class="col-md-3 text-right">
			    <a class="btn btn-success" href="${pageContext.request.contextPath}/cart" role="button">Back</a>
			    </div>
			</div>
	     	<hr>
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
						<input type="text" class="form-control" id="cvv" name="cvv" placeholder="352" minlength="3" maxlength="3" required>
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
	      <!-- checkout form -->
	    <div class="mx-auto col-9 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
			<div class="row">
			    <div class="col-md-9 custom-font-playfair fs-15">D u o - T e x t u r e - C h e c k o u t</div>
			    <div class="col-md-3 text-right">
			    	<form action="${pageContext.request.contextPath}/cart">
						<button type="submit" class="btn btn-success">Back</button>
					</form>
			    </div>
			</div>
	     	<hr>
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
	    
	    <% 
		double totalAmount=0;
		%>
	    <section class="col-4 p-5">
		    <div class="col-12">
		    	<div class="mx-auto col-12 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
		      		<h1 class="col-md-9 custom-font-playfair fs-15">Your cart</h4>
		      		<hr>
	      		<div style="max-height: 340px; overflow: auto;">
	      		<%
	        	try{
	        		// display cart
	        		if(request.getAttribute("cartArrayList") != null){
	        			ArrayList<Cart> cartArrayList = (ArrayList<Cart>) request.getAttribute("cartArrayList");
						
						for(int x=0; x<cartArrayList.size(); x++) {
						%>
						      	<p>
						      		<span><%=cartArrayList.get(x).getProductName()%></span>
						      		<br>
						      		<span class="price">$<%=cartArrayList.get(x).getProductCostPrice()%> x <%=cartArrayList.get(x).getQuantity()%> = $<%=cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity()%></span>
						      	</p> 
						      	<hr>
				        <% 
				        	totalAmount += cartArrayList.get(x).getProductCostPrice()*cartArrayList.get(x).getQuantity();
						} 	
	        		}
	        	} catch(Exception e){
		        	System.out.println("(checkout.jsp) Message Error: " + e + "\n");
		        }
		        %>
	      		</div>
	      		<p>Total (without GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount)%></b></span></p>
	      		<p>GST (7%): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*0.07)%></b></span></p>
	      		<p>Total (with GST): <span class="price" style="color:black"><b>$<%=String.format("%.2f", totalAmount*1.07)%></b></span></p>
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