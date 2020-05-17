<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Search Function</title>
</head>
<body>
<%
	try{
		int getCategory = Integer.parseInt(request.getParameter("categoryId"));
		String keywordInput = request.getParameter("keywordInput");
		
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);
		
		String getCategoryByKeywordQuery = "SELECT * FROM products WHERE products.name LIKE '%choker%' AND products.categoryId = 3;";
		PreparedStatement pstmt = conn.prepareStatement(getCategoryByKeywordQuery);
		//pstmt.setString(1, keywordInput);
	    //pstmt.setInt(2, getCategory);
		ResultSet getCategoryByKeywordResults = pstmt.executeQuery();
		
		while (getCategoryByKeywordResults.next()){ // how to display? include?
			int id = getCategoryByKeywordResults.getInt("productId");   
			String name = getCategoryByKeywordResults.getString("name");               
			String description = getCategoryByKeywordResults.getString("description");
			String image = getCategoryByKeywordResults.getString("image"); 
			
			%>
			<div class="card col-2 mx-2 mt-3" style="width: 18rem;">
	            <img src="<%= image %>" class="card-img-top mt-3" alt="...">
	            <div class="card-body d-flex flex-column">
	                <p class="card-title fs-14" style="font-weight: bold"><%= name %></p>
	                <p class="card-text fs-13"><%= description %></p>
	                <div class="mx-2 pl-1 mt-auto">
	                    <a href="product_details.jsp?productId=<%= id %>" class="btn btn-primary px-4 my-2">View Details</a>
			         	<%
			            try{
			            	if(session.getAttribute("accountType").equals("admin")){
			                   	%>
			                   	<div>
			                        <a href="edit_product.jsp?productId=<%= id %>" class="btn btn-warning mr-1">Edit</a>
			                        <a href="#" class="btn btn-danger">Delete</a>
			                    </div>
			                   	<%
			                }
			            }catch(Exception e){
			            	out.println("Error: " + e);
			            }
			            %>
	                </div>
	            </div>
	        </div>
			<% 
			} 
	           
	conn.close();
	}catch(Exception e){
		out.println("Error: " + e);
	}
%>
</body>
</html>