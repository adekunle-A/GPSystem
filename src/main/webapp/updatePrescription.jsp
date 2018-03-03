<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Prescription</title>
	   <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
       <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
       <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
       <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
       <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
       <script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
       <link rel='stylesheet' href='stylesheets/style2.css' />
       <script type="text/javascript" src="javascripts/scripts.js"></script>
	  <script type="text/javascript" src="javascripts/updatePrescriptionScripts.js"></script>

</head>
<body>

<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("doctorlogin.jsp");
  }
%>

<!-- <script>

$(document).ready(function() {   
    $( "#updatepresbtn" ).on( "click", function(e) {
    		e.preventDefault();
        var bookingid = $("#patientid").val();
        var docid = $("#docid").val();
        var appdate = $("#appdate").val();
        var status = $('#status').find(":selected").text();
    $.ajax({
        url:'updatePrescriptionStatus',
        data:{docid:docid,bookingid:bookingid,appdate:appdate,status:status},
        type:'POST',
        success:function(data){ 
          	 alert(data);
        	  $('.status').html(data);
        },error:function(){
          alert('error');
        }
     });
    });
});
</script> -->
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
        <li><a href="addDocAvailability.jsp">Add Availability</a></li>
        <li><a href="ViewAppointment.jsp">View Appointments</a></li>
        <li><a href="checkPatientHistory.jsp">checkPatientHistory</a></li>
        <li><a href="UpdateAvailability.jsp">Update Availability</a></li>
        <li><a href="addDiagnosisInfo.jsp">Add DiagnosisInfo</a></li>
        <li><a href="diagnosePatient.jsp">Diagnose Patient</a></li>
        <li class="active"><a href="updatePrescription.jsp">update Prescription</a></li>
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
                <a href="updatePrescription.jsp" class="active" id="register-form-link">Update Prescription Status</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body gen">
           <input type="hidden" name="docid " id="docid" tabindex="1" class="form-control"  value="<%= session.getAttribute("username") %>"required>
            <div class="row">
              <div class="col-lg-12">
              <form id="register-form" action="updatePrescriptionStatus" method="post" role="form">
                  <div class="form-group">
                  <label>Booking ID:</label>
                  <input type="text" name="patientid " id="patientid" tabindex="1" class="form-control" placeholder="Booking ID" value="" required>
                  </div>
                  <div class="form-group">
                  <label>Date:</label>
                  <input type="date" name="appdate" id="appdate" tabindex="1" class="form-control" placeholder="Appointment Date" value="" required>
                  </div>
                  <div class="form-group ">
	                  <label class="rememberme">Status:</label>
	                  <select class="form-control" name="status" id="status">
	                        <option value="issued"> Issued </option>
	                        <option value="Pending"> Pending </option>
	                  </select>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                      <p class="status text-center"></p>
                        <input type="submit" name="updatepresbtn" id="updatepresbtn" tabindex="3" class="form-control btn btn-register" value="Update Status">
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
