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
		            	
		            	if(payment.equals("fail")){
		            		%>
		            		<h1>Payment Failed</h1>
		            		<h2>Try checking if your card details is right!</h2>
		            		<form action="${pageContext.request.contextPath}/checkout">
		            			<button class="btn btn-primary">Back</button>
		            		</form>
		            		<%
		            	}else if(payment.equals("expired")){
		            		%>
		            		<h1>Payment Failed</h1>
		            		<h2>Your card has expired! Try using another card.</h2>
		            		<form action="${pageContext.request.contextPath}/checkout">
		            			<button class="btn btn-primary">Back</button>
		            		</form>
		            		<%
		            	}else if(payment.equals("success")){
		            		%>
		            		<h1>Payment Successful</h1>
		            		<h2>Thank you for shopping with duotexture!</h2>
		            		<p>Your items are on it's way to you! Once you have received your items, don't forget to head to orders tab to confirm the delivery :)</p>
		            		<form action="${pageContext.request.contextPath}/index">
		            			<button class="btn btn-primary">Continue shopping</button>
		            		</form>
		            		<%
		            	}else{
		            		%>
		            		<h1>Payment Failed</h1>
		            		<h2>Card does not exist! Please try again!</h2>
		            		<form action="${pageContext.request.contextPath}/checkout">
		            			<button class="btn btn-primary">Back</button>
		            		</form>
		            		<%
		            	}
	        		} else {
	        			%>
	        			<script type="text/javascript">
	        			window.location.href='${pageContext.request.contextPath}/index';
	        			alert("Please purchase items before accessing this page.");
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