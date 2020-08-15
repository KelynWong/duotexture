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
		System.out.println("(payment.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row">
      <div class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - P a y m e n t</p>
        <hr>
		        <%
	        	try{
	        		// display different error message dependant on request success and failure
	        		if(request.getParameter("payment")!=null){
		        		String errorMessage = "";
		            	String payment = request.getParameter("payment");
		            	
		            	if(payment.equals("cardFail")){
		            		%>
		            		<p>Dear Duo-Texture Customer, <span class="text-info"><%=session.getAttribute("username")%></span>,</p>
		            		<p>
			            		Unfortuantely, the credit card which you have input has failed our validation. <br>
			            		Please check if the details which you have input are correct. <br>
			            		A few criteria for you to check are as follow.
			            	</p>
			            	
		            		<ol class="mx-3">
		            			<li class="text-danger">Valid card number</li>
		            			<li>Valid expiry month</li>
		            			<li>Valid expiry year</li>
		            		</ol>
		            		
		            		<p>Your card has seemingly failed according to <span style="text-decoration: underline;">Luhn Algorithm</span>.<br>Please try again.</p>
		            		
		            		<a class="btn btn-primary" href="${pageContext.request.contextPath}/checkout" role="button">Back to Checkout</a>
		            		<%
		            	}else if(payment.equals("expired")){
		            		String expired = request.getParameter("expired");
		            		%>
		            		<p>Dear Duo-Texture Customer, <span class="text-info"><%=session.getAttribute("username")%></span>,</p>
		            		<p>
			            		Unfortuantely, the credit card which you have input has failed our validation. <br>
			            		Please check if the details which you have input are correct. <br>
			            		A few criteria for you to check are as follow.
			            	</p>
			            	
		            		<ol class="mx-3">
		            			<li>Valid card number</li>
		            			<% 
		            			if(expired.equals("month")){
		            				%>
		            				<li class="text-danger">Valid expiry month</li>
		            				<li>Valid expiry year</li>
		            				</ol>
		            				<p>It does seems like your card has either expired or the expiry inputs were wrong. <br>Please do <span class="text-warning">input your expiry month</span> again. Thank you.</p>
		            				<%
		            			}else {
		            			%>
			            			<li>Valid expiry month</li>
			            			<li class="text-danger">Valid expiry year</li>
			            			</ol>
			            			<p>It does seems like your card has either expired or the expiry inputs were wrong. <br>Please do <span class="text-warning">input your expiry year</span> again. Thank you.</p>
		            			<%}%>
		            		
		            		<a class="btn btn-primary" href="${pageContext.request.contextPath}/checkout" role="button">Back to Checkout</a>
		            		<%
		            	}else if(payment.equals("success")){
		            		%>
		            		<p>Dear Duo-Texture Customer, <span class="text-info"><%=session.getAttribute("username")%></span>,</p>
		            		<p>
		            			Thank you for shopping at Duo-Texture,<br>
			            		Your payment was made <span class="text-success">successfully</span>.<br><br>
			            		Please do wait patiently as we prepare the items you have bought and ship over.<br>
			            		The estimated shipping duration is around <span class="text-danger">7-8 working days</span>.<br>
			            		Once received, please kindly head over to the <span style="color: purple;">orders management to confirm the delivery</span>.<br><br>
			            		
			            		If you have any concerns or enquire, please feel free to dial <span class="text-primary">+65 8876 3325</span>. Thank you!
			            	</p>
		            		
		            		<div class="row">
			            		<a class="btn btn-primary mx-3" href="${pageContext.request.contextPath}/categories" role="button">Continue Shopping</a>
			            		<a class="btn btn-info" href="${pageContext.request.contextPath}/orders" role="button">Orders Management</a>
		            		</div>
		            		<%
		            	}else{
		            		%>
		            		<p>Dear Duo-Texture Customer, <span class="text-info"><%=session.getAttribute("username")%></span>,</p>
		            		<p>
			            		Unfortuantely, the credit card which you have input has failed our validation. <br>
			            		Please check if the details which you have input are correct. <br>
			            		A few criteria for you to check are as follow.
			            	</p>
			            	
		            		<ol class="mx-3">
		            			<li>Valid card number</li>
		            			<li>Valid expiry month</li>
		            			<li>Valid expiry year</li>
		            		</ol>
		            		
		            		<p>Something went wrong.<br>Please try again.</p>
		            		
		            		<a class="btn btn-primary" href="${pageContext.request.contextPath}/checkout" role="button">Back to Checkout</a>
		            		<%
		            	}
	        		} else {
	        			%>
	        			<script type="text/javascript">
	        			window.location.href='${pageContext.request.contextPath}/index';
	        			alert("Please purchase an item.");
	        			</script>
	        			<%
	        		}
	        	} catch(Exception e){
		        	System.out.println("(payment.jsp) Message Error: " + e + "\n");
		        }
		        %>
      </div>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>