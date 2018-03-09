<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Doctor Dashboard</title>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <!--    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> -->
       <link rel='stylesheet' href='stylesheets/style2.css' />
       <script type="text/javascript" src="javascripts/DocProfileInfoScripts.js"></script>

</head>
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("doctorlogin.jsp");
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
      <a class="navbar-brand" href="doctorsDashboard.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li  class="active"><a href="doctorsDashboard.jsp">Profile</a></li>
        <li><a href="addDocAvailability.jsp">Add Availability</a></li>
        <li><a href="ViewAppointment.jsp">View Appointments</a></li>
        <li><a href="checkPatientHistory.jsp">checkPatientHistory</a></li>
        <li><a href="UpdateAvailability.jsp">Update Availability</a></li>
        <li><a href="addDiagnosisInfo.jsp">Add DiagnosisInfo</a></li>
        <li><a href="diagnosePatient.jsp">Diagnose Patient</a></li>
        <li><a href="updatePrescription.jsp">update Prescription</a></li>
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
              <div class="col-xs-6">
                <a href="doctorsDasboard.jsp" class="active" id="login-form-link">Doctors Information</a>
              </div>
            </div>
            <hr>
          </div>
          <input type="hidden" name="userid " id="uid" tabindex="1" class="form-control" value="<%= session.getAttribute("username") %>" readonly>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-15">
					 <table class="table gen">
                      <thead class="thead-inverse">
                        <tr>
                          <th>DoctorID</th>
                          <th>Date Of Birth</th>
                          <th>Email</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Address</th>
                          <th>Gender</th>
                        </tr>
                      </thead>
                       <tbody class="getdocprofilerow">
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
