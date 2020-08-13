<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.* , javabeans.*"%>
<!DOCTYPE html>
<html>
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
		System.out.println("(analytics.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>

<body class="d-block w-100 vh-100 bg-img">

	<!-- import navigation bar -->
    <%@ include file="components/navigation.jsp" %>

    <section class="col-12 p-5 row">
    
      <!-- analytics -->
      <section class="mx-auto col-10 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - A n a l y t i c s</p>
        <hr>
        
        <section class="row">
        	<!-- tablist -->
	        <div class="nav flex-column nav-pills col-3" id="v-pills-tab" role="tablist">
			  <a class="nav-link active" id="v-pills-customer-tab" href="#v-pills-customer">Customer</a>
			  <a class="nav-link" id="v-pills-product-tab" href="/analyticsproduct" role="tab">Product</a>
			  <a class="nav-link" id="v-pills-order-tab" href="/analyticsorder">Order</a>
			</div>
			
			<!-- product content -->
			<div class="tab-content col-8" id="v-pills-tabContent" style="margin:auto">
			  <div class="tab-pane fade show active" id="v-pills-customer" role="tabpanel">
			  		
			  	  <!-- List of Customers -->
				  <table class="table table-bordered">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">User Id</th>
				      <th scope="col">First Name</th>
				      <th scope="col">Last Name</th>
				      <th scope="col">Country</th>
				      <th scope="col">Address</th>
				      <th scope="col">Postal Code</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%
				  ArrayList<Member> membersArrayList = (ArrayList<Member>) request.getAttribute("membersArrayList");
				  
				  for(int x=0; x<membersArrayList.size(); x++) {
				  %>
				    <tr>
				      <th scope="row"><%=membersArrayList.get(x).getUserId()%></th>
				      <td><%=membersArrayList.get(x).getFirstName()%></td>
				      <td><%=membersArrayList.get(x).getLastName()%></td>
				      <td><%=membersArrayList.get(x).getCountry()%></td>
				      <td><%=membersArrayList.get(x).getAddress()%></td>
				      <td><%=membersArrayList.get(x).getPostalCode()%></td>
				    </tr>
					<%}%>
				  </tbody>
				</table>
				
				<!-- Pagination -->
				<nav>
					<ul class="pagination justify-content-end">
						
				  <% 
				  try {
					  if(request.getParameter("page")!=null) {
						  int pageNumber = Integer.parseInt(request.getParameter("page")); 

						  if(pageNumber!=1){
						  %>
						  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticscustomer?page=<%=pageNumber-1%>">Previous</a></li>
						  <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticscustomer?page=<%=pageNumber-1%>"><%=pageNumber-1%></a></li>
						  <%
						  }
				  %>
					    <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/analyticscustomer?page=<%=pageNumber+1%>"><%=pageNumber%></a></li>
					    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/analyticscustomer?page=<%=pageNumber+1%>"><%=pageNumber+1%></a></li>
				  <%
					  } else {
						  System.out.println("(analytics-customer.jsp) Error: Pagination Error \n");
						  response.sendRedirect(request.getContextPath() + "/index");
					  }
				  } catch (Exception e) {
					  System.out.println("(analytics-customer.jsp) Error: " + e + "\n");      
				  }
				  %>
					  <li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</nav>
				
			  </div>
			</div>
        </section>
        
   	  </section>
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