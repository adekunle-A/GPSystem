<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patient Dashboard</title>
<meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
  <script type="text/javascript" src="javascripts/patientInfoScript.js"></script>
</head>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="patientDashboard.jsp">GP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active" id="getprofile"><a href="patientDashboard.jsp">Profile</a></li>
        <li><a href="bookAppointment.jsp">Book Appointment</a></li>
        <li><a href="ManageAppointment.jsp">Manage Appointment</a></li>
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
<body>
<% 
  if(session.getAttribute("username") == null){
	  response.sendRedirect("adminlogin.jsp");
  }
%>
<!-- <script>
$(document).ready(function(e) {   
/**when patient click to view profile**/
/*  $("#getprofile").on('click',function(e){ 
	e.preventDefault(); */
	var uid = $('#uid').val();
    //send the patient information to the server side when the book button is clicked and get response
     $.ajax({
        url:'patientDashboard',
        data:{username:uid},
        type:'POST',
        success:function(data){
        	 $('.getprofilerow').html(data);
        },
        error:function(){
          alert('error');
        }
     }); 
   //}); 
});
</script> -->
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-5">
                <a href="bookAppointment" class="active" id="login-form-link">Patient's Information </a>
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
                          <th>PatientID</th>
                          <th>Date Of Birth</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>Address</th>
                          <th>Gender</th>
                        </tr>
                      </thead>
                       <tbody class="getprofilerow">
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