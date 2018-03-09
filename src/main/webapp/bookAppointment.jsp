<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Appointment</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="content='user-scalable=no'">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
<link rel='stylesheet' href='stylesheets/style2.css' />
<script type="text/javascript" src="javascripts/scripts.js"></script>
<script type="text/javascript" src="javascripts/bookAppScripts.js"></script>
</head>
<body>

<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("patientlogin.jsp");
  }
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="patientDashboard">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="patientDashboard.jsp">Profile</a></li>
        <li class="active"><a href="bookAppointment.jsp">Book Appointment</a></li>
        <li><a href="ManageAppointments">Manage Appointment</a></li>
        <li><a href="viewPrescription">View Prescriptions</a></li>
        <li><a href="viewMedicalHistory.jsp">viewMedicalHistory</a></li>
        <li ><a href="patienthelp.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- <div class="jumbotron"> -->
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6">
                <a href="bookAppointment" class="active" id="login-form-link">New Appointment Booking</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form id="login-form" action="bookAppointment" method="post" role="form" style="display: block;">
                  <div class="form-group">
                  <label class="rememberme">Booking ID:</label>
                  <input type="text" name="BookingID" id="bookid" tabindex="1" class="form-control" placeholder="Booking ID" value="<%= session.getAttribute("username") %>" readonly>
                  </div>
               <div class="form-group gen">
                <label class="gen">Choose Date: </label>
                  <div class="input-group date form_datetime" data-date="" >
                    <input type="text" name="datepicker" id="datetimepicker" tabindex="2" class="form-control" placeholder="Appointment Date" required>
                    <span class="input-group-addon">
                          <li  id="remov" class="glyphicon glyphicon-remove"></li>
                      </span>
                      <span class="input-group-addon">
                          <li class="glyphicon glyphicon-calendar"></li>
                      </span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-5 col-sm-offset-3">
                       <input type="submit" id= checkavail class="btn btn-lg btn-success btn-block" value="CheckAvailability">
                      </div>
                    </div>
                  </div>
                </form>  
	 		      <table class="table gen" id="listable">
                      <thead class="thead-inverse">
                        <tr>
                          <th>ID</th>
                          <th>Name</th>
                          <th>Date</th>
                          <th>Start Time</th>
                          <th>End Time</th>
                          <th>Action</th>
                        </tr>
                      </thead>
                       <tbody class="bkapptable">
                       </tbody>
                       </table>
               </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
