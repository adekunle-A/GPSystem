<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CancelBooking</title>
  <meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
</head>
<body>
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
        <li><a href="patientDashboard">Profile</a></li>
        <li><a href="bookAppointment">Book Appointment</a></li>
        <li><a href="viewAppointments">View Appointment</a></li>
        <li class="active"><a href="cancelBooking">Cancel Booking</a></li>
        <li><a href="viewPrescription">View Prescriptions</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6 text-left">
                <a href="viewPatient" class="active" id="register-form-link">Patient Details</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
              <form id="register-form" action="cancelBooking" method="post" role="form">
                  <div class="form-group">
                  <label>Booking ID:</label>
                  <input type="text" name="Booking ID: " id="bookid" tabindex="1" class="form-control" placeholder="Booking ID" value="" required>
                  </div>
                  <div class="form-group">
                  <label>Doctor ID:</label>
                  <input type="text" name="Doctor ID: " id="bookid" tabindex="1" class="form-control" placeholder="DoctorID" value="" required>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                      <label class="status text-center">hh</label>
                        <input type="submit" name="register-submit" id="register-submit" tabindex="3" class="form-control btn btn-register" value="Cancel Booking">
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div>
</body>
</html>