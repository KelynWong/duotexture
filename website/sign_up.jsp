<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>duo-texture</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/styles.css"/>
    <link rel="stylesheet" href="assets/css/util.css" />
    <%
    try{
    	
	   	if(session.getAttribute("accountType").equals("admin") || session.getAttribute("accountType").equals("member")){
	   		%>
	   		  <script type="text/javascript">
	   		  	window.location.href='index.jsp';
	   			alert("You have already logged-in. Please log out first.");
	   		  </script>
	   		<%
   		}
    } catch (Exception e){
    	// out.println("Error: " + e);
    }
    %>
</head>

<body class="d-block w-100 vh-100 bg-img">

    <%@ include file = "navigation.jsp" %>

    <section class="col-12 p-5 row">
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="validate_sign_up.jsp" method="post">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - S i g n - U p - F o r m</p>

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="inputUsername">Username</label>
            <input type="text" class="form-control" id="inputUsername" name="inputUsername" placeholder="Username" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputEmail">Email</label>
            <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" minlength="8" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputFirstName">First Name</label>
            <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First Name" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputLastName">Last Name</label>
            <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last Name" required>
          </div>
        </div>

        <div class="form-group">
          <label for="inputAddress">Address</label>
          <input type="text" class="form-control" id="inputAddress" name="inputAddress" placeholder="1234 Main St" required>
        </div>

        <div class="form-row">
          <div class="form-group col-md-10">
            <label for="inputCountry">Country</label>
            <input type="text" class="form-control" id="inputCountry" name="inputCountry" required>
          </div>
          <div class="form-group col-md-2">
            <label for="inputPostalCode">Postal Code</label>
            <input type="text" class="form-control" id="inputPostalCode" name="inputPostalCode" required>
          </div>
        </div>
        <div class="form-group">
          <div class="form-check">
          	<input type="checkbox" class="form-check-input" id="inputAgreement" name="inputAgreement" required>
            <label class="form-check-label" for="inputAgreement">
              Agree with Duo-Texture Terms & Conditions
            </label>
          </div>
        </div>
        
        <%
        	try{
        		String errorMessage = "";
            	String registration = request.getParameter("registration");
            	
            	if(registration.equals("fail")){
            		errorMessage = "Registration failed. Please try again.";
		        %>
		        <small class="text-danger"><%= errorMessage %><br><br></small>
		        <%
            	}
	        }catch(Exception e){
	        }
        %>
        
        <button type="submit" class="btn btn-primary">Sign Up</button>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>