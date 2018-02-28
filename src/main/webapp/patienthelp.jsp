<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patient Medical History</title>
<meta name="viewport" content="content='user-scalable=no'">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel='stylesheet' href='stylesheets/style2.css' />
  <!--   <script type="text/javascript" src="javascripts/patientsendmessage.js"></script> -->
  <script type="text/javascript" src="javascripts/scripts.js"></script>
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
        <li><a href="patientDashboard.jsp">Profile</a></li>
        <li><a href="bookAppointment.jsp">Book Appointment</a></li>
        <li><a href="checkdocavailability.jsp">check docAvailability</a></li>
        <li><a href="ManageAppointment.jsp">Manage Appointment</a></li>
        <li><a href="viewPrescription.jsp">View Prescriptions</a></li>
        <li><a href="viewMedicalHistory.jsp">viewMedicalHistory</a></li>
        <li class="active"><a href="patienthelp.jsp">Help</a></li>
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
	  response.sendRedirect("patientlogin.jsp");
  }
%>
<script>
$(document).ready(function(e) {   
	var uid = $('#uid').val();
    //send the patient information to the server side when the book button is clicked and get response
     $.ajax({
        url:'PatientHelp',
        data:{username:uid},
        type:'POST',
        success:function(data){
        	var splitdata = data.split('/');
        	var name =splitdata[0];
        	var email = 	 splitdata[1];
        	$('#name').val(name);
        	$('#email').val(email);
       	 //when send message is clicked
       $("#btnContactUs").on('click',function(e){ 
        	e.preventDefault();
    		var message = $('#message').val();
        var subject = $('#subject').find(":selected").val();
	     if(!$('#message').val()){
	        	alert("Please check your entries");
	        }else{
		      	$.ajax({
			    	    url: "PatientSendMessage", 
			    	    method: "POST",
			    	    data: {username:uid,email:email,name:name,message:message,subject:subject},
			    	    success:function(data){
			    	    	location.reload(true);
					      	alert(data);
			    	    },
			            error:function(){
			              alert('error');
			         }
			   	});
	        }
        	});
        },
        error:function(){
          alert('error');
        }
     }); 
   
});
</script>
<div class="container pcontainer">
      <div class="row " style="margin-top:60px">
      <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <a href="patienthelp.jsp" class="active" id="login-form-link">Send Email </a>
              </div>
            </div>
            <hr>
          </div>
          <input type="hidden" name="userid " id="uid" tabindex="1" class="form-control" value="<%= session.getAttribute("username") %>" readonly>
          <div class="panel-body">
              <div class="col-lg-15 gen">
                <form id="patienthelp-form" action="PatientSendMessage" method="post" role="form">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Name</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" required="required" value="" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="email">
                                Email Address</label>
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email" class="form-control" id="email" placeholder="Enter email" required="required" value="" readonly/>
                             </div>
                        </div>
                        <div class="form-group">
                            <label for="subject">
                                Subject</label>
                            <select id="subject" name="subject" class="form-control" required="required">
                                <option value="service">General Customer Enquiry</option>
                                <option value="suggestions">Suggestions</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="name">
                                Message</label>
                            <textarea name="message" id="message" class="form-control" rows="9" cols="25" required="required"
                                placeholder="Message"></textarea>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
                            Send Message</button>
                    </div>
                </div>
                </form>
               </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>