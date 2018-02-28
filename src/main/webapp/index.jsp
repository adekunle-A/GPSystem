<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Login</title>
  <meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style.css' />
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/">GP Booking System</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">WHO</a></li>
        <li><a href="location.jsp">WHERE</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- second Container (Grid) -->
<div class="container-fluid bg-3 text-center">    
  <div class="row">
    <div class="col-sm-4">
      <a href='adminlogin.jsp' class='hbutton'>Admin Login</a>
    </div>
    <div class="col-sm-4"> 
      <a href='doctorlogin.jsp' class='hbutton'>Doctor Login</a>
    </div>
    <div class="col-sm-4"> 
    <a href='patientlogin.jsp' class='hbutton'>Patient Login</a>
    </div>
  </div>
</div>

<!-- Footer -->
<div class="navbar navbar-inverse navbar-fixed-bottom text-center">
    <p class="text-muted">Adekunle &copy; 2017</p>
                   
</div>
</body>
</html>