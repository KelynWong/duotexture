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
        <section id="carousellIndicators" class="carousel slide col-5" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousellIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carousellIndicators" data-slide-to="1"></li>
                <li data-target="#carousellIndicators" data-slide-to="2"></li>
            </ol>
            <section class="carousel-inner">
                <section class="carousel-item active">
                <img src="./assets/images/image1.jpg" class="d-block w-100" alt="sunglasses">
                </section>
                <section class="carousel-item">
                <img src="./assets/images/image2.jpg" class="d-block w-100" alt="shoes">
                </section>
                <section class="carousel-item">
                <img src="./assets/images/image3.jpg" class="d-block w-100" alt="kid model">
                </section>
            </section>
            <a class="carousel-control-prev" href="#carousellIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousellIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </section>
        <section class="m-5 col-5">
            <p class="text-white custom-font-playfair fs-50">In<br>Duo<br>Texture,</p>
            <p class="text-white custom-font-mont">you'll get no better but the best and that's on us.</p>
            <button onclick="window.location.href='category.jsp'" class="btn btn-danger">Shop Now</button>
        </section>
      </section>
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <!--===============================================================================================-->
</body>
</html>