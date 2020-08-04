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
</head>
<body class="d-block w-100 vh-100 bg-img">
	
	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row">
       
      <!-- edit profile form -->
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="${pageContext.request.contextPath}/EditProfileServlet" method="post">
		<%
		try{			
			if(session.getAttribute("accountType") !=null){
				User user = (User) request.getAttribute("user");
					String username = user.getUsername();
					String email = user.getEmail();
					String password = user.getPassword();
					String userRole = user.getUserRole();
					
					// check if user is a member
					if(userRole.equals("Member")){ %>
					<p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - M e m b e r - P r o f i l e</p>
     				<hr>
				        <div class="form-row">
				          <div class="form-group col-md-12">
				            <label for="inputUsername">Username</label>
				            <input type="text" class="form-control" id="inputUsername" name="inputUsername" placeholder="Username" value="<%=username%>" required>
				          </div>
				          <div class="form-group col-md-6">
				            <label for="inputEmail">Email</label>
				            <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email" value="<%=email%>" required>
				          </div>
				          <div class="form-group col-md-6">
				            <label for="inputPassword">Password</label>
				            <input type="text" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" value="<%=password%>" minlength="8" required>
				          </div>
				          <% 
					        Member member = (Member) request.getAttribute("member");
				          	
				           	String first_name = member.getFirstName();
				            String last_name = member.getLastName();
				            String address = member.getAddress();
				            String country = member.getCountry();
				            String postal_code = member.getPostalCode();
				          %>
				          <div class="form-group col-md-6">
				            <label for="inputFirstName">First Name</label>
				            <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="First Name" value="<%=first_name%>" required>
				          </div>
				          <div class="form-group col-md-6">
				            <label for="inputLastName">Last Name</label>
				            <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Last Name" value="<%=last_name%>" required>
				          </div>
				        </div>
				
				        <div class="form-group">
				          <label for="inputAddress">Address</label>
				          <textarea type="text" class="form-control" id="inputAddress" name="inputAddress" placeholder="1234 Main St" required><%=address%></textarea>
				        </div>
				
				        <div class="form-row">
				          <div class="form-group col-md-10">
				            <label for="inputCountry">Country</label>
				            <input type="text" class="form-control" id="inputCountry" name="inputCountry" value="<%=country%>" required>
				          </div>
				          <div class="form-group col-md-2">
				            <label for="inputPostalCode">Postal Code</label>
				            <input type="text" class="form-control" id="inputPostalCode" name="inputPostalCode" value="<%=postal_code%>" required>
				          </div>
				        </div>   
			        <% 
			        // check if user is admin
					}else if(userRole.equals("Admin")){ %>
			        	<p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - A d m i n - P r o f i l e</p>
	     				<hr>
				        <div class="form-row">
				          <div class="form-group col-md-12">
				            <label for="inputUsername">Username</label>
				            <input type="text" class="form-control" id="inputUsername" name="inputUsername" placeholder="Username" value="<%=username%>" required>
				          </div>
				          <div class="form-group col-md-6">
				            <label for="inputEmail">Email</label>
				            <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email" value="<%=email%>" required>
				          </div>
				          <div class="form-group col-md-6">
				            <label for="inputPassword">Password</label>
				            <input type="text" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" value="<%=password%>" minlength="8" required>
				          </div>
				        </div>
			        <% }
		        	try{
		        		// display different error message dependant on request success and failure
		        		if(request.getParameter("profileEdit")!=null){
			        		String errorMessage = "";
			            	String profileEdit = request.getParameter("profileEdit");
			            	
			            	if(profileEdit.equals("fail")){
			            		errorMessage = "Attempt to edit failed. Please try again.";
			            		%>
						        <small class="text-danger"><%= errorMessage %><br><br></small>
						        <%
			            	}else if(profileEdit.equals("success")){
			            		errorMessage = "Successfully Edited.";
			            		%>
						        <small class="text-success"><%= errorMessage %><br><br></small>
						        <%
			            	}
		        		}
			        }catch(Exception e){
			        	System.out.println("(edit_profile.jsp) Member Message Error: " + e + "\n");
			        }
			        %>
			        
			        <button type="submit" class="btn btn-primary">Edit</button>
		      		<%
			}
		} catch(Exception e){
			System.out.println("(edit_profile.jsp) Error: " + e + "\n");
		}
      %>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>