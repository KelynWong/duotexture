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
</head>
<% //double totalAmount = 0; %>
<body class="d-block w-100 vh-100 bg-img">
	
	<!-- import navigation bar -->
    <%@ include file = "components/navigation.jsp" %>

	<%
	if(session.getAttribute("userId") == null){
			%>
		<script type="text/javascript">
		window.location.href='index.jsp';
		alert("You do not have access rights.");
		</script>
		<%
		}else{
			int userId = (int)session.getAttribute("userId");
	%>
	<div class="row">
		<section class="col-12 p-5">
	      <div class="mx-auto col-10 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
			<p class="custom-font-playfair fs-15">D u o - T e x t u r e - P u r c h a s e s</p>
	     	<hr>
	     	<div class="table-responsive-sm">
				     	<%
				        	try{
				        		// display purchases
				        		if(request.getAttribute("purchaseArrayList") != null){
				        			ArrayList<Purchase> purchaseArrayList = (ArrayList<Purchase>) request.getAttribute("purchaseArrayList");
									
				        			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
				        			if(purchaseArrayList.size() != 0){
				        				%>
				        				<table class="table w-auto">
										  <thead class="thead-dark">
										    <tr>
										      <th scope="col">Image</th>
										      <th scope="col">Product Name</th>
										      <th scope="col">Price</th>
										      <th scope="col">Qty</th>
										      <th scope="col">Total</th>
										    </tr>
										  </thead>
										  <tbody>
										  	<% String date = purchaseArrayList.get(0).getDateTime().split(" ")[0]; %>
					  						<th scope="row"><%=date%></th>
					  					<%
					  					for(int x=1; x<purchaseArrayList.size(); x++) {
						  				    Date d1 = sdformat.parse(purchaseArrayList.get(x-1).getDateTime());
						  				    Date d2 = sdformat.parse(purchaseArrayList.get(x).getDateTime());
					  						if(d1.compareTo(d2) == 0){
					  							%>
					  							<tr>
											      <th scope="row"><img src="<%=purchaseArrayList.get(x).getProductImage()%>" style="height:50px" alt="..."></th>
											      <td><%=purchaseArrayList.get(x).getProductName()%></td>
											      <td>$<%=String.format("%.2f", purchaseArrayList.get(x).getProductCostPrice())%></td>
											      <td><%=purchaseArrayList.get(x).getQuantity()%></td>
											      <td>$<%=String.format("%.2f", purchaseArrayList.get(x).getProductCostPrice()*purchaseArrayList.get(x).getQuantity())%></td>
											    </tr>
											    <%
					  						}else{
					  							date = purchaseArrayList.get(x).getDateTime().split(" ")[0];
					  							%>
					  							<tr><th scope="row"><%=date%></th></tr>
					  							<tr>
											      <th scope="row"><img src="<%=purchaseArrayList.get(x).getProductImage()%>" style="height:50px" alt="..."></th>
											      <td><%=purchaseArrayList.get(x).getProductName()%></td>
											      <td>$<%=String.format("%.2f", purchaseArrayList.get(x).getProductCostPrice())%></td>
											      <td><%=purchaseArrayList.get(x).getQuantity()%></td>
											      <td>$<%=String.format("%.2f", purchaseArrayList.get(x).getProductCostPrice()*purchaseArrayList.get(x).getQuantity())%></td>
											    </tr>
					  							<%
					  						}
					  					}
				        			}else{ %>
				        				<p>You haven't made any purchases.</p>
				        				<hr>
				        		<%	}
				        		}
				        	} catch(Exception e){
					        	System.out.println("(purchases.jsp) Message Error: " + e + "\n");
					        }
					        %>
		        		</tbody>
					</table>
				</div>
				<form action="${pageContext.request.contextPath}/index">
					<button type="submit" class="btn btn-outline-success">Home</button>
				</form>
	      </div>
	    </section>
	</div>
    <%
	}
    %>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>