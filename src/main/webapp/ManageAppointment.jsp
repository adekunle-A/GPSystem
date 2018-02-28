<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Appointments</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="content='user-scalable=no'">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
<link rel='stylesheet' href='stylesheets/style2.css' />
  <script type="text/javascript" src="javascripts/managePatientAppScripts.js"></script>
  <script type="text/javascript" src="javascripts/cancelappointmentscript.js"></script>
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
        <li><a href="bookAppointment.jsp">Book Appointment</a></li>
        <li class="active" id="mgapp"><a href="ManageAppointment.jsp">Manage Appointments</a></li>
        <li><a href="viewPrescription.jsp">View Prescriptions</a></li>
        <li><a href="viewMedicalHistory.jsp">viewMedicalHistory</a></li>
        <li ><a href="patienthelp.jsp">Help</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-5">
                <a href="ManageAppointments" class="active" id="login-form-link">Upcoming Appointments </a>
              </div>
            </div>
            <hr>
            <input type="hidden" name="userid " id="uid" tabindex="1" class="form-control" value="<%= session.getAttribute("username") %>" readonly>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-15">
               <table class="table gen" id="infoTable">
                      <thead class="thead-inverse">
                        <tr>
                          <th>Appointment Date</th>
                          <th>Start Time</th>
                          <th>End Time</th>
                          <th>Doctor Name</th>
                          <th>BookingID</th>
                          <th>Action</th>
                        </tr>
                      </thead>
                       <tbody class="getapp">
                       <%-- <p class="status text-center">${messages.error}</p> --%> 
                      </tbody>
                  </table>
				</div>  
               </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>