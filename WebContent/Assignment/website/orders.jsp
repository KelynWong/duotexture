<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*, java.util.Date, java.text.*"%>
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
		System.out.println("(orders.jsp) Admin Validation Error: " + e + "\n");
	}
	%>
</head>
<% //double totalAmount = 0; %>
<body class="d-block w-100 vh-100 bg-img">
	
	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

	<div class="row">
		<section class="col-12 p-5">
	      <div class="mx-auto col-10 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
			<p class="custom-font-playfair fs-15">D u o - T e x t u r e - O r d e r s</p>
	     	<hr>
	     	<div class="table-responsive-sm">
		     	<%
	        	try{
	        		// display orders
	        		if(request.getAttribute("orderArrayList") != null){
	        			ArrayList<Order> orderArrayList = (ArrayList<Order>) request.getAttribute("orderArrayList");
	        			
	        			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	        			if(orderArrayList.size() != 0){
	        				%>
	        				<table class="table table-bordered">
							  <thead class="thead-dark">
							    <tr>
							      <th scope="col">Date of Order</th>
							      <th scope="col">Image</th>
							      <th scope="col">Product Name</th>
							      <th scope="col">Price</th>
							      <th scope="col">Quantity</th>
							      <th scope="col">Total</th>
							      <th scope="col">Action</th>
							    </tr>
							  </thead>
							  <tbody>
		  					<%
		  					for(int x=0; x<orderArrayList.size(); x++) {
		  							%>
		  							<tr>
		  							  <td><%=orderArrayList.get(x).getDateTime().split(" ")[0]%></td>
								      <th scope="row"><img src="<%=orderArrayList.get(x).getProductImage()%>" style="height:50px" alt="Product Image"></th>
								      <td><%=orderArrayList.get(x).getProductName()%></td>
								      <td>$<%=String.format("%.2f", orderArrayList.get(x).getProductCostPrice())%></td>
								      <td><%=orderArrayList.get(x).getQuantity()%></td>
								      <td>$<%=String.format("%.2f", orderArrayList.get(x).getProductCostPrice()*orderArrayList.get(x).getQuantity())%></td>
								      <td>
								      	<form action="${pageContext.request.contextPath}/EditOneOrderServlet?productId=<%=orderArrayList.get(x).getProductId()%>" method="post">
									    	<button type="submit" class="btn btn-outline-success">Delivered</button>
									    </form>
									  </td>
								    </tr>
								    <%
		  						}
		  					%>
			  					</tbody>
							</table>
						</div>
						
				        <form action="${pageContext.request.contextPath}/EditOrdersServlet" method="post">
				        	<button type="submit" class="btn btn-outline-success">All my orders are delivered</button>
				        </form>
		  					<%
	        			}else{ %>
	        				<p>You currently don't have any orders.</p>
        			<%	}
	        		}
	        	} catch(Exception e){
		        	System.out.println("(orders.jsp) Message Error: " + e + "\n");
		        }
		        %>
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