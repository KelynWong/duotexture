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
		// validate if user executing request is admin
		if(session.getAttribute("accountType")!=null){
			if(!session.getAttribute("accountType").equals("admin")){
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
		System.out.println("(edit_category.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

    <section class="col-12 p-5 row">
    	
      <!-- edit category form -->
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="${pageContext.request.contextPath}/EditCategoryServlet" method="post" enctype="multipart/form-data">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - E d i t - C a t e g o r y</p>
        <hr>
		<%
		try {			
			if(request.getParameter("categoryId")!=null){
				int getCategoryId = Integer.parseInt(request.getParameter("categoryId"));
				
				Category category = (Category) request.getAttribute("category");

				String categoryName = category.getName();
				String categoryDescription = category.getDescription();
				String categoryImage = category.getImage();
		%>

				<div class="form-row">
		          <div class="form-group row col-md-12">
		            <label class="my-1 col-2" for="inputCategoryId">Category Id</label>
		            <input type="text" class="form-control col-10" id="inputCategoryId" name="inputCategoryId" placeholder="Category Id" value="<%= getCategoryId %>" readonly required>
		          </div>
		          <div class="form-group col-md-12">
		            <label for="inputCategoryName">Name</label>
		            <input type="text" class="form-control" id="inputCategoryName" name="inputCategoryName" value="<%= categoryName %>" required>
		          </div>
		          <div class="form-group col-md-12">
		            <label for="inputCategoryDescription">Description</label>
		            <textarea type="text" class="form-control" id="inputCategoryDescription" name="inputCategoryDescription" required><%= categoryDescription %></textarea>
		          </div>
		          <div class="form-group col-md-12">
			        <label for="inputCategoryImageUrl">Image Upload</label>
			        <input type="file" accept=".jpg,.jpeg,.png" class="form-control" style="min-height: 45px" id="inputCategoryImageUrl" name="inputCategoryImageUrl" onchange="validateFileType()">
			      </div>
			      <div class="form-group col-md-12">
			        <label class="col-md-12 p-0" style="display: block; max-height: 200px" id="previewCategoryImageUrlLabel">Image Preview</label>
			        <img id="previewCategoryImageUrl" src="<%= categoryImage %>" style="display: block; max-height: 200px" alt="Category Image">
			        <input id="previewInputCategoryImageUrl" type="hidden" name="previewCategoryImageUrl" value="<%= categoryImage %>">
			      </div>
		        </div>
		        
		        <%
	        	try{
	        		// display different error message dependant on request success and failure
	        		if(request.getParameter("categoryEdit")!=null){
		        		String errorMessage = "";
		            	String categoryEdit = request.getParameter("categoryEdit");
		            	
		            	if(categoryEdit.equals("fail")){
		            		errorMessage = "Attempt to edit failed. Please try again.";
		            		%>
					        <small class="text-danger"><%= errorMessage %><br><br></small>
					        <%
		            	}else if(categoryEdit.equals("success")){
		            		errorMessage = "Successfully Edited.";
		            		%>
					        <small class="text-success"><%= errorMessage %><br><br></small>
					        <%
		            	}
	        		}
	        	} catch(Exception e){
		        	System.out.println("(edit_category.jsp) Message Error: " + e + "\n");
		        }
		        %>
		        <button type="submit" class="btn btn-success">Edit</button>
			<%    
			}else{
				%>
				<p>Category Id is not declared.</p>
				<%
			}
		} catch (Exception e) {         
			System.out.println("(edit_category.jsp) Error: " + e + "\n");      
		} 
		%>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
    
    function readFile(input, previewImage, previewLabel) {
    	if (input.files && input.files[0]) {
    		var reader = new FileReader();
    		
    		reader.onload = function(e) {
    			$(previewLabel).attr('style', "display: block")
    			$(previewImage).attr('src', e.target.result);
    			$(previewImage).attr('style', "display: block; max-height: 200px");
    		}
    		
    		reader.readAsDataURL(input.files[0]);
    	}	
    }
    
    $("#inputCategoryImageUrl").change(function() {
    	readFile(this, "#previewCategoryImageUrl", "previewCategoryImageUrlLabel")
    })
    
    function validateFileType(){
        var fileName = document.getElementById("inputCategoryImageUrl").value;
        var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="jpg" || extFile=="jpeg" || extFile=="png"){

        }else{
        	document.getElementById("inputCategoryImageUrl").value = "";
            alert("Only jpg/jpeg and png files are allowed!");
        }   
    }
    
    </script>
    <!--===============================================================================================-->
</body>
</html>