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
    <link rel="stylesheet" href="assets/css/styles.css"/>
    <link rel="stylesheet" href="assets/css/util.css" />
    <%
    try{
    	// validate if user has already logged in
    	if(session.getAttribute("accountType")!=null){
    		if(session.getAttribute("accountType").equals("admin") || session.getAttribute("accountType").equals("member")){
    	   		%>
    	   		  <script type="text/javascript">
    	   		  	window.location.href='index.jsp';
    	   			alert("You have already logged-in. Please log out first.");
    	   		  </script>
    	   		<%
       		}	
    	}
    } catch (Exception e){
    	System.out.println("(login.jsp) Login Validation Error: " + e + "\n");
    }
    %>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "navigation.jsp" %>
    
    <section class="col-12 p-5 row">
      <!-- login form -->
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="../LoginServlet" method="post">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - L o g I n</p>
        
        <div class="form-group">
          <label for="emailInput">Email address</label>
          <input type="email" name="emailInput" class="form-control my-input" id="emailInput" placeholder="Email" aria-describedby="emailHelp" minlength="8" required>
          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>

        <div class="form-group">
          <label for="passwordInput">Password</label>
          <input type="password" name="passwordInput" class="form-control" id="passwordInput" placeholder="Password" minlength="8" required>
        </div>
        
        <%
       	try{
       		// display different error message dependant on request success and failure
       		if(request.getParameter("accountType")!=null){
       			String errorMessage = "";
               	String account = request.getParameter("accountType");
               	
               	if(account.equals("none")){
               		errorMessage = "Account does not exists. Please try again.";
    		        %>
    		        <small class="text-danger"><%= errorMessage %><br><br></small>
    		        <%
    	        }
       		}
        } catch(Exception e){
        	System.out.println("(login.jsp) Error: " + e + "\n");
        }
        %>

        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>