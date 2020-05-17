<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>duo-texture</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/styles.css"/>
    <link rel="stylesheet" href="assets/css/util.css" />
</head>

<body class="d-block w-100 vh-100 bg-img">

    <!-- navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand custom-font-playfair fs-15" href="index.jsp">D u o - T e x t u r e</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <section class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.jsp" role="button">Men</a>
          </li>

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.jsp" role="button">Women</a>
          </li>

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.jsp" role="button">Kids</a>
          </li>

        </ul>
        
        <ul class="navbar-nav ml-auto">
		<%
		try{
			if(session.getAttribute("accountType").equals("admin") && session.getAttribute("adminUsername") != null){ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="editProfile.jsp"><%=session.getAttribute("adminUsername")%></a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="log_out.jsp">Log Out</a>
			<%
			}else if(session.getAttribute("accountType").equals("member") && session.getAttribute("memberUsername") != null){ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="editProfile.jsp"><%=session.getAttribute("memberUsername")%></a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="log_out.jsp">Log Out</a>
			<%
			}else{ %>
		          <a class="nav-link custom-font-mont fs-15 text-primary" href="login.jsp">Login</a>
		          <a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.jsp">Sign Up</a>
			<%
			}
			
		}catch(Exception e){ %>
	          <a class="nav-link custom-font-mont fs-15 text-primary" href="login.jsp">Login</a>
	          <a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.jsp">Sign Up</a>
		<%
		}
		%>
		</ul>
      </section>
    </nav>

    <section class="col-12 p-5 row justify-content-center">
        <form class="form-inline col-11 justify-content-center">
            <input class="form-control col-10" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-danger my-2 my-sm-0 search-btn" type="submit">Search</button>
            <a href="add_product.jsp" class="btn btn-success" style="margin-left: 10px">Add</a>
        </form>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <div class="mx-2 pl-1">
                    <a href="product_details.jsp" class="btn btn-primary px-4 my-2">View Details</a>
                    <div class="">
                        <a href="edit_product.jsp" class="btn btn-warning mr-1">Edit</a>
                        <a href="#" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

        <div class="card col-2 mx-2 mt-3" style="width: 18rem;">
            <img src="./assets//images/image1.jpg" class="card-img-top mt-3" alt="...">
            <div class="card-body">
                <p class="card-title fs-14" style="font-weight: bold">Duo-Breathable Shirt</p>
                <p class="card-text fs-13">Vintage styles with a modern twist to match everything you have.</p>
                <a href="product_details.jsp" class="btn btn-primary mx-4">View Details</a>
            </div>
        </div>

    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>