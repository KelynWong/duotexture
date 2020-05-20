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
</head>
<body class="d-block w-100 vh-100 bg-img">

    <%@ include file = "navigation.jsp" %>

    <section class="col-12 p-5 row">
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="validate_edit_profile.jsp" method="post">
      
		<%
		try{
			
			Class.forName("com.mysql.jdbc.Driver");         
			String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
			Connection conn = DriverManager.getConnection(connURL);
			
			if(session.getAttribute("accountType").equals("member")) {  
				
				String getAllMembersQuery = "SELECT * FROM members WHERE memberId = ? LIMIT 1;";
				PreparedStatement pstmt = conn.prepareStatement(getAllMembersQuery);
				pstmt.setObject(1, session.getAttribute("memberId"));
				ResultSet getAllMembersResult = pstmt.executeQuery();
				getAllMembersResult.next();
				
			%>
			<p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - M e m b e r - P r o f i l e</p>
		        <div class="form-row">
		          <div class="form-group col-md-12">
		            <label for="inputUsername">Username</label>
		            <input type="text" class="form-control" name="inputUsername" placeholder="Username" value="<%=getAllMembersResult.getString("username")%>" required>
		          </div>
		          <div class="form-group col-md-6">
		            <label for="inputEmail">Email</label>
		            <input type="email" class="form-control" name="inputEmail" placeholder="Email" value="<%=getAllMembersResult.getString("email")%>" required>
		          </div>
		          <div class="form-group col-md-6">
		            <label for="inputPassword">Password</label>
		            <input type="text" class="form-control" name="inputPassword" placeholder="Password" value="<%=getAllMembersResult.getString("password")%>" required>
		          </div>
		          <div class="form-group col-md-6">
		            <label for="inputFirstName">First Name</label>
		            <input type="text" class="form-control" name="inputFirstName" placeholder="First Name" value="<%=getAllMembersResult.getString("first_name")%>" required>
		          </div>
		          <div class="form-group col-md-6">
		            <label for="inputLastName">Last Name</label>
		            <input type="text" class="form-control" name="inputLastName" placeholder="Last Name" value="<%=getAllMembersResult.getString("last_name")%>" required>
		          </div>
		        </div>
		
		        <div class="form-group">
		          <label for="inputAddress">Address</label>
		          <input type="text" class="form-control" name="inputAddress" placeholder="1234 Main St" value="<%=getAllMembersResult.getString("address")%>" required>
		        </div>
		
		        <div class="form-row">
		          <div class="form-group col-md-10">
		            <label for="inputCountry">Country</label>
		            <input type="text" class="form-control" name="inputCountry" value="<%=getAllMembersResult.getString("country")%>" required>
		          </div>
		          <div class="form-group col-md-2">
		            <label for="inputPostalCode">Postal Code</label>
		            <input type="text" class="form-control" name="inputPostalCode" value="<%=getAllMembersResult.getString("postal_code")%>" required>
		          </div>
		        </div>
		        
	        	<%
		        	try{
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
		            	}else{	
		            	}
			        }catch(Exception e){
			        	// out.println("Error: " + e);
			        }
		        %>
		        
		        <button type="submit" class="btn btn-primary">Edit</button>
	      <%
	      } else if(session.getAttribute("accountType").equals("admin")){ 
	    		
	    	  String getAllAdminsQuery = "SELECT * FROM administrators WHERE administratorId = ? LIMIT 1;";
				PreparedStatement pstmt = conn.prepareStatement(getAllAdminsQuery);
				pstmt.setObject(1, session.getAttribute("adminId"));
				ResultSet getAllAdminsResult = pstmt.executeQuery();
				getAllAdminsResult.next();
	      
	      %>
	      <p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - A d m i n - P r o f i l e</p>
			<div class="form-row">
				 <div class="form-group col-md-12">
			       <label for="inputUsername">Username</label>
			         <input type="text" class="form-control" name="inputUsername" placeholder="Username" value="<%=getAllAdminsResult.getString("username")%>" required>
			       </div>
			      
			       <div class="form-group col-md-6">
			         <label for="inputEmail">Email</label>
			         <input type="email" class="form-control" name="inputEmail" placeholder="Email" value="<%=getAllAdminsResult.getString("email")%>" required>
			       </div>
			       
			       <div class="form-group col-md-6">
			         <label for="inputPassword">Password</label>
			         <input type="text" class="form-control" name="inputPassword" placeholder="Password" value="<%=getAllAdminsResult.getString("password")%>" required>
			       </div>
	       	</div>
			       	
			       	<%
			        	try{
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
			            	}else{	
			            	}
				        }catch(Exception e){
				        	// out.println("Error: " + e);
				        }
			        %>
			        
				 <button type="submit" class="btn btn-primary">Edit</button>
	      <%
	      }
		}catch(Exception e){
			out.println("Error: " + e);
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