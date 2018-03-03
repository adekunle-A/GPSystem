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
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
        <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
     <!--    <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
        <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
       <link rel='stylesheet' href='stylesheets/style2.css' />
       <script type="text/javascript" src="javascripts/scripts.js"></script>

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
      <a class="navbar-brand" href="addDocAvailability.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="doctorsDashboard.jsp">Profile</a></li>
        <li  class="active"><a href="addDocAvailability.jsp">Add Availability</a></li>
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
      <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6">
                <a href="doctorsDasboard.jsp" class="active" id="login-form-link">Add Appointment Availability</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form id="login-form" action="DoctorsAvailability" method="post" role="form" style="display: block;">
                  <div class="form-group">
                  <label class="rememberme">Doctor ID:</label>
                  <input type="text" name="username" id="doctorid" tabindex="1" class="form-control" placeholder="Doctor ID" value="<%= session.getAttribute("username") %>" readonly>
                  </div> 
                  <div class="form-group gen">
                  <label class="gen">Choose Date: </label>
                    <div class="input-group date form_datetime" data-date="01-06-2017T15:25:00Z" >
                    <input type="text" name="datepicker" id="datetimepicker" tabindex="2" class="form-control" placeholder="Choose Date" required>
                    <span class="input-group-addon">
                          <li  id="remov" class="glyphicon glyphicon-remove"></li>
                      </span>
                      <span class="input-group-addon">
                          <li class="glyphicon glyphicon-calendar"></li>
                      </span>
                    </div>
                  </div>
                  <div class="form-group timeslot">
                  <label class="gen">Select TimeSlot(s): </label>
                  <select multiple class="form-control" name="selectedslot">
                    <option value="9:00;9:30">9:00 AM – 9:30 AM</option>
                    <option value="9:30 ;10:00">9:30 AM – 10:00 AM</option>
                    <option value="10:00;10:30">10:00 AM – 10:30 AM</option>
                    <option value="10:30;11:00">10:30 AM – 11:00 AM</option>
                    <option value="11:00;11:30"> 11:00 AM – 11:30 AM</option>
                    <option value="11:30;12:00">11:30 Am – 12:00 PM</option>
                    <option value="12:00;12:30">12:00 PM – 12:30 PM</option>
                    <option value="12:30;13:00">12:30 PM – 1:00 PM</option>
                    <option value="13:00;13:30">1:00 PM – 1:30 PM</option>
                    <option value="14:30;14:00">1:30 PM – 2:00 PM</option>
                    <option value="14:30;15:00">2:30 PM – 3:00 PM</option>
                    <option value="15:00;15:30">3:00 PM – 3:30 PM</option>
                    <option value="15:30;16:00">3:30 PM – 4:00 PM</option>
                    <option value="17:00;17:30">4:00 PM – 4:30 PM</option>
                    <option value="17:30;18:00">4:30 PM – 5:00 PM</option>
                  </select>
                </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-2">
                       <input type="submit" class="btn btn-lg btn-success btn-block" value="Add Availability">
                  <c:when test="${messages.equals('success')}">
	 		       <p class="status text-center">${messages.success}</p>
				    </c:when>    
				    <c:otherwise>
				       <p class="status text-center">${messages.error}</p>
				        <br />
				    </c:otherwise>
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
  </body>
</html>
