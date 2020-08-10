<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*, java.util.* , javabeans.*"%>
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
<meta charset="ISO-8859-1">
<title>Navigation Bar</title>
</head>
<body>
<!-- navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand custom-font-playfair fs-15" href="${pageContext.request.contextPath}/index">D u o - T e x t u r e</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <section class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
        <li class="nav-item">
			<a class="nav-link custom-font-mont fs-15" href="${pageContext.request.contextPath}/categories" role="button">All Categories</a>
		</li>
		<%
		try{
			if(request.getAttribute("categoriesArrayList") != null) {
				ArrayList<Category> categoriesArrayList = (ArrayList<Category>) request.getAttribute("categoriesArrayList");
				
				for(int x=0; x<categoriesArrayList.size(); x++) {
				%>
					<li class="nav-item">
			          <a class="nav-link custom-font-mont fs-15" href="${pageContext.request.contextPath}/productlistings?categoryId=<%= categoriesArrayList.get(x).getCategoryId() %>" role="button"><%= categoriesArrayList.get(x).getName() %></a>
			        </li>
		        <% 
				} 
			} else {
				%>
				<script type="text/javascript">
				window.location.href='${pageContext.request.contextPath}/index';
				alert("Access denied.");
				</script>
				<%
			}
		} catch (Exception e) {
			System.out.println("(navigation.jsp) Admin Add Access Error: " + e + "\n");
		}
			
		try{
           	if(session.getAttribute("accountType")!=null){
           		// if account is admin, allow access to add function
            	if(session.getAttribute("accountType").equals("admin")){
                   	%>
                   	<a class="nav-link custom-font-mont fs-15 text-success" href="${pageContext.request.contextPath}/addcategory" style="margin-left: 10px">Add</a>
                   	<%
                }
           	}
           	
		} catch(Exception e){
        	System.out.println("(navigation.jsp) Admin Add Access Error: " + e + "\n");
        } 
		%>
        </ul>
        
        <ul class="navbar-nav ml-auto">
		<%
		try{
			// check if user's account and retrieve their username
			if(session.getAttribute("accountType")!=null){ %>
					<a class="nav-link custom-font-mont fs-15 text-primary" href="${pageContext.request.contextPath}/editprofile?userId=<%=session.getAttribute("userId")%>"><%=session.getAttribute("username")%></a>
					<a class="nav-link custom-font-mont fs-15 text-danger" href="${pageContext.request.contextPath}/SignOutServlet">Log Out</a>
					<%
			}else{
				%>
	        	<a class="nav-link custom-font-mont fs-15 text-primary" href="${pageContext.request.contextPath}/login">Login</a>
	        	<a class="nav-link custom-font-mont fs-15 text-danger" href="${pageContext.request.contextPath}/signup">Sign Up</a>
				<%
			}
			
		} catch(Exception e){ 
			System.out.println("(navigation.jsp) Error: " + e + "\n");
		}
		%>
		</ul>
      </section>
    </nav>
</body>
</html>