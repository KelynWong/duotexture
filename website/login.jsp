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
      <a class="navbar-brand custom-font-playfair fs-15" href="index.html">D u o - T e x t u r e</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <section class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.html" role="button">Men</a>
          </li>

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.html" role="button">Women</a>
          </li>

          <li class="nav-item">
            <a class="nav-link custom-font-mont fs-15" href="product_listings.html" role="button">Kids</a>
          </li>

        </ul>

        <ul class="navbar-nav ml-auto">
          <a class="nav-link custom-font-mont fs-15 text-primary" href="login.html">Login</a>
          <a class="nav-link custom-font-mont fs-15 text-danger" href="sign_up.html">Sign Up</a>
        </ul>
        
      </section>
    </nav>

    <section class="col-12 p-5 row">
      <form class="mx-auto col-8 p-5 bo-rad-10" style="background-color: rgb(255, 255, 255)" action="validateAccount.jsp" method="post">
        <p class="custom-font-playfair fs-15">D u o - T e x t u r e</p>
        
        <div class="form-group">
          <label for="emailInput">Email address</label>
          <input type="email" name="emailInput" class="form-control my-input" id="emailInput" placeholder="Email" aria-describedby="emailHelp">
          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>

        <div class="form-group">
          <label for="passwordInput">Password</label>
          <input type="password" name="passwordInput" class="form-control" id="passwordInput" placeholder="Password">
        </div>

        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>