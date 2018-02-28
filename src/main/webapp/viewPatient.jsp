<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
      <a class="navbar-brand" href="doctorsDashboard">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="doctorsDashboard">Profile</a></li>
        <li><a href="/doctorsDashboard">Add Appointments</a></li>
        <li><a href="/viewAppointment">View Appointments</a></li>
        <li class="active"><a href="/viewPatient">View Patients</a></li>
        <li><a href="#">Update Appointment</a></li>
        <li><a href="#">Add Prescriptions</a></li>
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
              <form id="register-form" action="viewPatient" method="post" role="form">
                  <div class="form-group">
                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Patient id" value="" required>
                  </div>
         <!--          <div class="form-group">
                    <input type="text" name="lname" id="lname" tabindex="1" class="form-control" placeholder="Lastname" value="" required>
                  </div>
                  <div class="form-group">
                    <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" required>
                  </div> -->
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                  <%--     <% if (fstatus) { %>
                          <p class="status text-center"><%= fstatus %></p>
                        <% } %> --%>
                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Search Patient">
                      </div>
                    </div>
                  </div>
              <%--        <%if (pdetails) { %> --%>
                      <table class="table gen">
                      <thead class="thead-inverse">
                        <tr>
                          <th>Patient Id</th>
                          <th>Email</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>address</th>
                          <th>Date of Birth</th>
                          <th>gender</th>
                        </tr>
                      </thead>
                       <tbody>
                       </tbody>
                       <tr>
                 
                       </tr>
                      </table>

                        <%-- <% } %> --%>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>