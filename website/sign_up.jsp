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

    <section class="col-12 p-5 row">
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e - S i g n U p F o r m</p>

        <div class="form-row">
          <div class="form-group col-md-12">
            <label for="inputUsername">Username</label>
            <input type="text" class="form-control" id="inputUsername" placeholder="Username" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputEmail">Email</label>
            <input type="email" class="form-control" id="inputEmail" placeholder="Email" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" placeholder="Password" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputFirstName">First Name</label>
            <input type="text" class="form-control" id="inputFirstName" placeholder="First Name" required>
          </div>
          <div class="form-group col-md-6">
            <label for="inputLastName">Last Name</label>
            <input type="text" class="form-control" id="inputLastName" placeholder="Last Name" required>
          </div>
        </div>

        <div class="form-group">
          <label for="inputAddress">Address</label>
          <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" required>
        </div>

        <div class="form-row">
          <div class="form-group col-md-10">
            <label for="inputCountry">Country</label>
            <input type="text" class="form-control" id="inputCountry" required>
          </div>
          <div class="form-group col-md-2">
            <label for="inputPostalCode">Postal Code</label>
            <input type="text" class="form-control" id="inputPostalCode" required>
          </div>
        </div>
        <div class="form-group">
          <div class="form-check">
            <label class="form-check-label" for="gridCheck">
              Agree with Duo-Texture Terms & Conditions
            </label>
          </div>
        </div>
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